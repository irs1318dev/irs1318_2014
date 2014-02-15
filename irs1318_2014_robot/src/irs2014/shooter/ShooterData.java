package irs2014.shooter;

public class ShooterData {
	
	private boolean currentShooterState;
	private boolean desiredShooterState;
	private int numPistons;
	private boolean pulse;
	
	private long timeLastShot;
	public static final long SHOT_INTERVAL = 1000000;
	public static final long PULSE_INTERVAL = 50000;
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
	public int getNumPistons(){
		return numPistons;
	}
	public void setNumPistons(int num){
		this.numPistons = num;
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
	public boolean getPulse() {
		return pulse;
	}
	public void setPulse(boolean pulse) {
		this.pulse = pulse;
	}
	
}
