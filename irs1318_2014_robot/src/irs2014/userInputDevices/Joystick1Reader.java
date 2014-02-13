package irs2014.userInputDevices;

import edu.wpi.first.wpilibj.Joystick;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import irs2014.generalOperations.JoystickFilter;

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
		joystickX = JoystickFilter.applyLinearDeadBand(joystick.getX(), 0.1);
		joystickY = -JoystickFilter.applyLinearDeadBand(joystick.getY(), 0.1);

		ReferenceData.getInstance().getUserInputData().setJoystickY(joystickX);
		ReferenceData.getInstance().getUserInputData().setJoystickX(joystickY);
		
		ReferenceData.getInstance().getUserInputData().setExtendCollector(getExtendCollector());
		ReferenceData.getInstance().getUserInputData().setRetractCollector(getRetractCollector());
		ReferenceData.getInstance().getUserInputData().setCollectorMotorIn(getCollectorMotorIn());
		ReferenceData.getInstance().getUserInputData().setCollectorMotorOut(getCollectorMotorOut());
//		ReferenceData.getInstance().getUserInputData().setStopCollectorMotor(getStopCollectorMotor());
		ReferenceData.getInstance().getUserInputData().setRetractShooterAngle(getRetractShooterAngle());
		ReferenceData.getInstance().getUserInputData().setExtendShooterAngle(getExtendShooterAngle());
		
		ReferenceData.getInstance().getUserInputData().setShoot5Pistons(getShoot5Pistons());
		ReferenceData.getInstance().getUserInputData().setRetract5Pistons(getRetract5Pistons());
		
		ReferenceData.getInstance().getUserInputData().setGoForward(getGoForward());
	}
	
	private boolean getShoot5Pistons(){
		return joystick.getRawButton(ButtonRef.SHOOT_5_PISTONS);
	}
	
	private boolean getRetract5Pistons(){
		return joystick.getRawButton(ButtonRef.RETRACT_5_PISTONS);
	}
	
	private boolean getShooterStep() {
		return joystick.getRawButton(ButtonRef.STEP_SHOOTER);
	}
	
	private boolean getExtendShooterAngle(){
		return joystick.getRawButton(ButtonRef.RETRACT_SHOOTER_ANGLE);
	}
	
	private boolean getRetractShooterAngle(){
		return joystick.getRawButton(ButtonRef.RETRACT_SHOOTER_ANGLE);
	}

	private boolean getTrigger() {
		return joystick.getRawButton(ButtonRef.TRIGGER);
	}

	public boolean getGoForward() {
		return joystick.getRawButton(ButtonRef.GO_FORWARD);
	}
	
	public boolean getExtendCollector(){
		return joystick.getRawButton(ButtonRef.EXTEND_COLLECTOR);
	}
	
	public boolean getRetractCollector(){
		return joystick.getRawButton(ButtonRef.RETRACT_COLLECTOR);
	}
	
	public boolean getCollectorMotorIn(){
		return joystick.getRawButton(ButtonRef.COLLECTOR_MOTOR_IN);
	}
	
	public boolean getCollectorMotorOut(){
		return joystick.getRawButton(ButtonRef.COLLECTOR_MOTOR_OUT);
	}
	
//	public boolean getStopCollectorMotor(){
//		return joystick.getRawButton(ButtonRef.STOP_COLLECTOR_MOTOR);
//	}

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
