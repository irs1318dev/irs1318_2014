package irs2014.collector;

import irs2014.components.RobotComponentBase;
import irs2014.dipSwitch.DipSwitchRef;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.Talon;

public class CollectorMotorRunner extends RobotComponentBase {
	private Talon collectorMotor;
	
	public void robotInit(){
		collectorMotor = getNewCollectorMotor();
	}
	
	public void teleopPeriodic(){
		getCollectorMotor().set(ReferenceData.getInstance().getCollectorData().getMotorData().getCollectorMotorSpeed());
	}
	
	public Talon getCollectorMotor() {
		return collectorMotor;
	}
	
	public Talon getNewCollectorMotor() {
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT) {
			collectorMotor = new Talon (PortRef.SIDECAR_SLOT, PortRef.COMPETITION_COLLECTOR_MOTOR);
		} else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT) {
			collectorMotor = new Talon(PortRef.SIDECAR_SLOT, PortRef.PRACTICE_COLLECTOR_MOTOR);
		}
		return collectorMotor;
	}
}
