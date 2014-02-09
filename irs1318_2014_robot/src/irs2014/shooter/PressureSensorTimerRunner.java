package irs2014.shooter;
import edu.wpi.first.wpilibj.Utility;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;

public class PressureSensorTimerRunner extends RobotComponentBase {
	
	public void teleopPeriodic() {
		if (ReferenceData.getInstance().getPressureSensorTimerData().getShouldStartTimer()) {
			ReferenceData.getInstance().getPressureSensorTimerData().setTimerStartTime(getFPGATime());
		}
		if (ReferenceData.getInstance().getPressureSensorTimerData().getShouldStopTimer() == false) {
			ReferenceData.getInstance().getPressureSensorTimerData().setTimerTime(getFPGATime() - ReferenceData.getInstance().getPressureSensorTimerData().getTimerStartTime());
		} else if (ReferenceData.getInstance().getPressureSensorTimerData().getShouldStopTimer()) {
			ReferenceData.getInstance().getPressureSensorTimerData().setTimerTime(0);
		}
	}
	
	public long getFPGATime() {
		return Utility.getFPGATime();
	}
}
