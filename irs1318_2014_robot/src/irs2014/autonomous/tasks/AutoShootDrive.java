package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class AutoShootDrive extends AutonomousCommand
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
			pause(SHIFT_WAIT_TIME);
			break;
		case 3:
			launch4Pistons();
			break;
		case 4:
			pause(MOVE_WAIT_TIME);
			break;
		case 5:
			goForwardRel(3 * 12 * 2.54);// feet to inches to centimeters.
			break;
		case 6:
			isDone = true;
			break;
		}
	}

	public void cancel() 
	{
		stopDriveTrain();
	}
}
