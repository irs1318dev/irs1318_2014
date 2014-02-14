package irs2014.shooter;

public class AngleData {

	private boolean currentShooterAngle;
	private boolean desiredShooterAngle;
	
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
	
}
