package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class CatchPrep extends AutonomousCommand
{
	public void run() 
	{	
		switch(currentState)
		{
		case 0:
			
			break;
		case 1:
			extendCollector();
			break;
		case 2:
			
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
		//Nothing required so far as we can tell, we don't want to use pneumatics if we don't have to.
	}

}
