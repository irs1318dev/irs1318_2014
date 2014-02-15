package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;
import irs2014.generalData.ReferenceData;

public class CollectBall extends AutonomousCommand{

	public void run() {
		switch(currentState){
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
			isDone = true;
			break;
		}
	}

	public void cancel() 
	{
		stopCollectorMotor();
	}	
}
