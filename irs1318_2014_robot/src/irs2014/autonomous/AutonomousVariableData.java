package irs2014.autonomous;

public class AutonomousVariableData {
	private int shiftWaitTime;
	private double distance;
	private int pauseAfterDriving;
	private int pauseAfterShot1;
	private int collectorIn;
	private int pauseAfterCollectorIn;
	private double speed;
	
	public AutonomousVariableData() {
		shiftWaitTime = 1000;
		distance = 15 * 12; //168;
		pauseAfterDriving = 1000;
		pauseAfterShot1 = 1200;
		collectorIn = 1500;
		pauseAfterCollectorIn = 600;
		speed = .7;
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
	
	
}
