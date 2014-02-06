package irs2014.collector;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;

public class CollectorSolenoidRunner extends RobotComponentBase {
	private DoubleSolenoid collectorSolenoid;
	
	public void robotInit(){
		collectorSolenoid = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.COLLECTOR_EXTENDER_SOLENOID_PORT, PortRef.COLLECTOR_RETRACTOR_SOLENOID_PORT);
	}
	
	public void teleopPeriodic(){
		if (ReferenceData.getInstance().getCollectorData().getSolenoidData().getDesiredSolenoidState() != ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState()) {
			if (ReferenceData.getInstance().getCollectorData().getSolenoidData().getDesiredSolenoidState() == CollectorRef.EXTEND) {
				getCollectorSolenoid().set(Value.kForward);
			} else if (ReferenceData.getInstance().getCollectorData().getSolenoidData().getDesiredSolenoidState() == CollectorRef.RETRACT) {
				getCollectorSolenoid().set(Value.kReverse);
			}
		}
	}
	
	public DoubleSolenoid getCollectorSolenoid(){
		return collectorSolenoid;
	}

}
