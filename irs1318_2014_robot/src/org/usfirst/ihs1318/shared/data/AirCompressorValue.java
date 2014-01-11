package org.usfirst.ihs1318.shared.data;

//Checked for NPEs. Only uses primitive types.
public class AirCompressorValue {
	boolean airCompressorState;
	
	public boolean getState(){
		return airCompressorState;
	}
	public void setState(boolean state){
		this.airCompressorState = state;
	}
	public void copyValues(AirCompressorValue dest){
		if (dest == null)
			throw new RuntimeException("dest must not be null.");
		dest.setState(getState());
	}
}
