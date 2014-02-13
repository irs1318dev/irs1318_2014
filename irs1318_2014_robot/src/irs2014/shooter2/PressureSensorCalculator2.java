package irs2014.shooter2;

import irs2014.generalData.ReferenceData;
import irs2014.components.RobotComponentBase;

public class PressureSensorCalculator2 extends RobotComponentBase {
	public void teleopPeriodic() {
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized()) {
			ReferenceData.getInstance().getPressureSensorTimerData().setShouldStopTimer(false);
			if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() != ReferenceData.getInstance().getPressureSensorData().getWasPressurized()) {
				ReferenceData.getInstance().getPressureSensorTimerData().setShouldStartTimer(true);
			} else if (ReferenceData.getInstance().getPressureSensorData().getIsPressurized() == ReferenceData.getInstance().getPressureSensorData().getWasPressurized()) {
				ReferenceData.getInstance().getPressureSensorTimerData().setShouldStartTimer(false);
			}
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() == false) {
			ReferenceData.getInstance().getPressureSensorTimerData().setShouldStopTimer(true);
		}
		
	}
}
