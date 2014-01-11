package org.usfirst.ihs1318.shared.data;

//Checked for NPEs. Only uses primitive types.
public class ClawValue {
	boolean clawState;
	
	public boolean getState(){
		return clawState;
	}
	public void setState(boolean state){
		this.clawState = state;
	}
	public void copyValues(ClawValue dest){
		if (dest == null)
			throw new RuntimeException("dest must not be null.");
		dest.setState(getState());
	}
}
