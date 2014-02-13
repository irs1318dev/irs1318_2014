package irs2014.shooter;

public class ShooterData {
	
	private boolean currentShooterState;
	private boolean desiredShooterState;
	
	private boolean currentShooterAngle;
	private boolean desiredShooterAngle;
	
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
		this.desiredShooterAngle = desiredShooterAngle;
	}
	
}
