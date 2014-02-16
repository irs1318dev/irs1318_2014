package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class EjectBall extends AutonomousCommand{

	public void run() {
		switch(currentState){
		case 0:
			retractCollector();
			break;
		case 1:
			collectorMotorOut(EJECT_WAIT_TIME);
			break;
		case 2:
			extendCollector();
			break;
		case 3:
			isDone = true;
			break;
		}
	}

	public void cancel() 
	{
		//Nothing required yet.
	}	
}
