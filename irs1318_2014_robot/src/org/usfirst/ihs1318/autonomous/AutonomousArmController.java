package org.usfirst.ihs1318.autonomous;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.usfirst.ihs1318.kinematics.ArmCalculator;
import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ArmSetPointsRef;
import org.usfirst.ihs1318.shared.constants.ButtonRef;
import org.usfirst.ihs1318.shared.data.ArmInput;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

public class AutonomousArmController{
	//TODO Refactor so that this uses TimerUtil
	private Timer timer;
	private Date currentTime;
	private Date setClawFalseTime;
	private TimerTask setClawFalse;
	
	private boolean setArmHeightReached;
	
	
	public void init() {
		setWristPosition(1);
		setClawOpen(false);
		timer = new Timer();	
		initTimerTask();
	}
	
	private void initTimerTask() {
		setClawFalse = new TimerTask(){
			public void run() {
				synchronized(ReferenceData.getInstance()){
					ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.CLAW_JS][ButtonRef.CLAW_BUTTON] = false;
				}
			}
		};
	}
	
	
	/**
	 * Pass in values to set the arm motor speed (it'll set the motor, not the wrists).
	 * 
	 * @param joystickValue The desired velocity between +/- 1
	 * @param setPosition An int between 1-6, will set the arm height by "pressing" a button
	 */
	public void setArmMotor(double joystickValue, int setPosition) {
		ArmInput desiredValues = new ArmInput();
		desiredValues.setAll(joystickValue, setPosition);
		
		//Write to ReferenceData
		synchronized(ReferenceData.getInstance()){
			desiredValues.copyValuesTo(ReferenceData.getInstance().getArmInput());
		}
	}
	
	/**
	 * Sets the wrist to one of the four positions by "pressing" a button.
	 * 
	 * @param position 4 = all exended, 1 = all retracted, 2 and 3 are in between
	 */
	public void setWristPosition(int position){
		int cleanedPosition;
		switch(position) {
		case 1:
			cleanedPosition = ButtonRef.WRIST_BUTTON_CLOSED;
			break;
		case 2:
			cleanedPosition = ButtonRef.WRIST_BUTTON_LOW;
			break;
		case 3:
			cleanedPosition = ButtonRef.WRIST_BUTTON_MED;
			break;
		case 4:
			cleanedPosition = ButtonRef.WRIST_BUTTON_OPEN;
			break;
			default:
				return;				
		}
		
		//Write values to ReferenceData
		synchronized(ReferenceData.getInstance()){
			ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][cleanedPosition] = true;
		}
	}
	/**
	 * "Pushes" a button to open/close the claw. To hold the claw open, add an argument for seconds.
	 * 
	 * @param clawIsOpen True opens the claw, false closes it.
	 */
	public void setClawOpen(boolean clawIsOpen) {
		synchronized(ReferenceData.getInstance()){
			ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.CLAW_JS][ButtonRef.CLAW_BUTTON] = clawIsOpen;
		}
	}
	
	/**
	 * 
	 * "Pushes" a button to open/close the claw. This function will hold the claw open for the amount of seconds passed to it.
	 * 
	 * @param clawIsOpen True opens the claw, false closes it
	 * @param seconds The number of seconds to keep the claw open
	 */
	public void setClawOpen(boolean clawIsOpen, double seconds) {		
		currentTime = new Date();
		setClawFalseTime = new Date((long) (currentTime.getTime() + seconds * 1000));
		timer.schedule(setClawFalse, setClawFalseTime);
		
		synchronized(ReferenceData.getInstance()){        
			ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.CLAW_JS][ButtonRef.CLAW_BUTTON] = clawIsOpen;		
		}
	}
	
	/**
	 * Positions the arm to place the tube on a desired peg.
	 * 
	 * @param desiredPeg Use the AutonomousController's Peg_--- constants
	 */
	public void setHeight(int desiredPeg) {
		
		//This checks to see if the set height has been reached. It needs to be called here and not in the getter
		//because the arm might be lowered, which would turn the "setArmHeightReached" false--even if the set height had been reached.
		
		calculateSetArmHeightReached();//In other words, this needs to be run here, where the values are being set.
		
		if(setArmHeightReached)
			desiredPeg = -78931; //set all the buttons to false.
		
		boolean[] heightButtons = new boolean[6]; //Array of buttons to push. NOTE: Booleans initialize to be false.
		switch(desiredPeg) {
		case ButtonRef.HEIGHT1:
			heightButtons[0] = true;
			break;
		case ButtonRef.HEIGHT2:
			heightButtons[1] = true;
			break;
		case ButtonRef.HEIGHT3:
			heightButtons[2] = true;
			break;
		case ButtonRef.HEIGHT4:
			heightButtons[3] = true;
			break;
		case ButtonRef.HEIGHT5:
			heightButtons[4] = true;
			break;
		case ButtonRef.HEIGHT6:
			heightButtons[5] = true;
			break;
		}
		
		synchronized(ReferenceData.getInstance()) {
			boolean buttons[][] = ReferenceData.getInstance().getButtonValues().getButtons();
			buttons[ButtonRef.JOYSTICK_ARM][ButtonRef.HEIGHT1] = heightButtons[0];
			buttons[ButtonRef.JOYSTICK_ARM][ButtonRef.HEIGHT2] = heightButtons[1];
			buttons[ButtonRef.JOYSTICK_ARM][ButtonRef.HEIGHT3] = heightButtons[2];
			buttons[ButtonRef.JOYSTICK_ARM][ButtonRef.HEIGHT4] = heightButtons[3];
			buttons[ButtonRef.JOYSTICK_ARM][ButtonRef.HEIGHT5] = heightButtons[4];
			buttons[ButtonRef.JOYSTICK_ARM][ButtonRef.HEIGHT6] = heightButtons[5];
		}
		

	}
	
	public boolean setArmHeightReached() {
		return setArmHeightReached;
	}
	
	
	private void calculateSetArmHeightReached() {
		//TODO Determine a reasonable epsilon value
		double epsilon = .5;//revolutions
		if(getCurrentArmHeight() != 0.0)
			setArmHeightReached = Math.abs(getCurrentArmHeight() - getSetArmHeight()) <= epsilon;
	}
	
	/**
	 * Reads the KinematicData and returns the most recent goal height (in encoder ticks)
	 * 
	 * @return setArmHeight In encoder ticks
	 */
	private double getSetArmHeight() {
		double setArmHeight = 0;
		synchronized(KinematicData.getInstance()){
			setArmHeight = KinematicData.getInstance().getArmData().getArmDistance();
		}
		return setArmHeight;
	}

	/**
	 * Reads the ReferenceData and returns the most recent arm height (in encoder ticks).
	 * 
	 * @return currentArmHeight In encoder ticks
	 */

	private double getCurrentArmHeight() {
		double currentArmHeight = 0;
		synchronized(ReferenceData.getInstance()){
			currentArmHeight = ReferenceData.getInstance().getArmEncoderValues().getEncoderValue();
		}
		return currentArmHeight;
	}

	public boolean isFinishedPlacingTube() {
		return false;
	}

	/**
	 * Reads the ReferenceData's claw state value and interprets the results.
	 * @return True if the claw is open, false if not.
	 */
	public boolean clawIsOpen() {
		boolean clawIsOpen;
		synchronized(ReferenceData.getInstance()){
			clawIsOpen = ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.CLAW_JS][ButtonRef.CLAW_BUTTON];
		}
		return clawIsOpen;
	}

	/**
	 * Moves the arm out of the way so that the tube can fall onto the peg and so that
	 * the robot can back up without knocking the tube off.
	 */
	public void lowerArm() {
		ArmInput armInput = new ArmInput();
		armInput.setAll(-.1, (int) ArmSetPointsRef.ARM_MOTOR_NO_SET_POINT);	
		
		synchronized (ReferenceData.getInstance()){
			armInput.copyValuesTo(ReferenceData.getInstance().getArmInput());
		}
	}

	public Date getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	public Date getSetClawFalseTime() {
		return setClawFalseTime;
	}

	public void setSetClawFalseTime(Date setClawFalseTime) {
		this.setClawFalseTime = setClawFalseTime;
	}


		
	}
