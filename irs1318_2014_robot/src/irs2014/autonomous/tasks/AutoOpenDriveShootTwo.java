package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class AutoOpenDriveShootTwo extends AutonomousCommand
{
	public void run() 
	{
		switch(currentState)
		{
		case 0:
			extendCollector();
			break;
		case 1:
			extendShooterFrame();
			break;
		case 2:
			pause(SHIFT_WAIT_TIME);
			break;
		case 3:
			collectorMotorIn(10000);// in milliseconds
			goForwardRel(15 * 12 * 2.54, .5);//ft to inches to centimeters
			break;
		case 4:
			pause(1000);
			break;
		case 5:
			System.out.println("We are launching ball 1!");
			launch4Pistons();
			break;
		case 6:
			pause(1500);
			break;
		case 7:
			collectorMotorIn(1750);
			break;
		case 8:
			pause(500);
			break;
		case 9:
			launch4Pistons();
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
