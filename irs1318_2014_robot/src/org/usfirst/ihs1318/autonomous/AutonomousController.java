package org.usfirst.ihs1318.autonomous;

import java.util.TimerTask;

import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.TimerUtil;
import org.usfirst.ihs1318.shared.constants.ButtonRef;
import org.usfirst.ihs1318.shared.data.InputVelocity;
import org.usfirst.ihs1318.shared.data.LineSensorInput;
import org.usfirst.ihs1318.shared.data.Orientation;
import org.usfirst.ihs1318.shared.data.WheelMotorEncoderRates;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

//Checked for NPEs. Uses lazy getters.
public class AutonomousController implements Runnable {
	public static final int LINE_STRAIGHT = 1;
	public static final int LINE_CENTER_LEFT = 2;
	public static final int LINE_CENTER_RIGHT = 3;
	public static final int LINE_FAR_RIGHT = LINE_STRAIGHT;

	public static final int PEG_SIDE_LOW = ButtonRef.HEIGHT1;
	public static final int PEG_CENTER_LOW = ButtonRef.HEIGHT2;
	public static final int PEG_SIDE_MID = ButtonRef.HEIGHT3;
	public static final int PEG_CENTER_MID = ButtonRef.HEIGHT4;
	public static final int PEG_SIDE_HIGH = 5900;
	public static final int PEG_CENTER_HIGH = 6000;
	public static final int LINE_DEFAULT = LINE_STRAIGHT;
	
	private static final double INCHES_TO_MOVE = 12;
	private static final double INCHES_FORWARD = 3;

	private LineSensorInput sensors;
	private WheelMotorEncoderRates wheelEncoderValues;
	private Orientation angle;

	private StraightLineTracker straightTracker;
	private ForkedLineTracker forkedTracker;
	private WheelDistancePID distanceCalculator;

	private ArmDistancePID armController;
	private AutonomousArmController wristController;
	
	private int desiredPeg = 0;
	private int desiredLine = 0;
	private boolean setClaw = true;
	private boolean enabled;
	private boolean lineTrackerFinished = false;
	private boolean autonomousFinished = false;
	private int setTicks;

	private RobotProcess robotProcess;
	
	public void init() {
		robotProcess = new RobotProcess();
		
		straightTracker = new StraightLineTracker();
		getStraightTracker().init();
		forkedTracker = new ForkedLineTracker();
		getForkedTracker().init();


		distanceCalculator = new WheelDistancePID();
		getDistanceCalculator().init();
		armController = new ArmDistancePID();
		getArmController().init();

		wristController = new AutonomousArmController();
		wristController.init();
		
		wheelEncoderValues = new WheelMotorEncoderRates();
		sensors = new LineSensorInput();
		angle = new Orientation();

	}

	/**
	 * Initializes the AutonomousController for Junit Testing purposes.
	 * 
	 * If run without this, the code will try to read a timer from the CRIO and
	 * throw an exception if the CRIO is not connected.
	 * 
	 */
	public void initTestMode() {
		straightTracker = new StraightLineTracker();

		forkedTracker = new ForkedLineTracker();

		wheelEncoderValues = new WheelMotorEncoderRates();

		sensors = new LineSensorInput();
		angle = new Orientation();
	}

	/**
	 * Lines up the arm to put the tube on the desired peg
	 * 
	 * @param desiredPeg
	 *            Use AutonomousController constants or you will get an error
	 */
	public void alignTube(int desiredPeg) {
		
	}

	/**
	 * Sets the peg that the robot will place its tube on. The pegs are labeled from the DRIVER'S
	 * perspective.
	 * 
	 * @param desiredPeg
	 *            Use the "Peg" constants from the AutonomousController class.
	 * @throws Exception
	 *             If the desired peg does not match one of the six pegs
	 */
	public void setDesiredPeg(int desiredPeg) {
		switch (desiredPeg) {
		case PEG_SIDE_LOW:
			this.desiredPeg = desiredPeg;
			break;
		case PEG_CENTER_LOW:
			this.desiredPeg = desiredPeg;
			break;
		case PEG_SIDE_MID:
			this.desiredPeg = desiredPeg;
			break;
		case PEG_CENTER_MID:
			this.desiredPeg = desiredPeg;
			break;
		case PEG_SIDE_HIGH:
			this.desiredPeg = desiredPeg;
			break;
		case PEG_CENTER_HIGH:
			this.desiredPeg = desiredPeg;
			break;

			
		default:
			throw new RuntimeException(desiredPeg
					+ " is not a valid peg. Use values 1-3.");
		}
	}


	private long startTime = 0;
	boolean startTimeSet = false;
	/**
	 * Tracks a line (that you set before), places a tube on a peg 
	 * (which you set before), and then backs up a little.
	 * 
	 * Call in a loop. This works in the order specified above.
	 * 
	 * When this is finished with its tasks, it will set a flag that says it
	 * is finished. Get that with the isAutonomousFinished() method.
	 * 
	 * This is NOT the only thing that runs in autonomous-----------
	 * 
	 * It also needs:
	 * Wheel encoders, Wheel kinematics, wheel PID; 
	 * Arm Calculator, Arm PID, 
	 * Discrete claw/wrist controllers and calculators 
	 * 
	 * 
	 */
	public void run() {
		Watchdog.getInstance().feed();
		long now = 0;
		long delayTime = 0;
		double ARM_DIRECTION = -1.0;
		
		switch (robotProcess.getState()) {
		case RobotProcess.RAISE_ARM:
			Watchdog.getInstance().feed();
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 4000;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					ReferenceData.getInstance().getArmInput().setDesiredJy(0.98*ARM_DIRECTION);
				} else {
					ReferenceData.getInstance().getArmInput().setDesiredJy(0.0);
					startTimeSet = false;
					robotProcess.nextState();
				}
			}
			break;
		case RobotProcess.EXTEND_WRIST:
			Watchdog.getInstance().feed();
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 100;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					// push wrist button
					ReferenceData.getInstance().getButtonValues()
					   .getButtons()[ButtonRef.JOYSTICK_ARM][2] = true;
				} else {
					startTimeSet = false;
					robotProcess.nextState();
				}
			}
			break;
		case RobotProcess.FOLLOW_LINE:
			Watchdog.getInstance().feed();
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 25000;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					Timer.delay(0.01);
				} else {
					startTimeSet = false;
					robotProcess.nextState();
				}
			}
/*
			if(!straightTracker.isFinished())
				straightTracker.trackLine(15 * 12);
			else {
				straightTracker.setFinished(false);
				robotProcess.nextState();
			}
			*/
			break;
		case RobotProcess.STABILIZE_PAUSE:
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 500;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					Timer.delay(0.01);
				} else {
					startTimeSet = false;
					robotProcess.nextState();
				}
			}
			break;
		case RobotProcess.FINAL_PLACEMENT:
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 100;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					Timer.delay(0.01);
				} else {
					startTimeSet = false;
					robotProcess.nextState();
				}
			}
/*
 			if(!straightTracker.isFinished())
 
				straightTracker.trackLine(3);
			else{
				straightTracker.setFinished(false);
				robotProcess.nextState();
			}
			*/
			break;
		case RobotProcess.RELEASE_FIRST_LOWER_ARM:
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 100;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					ReferenceData.getInstance().getArmInput().setDesiredJy(-0.1*ARM_DIRECTION);
				} else {
					ReferenceData.getInstance().getArmInput().setDesiredJy(0.0);
					startTimeSet = false;
					robotProcess.nextState();
				}
			}
			break;
		case RobotProcess.RELEASE_OPEN_CLAW:
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 250;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					Timer.delay(0.01);
				} else {
					wristController.setClawOpen(true);
					startTimeSet = false;
					robotProcess.nextState();
				}
			}
			break;
		case RobotProcess.RELEASE_LOWER_ARM:
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 100;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					ReferenceData.getInstance().getArmInput().setDesiredJy(-0.25*ARM_DIRECTION);
				} else {
					ReferenceData.getInstance().getArmInput().setDesiredJy(0.0);
					startTimeSet = false;
					robotProcess.nextState();
				}
			}
			break;
		case RobotProcess.RELEASE_BACK_UP:
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 500;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					ReferenceData.getInstance().getInputVelocity().setDesiredJy(0.25); // check reversal sign
				} else {
					ReferenceData.getInstance().getInputVelocity().setDesiredJy(0.0);
					startTimeSet = false;
					robotProcess.nextState();
				}
			}
			break;
		case RobotProcess.FINISHED:
			Timer.delay(0.01);
			break;
		}
	}

	private void raiseArm() {
		if(desiredLine == LINE_CENTER_RIGHT || desiredLine ==  LINE_CENTER_LEFT)
			setTicks = PEG_SIDE_HIGH;
		else
			setTicks = PEG_CENTER_HIGH;
		armController.goToSetTicks(setTicks);
		
	}

	public void setAutonomousFinished(boolean b) {
		autonomousFinished = b;
	}
	
	public boolean isAutonomousFinished() {
		return robotProcess.isFinished();
	}

	/**
	 * Positions the robot so that it can place a tube on the peg once it has
	 * reached the end of the line it has been tracking.
	 */
	public void backUp() {
		if(!distanceCalculator.isSetPointReached()) {
			distanceCalculator.calculateYDistance(-INCHES_TO_MOVE);
		}
	}

	/**
	 * Copies the LineSensorInput data from ReferenceData. Copies the
	 * Orientation data from ReferenceData. Copies the LineSensorHistory data
	 * from ReferenceData if need be.
	 * 
	 * Does some true/false evaluating in trackLine to determine joy stick
	 * values that allow the robot to track the desired line
	 * 
	 * Writes the determined values to
	 * ReferenceData.getInstance().getInputVelocity().
	 * 
	 * //TODO Verify mode with Joystick. If the user needs to override line
	 * tracking, he should be able to do so.
	 * 
	 * @param desiredLine
	 *            Use the AutonomousController's "LINE_--" constants. The
	 *            directions of the lines are from the driver's point of view.
	 */
	private boolean setLine = true;
	public void trackLine() {
		if(setLine) {
			setLine();
			forkedTracker.setDesiredLine(desiredLine);
			setLine = false;
		}
		readReferenceData();
		setWrappedClassValues();
		if (lineHasFork()) {
			writeToReferenceData(getForkedTracker().trackLine(this.desiredLine));
			lineTrackerFinished = getForkedTracker().isFinished();
		} else {
//			writeToReferenceData(getStraightTracker().trackLine());
			lineTrackerFinished = getStraightTracker().isFinished();
		}

	}

	private void setLine() {
		switch(sensors.getLineSensorState()) {
		case LineSensorInput.CENTER_ONLY_ON:
			setDesiredLine(LINE_FAR_RIGHT);
			break;
		case LineSensorInput.RIGHT_AND_CENTER_ON:
		case LineSensorInput.RIGHT_ONLY_ON:
			setDesiredLine(LINE_CENTER_RIGHT);
			break;
		case LineSensorInput.LEFT_AND_CENTER_ON:
		case LineSensorInput.LEFT_ONLY_ON:
			setDesiredLine(LINE_CENTER_LEFT);
			break;
			default:
				setDesiredLine(LINE_DEFAULT);
		}
	}

	/**
	 * Releases the claw to drop the tube. Moves the arm out of the way so that
	 * the tube can fall onto the peg. Moves the robot backward, and autonomous
	 * controller is finished.
	 * 
	 */
	public void placeTube() {
		double clawOpenDuration = 1;// seconds
		if (setClaw) {
			getWristController().setClawOpen(true, clawOpenDuration);
			setClaw = false;
		}
		if (getWristController().clawIsOpen()) {
			// This should run for amountOfTimeClawIsOpen
			distanceCalculator.calculateYDistance(-INCHES_TO_MOVE);
		}

	}

	public AutonomousArmController getWristController() {
		if(wristController == null)
			wristController = new AutonomousArmController();
		return wristController;
	}

	private void setWrappedClassValues() {
		getStraightTracker().setAngle(getAngle());
		getForkedTracker().setAngle(getAngle());

		getStraightTracker().setCurrentSensors(getSensors());
		getForkedTracker().setCurrentSensors(getSensors());
	}

	private Orientation getAngle() {
		if (angle == null) {
			angle = new Orientation();
		}
		return angle;
	}

	private ArmDistancePID getArmController() {
		if (armController == null) {
			armController = new ArmDistancePID();
			armController.init();
		}
		return armController;
	}

	private LineSensorInput getSensors() {
		if (sensors == null) {
			sensors = new LineSensorInput();
		}
		return sensors;
	}

	/**
	 * Writes "joy stick" values to the ReferenceData that make the robot follow
	 * the line.
	 * 
	 * @param trackLine
	 *            The "joy stick" values
	 */
	private void writeToReferenceData(InputVelocity trackLine) {
		synchronized (ReferenceData.getInstance()) {
			trackLine
					.copyValues(ReferenceData.getInstance().getInputVelocity());
		}
	}

	/**
	 * Gets the current LineSensorInput information from reference.
	 *
	 */

	private void readReferenceData() {
		synchronized (ReferenceData.getInstance()) {
			ReferenceData.getInstance().getOrientation().copyValues(getAngle());
			ReferenceData.getInstance().getLineSensorValues()
					.copyValuesTo(getSensors());
			ReferenceData.getInstance().getWheelEncoderRates()
					.copyValues(getWheelEncoderValues());
		}

	}

	/**
	 * 
	 * @return True if the desired line begins at the center, false otherwise.
	 */

	private boolean lineHasFork() {
		if (desiredLine == LINE_CENTER_LEFT || desiredLine == LINE_CENTER_RIGHT)
			return true;
		return false;
	}

	/**
	 * 
	 * @return The time that the LineTracker has been running (in seconds)
	 */

	// public double getTime() {
	// return timer.getTime();
	// }
	//
	/**
	 * 
	 * @param desiredLine
	 *            The line value to which you would like to set the local
	 *            desiredLine.
	 * @throws Exception
	 *             if the line value passed is not a valid line.
	 */

	public void setDesiredLine(int desiredLine) {
		if (desiredLine == LINE_STRAIGHT) {
			this.desiredLine = LINE_STRAIGHT;
		} else if (desiredLine == LINE_CENTER_LEFT) {
			this.desiredLine = LINE_CENTER_LEFT;
		} else if (desiredLine == LINE_CENTER_RIGHT) {
			this.desiredLine = LINE_CENTER_RIGHT;
		} else if (desiredLine == LINE_FAR_RIGHT) {
			this.desiredLine = LINE_FAR_RIGHT;
		} else {
			throw new RuntimeException(desiredLine + " is not a line value.");
		}
	}

	public StraightLineTracker getStraightTracker() {
		if (straightTracker == null) {
			straightTracker = new StraightLineTracker();
			straightTracker.init();
		}
		return straightTracker;
	}

	public void setStraightTracker(StraightLineTracker straightTracker) {
		this.straightTracker = straightTracker;
	}

	public ForkedLineTracker getForkedTracker() {
		if (forkedTracker == null) {
			forkedTracker = new ForkedLineTracker();
			forkedTracker.init();
		}
		return forkedTracker;
	}

	public void setForkedTracker(ForkedLineTracker forkedTracker) {
		this.forkedTracker = forkedTracker;
	}

	public void setEnabled(boolean b) {
		this.enabled = b;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isFinished() {
		return lineTrackerFinished;
	}

	public WheelMotorEncoderRates getWheelEncoderValues() {
		if (wheelEncoderValues == null) {
			wheelEncoderValues = new WheelMotorEncoderRates();
		}
		return wheelEncoderValues;
	}

	public void setWheelEncoderValues(WheelMotorEncoderRates wheelEncoderValues) {
		this.wheelEncoderValues = wheelEncoderValues;
	}

	public WheelDistancePID getDistanceCalculator() {
		if (distanceCalculator == null) {
			distanceCalculator = new WheelDistancePID();
			distanceCalculator.init();
		}
		return distanceCalculator;
	}

	public void setDistanceCalculator(WheelDistancePID distanceCalculator) {
		this.distanceCalculator = distanceCalculator;
	}

	public boolean isLineTrackerFinished() {
		return lineTrackerFinished;
	}

	public void setLineTrackerFinished(boolean lineTrackerFinished) {
		this.lineTrackerFinished = lineTrackerFinished;
	}

	public int getDesiredPeg() {
		return desiredPeg;
	}

	public int getDesiredLine() {
		return desiredLine;
	}

	// public TimerIHS getTimer() {
	// return timer;
	// }
	//
	// public void setTimer(TimerIHS timer) {
	// this.timer = timer;
	// }

}
