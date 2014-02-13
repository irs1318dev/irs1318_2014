package irs2014.userInputDevices2;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;
import irs2014.networkTable.IRSTable;

public class UserInputCalculator2 extends RobotComponentBase{
	
	public void robotInit(){
		IRSTable.putBoolean(SmartDashRef2.EXTEND_SHOOTER, false);
		IRSTable.putBoolean(SmartDashRef2.RETRACT_SHOOTER, false);
		IRSTable.putBoolean(SmartDashRef2.FIVE_PISTON_SHOT, false);
		IRSTable.putBoolean(SmartDashRef2.THREE_PISTON_SHOT, false);
		IRSTable.putBoolean(SmartDashRef2.ONE_PISTON_SHOT, false);
		IRSTable.putBoolean(SmartDashRef2.SET_ONE_SHOT, false);
		IRSTable.putBoolean(SmartDashRef2.SET_THREE_SHOT, false);
		IRSTable.putBoolean(SmartDashRef2.SET_FIVE_SHOT, false);
		
		ReferenceData.getInstance().getUserInputData().setTriggerSet(5);
	}
	
	public void teleopPeriodic(){
		if(IRSTable.getBoolean(SmartDashRef2.EXTEND_SHOOTER)){
			ReferenceData.getInstance().getUserInputData2().setExtendShooterAngle(true);
			IRSTable.putBoolean(SmartDashRef2.EXTEND_SHOOTER, false);
		}
		if(IRSTable.getBoolean(SmartDashRef2.RETRACT_SHOOTER)){
			ReferenceData.getInstance().getUserInputData2().setRetractShooterAngle(true);
			IRSTable.putBoolean(SmartDashRef2.RETRACT_SHOOTER, false);
		}
		if(IRSTable.getBoolean(SmartDashRef2.FIVE_PISTON_SHOT)){
			ReferenceData.getInstance().getUserInputData2().setExtendAllShooterSolenoids(true);
			IRSTable.putBoolean(SmartDashRef2.FIVE_PISTON_SHOT, false);
		}
		if(IRSTable.getBoolean(SmartDashRef2.THREE_PISTON_SHOT)){
			ReferenceData.getInstance().getUserInputData2().setExtendInnerThreeShooterSolenoids(true);
			IRSTable.putBoolean(SmartDashRef2.THREE_PISTON_SHOT, false);
		}
		if(IRSTable.getBoolean(SmartDashRef2.ONE_PISTON_SHOT)){
			ReferenceData.getInstance().getUserInputData2().setExtendInnerShooterSolenoids(true);
			IRSTable.putBoolean(SmartDashRef2.ONE_PISTON_SHOT, false);
		}
		if(IRSTable.getBoolean(SmartDashRef2.SET_ONE_SHOT)){
			ReferenceData.getInstance().getUserInputData2().setTriggerSet(1);
			IRSTable.putBoolean(SmartDashRef2.SET_ONE_SHOT, false);
		}
		if(IRSTable.getBoolean(SmartDashRef2.SET_THREE_SHOT)){
			ReferenceData.getInstance().getUserInputData2().setTriggerSet(3);
			IRSTable.putBoolean(SmartDashRef2.SET_THREE_SHOT, false);
		}
		if(IRSTable.getBoolean(SmartDashRef2.SET_FIVE_SHOT)){
			ReferenceData.getInstance().getUserInputData2().setTriggerSet(5);
			IRSTable.putBoolean(SmartDashRef2.SET_FIVE_SHOT, false);
		}
		
		if(ReferenceData.getInstance().getUserInputData2().getTrigger()){
			int temp = ReferenceData.getInstance().getUserInputData().getTriggerSet();
			switch(temp){
			case 1:
				ReferenceData.getInstance().getUserInputData2().setExtendInnerShooterSolenoids(true);
				break;
			case 3:
				ReferenceData.getInstance().getUserInputData2().setExtendMiddleShooterSolenoid(true);
				break;
			case 5:
				ReferenceData.getInstance().getUserInputData2().setExtendAllShooterSolenoids(true);
				break;
			}
		}
		
		if(ReferenceData.getInstance().getUserInputData2().getExtendBoth()){
			ReferenceData.getInstance().getUserInputData2().setExtendShooterAngle(true);
			ReferenceData.getInstance().getUserInputData2().setExtendCollector(true);
		}
		if(ReferenceData.getInstance().getUserInputData2().getRetractBoth()){
			ReferenceData.getInstance().getUserInputData2().setRetractShooterAngle(true);
			ReferenceData.getInstance().getUserInputData2().setRetractCollector(true);
		}
		
		if(ReferenceData.getInstance().getUserInputData2().getShooterStep()){
			int temp = ReferenceData.getInstance().getUserInputData().getTriggerSet();
			switch(temp){
			case 1:
				ReferenceData.getInstance().getUserInputData2().setTriggerSet(3);
				break;
			case 3:
				ReferenceData.getInstance().getUserInputData2().setTriggerSet(5);
				break;
			case 5:
				ReferenceData.getInstance().getUserInputData2().setTriggerSet(1);
				break;
			}
		}
	}

}
