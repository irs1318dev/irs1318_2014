package org.usfirst.ihs1318.shared.data;

/**
 * 
 * Contains the actual RATE of wheel rotation for each of the four wheels.
 * 
 * @author Patrick
 *
 */
//Checked for NPEs. Only uses primitive types.
public class WheelMotorEncoderRates {
	
	private double lf;
	private double lr;
	private double rf;
	private double rr;
	
	public double getLf() {
		return lf;
	}
	public void setLf(double leftFront) {
		this.lf = leftFront;
	}
	public double getLr() {
		return lr;
	}
	public void setLr(double leftRear) {
		this.lr = leftRear;
	}
	public double getRf() {
		return rf;
	}
	public void setRf(double rightFront) {
		this.rf = rightFront;
	}
	public double getRr() {
		return rr;
	}
	public void setRr(double rightRear) {
		this.rr = rightRear;
	}

	public void copyValues(WheelMotorEncoderRates dest) {
		if (dest==null) throw new RuntimeException("dest must not be null.");
		dest.setLf(this.getLf());
		dest.setRf(this.getRf());
		dest.setLr(this.getLr());
		dest.setRr(this.getRr());
	}
	
	public boolean equals(Object that){
		if(!(that instanceof WheelMotorEncoderRates))
			return false;
		double eps = .01;
		return compareDoubles(getLf(), ((WheelMotorEncoderRates) that).getLf(), eps) 
			&& compareDoubles(getRf(), ((WheelMotorEncoderRates) that).getRf(), eps)
			&& compareDoubles(getLr(), ((WheelMotorEncoderRates) that).getLr(), eps)
			&& compareDoubles(getRr(), ((WheelMotorEncoderRates) that).getRr(), eps);
		
	}
	private boolean compareDoubles(double x, double y, double eps){
		return Math.abs(x - y) <= eps;
	}
	public void setAll(double lf, double rf, double lr, double rr) {
		setLf(lf);
		setRf(rf);
		setLr(lr);
		setRr(rr);
		
	}
	public void add(double numToAdd) {
		setAll(getLf() + numToAdd, getRf() + numToAdd, getLr() + numToAdd, getRr() + numToAdd); 
		
	}
}
