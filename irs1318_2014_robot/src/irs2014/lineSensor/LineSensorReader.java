package irs2014.lineSensor;

import edu.wpi.first.wpilibj.DigitalInput;
import irs2014.components.RobotComponentBase;
import irs2014.dipSwitch.DipSwitchRef;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;

public class LineSensorReader extends RobotComponentBase {
	private DigitalInput lineSensor;
	
	public void robotInit() {
		lineSensor = getNewLineSensor();
	}
	
	public DigitalInput getNewLineSensor() {
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT) {
			return new DigitalInput(PortRef.DIGITAL_IO, PortRef.COMPETITION_LINE_SENSOR_PORT);
		} else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT) {
			return new DigitalInput(PortRef.DIGITAL_IO, PortRef.PRACTICE_LINE_SENSOR_PORT);
		}
		return null;
	}
}
