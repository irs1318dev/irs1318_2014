package irs2014.shooter;

public class ShooterData {
	
	private boolean currentMiddleSolenoidState;
	private boolean currentInnerSolenoidsState; 
	private boolean currentOuterSolenoidsState;
	private boolean currentShooterAngleSolenoidState; 
	
	private boolean desiredMiddleSolenoidState;
	private boolean desiredInnerSolenoidsState;
	private boolean desiredOuterSolenoidsState;
	private boolean desiredShooterAngleSolenoidState; 
	
	private long pulseTime; //TODO set pulse time somewhere
	private long startTime; 
	private boolean isShooting; 
	
	public boolean getCurrentMiddleSolenoidState() {
		return currentMiddleSolenoidState;
	}
	
	public boolean getCurrentInnerSolenoidsState() {
		return currentInnerSolenoidsState;
	}
	
	public boolean getCurrentOuterSolenoidsState() {
		return currentOuterSolenoidsState; 
	}
	
	public boolean getCurrentShooterAngleSolenoidState() {
		return currentShooterAngleSolenoidState;
	}
	
	public boolean getDesiredMiddleSolenoidState() {
		return desiredMiddleSolenoidState;
	}
	
	public boolean getDesiredInnerSolenoidsState() {
		return desiredInnerSolenoidsState;
	}
	
	public boolean getDesiredOuterSolenoidsState() {
		return desiredOuterSolenoidsState;
	}
	
	public boolean getDesiredShooterAngleSolenoidState() {
		return desiredShooterAngleSolenoidState; 
	}
	
	public long getPulseTime() {
		return pulseTime; 
	}
	
	public long getStartTime() {
		return startTime;
	}
	
	public boolean getIsShooting() {
		return isShooting; 
	}
	
	public void setCurrentMiddleSolenoidState(boolean middleSolenoidState) {
		this.currentMiddleSolenoidState = middleSolenoidState; 
	}
	
	public void setCurrentInnerSolenoidsState(boolean innerSolenoidsState) {
		this.currentInnerSolenoidsState = innerSolenoidsState;
	}
	public void setCurrentOuterSolenoidsState (boolean outerSolenoidsState) {
		this.currentOuterSolenoidsState = outerSolenoidsState;
	}
	
	public void setCurrentShooterAngleSolenoidState(boolean shooterAngleSolenoidState) {
		this.currentShooterAngleSolenoidState = shooterAngleSolenoidState; 
	}
	
	public void setDesiredMiddleSolenoidState(boolean desiredMiddleSolenoidState) {
		setCurrentMiddleSolenoidState(this.desiredMiddleSolenoidState);
		this.desiredMiddleSolenoidState = desiredMiddleSolenoidState; 
	}
	
	public void setDesiredInnerSolenoidsState(boolean desiredInnerSolenoidsState) {
		setCurrentInnerSolenoidsState(this.desiredInnerSolenoidsState);
		this.desiredInnerSolenoidsState = desiredInnerSolenoidsState; 
	}
	
	public void setDesiredOuterSolenoidsState(boolean desiredOuterSolenoidsState) {
		setCurrentOuterSolenoidsState(this.desiredOuterSolenoidsState);
		this.desiredOuterSolenoidsState = desiredOuterSolenoidsState; 
	}
	
	public void setDesiredShooterAngleSolenoidState(boolean desiredShooterAngleSolenoidState) {
		setCurrentShooterAngleSolenoidState(this.desiredShooterAngleSolenoidState);
		this.desiredShooterAngleSolenoidState = desiredShooterAngleSolenoidState; 
	}
	
	public void setPulseTime(long pulseTime) {
		this.pulseTime = pulseTime; 
	}
	
	public void setStartTime(long startTime) {
		this.startTime = startTime; 
	}
	
	public void setIsShooting(boolean isShooting) {
		this.isShooting = isShooting; 
	}
	
}
