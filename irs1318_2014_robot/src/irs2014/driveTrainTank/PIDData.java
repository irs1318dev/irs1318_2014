package irs2014.driveTrainTank;

public class PIDData {
	private double velocitySetpoint;
	private double positionSetpoint;
	private double PIDVelocity;
	
	public double getVelocitySetpoint(){
		return velocitySetpoint;
	}
	
	public void setVelocitySetpoint(double velocitySetpoint){
		this.velocitySetpoint = velocitySetpoint;
	}
	
	public double getPositionSetpoint(){
		return positionSetpoint;
	}
	
	public void setPostionSetpoint(double positionSetpoint){
		this.positionSetpoint = positionSetpoint;
	}
	
	public double getPIDVelocity(){
		return PIDVelocity;
	}
	
	public void setPIDVelocity(double PIDSpeed){
		this.PIDVelocity = PIDSpeed;
	}
}
