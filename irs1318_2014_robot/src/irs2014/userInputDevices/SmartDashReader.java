package irs2014.userInputDevices;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;
import irs2014.networkTable.IRSTable;

public class SmartDashReader extends RobotComponentBase {
	public void robotInit() {
		IRSTable.putNumber(SmartDashRef.SHIFT_WAIT_TIME_INPUT, new Double(0));
		IRSTable.putNumber(SmartDashRef.DISTANCE_INPUT, new Double(0));
		IRSTable.putNumber(SmartDashRef.PAUSE_AFTER_DRIVING_INPUT, new Double(0));
		IRSTable.putNumber(SmartDashRef.PAUSE_AFTER_SHOT_1_INPUT, new Double(0));
		IRSTable.putNumber(SmartDashRef.COLLECTOR_IN_INPUT, new Double(0));
		IRSTable.putNumber(SmartDashRef.PAUSE_AFTER_COLLECTOR_IN_INPUT, new Double(0));
		IRSTable.putNumber(SmartDashRef.SPEED_INPUT, new Double(0));
	}
	
	public void teleopPeriodic() {
		try {
			ReferenceData.getInstance().getAutonomousVariableData().setShiftWaitTime((int)IRSTable.getNumber(SmartDashRef.SHIFT_WAIT_TIME_INPUT));
		} catch (Exception e) {
			ReferenceData.getInstance().getAutonomousVariableData().setShiftWaitTime(1000);
		}
		try {
			ReferenceData.getInstance().getAutonomousVariableData().setDistance(IRSTable.getNumber(SmartDashRef.DISTANCE_INPUT));
		} catch (Exception e) {
			ReferenceData.getInstance().getAutonomousVariableData().setDistance(15 * 12);
		}
		try {
			ReferenceData.getInstance().getAutonomousVariableData().setPauseAfterDriving((int)IRSTable.getNumber(SmartDashRef.PAUSE_AFTER_DRIVING_INPUT));
		} catch (Exception e) {
			ReferenceData.getInstance().getAutonomousVariableData().setPauseAfterDriving(1000);
		}
		try {
			ReferenceData.getInstance().getAutonomousVariableData().setPauseAfterShot1((int)IRSTable.getNumber(SmartDashRef.PAUSE_AFTER_SHOT_1_INPUT));
		} catch (Exception e) {
			ReferenceData.getInstance().getAutonomousVariableData().setPauseAfterShot1(1200);
		}
		try {
			ReferenceData.getInstance().getAutonomousVariableData().setCollectorIn((int)IRSTable.getNumber(SmartDashRef.COLLECTOR_IN_INPUT));
		} catch (Exception e) {
			ReferenceData.getInstance().getAutonomousVariableData().setCollectorIn(1500);
		}
		try {
			ReferenceData.getInstance().getAutonomousVariableData().setPauseAfterCollectorIn((int)IRSTable.getNumber(SmartDashRef.PAUSE_AFTER_COLLECTOR_IN_INPUT));
		} catch (Exception e) {
			ReferenceData.getInstance().getAutonomousVariableData().setPauseAfterCollectorIn(600);
		}
		try {
			ReferenceData.getInstance().getAutonomousVariableData().setSpeed(IRSTable.getNumber(SmartDashRef.SPEED_INPUT));
		} catch (Exception e) {
			ReferenceData.getInstance().getAutonomousVariableData().setSpeed(.7);
		}
	}
}
