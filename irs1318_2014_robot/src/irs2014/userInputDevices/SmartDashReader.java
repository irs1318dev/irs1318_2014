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
			ReferenceData.getInstance().getAutonomousVariableData()
		}
	}
}
