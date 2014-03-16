package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;
import irs2014.generalData.ReferenceData;

public class Clam extends AutonomousCommand{

	private boolean shoulderAlreadyRetracted = false;
	
	@Override
	public void run() {
		switch(currentState){
		case 0:
			retractShooterFrame();
			break;
		case 1:
			pause(ReferenceData.getInstance().getAutonomousVariableData().getShoulderWaitTime());
			break;
		case 2:
			retractCollector();
			break;
		case 3:
			collectorMotorOut(ReferenceData.getInstance().getAutonomousVariableData().getCollectorWaitTime());
			break;
		case 4:
			isDone = true;
			break;
		}
	}

	@Override
	public void cancel() {
		
	}
	
	

}
