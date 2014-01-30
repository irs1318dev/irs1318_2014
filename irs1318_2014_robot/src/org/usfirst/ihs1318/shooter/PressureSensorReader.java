package org.usfirst.ihs1318.shooter;

import edu.wpi.first.wpilibj.DigitalInput;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import org.usfirst.ihs1318.shooter.PressureSensorData;;

public class PressureSensorReader {
	
	DigitalInput pressureSensor;
	
	public void robotInit() {
		pressureSensor = new DigitalInput(PortRef.PRESSURE_SENSOR);
		System.out.println("PressureSensor robotInit()");
	}
	
	public void teleopPeriodic() {
		boolean value = pressureSensor.get();
		ReferenceData.getInstance().getPressureSensorData().setIsPressurized(value);
		System.out.println("pressureSensor= " + value + ", isPressurized=" + ReferenceData.getInstance().getPressureSensorData().getIsPressurized());
	}

}
