package irs2014.shooter;

import org.usfirst.ihs1318.shared.ReferenceData;
import irs2014.components.RobotComponentBase;
import edu.wpi.first.wpilibj.*;

public class PressureSensorReader extends RobotComponentBase {
	DigitalInput pressureSensor;
	
	public void robotInit() {
		pressureSensor = new Dig
				
				
				
				italInput(PressureSensorRef.PRESSURE_SENSOR);//PressureSensorRef.PRESSURE_SENSOR == -1? null : new DigitalInput(PressureSensorRef.PRESSURE_SENSOR);
		System.out.println("PressureSensor robotInit()");
	}
	
	public void teleopPeriodic() {
		
		boolean value = pressureSensor.get();
		ReferenceData.getInstance().getPressureSensorData().setIsPressurized(value);
		System.out.println("pressureSensor="+value+",isPressurized=" + ReferenceData.getInstance().getPressureSensorData().getIsPressurized());
	}
}




































































































