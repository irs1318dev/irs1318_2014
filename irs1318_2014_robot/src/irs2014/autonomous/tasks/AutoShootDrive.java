package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class AutoShootDrive extends AutonomousCommand
{
	public void run() 
	{
		switch(currentState)
		{
		case 0:
			collectorMotorIn();
			break;
		case 1:
			extendShooterFrame();
			break;
		case 2:
			extendCollector();
			break;
		case 3:
			pause(COLLECT_WAIT_TIME);
			break;
		case 4:
			stopCollectorMotor();
			break;
		case 5:
			extendLauncher();
			break;
		case 6:
			pause(LAUNCH_WAIT_TIME);
			break;
		case 7:
			lowerLauncher();
			break;
		case 8:
			goForwardRel(3 * 12 * 2.54);// feet to inches to centimeters.
			break;
		case 9:
			isDone = true;
			break;
		}
	}

	public void cancel() 
	{
		stopCollectorMotor();
		stopDriveTrain();
	}
}
