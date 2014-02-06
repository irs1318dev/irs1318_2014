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
		this.desiredMiddleSolenoidState = desiredMiddleSolenoidState; 
	}
	
	public void setDesiredInnerSolenoidsState(boolean desiredInnerSolenoidsState) {
		this.desiredInnerSolenoidsState = desiredInnerSolenoidsState; 
	}
	
	public void setDesiredOuterSolenoidsState(boolean desiredOuterSolenoidsState) {
		this.desiredOuterSolenoidsState = desiredOuterSolenoidsState; 
	}
	
	public void setDesiredShooterAngleSolenoidState(boolean desiredShooterAngleSolenoidState) {
		this.desiredShooterAngleSolenoidState = desiredShooterAngleSolenoidState; 
	}
	
}
