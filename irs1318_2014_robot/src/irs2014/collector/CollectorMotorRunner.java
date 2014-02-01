package irs2014.collector;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.Talon;

public class CollectorMotorRunner extends RobotComponentBase {
	private Talon collectorMotor;
	
	public void robotInit(){
		collectorMotor = new Talon (PortRef.SIDECAR_SLOT, PortRef.COLLECTOR_MOTOR);
	}
	
	public void teleopPeriodic(){
		collectorMotor.set(ReferenceData.getInstance().getCollectorData().getMotorData().getCollectorMotorSpeed());
	}
}
