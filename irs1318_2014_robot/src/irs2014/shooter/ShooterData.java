package irs2014.shooter;

public class ShooterData {
	
	private boolean currentShooterState;
	private boolean desiredShooterState;
	private int numPistons;
	private boolean pulse;
	private boolean prePulse;
	
	private int pulseExtendTime;
	private int pulseRetractTime;
	private int pulseState;
	private int counter;
	private boolean nextSetShoot;
	
	public static final int PRE_PULSE = 0;
	public static final int EXTEND_1 = 1;
	public static final int RETRACT = 2;
	public static final int EXTEND_2 = 3;
	public static final int AFTER = 4;
	
	private long timeLastShot;
	public static final long SHOT_INTERVAL = 400000;
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
	public boolean getPrePulse() {
		return prePulse;
	}
	public void setPrePulse(boolean prePulse) {
		this.prePulse = prePulse;
	}
	public int getPulseExtendTime() {
		return pulseExtendTime;
	}
	public void setPulseExtendTime(int pulseExtendTime) {
		this.pulseExtendTime = pulseExtendTime;
	}
	public int getPulseRetractTime() {
		return pulseRetractTime;
	}
	public void setPulseRetractTime(int pulseRetractTime) {
		this.pulseRetractTime = pulseRetractTime;
	}
	public int getPulseState() {
		return pulseState;
	}
	public void setPulseState(int pulseState) {
		this.pulseState = pulseState;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public boolean getNextSetShoot() {
		return nextSetShoot;
	}
	public void setNextSetShoot(boolean nextSetShoot) {
		this.nextSetShoot = nextSetShoot;
	}
	
}
