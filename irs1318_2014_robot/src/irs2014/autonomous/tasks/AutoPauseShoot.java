package irs2014.autonomous.tasks;

import irs2014.autonomous.AutoTaskRunner;
import irs2014.autonomous.AutonomousCommand;

public class AutoPauseShoot extends AutonomousCommand
{
	public static final long TIME_TO_WAIT = 100; //Measured in milliseconds
	public void run() 
	{
		switch(currentState)
		{
		case 0:
			pause(TIME_TO_WAIT);
			break;
		case 1:
			isDone = true;
			AutoTaskRunner.setAutoTask(new PrepThenShoot());
			break;
		}
	}

	public void cancel() 
	{
		//Nothing required as of yet
	}
}
