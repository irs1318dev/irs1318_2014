package org.usfirst.ihs1318.shared.data;

//Checked for NPEs. Only uses primitive types.
public class ArmInput {	
	private double desiredJy;
	private int desiredSetPoint;
	
	public double getDesiredJy() {
		return desiredJy;
	}
	public void setDesiredJy(double desiredJy) {
		this.desiredJy = desiredJy;
	}
	public int getDesiredSetPoint() {
		return desiredSetPoint;
	}
	public void setDesiredSetPoint(int desiredSetPoint) {
		this.desiredSetPoint = desiredSetPoint;
	}
	
	public void setAll(double desiredJy, int desiredSetPoint) {
		setDesiredJy(desiredJy);
		setDesiredSetPoint(desiredSetPoint);
	}
	
	public void copyValuesTo(ArmInput dest) {
		if (dest==null) throw new RuntimeException("dest must not be null.");
		dest.setAll(getDesiredJy(), getDesiredSetPoint());
	}
	
}
