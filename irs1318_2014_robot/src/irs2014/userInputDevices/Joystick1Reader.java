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
		joystick = getNewJoystick();//new Joystick(PortRef.JOYSTICK_1);
	}
	
	public Joystick getNewJoystick(){
		return new Joystick(PortRef.JOYSTICK_1);
	}

	public void teleopPeriodic() {
		joystickX = JoystickFilter.applyLinearDeadBand(joystick.getX(),0.1);
		joystickY = -JoystickFilter.applyLinearDeadBand(joystick.getY(),0.1);
		
		//Theoretical Throttle
		
		//TODO X and Y were switched on hardware, switched in software
//		ReferenceData.getInstance().getUserInputData().setJoystickY(-joystickX);
//		ReferenceData.getInstance().getUserInputData().setJoystickX(-joystickY);
		
//		System.out.println("joystick Y: " + joystickY);
//		System.out.println("joystick X: " + joystickX);
		
		//TODO X and Y were switched on hardware, switched in software
		ReferenceData.getInstance().getUserInputData().setJoystickY(joystickX);
		ReferenceData.getInstance().getUserInputData().setJoystickX(joystickY);
		
		ReferenceData.getInstance().getUserInputData().setFire(joystick.getRawButton(PortRef.FIRE));
		ReferenceData.getInstance().getUserInputData().setExtendCollector(joystick.getRawButton(PortRef.EXTEND_COLLECTOR));
		
		//ReferenceData.getInstance().getUserInputData().setGoForward(joystick.getRawButton(PortRef.GO_FORWARD));
		ReferenceData.getInstance().getUserInputData().setGoForward(getGoForward());
	}
	
	public boolean getGoForward() {
		return joystick.getRawButton(PortRef.GO_FORWARD);
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
