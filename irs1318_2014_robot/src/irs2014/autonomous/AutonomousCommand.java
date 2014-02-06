package irs2014.autonomous;

import irs2014.generalData.*;

public abstract class AutonomousCommand implements AutoTask
{//The entire point of this is to create a system that allows us to just call one of these methods if we want to go forward.
	
	//Variables
	protected int currentState; //The integer representing the current state.
	protected double stateEncoderTicks; //The starting encoder ticks of the current state.
	protected long stateTime; //The start time of the current state.
	protected boolean isDone;
	
	public void init()
	{//Initializes a series of variables- this makes it so you don't have to have it. 
		currentState = 0;
		stateEncoderTicks = 0;//? Is this accurate?
		stateTime = System.currentTimeMillis();
		isDone = false;
	}

	public abstract void run();
	
	public abstract void cancel();
	
	public void advanceState() 
	{//Advance the macro to the next state
		currentState++;
		stateEncoderTicks = ReferenceData.getInstance().getDriveTrainData().getLeftEncoderTicks();
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
		
		return centimeters * 360 / (2 * Math.PI *practiceWheelRaduis); 
	}
	
	
	
	
	
	////////////////////////////////////////////////////////////////////////
	//Custom Methods that can be used to accomplish tasks. Eg: go forward
    ////////////////////////////////////////////////////////////////////////	
	public void pause(long delayMillis)
	{
		if(stateTime + delayMillis < System.currentTimeMillis())
			advanceState();
	}
	
	public void extendCollector(){
		ReferenceData.getInstance().getUserInputData().setExtendCollector(true);
		advanceState();
	}
	
	public void retractCollector(){
		ReferenceData.getInstance().getUserInputData().setRetractCollector(true);
		advanceState();
	}
	
	public void collectorMotorIn(){
		ReferenceData.getInstance().getUserInputData().setCollectorMotorIn(true);
		advanceState();
	}
	
	public void collectorMotorOut(){
		ReferenceData.getInstance().getUserInputData().setCollectorMotorOut(true);
		advanceState();
	}
	
	public void stopCollectorMotor(){
		ReferenceData.getInstance().getUserInputData().setStopCollectorMotor(true);
		advanceState();
	}
	
	public void goForwardRel(double centimeters)
	{//Go forward relative- goes forward relative to the encoder value from the last state.
		if(stateEncoderTicks + toTicks(centimeters) < ReferenceData.getInstance().getDriveTrainData().getLeftEncoder())
			advanceState();
		else
			ReferenceData.getInstance().getUserInputData().setJoystickX(.66 / 2.5);
	}
	
	/**
	 * Go forward relative- goes forward relative to the encoder value from the last state.
	 * @param centimeters
	 * @param speed
	 */
	public void goForwardRel(double centimeters, double speed)
	{
		if(stateEncoderTicks + toTicks(centimeters) < ReferenceData.getInstance().getDriveTrainData().getLeftEncoder())
			advanceState();
		else
			ReferenceData.getInstance().getUserInputData().setJoystickX(speed / 2.5);
	}
	
	private final double EPSILON = 10; //Ticks
	private final double VEL_CUTOFF = 24 * 2.54; // measured in cm, converted from inches.
	private boolean IS_POSITION_PID = false; // ...
	
	/**
	 * As long as it is being called, this will send you toward the calculated launch tick, with a small margin of error.
	 */
	public void goToLaunchTick()
	{
		double launchTick = ReferenceData.getInstance().getEncoderState().getLaunchTick(); // specific tick
		if(Math.abs(launchTick - ReferenceData.getInstance().getDriveTrainData().getLeftEncoder()) < EPSILON)
		{
			IS_POSITION_PID = false;
			advanceState();
		}
		else
		{
			int direction = 1;
			//if(launchTick > toTicks(VEL_CUTOFF))
				//ReferenceData.getInstance().getUserInputData().setJoystickX(.66 / 2.5); if 2ft, right
		}
	}
	
	public void turn(double degrees)
	{
		//TODO: Add this!
	}
	

}
