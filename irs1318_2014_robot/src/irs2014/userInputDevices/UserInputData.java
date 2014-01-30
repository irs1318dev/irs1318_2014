package irs2014.userInputDevices;

public class UserInputData {
	private int userInputType;
	//joysticks
	private double joystickX;
	private double joystickY;
	//regular buttons 
	private boolean fire;
	private boolean extendCollector; //I changed this from extendLoader to extendCollector to be the same as the package for that component, same for getter/setter.
	//Grant's stuff, could be totally wrong.
	private boolean retractCollector;
	private boolean motorInCollector;
	private boolean motorOutCollector;
	private boolean motorStopCollector;
	//macros
	private boolean goForward;
	
	public boolean getIsActive(){
		return fire || extendCollector || (joystickX != 0) || (joystickY != 0); 
	}
	
	public double getJoystickX(){
		return joystickX;
	}

	public void setJoystickX(double joystickX){
		this.joystickX = joystickX;
	}

	public double getJoystickY(){
		return joystickY;
	}

	public void setJoystickY(double joystickY){
		this.joystickY = joystickY;
	}
	
	public boolean getFire(){
		return fire;
	}
	
	public void setFire(boolean value){
		fire = value;
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
	// Also Grant's stuff, ditto.
	public boolean getRetractCollector(){
		return retractCollector;
	}
	public void setRetractCollector(boolean value){
		retractCollector = value;
	}
	public boolean getMotorInCollector(){
		return motorInCollector;
	}
	public void setMotorInCollector(boolean value){
		motorInCollector = value;
	}
	public boolean getMotorOutCollector(){
		return motorOutCollector;
	}
	public void setMotorOutCollector(boolean value){
		motorOutCollector = value;
	}
	public boolean getMotorStopCollector(){
		return motorStopCollector;
	}
	public void setMotorStopCollector(boolean value){
		motorStopCollector = value;
	}
}
