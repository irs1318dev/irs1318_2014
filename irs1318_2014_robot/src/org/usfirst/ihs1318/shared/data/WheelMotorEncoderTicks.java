package org.usfirst.ihs1318.shared.data;

/**
 * This class is used to measure DISTANCE. If you want rates, use WheelMotorEncoderRates.
 * 
 * Stores a double value of encoder ticks for each of the four wheels.
 * 
 * @author Patrick
 *
 */
//Checked for NPEs. Only uses primitive types.
public class WheelMotorEncoderTicks {
	double rf;
	double lf;
	double lr;
	double rr;
	

	public void copyValues(WheelMotorEncoderTicks dest) {
		if (dest==null) throw new RuntimeException("dest must not be null.");
		dest.setLf(this.getLf());
		dest.setRf(this.getRf());
		dest.setLr(this.getLr());
		dest.setRr(this.getRr());
	}
	
	public boolean equals(Object that){
		if(!(that instanceof WheelMotorEncoderTicks))
			return false;
		double eps = .01;
		return compareDoubles(getLf(), ((WheelMotorEncoderTicks) that).getLf(), eps) 
			&& compareDoubles(getRf(), ((WheelMotorEncoderTicks) that).getRf(), eps)
			&& compareDoubles(getLr(), ((WheelMotorEncoderTicks) that).getLr(), eps)
			&& compareDoubles(getRr(), ((WheelMotorEncoderTicks) that).getRr(), eps);
		
	}
	

	public boolean isLessThan(WheelMotorEncoderTicks that) {
		return getLf() < that.getLf() 
			&& getRf() < that.getRf()
			&& getLr() < that.getLr()
			&& getRr() < that.getRr();
	}
	
	public boolean isGreaterThan(WheelMotorEncoderTicks that) {
		return getLf() > that.getLf() 
			&& getRf() > that.getRf()
			&& getLr() > that.getLr()
			&& getRr() > that.getRr();
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
	
	public double getRf() {
		return rf;
	}
	public void setRf(double rf) {
		this.rf = rf;
	}
	public double getLf() {
		return lf;
	}
	public void setLf(double lf) {
		this.lf = lf;
	}
	public double getLr() {
		return lr;
	}
	public void setLr(double lr) {
		this.lr = lr;
	}
	public double getRr() {
		return rr;
	}
	public void setRr(double rr) {
		this.rr = rr;
	}

	public void add(WheelMotorEncoderTicks tickChanges) {
		this.setAll(getLf() + tickChanges.getLf(), 
				getRf() + tickChanges.getRf(),
				getLr() + tickChanges.getLr(),
				getRr() + tickChanges.getRr());	
	}

	public double getMin() {
		//find minimum value
		double smallOne = Math.min(Math.abs(getLf()), Math.abs(getRf()));
		double smallTwo = Math.min(Math.abs(getLr()), Math.abs(getRr()));
		double absMin = Math.min(smallOne, smallTwo);
		
		//return the correct value
		if(absMin == Math.abs(getLf()))
			return getLf();
		if(absMin == Math.abs(getLr()))
			return getLr();
		if(absMin == Math.abs(getRf()))
			return getRf();
		if(absMin == Math.abs(getRr()))
			return getRr();
		
		return 0;
	}

	public double getMax() {
		//find minimum value
		double bigOne = Math.max(Math.abs(getLf()), Math.abs(getRf()));
		double bigTwo = Math.max(Math.abs(getLr()), Math.abs(getRr()));
		double absMax = Math.max(bigOne, bigTwo);
		
		//return the correct value
		if(absMax == Math.abs(getLf()))
			return getLf();
		if(absMax == Math.abs(getLr()))
			return getLr();
		if(absMax == Math.abs(getRf()))
			return getRf();
		if(absMax == Math.abs(getRr()))
			return getRr();
		
		return 0;
	}

	public double getAverage() {
		//TODO verify the signs for the averages... 
		// left and right are inverted, based on telemetry data 
		return (-getLf() - getLr() + getRf() + getRr()) / 4;
	}

	public WheelMotorEncoderTicks subtract(WheelMotorEncoderTicks tickChanges) {
		WheelMotorEncoderTicks subtractedTicks = new WheelMotorEncoderTicks();
		subtractedTicks.setAll(getLf() - tickChanges.getLf(), 
				getRf() - tickChanges.getRf(),
				getLr() - tickChanges.getLr(),
				getRr() - tickChanges.getRr());	
		return subtractedTicks;
	}

	
}
