package irs2014.collector;

import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.DigitalInput;

public class CollectorLimitSwitchReader {
	private DigitalInput collectorLimitSwitch;
	
	public void robotInit() {
		collectorLimitSwitch = new DigitalInput(PortRef.DIGITAL_IO, PortRef.COLLECTOR_LIMIT_SWITCH_PORT);
	}
	
	public void teleopPeriodic() {
		ReferenceData.getInstance().getCollectorData().getLimitSwitchData().setBallPresent(getCollectorLimitSwitch().get());
	}
	
	public DigitalInput getCollectorLimitSwitch() {
		return collectorLimitSwitch;
	}
}
