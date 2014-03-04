package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;
import irs2014.generalData.ReferenceData;

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
			pause(ReferenceData.getInstance().getAutonomousVariableData().getShiftWaitTime());
			break;
		case 3:
			collectorMotorIn(10000);// in milliseconds
			goForwardRel(ReferenceData.getInstance().getAutonomousVariableData().getDistance() * 2.54, ReferenceData.getInstance().getAutonomousVariableData().getSpeed());//inches to centimeters
			break;
		case 4:
			pause(ReferenceData.getInstance().getAutonomousVariableData().getPauseAfterDriving());
			break;
		case 5:
			System.out.println("We are launching ball 1!");
			launch4Pistons();
			break;
		case 6:
			pause(ReferenceData.getInstance().getAutonomousVariableData().getPauseAfterShot1());
			break;
		case 7:
			collectorMotorIn(ReferenceData.getInstance().getAutonomousVariableData().getCollectorIn());
			break;
		case 8:
			pause(ReferenceData.getInstance().getAutonomousVariableData().getPauseAfterCollectorIn());
			break;
		case 9:
			launch5Pistons();
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
