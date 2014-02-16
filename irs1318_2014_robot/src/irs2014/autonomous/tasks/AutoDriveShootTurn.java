package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class AutoDriveShootTurn extends AutonomousCommand
{
	public void run() 
	{
		switch(currentState)
		{
		case 0:
			goForwardRel(10 * 12 * 2.54); // 10 feet, converted to inches, converted to centimeters.
			break;
		case 1:
			extendShooterFrame();
			break;
		case 2:
			extendCollector();
			break;
		case 3:
			pause(SHIFT_WAIT_TIME);
			break;
		case 4:
			launch3Pistons();
			break;
		case 5:
			pause(MOVE_WAIT_TIME);
			break;
		case 6:
			rotate(180);
			break;
		case 7:
			isDone = true;
			break;
		}
	}

	public void cancel() 
	{
		stopDriveTrain();
	}
}
