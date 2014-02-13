package irs2014.userInputDevices2;

import edu.wpi.first.wpilibj.Joystick;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import irs2014.generalOperations.JoystickFilter;

public class Joystick1Reader2 extends RobotComponentBase {
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
//		System.out.println("joystickX: " + joystickX);
//		System.out.println("joystickY: " + joystickY);
		
		//Theoretical Throttle
		
		//TODO X and Y were switched on hardware, switched in software
//		ReferenceData.getInstance().getUserInputData().setJoystickY(-joystickX);
//		ReferenceData.getInstance().getUserInputData().setJoystickX(-joystickY);
		
//		System.out.println("joystick Y: " + joystickY);
//		System.out.println("joystick X: " + joystickX);
		
		//TODO X and Y were switched on hardware, switched in software
		ReferenceData.getInstance().getUserInputData2().setJoystickY(joystickX);
		ReferenceData.getInstance().getUserInputData2().setJoystickX(joystickY);
		
		ReferenceData.getInstance().getUserInputData2().setExtendCollector(getExtendCollector());
		ReferenceData.getInstance().getUserInputData2().setRetractCollector(getRetractCollector());
		ReferenceData.getInstance().getUserInputData2().setCollectorMotorIn(getCollectorMotorIn());
		ReferenceData.getInstance().getUserInputData2().setCollectorMotorOut(getCollectorMotorOut());
		ReferenceData.getInstance().getUserInputData2().setStopCollectorMotor(getStopCollectorMotor());
//		ReferenceData.getInstance().getUserInputData().setExtendAllShooterSolenoids(getExtendAllPistons());
		ReferenceData.getInstance().getUserInputData2().setExtendAllShooterSolenoids(getTrigger());
		ReferenceData.getInstance().getUserInputData2().setRetractShooter(joystick.getRawButton(2));
//		ReferenceData.getInstance().getUserInputData().setTrigger(getTrigger());
//		ReferenceData.getInstance().getUserInputData().setExtendBoth(getExtendBoth());
		ReferenceData.getInstance().getUserInputData2().setExtendShooterAngle(getExtendBoth());
		ReferenceData.getInstance().getUserInputData2().setRetractShooterAngle(getRetractBoth());
//		ReferenceData.getInstance().getUserInputData().setRetractBoth(getRetractBoth());
		ReferenceData.getInstance().getUserInputData2().setShooterStep(getShooterStep());

//		ReferenceData.getInstance().getUserInputData().setShooterPulse(getShooterPulse());
		
		ReferenceData.getInstance().getUserInputData2().setGoForward(getGoForward());
//		ReferenceData.getInstance().getUserInputData().setCollectBall(getCollectBall());
//		ReferenceData.getInstance().getUserInputData().setEjectBall(getEjectBall());
	}
	
	private boolean getShooterStep() {
		return joystick.getRawButton(ButtonRef2.STEP_SHOOTER);
	}

	private boolean getRetractBoth() {
		return joystick.getRawButton(ButtonRef2.RETRACT_BOTH);
	}

	private boolean getExtendBoth() {
		return joystick.getRawButton(ButtonRef2.EXTEND_BOTH);
	}

	private boolean getTrigger() {
		return joystick.getRawButton(ButtonRef2.TRIGGER);
	}

	public boolean getGoForward() {
		return joystick.getRawButton(ButtonRef2.GO_FORWARD);
	}
	
	public boolean getExtendCollector(){
		return joystick.getRawButton(ButtonRef2.EXTEND_COLLECTOR);
	}
	
	public boolean getRetractCollector(){
		return joystick.getRawButton(ButtonRef2.RETRACT_COLLECTOR);
	}
	
	public boolean getCollectorMotorIn(){
		return joystick.getRawButton(ButtonRef2.COLLECTOR_MOTOR_IN);
	}
	
	public boolean getCollectorMotorOut(){
		return joystick.getRawButton(ButtonRef2.COLLECTOR_MOTOR_OUT);
	}
	
	public boolean getStopCollectorMotor(){
		return joystick.getRawButton(ButtonRef2.STOP_COLLECTOR_MOTOR);
	}
	
//	public boolean getCollectBall(){
//		return joystick.getRawButton(ButtonRef.COLLECT_BALL);
//	}
	
//	public boolean getEjectBall(){
//		return joystick.getRawButton(ButtonRef.EJECT_BALL);
//	}
	
//	public boolean getExtendAllPistons() {
//		return joystick.getRawButton(ButtonRef.EXTEND_ALL_SHOOTER_SOLENOIDS);
//	}
	
//	public boolean getExtendAllShooterSolenoids() {
//		return joystick.getRawButton(ButtonRef.EXTEND_ALL_SHOOTER_SOLENOIDS);
//	}
//	
//	public boolean getExtendInnerShooterSolenoids() {
//		return joystick.getRawButton(ButtonRef.EXTEND_INNER_SHOOTER_SOLENOIDS);
//	}
//	
//	public boolean getExtendInnerThreeShooterSolenoids() {
//		return joystick.getRawButton(ButtonRef.EXTEND_INNER_THREE_SHOOTER_SOLENOIDS);
//	}
//	
//	public boolean getExtendMiddleShooterSolenoid() {
//		return joystick.getRawButton(ButtonRef.EXTEND_MIDDLE_SHOOTER_SOLENOID);
//	}
//	
//	public boolean getShooterPulse() {
//		return joystick.getRawButton(ButtonRef.SHOOTER_PULSE);
//	}
//	
//	public boolean getRetractShooter() {
//		return joystick.getRawButton(ButtonRef.RETRACT_SHOOTER);
//	}
//	
//	public boolean getExtendShooterAngle() {
//		return joystick.getRawButton(ButtonRef.EXTEND_SHOOTER_ANGLE);
//	}
//	
//	public boolean getRetractShooterAngle() {
//		return joystick.getRawButton(ButtonRef.RETRACT_SHOOTER_ANGLE);
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
