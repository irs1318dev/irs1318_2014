package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class AutoOpenDriveShootOne extends AutonomousCommand
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
			goForwardRel(15 * 12 * 2.54);//ft to inches to centimeters
			break;
		case 3:
			System.out.println("We are paused!");
			pause(1000);
			break;
		case 4:
			System.out.println("We are launching!");
			launch5Pistons();
			break;
		case 5:
			isDone = true;
			break; //add some turning code in here...?
			
		}
	}
	
	public void cancel() 
	{
		stopDriveTrain();
	}
	
}