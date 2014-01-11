package org.usfirst.ihs1318.reference;

import org.usfirst.ihs1318.kinematics.ManualGyroKinematics;
import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.TimerUtil;
import org.usfirst.ihs1318.shared.constants.ButtonRef;
import org.usfirst.ihs1318.shared.constants.ConnectionRef;
import org.usfirst.ihs1318.shared.data.ManualGyroValue;

import edu.wpi.first.wpilibj.*;

public class GyroReader {

	AnalogSensorGyro gyro;
	ManualGyroKinematics manualGyro;
	ManualGyroValue manualGyroValue;
	
	public static final double GYRO_SENSITIVITY = 0.0125;
	// value in degrees per second of drift elimination
	// TODO: parameterize this
	private double elimDrift;

	
	private boolean prevGyroToggle;
	private boolean changeGyroToggle = false;
	
	private boolean resetPressed;
	private boolean gyroEnabled = true;
	private double theta;

	double[][] storedTheta = new double[2][5000];
	static int counter = 0;
	double startTime;
	
	
	public void run() {
		while (true) {
			if (gyroEnabled) {
				setTheta();
				readAngle();
			}
			try {
				// TODO: fix warning
				Thread.currentThread().sleep(5); // fast loop
			} catch (InterruptedException e) {
				// notify will wake up early.
			}

		}
	}

	public void readAngle() {
		// read gyro commands
		synchronized (ReferenceData.getInstance()) {
			resetPressed = ReferenceData
					.getInstance()
					.getButtonValues()
					.getButtonValue(ButtonRef.GYRO_RESET_JS,
							ButtonRef.GYRO_RESET_BUTTON);
			changeGyroToggle = ReferenceData
					.getInstance()
					.getButtonValues()
					.getButtonValue(ButtonRef.GYRO_TOGGLE_JS,
							ButtonRef.GYRO_TOGGLE_BUTTON);
		}
		checkReset();
		
		//make sure that the state only gets toggled once per button press.
		// 1) changGyroToggle--if the button isn't pressed at all, do nothing.
		if (changeGyroToggle) {
			//2) if there wasn't a previous toggle, then don't do anything.
			if (!prevGyroToggle) {
				// 3) flip state, and then make prevGyroToggle true.
				if(gyroEnabled)
					gyroEnabled = false;				
				else
					gyroEnabled = true;
				
				prevGyroToggle = true;
			}
		} else {
			prevGyroToggle = false;
		}
		
		if (gyroEnabled) {
			setTheta();
		} else {
			manualGyro.calculateManualGyroKinematics(); 
			synchronized (KinematicData.getInstance()) {
				KinematicData.getInstance().getManualGyroValue().copyValue(manualGyroValue);
			}
			theta = manualGyroValue.getManualGyroAngle() * Math.PI / 180;
		}
		
		
		// write out answer.
		synchronized (ReferenceData.getInstance()) {
			ReferenceData.getInstance().getOrientation().setTheta(getTheta());
			ReferenceData.getInstance().getOrientation()
					.setGyroActive(isGyroEnabled());
		}
		
	}

	public AnalogSensorGyro getGyro() {
		return gyro;
	}

	public double getTheta() {
		return theta;
	}

	public void setTheta() {
		this.theta = getAngle();
	}

	/**
	 * resets gyro if button 3 is pressed
	 */
	private void checkReset() {
		if (resetPressed) {
			resetGyro();
		}
	}

	public void init() {
		gyro = new AnalogSensorGyro(ConnectionRef.GYRO_SLOT, ConnectionRef.GYRO_CHANNEL);
		gyro.setSensitivity(GYRO_SENSITIVITY);
		gyro.reset();
		startTime = TimerUtil.getSeconds();
		gyroEnabled = true;
		manualGyroValue = new ManualGyroValue();
		manualGyro = new ManualGyroKinematics();
	}

	public void resetGyro() {
		gyro.reset();
		startTime = TimerUtil.getSeconds();
	}

	/**
	 * 
	 * @return angle in radians
	 */
	public double getAngle() {
		if(gyroEnabled)
			return (gyro.getAngle() % 360) * Math.PI / 180.0;
		return 2 * Math.PI;	
	}
	

	public void setElimDrift(double elim) {
		elimDrift = elim;
	}

	public double getElimDrift() {
		return elimDrift;
	}

	public double getAccumulatorOffset() {
		return elimDrift * getGyroTime();
	}

	public double getGyroTime() {
		return TimerUtil.getSeconds() - startTime;
	}

	public void setAccumulatorDeadband(int deadband) {
		gyro.m_analog.setAccumulatorDeadband(deadband);
	}

	// All methods below this are test/debugging methods for the gyro

	/**
	 * this method returns the drift in degrees per second the return value
	 * should be input to the elimDrift variable
	 */
	public double getDriftPerSecond() {
		return gyro.getAngle() / getGyroTime();
	}

	public static boolean testDeadband = true;

	// tests a variety of deadbands on the default sensitivity
	public void testDeadband() {
		int deadband = 1;
		double drift = 0, bestDrift = 100;
		while (testDeadband) {
			setAccumulatorDeadband(deadband);
			drift = getDrift();
			System.out.println("Deadband: " + deadband + "\tDrift: " + drift);
			if (drift < bestDrift) {
				bestDrift = drift;
			}
			deadband++;
		}
	}

	/**
	 * records theta values over time to calculate drift
	 * 
	 * @param theta
	 *            current value from the gyro
	 */
	public void recordTheta(double theta) {
		if (counter < 5000) {
			storedTheta[0][counter] = getGyroTime();
			storedTheta[1][counter] = theta;
			counter++;
		}
	}

	public void printStoredTheta() {
		for (int i = 0; i < counter; i++) {
			if (i % 25 == 0)
				System.out.println(storedTheta[0][i] + "," + storedTheta[1][i]);
		}
	}

	/**
	 * Calculates average drift over the delay period using storedTheta array
	 * 
	 * @return drift Gyro drift in degrees
	 */
	public double getDrift() {
		double start = gyro.getAngle(), DELAY = 8.0;
		Timer.delay(DELAY);
		double end = gyro.getAngle();
		return end - start;
	}

	public synchronized void calibration() {
		// min (negative drift)
		// max (positive drift)

		double minSensitivity = 0.01;
		double maxSensitivity = 1.1;
		double minDrift, maxDrift;
		int counter = 0;
		while (true) {
			System.out.println("calibration band " + counter);
			double avgMaxDrift = 0, avgMinDrift = 0;
			for (int i = 0; i < 3; i++) {
				// min sensitivity

				gyro.setSensitivity(minSensitivity);
				System.out.println("calc min " + i);
				minDrift = getDrift();
				avgMinDrift += minDrift;

				// max sensitivity
				gyro.setSensitivity(maxSensitivity);
				System.out.println("calc max " + i);
				maxDrift = getDrift();
				avgMaxDrift += maxDrift;

				System.out.println("" + i + "min(s,d):" + minSensitivity + ", "
						+ minDrift);
				System.out.println("" + i + "max(s,d):" + maxSensitivity + ", "
						+ maxDrift);
			}
			minDrift = avgMinDrift / 3.0;
			maxDrift = avgMaxDrift / 3.0;

			// to find zero
			double diffDrift = (minDrift + maxDrift) / 2.0;
			double diffSensitivity = (minSensitivity + maxSensitivity) / 2;
			if (diffDrift <= 0) {
				minSensitivity = diffSensitivity;
			} else {
				maxSensitivity = diffSensitivity;
			}
		}
	}

	public double linearSearch() {
		double sensitivity = .01, SENSITIVITY_MAX = .014, INCREMENT = .0001;
		double bestDrift = 10, bestSensitivity = 10, drift;
		System.out.println("got called");
		while (sensitivity < SENSITIVITY_MAX) {
			gyro.setSensitivity(sensitivity);
			System.out.println("entered sensitivity");
			drift = getDrift();
			if (drift < bestDrift) {
				bestDrift = drift;
				bestSensitivity = sensitivity;
				System.out.println(bestDrift + ", " + bestSensitivity);
			}
			sensitivity += INCREMENT;
		}
		return bestSensitivity;
	}

	public double binary() {
		double maxSensitivity = .02, minSensitivity = .005, midSensitivity, perfectV = 0, perfectD, maxVDrift, minVDrift, midVDrift;
		boolean finished = false;
		while (finished == false) {
			gyro.setSensitivity(minSensitivity);
			minVDrift = getDrift();
			gyro.setSensitivity(maxSensitivity);
			maxVDrift = getDrift();
			midSensitivity = (maxSensitivity - minSensitivity) / 2
					+ minSensitivity;
			gyro.setSensitivity(midSensitivity);
			midVDrift = getDrift();
			if (minVDrift < midVDrift) {
				maxSensitivity = midSensitivity;
			} else {
				minSensitivity = midSensitivity;
			}
			if (minVDrift < maxVDrift) {
				perfectV = minSensitivity;
				perfectD = minVDrift;
			} else {
				perfectV = maxSensitivity;
				perfectD = maxVDrift;
			}
			if (maxSensitivity - maxSensitivity < .0001) {
				finished = true;
			}
		}
		return perfectV;
	}

	public boolean isGyroActive() {
		return gyroEnabled;
	}

	public void setGyroActive(boolean gyroActive) {
		this.gyroEnabled = gyroActive;
	}

	public boolean isGyroEnabled() {
		return gyroEnabled;
	}

	public void setGyroEnabled(boolean gyroEnabled) {
		this.gyroEnabled = gyroEnabled;
	}
	
}

