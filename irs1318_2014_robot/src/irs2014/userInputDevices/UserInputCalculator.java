package irs2014.userInputDevices;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;
import irs2014.networkTable.IRSTable;

public class UserInputCalculator extends RobotComponentBase{
	
	public void robotInit(){
		IRSTable.putBoolean(SmartDashRef.EXTEND_SHOOTER, false);
		IRSTable.putBoolean(SmartDashRef.RETRACT_SHOOTER, false);
		IRSTable.putBoolean(SmartDashRef.FIVE_PISTON_SHOT, false);
		IRSTable.putBoolean(SmartDashRef.THREE_PISTON_SHOT, false);
		IRSTable.putBoolean(SmartDashRef.ONE_PISTON_SHOT, false);
		IRSTable.putBoolean(SmartDashRef.SET_ONE_SHOT, false);
		IRSTable.putBoolean(SmartDashRef.SET_THREE_SHOT, false);
		IRSTable.putBoolean(SmartDashRef.SET_FIVE_SHOT, false);
		
		ReferenceData.getInstance().getUserInputData().setTriggerSet(5);
	}
	
	public void teleopPeriodic(){
		if(IRSTable.getBoolean(SmartDashRef.EXTEND_SHOOTER)){
//			ReferenceData.getInstance().getUserInputData().setExtendShooterAngle(true);
			IRSTable.putBoolean(SmartDashRef.EXTEND_SHOOTER, false);
		}
		if(IRSTable.getBoolean(SmartDashRef.RETRACT_SHOOTER)){
//			ReferenceData.getInstance().getUserInputData().setRetractShooterAngle(true);
			IRSTable.putBoolean(SmartDashRef.RETRACT_SHOOTER, false);
		}
		if(IRSTable.getBoolean(SmartDashRef.FIVE_PISTON_SHOT)){
//			ReferenceData.getInstance().getUserInputData().setExtendAllShooterSolenoids(true);
			IRSTable.putBoolean(SmartDashRef.FIVE_PISTON_SHOT, false);
		}
		if(IRSTable.getBoolean(SmartDashRef.THREE_PISTON_SHOT)){
//			ReferenceData.getInstance().getUserInputData().setExtendInnerThreeShooterSolenoids(true);
			IRSTable.putBoolean(SmartDashRef.THREE_PISTON_SHOT, false);
		}
		if(IRSTable.getBoolean(SmartDashRef.ONE_PISTON_SHOT)){
//			ReferenceData.getInstance().getUserInputData().setExtendInnerShooterSolenoids(true);
			IRSTable.putBoolean(SmartDashRef.ONE_PISTON_SHOT, false);
		}
		if(IRSTable.getBoolean(SmartDashRef.SET_ONE_SHOT)){
			ReferenceData.getInstance().getUserInputData().setTriggerSet(1);
			IRSTable.putBoolean(SmartDashRef.SET_ONE_SHOT, false);
		}
		if(IRSTable.getBoolean(SmartDashRef.SET_THREE_SHOT)){
			ReferenceData.getInstance().getUserInputData().setTriggerSet(3);
			IRSTable.putBoolean(SmartDashRef.SET_THREE_SHOT, false);
		}
		if(IRSTable.getBoolean(SmartDashRef.SET_FIVE_SHOT)){
			ReferenceData.getInstance().getUserInputData().setTriggerSet(5);
			IRSTable.putBoolean(SmartDashRef.SET_FIVE_SHOT, false);
		}
		
//		if(ReferenceData.getInstance().getUserInputData().getTrigger()){
			int temp = ReferenceData.getInstance().getUserInputData().getTriggerSet();
			switch(temp){
			case 1:
//				ReferenceData.getInstance().getUserInputData().setExtendInnerShooterSolenoids(true);
				break;
			case 3:
//				ReferenceData.getInstance().getUserInputData().setExtendMiddleShooterSolenoid(true);
				break;
			case 5:
//				ReferenceData.getInstance().getUserInputData().setExtendAllShooterSolenoids(true);
				break;
			}
//		}
		
		if(ReferenceData.getInstance().getUserInputData().getExtendBoth()){
//			ReferenceData.getInstance().getUserInputData().setExtendShooterAngle(true);
			ReferenceData.getInstance().getUserInputData().setExtendCollector(true);
		}
		if(ReferenceData.getInstance().getUserInputData().getRetractBoth()){
//			ReferenceData.getInstance().getUserInputData().setRetractShooterAngle(true);
			ReferenceData.getInstance().getUserInputData().setRetractCollector(true);
		}
		
//		if(ReferenceData.getInstance().getUserInputData().getShooterStep()){
//			int temp = ReferenceData.getInstance().getUserInputData().getTriggerSet();
//			switch(temp){
//			case 1:
//				ReferenceData.getInstance().getUserInputData().setTriggerSet(3);
//				break;
//			case 3:
//				ReferenceData.getInstance().getUserInputData().setTriggerSet(5);
//				break;
//			case 5:
//				ReferenceData.getInstance().getUserInputData().setTriggerSet(1);
//				break;
//			}
//		}
	}

}
