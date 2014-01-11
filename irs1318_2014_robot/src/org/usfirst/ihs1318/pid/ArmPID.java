package org.usfirst.ihs1318.pid;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ArmSetPointsRef;
import org.usfirst.ihs1318.shared.constants.ConnectionRef;
import org.usfirst.ihs1318.shared.constants.EncoderRef;
import org.usfirst.ihs1318.shared.data.ArmData;
import org.usfirst.ihs1318.shared.data.ArmEncoderValues;
import org.usfirst.ihs1318.shared.data.LimitSwitchValue;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANJaguar.ControlMode;
import edu.wpi.first.wpilibj.CANJaguar.PositionReference;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

//Checked for NPE. Uses lazy getters.
public class ArmPID {
	private PIDGain gains;
	private ArmData desiredArmData;
	private ArmEncoderValues currentEncoderValues;
	
	private double motorSpeed = 0;
	private CANJaguar armMotor;
	
	int debugCounter = 0 ;
	
	/**
	 * Calculates the arm speeds according to the PID method.
	 * 
	 * Reads KinematicData's desired values.
	 * Reads ReferenceData's arm encoder values.
	 * 
	 * This method does NOT actually send the values to the motors.
	 */
	public void calculateArmSpeed() {
		setPIDGains();
		readSharedData();

		synchronized (ReferenceData.getInstance()) {
			double joystick = ReferenceData.getInstance().getArmInput().getDesiredJy();
			
			if(joystick != 0){
			// desiredSetSpeed + Kd(OmegaMax * desiredSetSpeed- encoder rate)
				motorSpeed = joystick;
			}
			else if(getDesiredArmData().getArmDistance() != ArmSetPointsRef.ARM_MOTOR_NO_SET_POINT) {
				double setRevs = getDesiredArmData().getArmDistance();
				motorSpeed = clamp(getGains().getKp() * 
						(setRevs - getCurrentEncoderValues().getEncoderValue()), -1.0, 1.0);
			}
			else { // fix for stationary motor voltage.
				motorSpeed = 0;
			}

		}
	}
	
	
	/**
	 * 
	 * @param clampValue The value to be clamped.
	 * @param minValue The minimum value that the clamp should allow.
	 * @param maxValue The maximum value that the clamp should allow.
	 * @return clampedValue A value between the min and max values.
	 */
	
	private double clamp(double clampValue, double minValue, double maxValue) {
		if(clampValue < minValue) {
			clampValue = minValue;
		}
		if(clampValue > maxValue){
			clampValue = maxValue;
		}
		return clampValue;
	}

//	/**
//	 * Converts the desired arm height into a number of ticks. Note that it is an actual number, not a rate.
//	 * @return The number of ticks required to reach a set height.
//	 */
//	private double convertHeightToTicks() {
//		return getDesiredArmData().getArmDistance() * REVOLUTIONS_PER_DISTANCE * TICKS_PER_REVOLUTION;
//	}

	/**
	 * Runs the arm motor at the current calculated speed.
	 * The MotorSpeed is inverted due to the design: up is negative, down is positive.
	 */
	public void sendSpeed() {
		motorSpeed *= -1; //<--inverted because we had to mirror the design. a negative speed now goes up.	
		
		//Set the actual arm motor to run.
		try {
			getArmMotor().setX(motorSpeed);
		} catch (CANTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//debug
		if(debugCounter % 10 == 0)  {
			//add print statement here
			debugCounter=0;
		}
		debugCounter++;
	}
	
	/**
	 * @return armMotor The Jaguar instance that runs the arm motor.
	 */
	
	private CANJaguar getArmMotor() {
		if(armMotor == null) {
			try {
				armMotor = new CANJaguar(ConnectionRef.ARM_MOTOR_CAN, ControlMode.kPercentVbus);
			} catch (CANTimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return armMotor;
	}
	
	
	/**
	 * Copies the ArmData instance from KinematicData to the local desiredArmData object.
	 * Copies the ArmEncoderValues instance from ReferenceData to the local currentEncoderValues object.
	 */
	private void readSharedData() {
		synchronized (KinematicData.getInstance()) {
			KinematicData.getInstance().getArmData().copyValuesTo(getDesiredArmData());
		}	
		synchronized (ReferenceData.getInstance()) {
			ReferenceData.getInstance().getArmEncoderValues().copyValues(getCurrentEncoderValues());
		}
	}
	
	/**
	 * Sets PID gains.
	 */
	private void setPIDGains() {
		double kp = .1;
		double kd = 0.0005;
		double ki = 0.0;
		getGains().setKp(kp);
		getGains().setKd(kd);
		getGains().setKi(ki);
	};

	public void readEncoder() {
		double currentRevs = 0;
		try {
			currentRevs = getArmMotor().getPosition();
		} catch (CANTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getCurrentEncoderValues().setAll(currentRevs, 0);
		
		synchronized(ReferenceData.getInstance()){
			getCurrentEncoderValues().copyValues(ReferenceData.getInstance().getArmEncoderValues());
		}
		
	}
	/**
	 * 
	 * @return currentEncoderValues The most recent local copy of the ArmEncoderValues instance 
	 * from ReferenceData
	 */
	public ArmEncoderValues getCurrentEncoderValues() {
		if(currentEncoderValues == null) {
			currentEncoderValues = new ArmEncoderValues();
		}
		return currentEncoderValues;
	}
	/**
	 * 
	 * @param armEncoder The armEncoder you wish to copy.
	 */

	public void setArmEncoder(ArmEncoderValues armEncoder) {
		this.currentEncoderValues = armEncoder;
	}
	
	/**
	 * 
	 * @return gains The current PID gains. These most likely won't be changing.
	 */

	private PIDGain getGains() {
		if(gains == null){
			gains = new PIDGain();
		}
		return gains;
	}
	
	/**
	 * 
	 * @param gains The object whose reference you would like to copy locally.
	 */
	
	public void setGains(PIDGain gains) {
		this.gains = gains;
	}

	/**
	 * 
	 * @return desiredArmData The most recent local copy of the ArmData values from the KinematicData
	 */
	public ArmData getDesiredArmData() {
		if(desiredArmData == null) {
			desiredArmData = new ArmData();
		}
		return desiredArmData;
	}

	/**
	 * 
	 * @param desiredArmData The object whose reference you would like to copy locally.
	 */
	public void setDesiredArmData(ArmData desiredArmData) {
		this.desiredArmData = desiredArmData;
	}

	/**
	 * 
	 * @return motorSpeed The most current calculated motorSpeed. This will be what is sent to the arm motor.
	 */
	public double getMotorSpeed() {
		return motorSpeed;
	}
	
	/**
	 * 
	 * @param motorSpeed The value to which you would like to set the motorSpeed.
	 */
	public void setMotorSpeed(double motorSpeed){
		this.motorSpeed = motorSpeed;
	}

	public void init() {
		try {
			armMotor = new CANJaguar(ConnectionRef.ARM_MOTOR_CAN);
		} catch (CANTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			armMotor.enableControl();
			armMotor.configEncoderCodesPerRev(90);//360 ticks
			armMotor.setPositionReference(PositionReference.kQuadEncoder);// no regular encoder choice
			
			System.out.println("init");
		} catch (CANTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
}
