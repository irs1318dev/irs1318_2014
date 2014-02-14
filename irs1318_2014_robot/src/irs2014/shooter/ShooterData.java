package irs2014.shooter;

public class ShooterData {
	
	private boolean currentShooterState;
	private boolean desiredShooterState;
	
	private boolean currentShooterAngle;
	private boolean desiredShooterAngle;
	
	private long timeLastShot;
	public static final long SHOT_INTERVAL = 300000000;
	private boolean inShot;
	
	public boolean getCurrentShooterState() {
		return currentShooterState;
	}
	public void setCurrentShooterState(boolean currentShooterState) {
		this.currentShooterState = currentShooterState;
	}
	public boolean getDesiredShooterState() {
		return desiredShooterState;
	}
	public void setDesiredShooterState(boolean desiredShooterState) {
		this.desiredShooterState = desiredShooterState;
	}
	public boolean getCurrentShooterAngle() {
		return currentShooterAngle;
	}
	public void setCurrentShooterAngle(boolean currentShooterAngle) {
		this.currentShooterAngle = currentShooterAngle;
	}
	public boolean getDesiredShooterAngle() {
		return desiredShooterAngle;
	}
	public void setDesiredShooterAngle(boolean desiredShooterAngle) {
		System.out.println("desiredShooterAngle = " + desiredShooterAngle);
		this.desiredShooterAngle = desiredShooterAngle;
	}
	public long getTimeLastShot() {
		return timeLastShot;
	}
	public void setTimeLastShot(long d) {
		this.timeLastShot = d;
	}
	public boolean getInShot() {
		return inShot;
	}
	public void setInShot(boolean inShot) {
		this.inShot = inShot;
	}
	
}
