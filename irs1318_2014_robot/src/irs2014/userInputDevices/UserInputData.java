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
	private boolean extendInnerThreeShooterSolenoids;
	private boolean extendMiddleShooterSolenoid; 
	private boolean retractShooter; 
	private boolean extendShooterAngle; 
	private boolean retractShooterAngle; 
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
	public void setExtendInnerShooterSolenoids(boolean value) {
		extendInnerShooterSolenoids = value; 
	}
	public boolean getExtendInnerThreeShooterSolenoids() {
		return extendInnerThreeShooterSolenoids;
	}
	public void setExtendInnerThreeShooterSolenoids(boolean value) {
		extendInnerThreeShooterSolenoids = value; 
	}
	public boolean getExtendMiddleShooterSolenoid() {
		return extendMiddleShooterSolenoid;
	}
	public void setExtendMiddleShooterSolenoid(boolean value) {
		extendMiddleShooterSolenoid = value; 
	}
	public boolean getRetractShooter() {
		return retractShooter;
	}
	public void setRetractShooter(boolean value) {
		retractShooter = value; 
	}
	public boolean getExtendShooterAngle() {
		return extendShooterAngle;
	}
	public void setExtendShooterAngle(boolean value) {
		extendShooterAngle = value; 
	}
	public boolean getRetractShooterAngle() {
		return retractShooterAngle; 
	}
	public void setRetractShooterAngle(boolean value) {
		retractShooterAngle = value; 
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
