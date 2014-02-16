package irs2014.pressure;

import irs2014.generalData.ReferenceData;
import irs2014.components.RobotComponentBase;

public class PressureSensorCalculator extends RobotComponentBase {
	public void teleopPeriodic() {
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized()) {
			ReferenceData.getInstance().getPressureSensorTimerData().setShouldStopTimer(false);
			if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() != ReferenceData.getInstance().getPressureSensorData().getWasPressurized()) {
				ReferenceData.getInstance().getPressureSensorTimerData().setShouldStartTimer(true);
//				System.out.println("pressure sensor became true");
			} else if (ReferenceData.getInstance().getPressureSensorData().getIsPressurized() == ReferenceData.getInstance().getPressureSensorData().getWasPressurized()) {
				ReferenceData.getInstance().getPressureSensorTimerData().setShouldStartTimer(false);
			}
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() == false) {
			ReferenceData.getInstance().getPressureSensorTimerData().setShouldStopTimer(true);
//			System.out.println("pressure sensor is false");
		}
		
	}
}
