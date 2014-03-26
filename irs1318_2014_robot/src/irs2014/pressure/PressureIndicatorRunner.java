package irs2014.pressure;

import edu.wpi.first.wpilibj.DigitalOutput;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;

public class PressureIndicatorRunner extends RobotComponentBase {
	private DigitalOutput useFivePistonLight;
	private DigitalOutput notEnoughPressureLight;

	public void robotInit() {
		useFivePistonLight = getNewUseFivePistonLight();
		notEnoughPressureLight = getNewNotEnoughPressureLight();
	}
	
	public void teleopPeriodic() {
		getUseFivePistonLight().set(ReferenceData.getInstance().getPressureIndicatorData().getUseFivePiston());
		getNotEnoughPressureLight().set(ReferenceData.getInstance().getPressureIndicatorData().getNotEnoughPressure());
	}
	
	public DigitalOutput getUseFivePistonLight() {
		return useFivePistonLight;
	}

	public DigitalOutput getNotEnoughPressureLight() {
		return notEnoughPressureLight;
	}

	public DigitalOutput getNewUseFivePistonLight() {
		return new DigitalOutput(PortRef.DIGITAL_IO, PortRef.COMPETITION_USE_FIVE_PISTON_INDICATOR_LIGHT_PORT);
	}
	
	public DigitalOutput getNewNotEnoughPressureLight() {
		return new DigitalOutput(PortRef.DIGITAL_IO, PortRef.COMPETITION_NOT_ENOUGH_PRESSURE_INDICATOR_LIGHT_PORT);
	}
}
