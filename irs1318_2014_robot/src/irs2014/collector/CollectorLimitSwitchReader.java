package irs2014.collector;

import irs2014.components.RobotComponentBase;
import irs2014.dipSwitch.DipSwitchRef;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.DigitalInput;

public class CollectorLimitSwitchReader extends RobotComponentBase{
	private DigitalInput collectorLimitSwitch;
	
	public void robotInit() {
		collectorLimitSwitch = getNewCollectorLimitSwitch();
	}
	
	public void teleopPeriodic() {
		ReferenceData.getInstance().getCollectorData().getLimitSwitchData().setBallPresent(getCollectorLimitSwitch().get());
	}
	
	public DigitalInput getCollectorLimitSwitch() {
		return collectorLimitSwitch;
	}
	
	public DigitalInput getNewCollectorLimitSwitch() {
//		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT) {
//			collectorLimitSwitch = new DigitalInput(PortRef.DIGITAL_IO, PortRef.COMPETITION_COLLECTOR_LIMIT_SWITCH_PORT);
//		} else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT) {
//			collectorLimitSwitch = new DigitalInput(PortRef.DIGITAL_IO, PortRef.PRACTICE_COLLECTOR_LIMIT_SWITCH_PORT);
//		}
		return new DigitalInput(PortRef.DIGITAL_IO, PortRef.PRACTICE_COLLECTOR_LIMIT_SWITCH_PORT);
//		return null;
	}
}
