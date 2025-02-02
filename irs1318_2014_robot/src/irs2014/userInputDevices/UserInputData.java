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
	
	private boolean shoot5Pistons;
	private boolean retract5Pistons;
	
	private boolean extendShooterAngle; 
	private boolean retractShooterAngle; 
	
	private boolean extendBoth;
	private boolean retractBoth;
	
	//macros
	private boolean goForward;
	private boolean collectBall;
	private boolean ejectBall;
	
	private int triggerSet;
	
	
	
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
	
	public int getUserInputType() {
		return userInputType;
	}

	public void setUserInputType(int userInputType) {
		this.userInputType = userInputType;
	}

	public int getTriggerSet() {
		return triggerSet;
	}

	public void setTriggerSet(int triggerSet) {
		this.triggerSet = triggerSet;
	}

	public boolean getExtendBoth() {
		return extendBoth;
	}

	public void setExtendBoth(boolean extendBoth) {
		this.extendBoth = extendBoth;
	}

	public boolean getRetractBoth() {
		return retractBoth;
	}

	public void setRetractBoth(boolean retractBoth) {
		this.retractBoth = retractBoth;
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
	
	public boolean getExtendShooterAngle() {
		return extendShooterAngle;
	}

	public void setExtendShooterAngle(boolean extendShooterAngle) {
		this.extendShooterAngle = extendShooterAngle;
	}

	public boolean getRetractShooterAngle() {
		return retractShooterAngle;
	}

	public void setRetractShooterAngle(boolean retractShooterAngle) {
		this.retractShooterAngle = retractShooterAngle;
	}

	public boolean getShoot5Pistons() {
		return shoot5Pistons;
	}

	public void setShoot5Pistons(boolean shoot5Pistons) {
		this.shoot5Pistons = shoot5Pistons;
	}

	public boolean getRetract5Pistons() {
		return retract5Pistons;
	}

	public void setRetract5Pistons(boolean retract5Pistons) {
		this.retract5Pistons = retract5Pistons;
	}
}
