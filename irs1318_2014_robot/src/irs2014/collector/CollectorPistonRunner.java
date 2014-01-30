package irs2014.collector;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class CollectorPistonRunner extends RobotComponentBase {
	private DoubleSolenoid collectorPiston;
	
	public void robotInit(){
		collectorPiston = new DoubleSolenoid(PortRef.DIGITAL_IO, PortRef.COLLECTOR_PISTON);
	}
	
	public void teleopPeriodic(){
		collectorPiston.set(ReferenceData.getInstance().getCollectorData().getPistonData().getPistonState());
	}

}
