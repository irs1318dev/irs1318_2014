package irs2014.autonomous.tasks;

import irs2014.autonomous.AutoTaskRunner;
import irs2014.autonomous.AutonomousCommand;

public class PrepThenShoot extends AutonomousCommand{

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
			pause(150);
			break;
		case 4:
			stopCollectorMotor();
			break;
		case 5:
			isDone = true;
			AutoTaskRunner.setAutoTask(new LaunchBall());
			break;
		}
	}

	public void cancel() 
	{
		stopCollectorMotor();
	}
}
