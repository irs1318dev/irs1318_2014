package irs2014.userInputDevices;

import edu.wpi.first.wpilibj.Joystick;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import irs2014.generalOpperations.JoystickFilter;

public class Joystick1Reader extends RobotComponentBase {
	Joystick joystick;
	
	private double joystickX;
	private double joystickY;
	
	public void robotInit() {
		joystick = new Joystick(PortRef.JOYSTICK);
	}

	public void teleopPeriodic() {
		joystickX = JoystickFilter.applyLinearDeadBand(joystick.getX(),0.1);
		joystickY = -JoystickFilter.applyLinearDeadBand(joystick.getY(),0.1);
		
		//Theoretical Throttle
		
		//TODO X and Y were switched on hardware, switched in software
//		ReferenceData.getInstance().getUserInputData().setJoystickY(-joystickX);
//		ReferenceData.getInstance().getUserInputData().setJoystickX(-joystickY);
		
		//TODO X and Y were switched on hardware, switched in software
		ReferenceData.getInstance().getUserInputData().setJoystickY(joystickX);
		ReferenceData.getInstance().getUserInputData().setJoystickX(joystickY);
	}
	
	public double applyLinearDeadBand(double x, double band) {
		double output = 0;
		if (Math.abs(x) < band){
			output = 0;
		} else if (x <= -band) {
			output = (x + band)/(1-band);
			
		} else if (x >= band) {
			output = (x - band)/(1-band);
		}
		return output;
	}
}
