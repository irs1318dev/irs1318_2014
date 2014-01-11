package org.usfirst.ihs1318.shared.data;

//Checked for NPEs. Only uses primitive types.
public class CameraFlipValue {

	boolean servoState;
	
	public boolean getState(){
		return servoState;
	}
	public void setState(boolean state){
		this.servoState = state;
	}
	public void changeState(){
		if(getState() == true){
			this.servoState = false;
		}else{
			this.servoState = true;
		}
	}
	public void copyValues(CameraFlipValue dest) {
		if (dest == null)
			throw new RuntimeException("dest must not be null.");
		dest.setState(getState());
	}
	public String getServoDisplay() {
		return (getState()?"Forward":"Backward");
	}
}
