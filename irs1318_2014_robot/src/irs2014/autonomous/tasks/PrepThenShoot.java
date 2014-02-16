package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class PrepThenShoot extends AutonomousCommand{

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
			launch5Pistons();
			break;
		case 3:
			isDone = true;
			break;
		}
	}

	public void cancel() 
	{
		//Nothing required here
	}
}
