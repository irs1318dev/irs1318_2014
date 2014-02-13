package irs2014.userInputDevices2;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;
import edu.wpi.first.wpilibj.Joystick;

public class JoystickPortTest2 extends RobotComponentBase{
	
	Joystick joystick1;
	Joystick joystick2;
	
	public void robotInit(){
		joystick1 = new Joystick(PortRef.JOYSTICK_1);
		joystick2 = new Joystick(PortRef.JOYSTICK_2);
	}
	
	public void teleopPeriodic(){
		System.out.println("joystick port 1 (y) = " + joystick1.getY());
		System.out.println("joystick port 2 (y) = " + joystick2.getY());
	}

}
