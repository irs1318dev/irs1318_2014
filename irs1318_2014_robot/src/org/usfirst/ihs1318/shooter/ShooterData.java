package org.usfirst.ihs1318.shooter;

public class ShooterData {
	
	private boolean middleSolenoidState;
	private boolean innerSolenoidsState; 
	private boolean outerSolenoidsState;
	private boolean shooterAngleSolenoidState; 
	
	public boolean getMiddleSolenoidState() {
		return middleSolenoidState;
	}
	
	public boolean getInnerSolenoidsState() {
		return innerSolenoidsState;
	}
	
	public boolean getOuterSolenoidsState() {
		return outerSolenoidsState; 
	}
	
	public boolean getShooterAngleSolenoidState() {
		return shooterAngleSolenoidState;
	}
	
	public void setMiddleSolenoidState(boolean middleSolenoidState) {
		this.middleSolenoidState = middleSolenoidState; 
	}
	
	public void setInnerSolenoidsState(boolean innerSolenoidsState) {
		this.innerSolenoidsState = innerSolenoidsState;
	}
	public void setOuterSolenoidsState (boolean outerSolenoidsState) {
		this.outerSolenoidsState = outerSolenoidsState;
	}
	
	public void setShooterAngleSolenoidState(boolean shooterAngleSolenoidState) {
		this.shooterAngleSolenoidState = shooterAngleSolenoidState; 
	}
	
}
