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
		//TODO: set 'stateEncoderTicks' to appropriate value. Verify code w/ programming lead
		stateTime = System.currentTimeMillis();
	}
	
	public boolean isDone()
	{//Return whether or not we are done.
		return isDone;
	}
	
	
	
	
	
	
	
	////////////////////////////////////////////////////////////////////////
	//Custom Methods that can be used to accomplish tasks. Eg: go forward
    ////////////////////////////////////////////////////////////////////////
	/*
	public void driveForward(double ticks)
	{
		//TODO: Write this.
	}
	*/
	public void driveForward(long timeSec)
	{
		if(System.currentTimeMillis() <= (stateTime + timeSec * 1000))
		{
			ReferenceData.getInstance().getUserInputData().setJoystickY(.5);
		}
		else
			advanceState();
	}
	
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
}
