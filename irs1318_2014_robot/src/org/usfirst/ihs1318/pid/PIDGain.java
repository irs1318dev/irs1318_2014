package org.usfirst.ihs1318.pid;

//Checked for NPE. Only uses primitives.
public class PIDGain {
	private double kd;
	private double ki;
	private double kp;
	public double getKd() {
		return kd;
	}
	public void setKd(double kd) {
		this.kd = kd;
	}
	public double getKi() {
		return ki;
	}
	public void setKi(double ki) {
		this.ki = ki;
	}
	public double getKp() {
		return kp;
	}
	public void setKp(double kp) {
		this.kp = kp;
	}
	public void setAll(double kP, double kD, double kI) {
		setKp(kP);
		setKd(kD);
		setKi(kI);
		
	}
	

}
