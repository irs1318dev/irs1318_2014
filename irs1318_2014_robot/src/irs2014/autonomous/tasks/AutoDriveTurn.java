package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class AutoDriveTurn extends AutonomousCommand
{
	public void run() 
	{
		switch(currentState)
		{
		case 0:
			goForwardRel(10 * 12 * 2.54); // 10 feet, converted to inches, converted to centimeters.
			break;
		case 1:
			rotate(180);
		case 2:
			isDone = true;
			break;
		}
	}

	public void cancel() 
	{
		stopDriveTrain();
	}
}
