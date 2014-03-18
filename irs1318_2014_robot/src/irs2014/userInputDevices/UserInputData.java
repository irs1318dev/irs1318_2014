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
	private boolean shoot3Pistons;
	private boolean shoot4Pistons;
	private boolean shootPulse;
	private boolean shoot1Piston;
	
	private boolean extendShooterAngle; 
	private boolean retractShooterAngle; 
	
	private boolean extendBoth;
	private boolean retractBoth;
	
	//macros
	private boolean goForward;
	private boolean collectBall;
	private boolean ejectBall;
	private boolean clam;
	private boolean unClam;
	
	private boolean boost;


	private int triggerSet;
	
	
	
	public boolean getIsActive(){
//		return ((joystickX != 0) || (joystickY != 0));
		return extendCollector || retractCollector || collectorMotorIn || collectorMotorOut || shoot5Pistons ||
				shoot3Pistons || shoot4Pistons || extendShooterAngle || retractShooterAngle || boost;
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
//		if(!extendShooterAngle){
//			System.out.println("set extendShooterAngle to false");
//		}
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

	public boolean getShoot3Pistons() {
		return shoot3Pistons;
	}

	public void setShoot3Pistons(boolean shoot3Pistons) {
		this.shoot3Pistons = shoot3Pistons;
	}

	public boolean getShoot4Pistons() {
		return shoot4Pistons;
	}

	public void setShoot4Pistons(boolean shoot4Pistons) {
		this.shoot4Pistons = shoot4Pistons;
	}

	public boolean getShootPulse() {
		return shootPulse;
	}

	public void setShootPulse(boolean shootPulse) {
		this.shootPulse = shootPulse;
	}

	public boolean getShoot1Piston() {
		return shoot1Piston;
	}

	public void setShoot1Piston(boolean shoot1Piston) {
		this.shoot1Piston = shoot1Piston;
	}

	public boolean getClam() {
		if(clam){
			System.out.println("got clam while true");
		}
		return clam;
	}

	public void setClam(boolean clam) {
		this.clam = clam;
	}

	public boolean getUnClam() {
		return unClam;
	}

	public void setUnClam(boolean unClam) {
		this.unClam = unClam;
	}
	
	public boolean getBoost() {
		return boost;
	}

	public void setBoost(boolean boost) {
		this.boost = boost;
	}
}
