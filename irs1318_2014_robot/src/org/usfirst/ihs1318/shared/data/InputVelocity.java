package org.usfirst.ihs1318.shared.data;

//Checked for NPEs. Only uses primitive types.
public class InputVelocity {

	private double desiredJx;
	private double desiredJy;
	private double desiredJRY;
	private double desiredW;

	public boolean equals(Object that) {
		if(!(that instanceof InputVelocity))
			return false;
		double epsilon = .01;
		boolean equal = compareDoubles(this.desiredJRY, ((InputVelocity)that).desiredJRY, epsilon)
			&& compareDoubles(this.desiredJx, ((InputVelocity)that).desiredJx, epsilon)
			&& compareDoubles(this.desiredJy, ((InputVelocity)that).desiredJy, epsilon)
			&& compareDoubles(this.desiredW, ((InputVelocity)that).desiredW, epsilon);
		return equal;
	}
	
	private boolean compareDoubles(double x, double y, double epsilon) {
		return Math.abs(x - y) < epsilon;
	}
	
	public double getDesiredJx() {
		return desiredJx;
	}
	public double getDesiredJRY() {
		return desiredJRY;
	}
	public void setDesiredJx(double desiredJx) {
		this.desiredJx = desiredJx;
	}
	public double getDesiredJy() {
		return desiredJy;
	}
	public void setDesiredJy(double desiredJy) {
		this.desiredJy = desiredJy;
	}
	public void setDesiredJRY(double desiredJRY) {
		this.desiredJRY= desiredJRY;
	}
	public double getDesiredW() {
		return desiredW;
	}
	public void setDesiredW(double desiredW) {
		this.desiredW = desiredW;
	}
	
	public void setAll(double desiredJX, double desiredJy, double desiredJRy, double desiredW) {
		this.setDesiredJRY(desiredJRy);
		this.setDesiredJx(desiredJX);
		this.setDesiredJy(desiredJy);
		this.setDesiredW(desiredW);
	}
	
	public void copyValues(InputVelocity dest) {
		if (dest==null) throw new RuntimeException("dest must not be null.");
		dest.setDesiredJx(this.getDesiredJx());
		dest.setDesiredJy(this.getDesiredJy());
		dest.setDesiredW(this.getDesiredW());
		dest.setDesiredJRY(this.getDesiredJRY());
	}

	
	//TODO:  why are getMaxVelocity and getDeltaTime in this data transfer class ?
	/**
	 * TODO Make this method work by fitting an equation to it.
	 * 
	 * The fastest speed will be straight forward. The slowest speeds will be at the 45 degree angles.
	 * Not really sure about anything in between o.O
	 * 
	 * @param takes a direction in radians, where 0 is forward, pi/2 is left, pi is back, and 3pi/2 is right
	 * @returns maximum velocity in direction in feet/second
	 */
	public static double getMaxVelocity(double direction) {
		double maxSpeed = 10.0;//ft/sec
		return maxSpeed;		
	}
	
	/**
	 * TODO Make this method return the speed that the jaguars can make the motors run, about 20 Hertz
	 * 
	 * @return delta time between actuators (in seconds)
	 */
	public static double getDeltaTime() {
		double deltaTimeBetweenActuators = .05; //we need the hertz of the actuators
		return deltaTimeBetweenActuators;
	}

}
