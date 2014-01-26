package org.usfirst.frc1318.data;

import org.usfirst.frc1318.utils.Utils;

public class Position implements DataStructure {
	private double r;
	private double theta;
	
	public boolean copyTo(DataStructure otherData) {
		if(otherData == null)
			return false;
		if(otherData.getClass() != this.getClass())
			return false;
		Position that = (Position) otherData;
		that.setAll(r, theta);
		return true;
	}

	public void setAll(double r, double theta) {
		setR(r);
		setTheta(theta);
	}
	
	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(r);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(theta);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (!Utils.compareDoubles(r, other.r, .01))
			return false;
		if (!Utils.compareDoubles(theta, other.theta, .01))
			return false;
		return true;
	}
	
	

}
