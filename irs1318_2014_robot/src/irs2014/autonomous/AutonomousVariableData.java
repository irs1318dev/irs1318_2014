package irs2014.autonomous;

public class AutonomousVariableData {
	private int shiftWaitTime;
	private double distance;
	private int pauseAfterDriving;
	private int pauseAfterShot1;
	private int collectorIn;
	private int pauseAfterCollectorIn;
	private double speed;
	private int numPistons;
	
	private int collectorWaitTime;
	private int shoulderWaitTime;
	
	public AutonomousVariableData() {
		shiftWaitTime = 1000;
		distance = 15 * 12; //168;
		pauseAfterDriving = 1000;
		pauseAfterShot1 = 1200;
		collectorIn = 1500;
		pauseAfterCollectorIn = 600;
		speed = .7;
		numPistons = 4;
		
		collectorWaitTime = 600;
		shoulderWaitTime = 400;
	}
	
	public int getShiftWaitTime() {
		return shiftWaitTime;
	}
	public void setShiftWaitTime(int shiftWaitTime) {
		this.shiftWaitTime = shiftWaitTime;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public int getPauseAfterDriving() {
		return pauseAfterDriving;
	}
	public void setPauseAfterDriving(int pauseAfterDriving) {
		this.pauseAfterDriving = pauseAfterDriving;
	}
	public int getPauseAfterShot1() {
		return pauseAfterShot1;
	}
	public void setPauseAfterShot1(int pauseAfterShot1) {
		this.pauseAfterShot1 = pauseAfterShot1;
	}
	public int getCollectorIn() {
		return collectorIn;
	}
	public void setCollectorIn(int collectorIn) {
		this.collectorIn = collectorIn;
	}
	public int getPauseAfterCollectorIn() {
		return pauseAfterCollectorIn;
	}
	public void setPauseAfterCollectorIn(int pauseAfterCollectorIn) {
		this.pauseAfterCollectorIn = pauseAfterCollectorIn;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed; 
	}

	public int getNumPistons() {
		return numPistons;
	}

	public void setNumPistons(double d) {
		if((d == 4) || (d == 5)){
			this.numPistons = (int)d;
		}
	}

	public int getCollectorWaitTime() {
		return collectorWaitTime;
	}

	public void setCollectorWaitTime(int collectorWaitTime) {
		this.collectorWaitTime = collectorWaitTime;
	}

	public int getShoulderWaitTime() {
		return shoulderWaitTime;
	}

	public void setShoulderWaitTime(int shoulderWaitTime) {
		this.shoulderWaitTime = shoulderWaitTime;
	}
	
	
}
