package irs2014.shooter;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import irs2014.components.RobotComponentBase;
import irs2014.dipSwitch.DipSwitchData;
import irs2014.dipSwitch.DipSwitchRef;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;

public class PressureSensorReader extends RobotComponentBase{
	
	DigitalInput pressureSensor;
	
	public void robotInit() {
		pressureSensor = getPressureSensor();
		System.out.println("PressureSensor robotInit()");
	}
	
	public void teleopPeriodic() {
		boolean value = getPressureSensor().get();
		ReferenceData.getInstance().getPressureSensorData().setIsPressurized(value);
		System.out.println("pressureSensor= " + value + ", isPressurized=" + ReferenceData.getInstance().getPressureSensorData().getIsPressurized());
	}
	
	public DigitalInput getPressureSensor() {
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT){
			return pressureSensor = new DigitalInput(PortRef.COMPETITION_PRESSURE_SENSOR);
		}else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT){
			return pressureSensor = new DigitalInput(PortRef.DIGITAL_IO, PortRef.PRACTICE_PRESSURE_SENSOR);
		}
		return null;
	}
}
