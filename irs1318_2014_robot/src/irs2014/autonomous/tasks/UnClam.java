package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;
import irs2014.generalData.ReferenceData;

public class UnClam extends AutonomousCommand{

	@Override
	public void run() {
		switch(currentState){
		case 0:
			extendCollector();
			break;
		case 1:
			collectorMotorIn(ReferenceData.getInstance().getAutonomousVariableData().getCollectorWaitTime());
			break;
		case 2:
			extendShooterFrame();
		case 3:
			isDone = true;
			break;
		}
	}

	@Override
	public void cancel() {
		
	}
	
	

}
