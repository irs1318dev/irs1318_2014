package irs2014.autonomous.tasks;

import irs2014.autonomous.AutoTaskRunner;
import irs2014.autonomous.AutonomousCommand;

public class AutoDriveTurnShoot extends AutonomousCommand
{
	public void run() 
	{
		switch(currentState)
		{
		case 0:
			goForwardRel(8 * 12 * 2.54); // Feet, converted to inches, converted to centimeters.
			break;
		case 1:
			rotate(45);
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
