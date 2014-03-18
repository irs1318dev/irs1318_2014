package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;
import irs2014.generalData.ReferenceData;

public class Clam extends AutonomousCommand{

	public Clam() {
		numberOfTimesCalled++;
	}
	
	private boolean shoulderAlreadyRetracted = false;
	
	public static int numberOfTimesCalled= 0;
	
	public void run() {
		switch(currentState){
		case 0:
			retractShooterFrame();
//			System.out.println("Clam state 0, numberOfTimesCalled="+numberOfTimesCalled);
			break;
		case 1:
			pause(ReferenceData.getInstance().getAutonomousVariableData().getShoulderWaitTime());
//			System.out.println("Clam state 1");
			break;
		case 2:
			retractCollector();
//			System.out.println("Clam state 2");
			break;
		case 3:
			collectorMotorOut(ReferenceData.getInstance().getAutonomousVariableData().getCollectorWaitTime());
//			System.out.println("Clam state 3");
			break;
		case 4:
			isDone = true;
//			System.out.println("Clam state 4 - is done");
			break;
		}
	}

	public void cancel() {
		
	}
	
	

}
