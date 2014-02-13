package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class LaunchBall extends AutonomousCommand
{
	public void run() 
	{
		switch(currentState)
		{
		case 0:
			extendLauncher();
			break;
		case 1:
			pause(1000);
			break;
		case 2:
			lowerLauncher();
			break;
		case 3:
			isDone = true;
			break;
		}
	}

	public void cancel() 
	{
		lowerLauncher();
	}
	
}
