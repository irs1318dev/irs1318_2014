package irs2014.collector;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import irs2014.components.RobotComponentBase;
import irs2014.dipSwitch.DipSwitchRef;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;

public class CollectorSolenoidRunner extends RobotComponentBase {
	private DoubleSolenoid collectorSolenoid;
	
	public void robotInit(){
		collectorSolenoid = getNewCollectorSolenoid();
	}
	
	public void teleopPeriodic(){
		if (ReferenceData.getInstance().getCollectorData().getSolenoidData().getDesiredSolenoidState() != ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState()) {
			if (ReferenceData.getInstance().getCollectorData().getSolenoidData().getDesiredSolenoidState() == CollectorRef.EXTEND) {
				getCollectorSolenoid().set(Value.kForward);
				ReferenceData.getInstance().getCollectorData().getSolenoidData().setCurrentSolenoidState(CollectorRef.EXTEND);
			} else if (ReferenceData.getInstance().getCollectorData().getSolenoidData().getDesiredSolenoidState() == CollectorRef.RETRACT) {
				getCollectorSolenoid().set(Value.kReverse);
				ReferenceData.getInstance().getCollectorData().getSolenoidData().setCurrentSolenoidState(CollectorRef.RETRACT);
			}
		}
	}
	
	public DoubleSolenoid getCollectorSolenoid(){
		return collectorSolenoid;
	}

	public DoubleSolenoid getNewCollectorSolenoid() {
//		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT) {
//			collectorSolenoid = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, PortRef.COMPETITION_COLLECTOR_EXTENDER_SOLENOID_PORT, PortRef.COMPETITION_COLLECTOR_RETRACTOR_SOLENOID_PORT);
//		} else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT) {
//			collectorSolenoid = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, PortRef.PRACTICE_COLLECTOR_EXTENDER_SOLENOID_PORT, PortRef.PRACTICE_COLLECTOR_RETRACTOR_SOLENOID_PORT);
//		}
//		return collectorSolenoid;
		return new DoubleSolenoid(/*PortRef.SOLENOID_MODULE_PORT_2*/1, PortRef.PRACTICE_COLLECTOR_EXTENDER_SOLENOID_PORT, PortRef.PRACTICE_COLLECTOR_RETRACTOR_SOLENOID_PORT);
	}
}
