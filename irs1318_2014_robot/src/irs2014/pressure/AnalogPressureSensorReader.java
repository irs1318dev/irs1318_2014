package irs2014.pressure;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import irs2014.generalOperations.AnalogPressureSensor;

public class AnalogPressureSensorReader extends RobotComponentBase{
	
	private AnalogPressureSensor sensor;
	private final int MAX_PSI = 150;
	
	public void robotInit(){
		sensor = new AnalogPressureSensor(PortRef.ANALOG_MODULE, PortRef.ANALOG_PRESSURE_SENSOR, MAX_PSI);
	}
	
	public void teleopPeriodic(){
		ReferenceData.getInstance().getAnalogPressureSensorData().setPressure(sensor.getPSI());
	}

}
