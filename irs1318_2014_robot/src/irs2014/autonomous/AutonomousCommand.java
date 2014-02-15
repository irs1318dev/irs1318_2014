package irs2014.autonomous;

import irs2014.collector.CollectorRef;
import irs2014.generalData.*;
import irs2014.shooter.ShooterRef;

public abstract class AutonomousCommand implements AutoTask
{//The entire point of this is to create a system that allows us to just call one of these methods if we want to go forward.
	
	//Static Variables
	public static int COLLECT_WAIT_TIME = 3000; //TODO make sure this is tuned properly. (in milliseconds)
	public static int LAUNCH_WAIT_TIME = 1000; //TODO make sure this is tuned properly. (in milliseconds)
	public static int EJECT_WAIT_TIME = 3000; //TODO make sure this is tuned properly. (in milliseconds)
	
	//Variables
	protected int currentState; //The integer representing the current state.
	protected double stateLeftEncoderTicks; //The starting encoder ticks of the current state- left encoder.
	protected double stateRightEncoderTicks; //The starting encoder tick for the right encoder
	protected long stateTime; //The start time of the current state.
	protected boolean isDone;
	
	public void init()
	{//Initializes a series of variables- this makes it so you don't have to have it. 
		currentState = 0;
		stateLeftEncoderTicks = 0;//? Is this accurate?
		stateRightEncoderTicks = 0;
		stateTime = System.currentTimeMillis();
		isDone = false;
	}

	public abstract void run();
	
	public abstract void cancel();
	
	public void advanceState() 
	{//Advance the macro to the next state
		currentState++;
		stateLeftEncoderTicks = ReferenceData.getInstance().getDriveTrainData().getLeftEncoderData().getTicks();
		stateRightEncoderTicks = ReferenceData.getInstance().getDriveTrainData().getRightEncoderData().getTicks();
		stateTime = System.currentTimeMillis();
	}
	
	public boolean isDone()
	{//Return whether or not we are done.
		return isDone;
	}
	
	private double toTicks(double centimeters)
	{//Converts centimeters to ticks
		double practiceWheelRaduis = 4.0 * 2.54; // centimeters
		double competitionWheelRadius = 2.5 * 2.54; // centimeters
		if(ReferenceData.getInstance().getDipSwitchData().getDipSwitchState()) // if it is the practice robot
			return centimeters * 360 / (2 * Math.PI * practiceWheelRaduis); 
		return centimeters * 360 / (2 * Math.PI * competitionWheelRadius);
	}
	
	/**
	 * the distance between both wheels
	 * @return
	 */
	private double getWheelDistance()
	{
		if(ReferenceData.getInstance().getDipSwitchData().getDipSwitchState()) // if it is the practice robot
			return 16.33 * 2.54;//now in centimeters! (practice robot)
		return 24.25 * 2.54;//now in centimeters! (real robot)
	}
	
	
	
	
	
	/////////////////////////////////////////////////////////
	//Custom Methods that can be used to accomplish tasks. //
    /////////////////////////////////////////////////////////	
	public void pause(long delayMillis)
	{
		if(stateTime + delayMillis < System.currentTimeMillis())
			advanceState();
	}
	
	public void extendCollector(){
		ReferenceData.getInstance().getUserInputData().setExtendCollector(CollectorRef.EXTEND);
		advanceState();
	}
	
	public void retractCollector(){
		ReferenceData.getInstance().getUserInputData().setRetractCollector(CollectorRef.RETRACT);
		advanceState();
	}
	
	public void collectorMotorIn()
	{//This will start the motor, it must be stopped later.
		ReferenceData.getInstance().getUserInputData().setCollectorMotorIn(true);
		advanceState();
	}
	
	public void collectorMotorOut()
	{//This also starts the motor (spitting the ball out) and must be stopped later.
		ReferenceData.getInstance().getUserInputData().setCollectorMotorOut(true);
		advanceState();
	}
	
	public void stopCollectorMotor(){
		ReferenceData.getInstance().getUserInputData().setStopCollectorMotor(true);
		advanceState();
	}
	
	public void extendShooterFrame()
	{
		ReferenceData.getInstance().getUserInputData().setExtendShooterAngle(ShooterRef.EXTEND);
		advanceState();
	}
	
	public void retractShooterFrame()
	{
		ReferenceData.getInstance().getUserInputData().setExtendShooterAngle(ShooterRef.RETRACT);
		advanceState();
	}
	
	public void lowerLauncher()
	{//Lowers shooter into a position from which we can launch it again.
		ReferenceData.getInstance().getUserInputData().setShoot5Pistons(ShooterRef.RETRACT);
		advanceState();
		//TODO: Rev's note: this is being used instead of setting 3 other buttons individually. As a result, we need to make sure that this method stays updated.
	}
	
	public void extendLauncher()
	{//Launches the ball, hopefully you lowered it before.
		ReferenceData.getInstance().getUserInputData().setShoot5Pistons(ShooterRef.EXTEND);
		advanceState();
		//TODO: Rev's note: this is being used instead of setting 3 other buttons individually. As a result, we need to make sure that this method stays updated.
	}
	
	public void stopDriveTrain()
	{//probably for use in quick-set situations, this will tell the robot to stop moving for one cycle. May be good for cancels.
		ReferenceData.getInstance().getUserInputData().setJoystickX(0);
		ReferenceData.getInstance().getUserInputData().setJoystickY(0);
		advanceState();
	}
	
	public void goForwardRel(double centimeters)
	{//Go forward relative- goes forward relative to the encoder value from the last state.
		goForwardRel(centimeters, .66);
	}
	
	/**
	 * Go forward relative- goes forward relative to the encoder value from the last state.
	 * @param centimeters
	 * @param speed (between 0 and 1)
	 */
	public void goForwardRel(double centimeters, double speed)
	{//Go forward relative- goes forward relative to the encoder value from the last state.
		if(stateLeftEncoderTicks + toTicks(centimeters) < ReferenceData.getInstance().getDriveTrainData().getLeftEncoderData().getVelocity())
			advanceState();
		else
			ReferenceData.getInstance().getUserInputData().setJoystickX(speed / 2.5);
	}
	
	/**
	 * Condensed everything so you don't have to go through reference data to set the left and right desired launch ticks.
	 * @param launchLeft
	 * @param launchRight
	 */
	public void setLaunchTicks(double launchLeft, double launchRight)
	{
		ReferenceData.getInstance().getEncoderState().setLaunchTickLeft(launchLeft);
		ReferenceData.getInstance().getEncoderState().setLaunchTickRight(launchRight);
		advanceState();
	}
	
	
	
	private final double EPSILON = 10; //Ticks
	private final double VEL_CUTOFF = 24 * 2.54; // measured in cm, converted from inches.
	private final double VEL_DRIVE_SPEED = .66 / 2.5;//set the x to go forward to backward, the 2.5 is a magic number from jim.
	/**
	 * As long as it is being called, this will send you toward the calculated launch tick, with a small margin of error.
	 * Does not handle turning toward the target.
	 */
	public void goToLaunchTick()
	{
		double launchTickRight = ReferenceData.getInstance().getEncoderState().getLaunchTickRight(); // specific tick
		double launchTickLeft = ReferenceData.getInstance().getEncoderState().getLaunchTickLeft();
		double currentTick = ReferenceData.getInstance().getDriveTrainData().getLeftEncoderData().getTicks(); //TODO: revise this line to use an average of both sides.
		
		boolean behindDestinationLine = true;
		if(launchTickLeft > currentTick)
			behindDestinationLine = false;
		
		if(Math.abs(launchTickRight - currentTick) < EPSILON && Math.abs(launchTickLeft - currentTick) < EPSILON)
		{//If we're within the small number of ticks we want to shoot from
			advanceState();
			ReferenceData.getInstance().getEncoderState().setPIDType(EncoderState.VELOCITY_PID);
		}
		else
		{//If we're not within the shot range, then we use one of two forms of PID.
			if(Math.abs(launchTickRight - currentTick) > VEL_CUTOFF || Math.abs(launchTickLeft - currentTick) > VEL_CUTOFF)
			{// if we're further away than the velocity PID cutoff for right or left side...
				ReferenceData.getInstance().getEncoderState().setPIDType(EncoderState.VELOCITY_PID);
				if(behindDestinationLine)
					ReferenceData.getInstance().getUserInputData().setJoystickX(VEL_DRIVE_SPEED);
				else
					ReferenceData.getInstance().getUserInputData().setJoystickX(VEL_DRIVE_SPEED * -1);
			}
			else
			{// if we're within the range where we want to be more specific
				ReferenceData.getInstance().getEncoderState().setPIDType(EncoderState.POSITION_PID);
				if(Math.abs(launchTickRight - currentTick) < VEL_CUTOFF)
					ReferenceData.getInstance().getDriveTrainData().getRightPIDData().setPostionSetpoint(launchTickRight);
				if(Math.abs(launchTickLeft - currentTick) < VEL_CUTOFF)
					ReferenceData.getInstance().getDriveTrainData().getLeftPIDData().setPostionSetpoint(launchTickLeft);
			}
		}
	}

	
	
	private final double ACCEPTED_TICK_ERROR = 10;	
	/**
	 * This method takes a degree value in and acts according to what you gave it. PID currently commented out, verify you are setting it correctly.
	 * @param degrees degrees to turn. Positive is counterClockwise, Negative is clockwise.
	 */
	public void rotate(double degrees)
	{
		//This is the arc length we are trying to turn. This value is to be divided between both sides.
		double arcLength = 2 * Math.PI * (getWheelDistance() / 2) * (degrees / 360);
		ReferenceData.getInstance().getEncoderState().setPIDType(EncoderState.POSITION_PID);
		
		if(degrees > 0) // If we're turning left...
		{
			double desiredRightTick = stateRightEncoderTicks + toTicks(arcLength / 2);
			double desiredLeftTick = stateRightEncoderTicks - toTicks(arcLength / 2);
			if(Math.abs(ReferenceData.getInstance().getDriveTrainData().getRightEncoderData().getTicks() - desiredRightTick) <= ACCEPTED_TICK_ERROR &&
				Math.abs(ReferenceData.getInstance().getDriveTrainData().getLeftEncoderData().getTicks() - desiredLeftTick) <= ACCEPTED_TICK_ERROR)
			{
				advanceState();
				ReferenceData.getInstance().getEncoderState().setPIDType(EncoderState.VELOCITY_PID);
			}
			else
			{
				ReferenceData.getInstance().getDriveTrainData().getRightPIDData().setPostionSetpoint(desiredRightTick);
				ReferenceData.getInstance().getDriveTrainData().getLeftPIDData().setPostionSetpoint(desiredLeftTick);
			}
		}
		else // Well, we must be turning right...
		{
			double desiredRightTick = stateRightEncoderTicks - toTicks(arcLength / 2);
			double desiredLeftTick = stateLeftEncoderTicks - toTicks(arcLength / 2);
			if(Math.abs(ReferenceData.getInstance().getDriveTrainData().getRightEncoderData().getTicks() - desiredRightTick) <= ACCEPTED_TICK_ERROR &&
					Math.abs(ReferenceData.getInstance().getDriveTrainData().getLeftEncoderData().getTicks() - desiredLeftTick) <= ACCEPTED_TICK_ERROR)
			{
				advanceState();
				ReferenceData.getInstance().getEncoderState().setPIDType(EncoderState.VELOCITY_PID);
			}
			else
			{
				ReferenceData.getInstance().getDriveTrainData().getRightPIDData().setPostionSetpoint(desiredRightTick);
				ReferenceData.getInstance().getDriveTrainData().getLeftPIDData().setPostionSetpoint(desiredLeftTick);
			}
		}
	}
}
