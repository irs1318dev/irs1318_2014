package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class EjectBall extends AutonomousCommand{

	public void run() {
		switch(currentState){
		case 0:
			retractCollector();
			break;
		case 1:
			collectorMotorOut();
			break;
		case 2:
			pause(EJECT_WAIT_TIME);
			break;
		case 3:
			extendCollector();
			break;
		case 4:
			stopCollectorMotor();
			break;
		case 5:
			isDone = true;
			break;
		}
	}

	public void cancel() {
		stopCollectorMotor();
	}

	private final static int EJECT_WAIT_TIME = -1; //TODO
	
}
