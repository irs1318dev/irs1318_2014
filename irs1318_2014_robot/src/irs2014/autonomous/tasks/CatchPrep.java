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
			isDone = true;
			break;
		}
	}

	public void cancel() 
	{
		//Nothing required right now
	}
}
