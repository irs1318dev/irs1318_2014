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
			collectorMotorIn(COLLECT_WAIT_TIME);
			break;
		case 2:
			goForwardRel(DRIVE_DISTANCE);
			break;
		case 3:
			pause(500);
			break;
		case 4:
			isDone = true;
			AutoTaskRunner.setAutoTask(new PrepThenShoot());
			break;
		}
	}

	public void cancel() 
	{
		stopDriveTrain();
	}
	
	private static final double DRIVE_DISTANCE = 10 * 12 * 2.54;//Feet to inches to centimeters
}
