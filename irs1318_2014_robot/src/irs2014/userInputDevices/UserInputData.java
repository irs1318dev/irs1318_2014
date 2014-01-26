package irs2014.userInputDevices;

public class UserInputData {
	private int userInputType;
	//joysticks
	private double joystickX;
	private double joystickY;
	//regular buttons 
	private boolean fire;
	private boolean extendLoader;
	//macros
	private boolean goForward;
	
	public boolean getIsActive(){
		return fire || extendLoader || (joystickX != 0) || (joystickY != 0); 
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
	
	public boolean getExtendLoader(){
		return extendLoader;
	}
	
	public void setExtendLoader(boolean value){
		extendLoader = value;
	}
	
	public boolean getGoForward(){
		return goForward;
	}
	
	public void setGoForward(boolean value){
		goForward = value;
	}
	
}
