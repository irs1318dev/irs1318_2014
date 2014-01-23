package org.usfirst.ihs1318.shared.data;

//Checked for NPEs. Only uses primitive types.
public class ArmEncoderValues {
	private double encoderValue;
	private double encoderRate;

	public double getEncoderValue() {
		return encoderValue;
	}

	public void setEncoderValue(double encoderValue) {
		this.encoderValue = encoderValue;
	}

	public double getEncoderRate() {
		return encoderRate;
	}

	public void setEncoderRate(double encoderRate) {
		this.encoderRate = encoderRate;
	}
	
	public void setAll(double encoderValue, double encoderRate){
		setEncoderValue(encoderValue);
		setEncoderRate(encoderRate);
	}

	public void copyValues(ArmEncoderValues dest) {
		if (dest==null) 
			throw new RuntimeException("dest must not be null.");
		dest.setAll(this.encoderValue, this.encoderRate);
	}
}
