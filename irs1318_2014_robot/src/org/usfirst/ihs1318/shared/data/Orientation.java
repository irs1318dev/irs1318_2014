package org.usfirst.ihs1318.shared.data;

//Checked for NPEs. Only uses primitive types.
public class Orientation {
	private double theta;
	private boolean gyroActive;

	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}
	
	public void copyValues(Orientation dest) {
		if (dest==null) throw new RuntimeException("dest must not be null.");
		dest.setTheta(this.theta);
	
	}

	public boolean isGyroActive() {
		return gyroActive;
	}

	public void setGyroActive(boolean gyroActive) {
		this.gyroActive = gyroActive;
	}
	

}
