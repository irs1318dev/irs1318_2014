package irs2014.lineSensor;

import irs2014.driveTrainTank.EncoderData;

public class LineSensorData {
	
	private boolean isOnLine;
	private double onLineLeftTick;
	private double onLineRightTick; 
	public boolean getIsOnLine() {
		return isOnLine;
	}
	
	public double getOnLineLeftTick() {
		return onLineLeftTick; 
	}
	
	public double getOnLineRightTick() {
		return onLineRightTick; 
	}
	
	public void setIsOnLine(boolean isOnLine) {
		this.isOnLine = isOnLine;
	}
	
	public void setOnLineLeftTick(double onLineLeftTick) {
		this.onLineLeftTick = onLineLeftTick; 
	}
	
	public void setOnLineRightTick(double onLineRightTick) {
		this.onLineRightTick = onLineRightTick; 
	}

}
