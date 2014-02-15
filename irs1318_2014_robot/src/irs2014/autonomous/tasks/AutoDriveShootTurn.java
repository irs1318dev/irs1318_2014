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
			collectorMotorIn();
			break;
		case 2:
			extendShooterFrame();
			break;
		case 3:
			extendCollector();
			break;
		case 4:
			pause(COLLECT_WAIT_TIME);
			break;
		case 5:
			stopCollectorMotor();
			break;
		case 6:
			extendLauncher();
			break;
		case 7:
			pause(LAUNCH_WAIT_TIME);
			break;
		case 8:
			lowerLauncher();
			break;
		case 9:
			rotate(180);
			break;
		case 10:
			isDone = true;
			break;
		}
	}

	public void cancel() 
	{
		stopDriveTrain();
	}
}
