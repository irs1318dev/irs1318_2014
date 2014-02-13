package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class ShootPrep extends AutonomousCommand
{
	public void run() 
	{
		switch(currentState)
		{
		case 1:
			collectorMotorIn();
			break;
		case 2:
			extendShooterFrame();
		case 3:
			extendCollector();
			break;
		case 4:
			pause(150);
			break;
		case 5:
			stopCollectorMotor();
			break;
		case 6:
			isDone = true;
			break;
		}
	}

	public void cancel() 
	{
		stopCollectorMotor();
	}
}
