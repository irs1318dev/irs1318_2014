package irs2014.pressure;
import edu.wpi.first.wpilibj.Utility;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;

public class PressureSensorTimerRunner extends RobotComponentBase {
	
	public void teleopPeriodic() {
		if (ReferenceData.getInstance().getPressureSensorTimerData().getShouldStartTimer()) {
			ReferenceData.getInstance().getPressureSensorTimerData().setTimerStartTime(getFPGATime());
		}
		if(ReferenceData.getInstance().getPressureSensorTimerData().getShouldRestartTimer()){
			ReferenceData.getInstance().getPressureSensorTimerData().setTimerStartTime(getFPGATime());
			ReferenceData.getInstance().getPressureSensorTimerData().setTimerTime(0);
		}
		if (!ReferenceData.getInstance().getPressureSensorTimerData().getShouldStopTimer()) {
			ReferenceData.getInstance().getPressureSensorTimerData().setTimerTime(getFPGATime() - ReferenceData.getInstance().getPressureSensorTimerData().getTimerStartTime());
//			System.out.println(ReferenceData.getInstance().getPressureSensorTimerData().getTimerTime());
		} else if (ReferenceData.getInstance().getPressureSensorTimerData().getShouldStopTimer()) {
			ReferenceData.getInstance().getPressureSensorTimerData().setTimerTime(0);
		}
	}
	
	public long getFPGATime() {
		return Utility.getFPGATime();
	}
}
