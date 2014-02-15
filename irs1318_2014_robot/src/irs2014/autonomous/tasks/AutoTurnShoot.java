package irs2014.autonomous.tasks;

import irs2014.autonomous.AutoTaskRunner;
import irs2014.autonomous.AutonomousCommand;

public class AutoTurnShoot extends AutonomousCommand
{
	public void run() 
	{
		switch(currentState)
		{
		case 0:
			goForwardRel(10 * 12 * 2.54);//Feet to inches to centimeters
		case 1:
			rotate(45); // 10 feet, converted to inches, converted to centimeters.
			break;
		case 2:
			isDone = true;
			AutoTaskRunner.setAutoTask(new PrepThenShoot());
			break;
		}
	}

	public void cancel() 
	{
		stopDriveTrain();
	}
}
