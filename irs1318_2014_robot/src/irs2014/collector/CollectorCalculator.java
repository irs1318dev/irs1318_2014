package irs2014.collector;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;

public class CollectorCalculator extends RobotComponentBase{
	
	public void teleopPeriodic(){
		//Extends or retracts the collector; prefers extend.
		if (ReferenceData.getInstance().getUserInputData().getExtendCollector()){
			ReferenceData.getInstance().getCollectorData().getSolenoidData().setDesiredSolenoidState(CollectorRef.EXTEND);
		} else if (ReferenceData.getInstance().getUserInputData().getRetractCollector()){
			ReferenceData.getInstance().getCollectorData().getSolenoidData().setDesiredSolenoidState(CollectorRef.RETRACT);
		}
		
		//Sets the motor to roll in, out, or turn off; prefers stop, then out, then in.
		if (ReferenceData.getInstance().getUserInputData().getStopCollectorMotor()){
			ReferenceData.getInstance().getCollectorData().getMotorData().setCollectorMotorOff();
		} else if (ReferenceData.getInstance().getUserInputData().getCollectorMotorOut()){
			ReferenceData.getInstance().getCollectorData().getMotorData().setCollectorMotorOut();
		} else if (ReferenceData.getInstance().getUserInputData().getCollectorMotorIn()){
			ReferenceData.getInstance().getCollectorData().getMotorData().setCollectorMotorIn();
		} else {
			ReferenceData.getInstance().getCollectorData().getMotorData().setCollectorMotorOff();
		}
	}
}
