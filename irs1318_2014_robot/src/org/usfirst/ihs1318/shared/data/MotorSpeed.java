package org.usfirst.ihs1318.shared.data;

//Checked for NPEs. Only uses primitive types.
public class MotorSpeed {
	private double lf;
	private double lr;
	private double rf;
	private double rr;
	
	// omega max
	private final double omegaMax = 1760.0;
	
	/**
	 * Convert motor speed (-1.0 to 1.0) into encoder value (radians / sec)
	 * @param speed
	 * @return 
	 */
	public double calculateWheelOmega(double speed) {
		return speed * getOmegaMax();
	}
	
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

	public double getOmegaMax() {
		return omegaMax;
	}
	public void copyValues(MotorSpeed dest) {
		if (dest==null) throw new RuntimeException("dest must not be null.");
		dest.setLf(this.getLf());
		dest.setRf(this.getRf());
		dest.setLr(this.getLr());
		dest.setRr(this.getRr());
	}


}
