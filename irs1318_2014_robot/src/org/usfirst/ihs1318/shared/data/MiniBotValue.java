package org.usfirst.ihs1318.shared.data;

//Checked for NPEs. Only uses primitive types.
public class MiniBotValue {
	boolean deployState;
	
	public boolean getState(){
		return deployState;
	}
	public void setState(boolean state){
		this.deployState = state;
	}
	public void changeState(){
		if(getState() == true){
			this.deployState = false;
		}else{
			this.deployState = true;
		}
	}
	public void copyValues(MiniBotValue dest) {
		if (dest == null)
			throw new RuntimeException("dest must not be null.");
		dest.setState(getState());
	}
}
