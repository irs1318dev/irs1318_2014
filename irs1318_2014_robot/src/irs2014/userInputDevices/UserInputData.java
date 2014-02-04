package irs2014.userInputDevices;

public class UserInputData {
	private int userInputType;
	//joysticks
	private double joystickX;
	private double joystickY;
	//regular buttons 
	private boolean extendCollector; 
	private boolean retractCollector;
	private boolean collectorMotorIn;
	private boolean collectorMotorOut;
	private boolean stopCollectorMotor;
	private boolean extendAllShooterSolenoids; 
	private boolean extendInnerShooterSolenoids;
	private boolean extendThreeShooterSolenoids;
	private boolean extendMiddleShooterSolenoid; 
	//macros
	private boolean goForward;
	private boolean collectBall;
	private boolean ejectBall;
	
	
	
	public boolean getIsActive(){
		return ((joystickX != 0) || (joystickY != 0) || goForward); 
	}
	
	public double getJoystickX(){
		return joystickX;
	}

	public void setJoystickX(double joystickX){
		this.joystickX = joystickX;
//		System.out.println("UserInputData set joystickX to: " + joystickX);
	}

	public double getJoystickY(){
		return joystickY;
	}

	public void setJoystickY(double joystickY){
		this.joystickY = joystickY;
	}
	
	public boolean getExtendCollector(){
		return extendCollector;
	}
	
	public void setExtendCollector(boolean value){
		extendCollector = value;
	}
	
	public boolean getGoForward(){
		return goForward;
	}
	
	public void setGoForward(boolean value){
		goForward = value;
	}

	public boolean getRetractCollector(){
		return retractCollector;
	}
	public void setRetractCollector(boolean value){
		retractCollector = value;
	}
	public boolean getCollectorMotorIn(){
		return collectorMotorIn;
	}
	public void setCollectorMotorIn(boolean value){
		collectorMotorIn = value;
	}
	public boolean getCollectorMotorOut(){
		return collectorMotorOut;
	}
	public void setCollectorMotorOut(boolean value){
		collectorMotorOut = value;
	}
	public boolean getStopCollectorMotor(){
		return stopCollectorMotor;
	}
	public void setStopCollectorMotor(boolean value){
		stopCollectorMotor = value;
	}
	public boolean getExtendAllShooterSolenoids() {
		return extendAllShooterSolenoids;
	}
	public void setExtendAllShooterSolenoids(boolean value) {
		extendAllShooterSolenoids = value; 
	}
	public boolean getExtendInnerShooterSolenoids() {
		return extendInnerShooterSolenoids; 
	}
	
	public boolean getCollectBall(){
		return collectBall;
	}
	
	public void setCollectBall (boolean value){
		collectBall = value;
	}
	
	public boolean getEjectBall(){
		return ejectBall;
	}
	
	public void setEjectBall (boolean value){
		ejectBall = value;
	}
}
