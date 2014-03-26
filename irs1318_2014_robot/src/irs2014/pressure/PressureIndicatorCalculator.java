package irs2014.pressure;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;

public class PressureIndicatorCalculator extends RobotComponentBase {
	
	public void teleopPeriodic() {
		double pressure = ReferenceData.getInstance().getAnalogPressureSensorData().getPressure();
		if (pressure <= 45 && pressure >= 30) {
			ReferenceData.getInstance().getPressureIndicatorData().setUseFivePiston(true);
			ReferenceData.getInstance().getPressureIndicatorData().setNotEnoughPressure(false);
		} else if (pressure < 30) {
			ReferenceData.getInstance().getPressureIndicatorData().setUseFivePiston(false);
			ReferenceData.getInstance().getPressureIndicatorData().setNotEnoughPressure(true);
		} else {
			ReferenceData.getInstance().getPressureIndicatorData().setUseFivePiston(false);
			ReferenceData.getInstance().getPressureIndicatorData().setNotEnoughPressure(false);
		}
	}
}
