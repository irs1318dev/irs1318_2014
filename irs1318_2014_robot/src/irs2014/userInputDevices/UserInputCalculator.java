package irs2014.userInputDevices;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;
import irs2014.networkTable.IRSTable;

public class UserInputCalculator extends RobotComponentBase{
	
	public void robotInit(){
		IRSTable.putBoolean(SmartDashRef.EXTEND_SHOOTER, false);
		IRSTable.putBoolean(SmartDashRef.RETRACT_SHOOTER, false);
		IRSTable.putBoolean(SmartDashRef.EXTEND_COLLECTOR, false);
		IRSTable.putBoolean(SmartDashRef.RETRACT_COLLECTOR, false);
		
	}
	
	public void teleopPeriodic(){
		if(IRSTable.getBoolean(SmartDashRef.EXTEND_SHOOTER)){
			ReferenceData.getInstance().getUserInputData().setExtendShooterAngle(true);
			IRSTable.putBoolean(SmartDashRef.EXTEND_SHOOTER, false);
		}
		if(IRSTable.getBoolean(SmartDashRef.RETRACT_SHOOTER)){
			ReferenceData.getInstance().getUserInputData().setRetractShooterAngle(true);
			IRSTable.putBoolean(SmartDashRef.RETRACT_SHOOTER, false);
		}
		if(IRSTable.getBoolean(SmartDashRef.EXTEND_COLLECTOR)){
			ReferenceData.getInstance().getUserInputData().setExtendCollector(true);
			IRSTable.putBoolean(SmartDashRef.EXTEND_COLLECTOR, false);
		}
		if(IRSTable.getBoolean(SmartDashRef.RETRACT_COLLECTOR)){
			ReferenceData.getInstance().getUserInputData().setRetractCollector(true);
			IRSTable.putBoolean(SmartDashRef.RETRACT_COLLECTOR, false);

		}
		
		
		if(ReferenceData.getInstance().getUserInputData().getExtendBoth()){
			ReferenceData.getInstance().getUserInputData().setExtendShooterAngle(true);
			ReferenceData.getInstance().getUserInputData().setExtendCollector(true);
		}
		if(ReferenceData.getInstance().getUserInputData().getRetractBoth()){
			ReferenceData.getInstance().getUserInputData().setRetractShooterAngle(true);
			ReferenceData.getInstance().getUserInputData().setRetractCollector(true);
		}
		
	}

}
