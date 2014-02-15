package irs2014.autonomous.tasks;

import irs2014.autonomous.AutoTaskRunner;
import irs2014.autonomous.AutonomousCommand;

public class AutoLoadDriveShoot extends AutonomousCommand
{
	public void run() 
	{
		switch(currentState)
		{
		case 0:
			extendCollector();
			break;
		case 1:
			collectorMotorIn();
			break;
		case 2: 
			pause(COLLECT_WAIT_TIME);
			break;
		case 3:
			stopCollectorMotor();
			break;
		case 4:
			retractCollector();
			break;
		case 5:
			goForwardRel(DRIVE_DISTANCE);
			break;
		case 6:
			isDone = true;
			AutoTaskRunner.setAutoTask(new PrepThenShoot());
			break;
		}
	}

	public void cancel() 
	{
		stopCollectorMotor();
		stopDriveTrain();
	}
	
	private static final double DRIVE_DISTANCE = 5 * 12 * 2.54;//Feet to inches to centimeters
}
