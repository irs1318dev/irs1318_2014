package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class CatchPrep extends AutonomousCommand
{
	public void run() 
	{	
		switch(currentState)
		{
		case 0:
			extendShooterFrame();
			break;
		case 1:
			extendCollector();
			break;
		case 2:
			lowerLauncher();
			break;
		case 3:
			stopCollectorMotor();
			break;
		case 4: 
			isDone = true;
			break;
		}
	}

	public void cancel() 
	{
		stopCollectorMotor();
	}

}
