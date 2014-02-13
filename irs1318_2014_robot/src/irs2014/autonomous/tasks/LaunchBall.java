package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class LaunchBall extends AutonomousCommand
{
	public void run() 
	{
		switch(currentState)
		{
		case 1:
			extendLauncher();
			break;
		case 2:
			pause(1000);
			break;
		case 3:
			lowerLauncher();
			break;
		}
	}

	public void cancel() 
	{
		//Nothing required.
		lowerLauncher();
	}
	
}
