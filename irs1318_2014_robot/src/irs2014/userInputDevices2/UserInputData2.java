package irs2014.userInputDevices2;

public class UserInputData2 {
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
	private boolean trigger;
	private boolean extendBoth;
	private boolean retractBoth;
	private boolean shooterStep;
	private boolean shooterPulse;
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
	public boolean getShooterPulse() {
		return shooterPulse;
	}
	public void setShooterPulse(boolean value) {
		shooterPulse = value; 
	}
	
	public int getUserInputType() {
		return userInputType;
	}

	public void setUserInputType(int userInputType) {
		this.userInputType = userInputType;
	}

	public boolean getTrigger() {
		return trigger;
	}

	public void setTrigger(boolean trigger) {
		this.trigger = trigger;
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

	public boolean getShooterStep() {
		return shooterStep;
	}

	public void setShooterStep(boolean shooterStep) {
		this.shooterStep = shooterStep;
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
