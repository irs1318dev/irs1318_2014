package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class CollectBall extends AutonomousCommand{

	public void run() {
		switch(currentState){
		case 0:
			extendCollector();
			break;
		case 1:
			collectorMotorIn(COLLECT_WAIT_TIME);
			break;
		case 2:
			isDone = true;
			break;
		}
	}

	public void cancel() 
	{
		//Nothing required
	}	
}
