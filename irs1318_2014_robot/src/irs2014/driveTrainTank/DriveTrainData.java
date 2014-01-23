package irs2014.driveTrainTank;

public class DriveTrainData {
	
	private double rightEncoder, leftEncoder;
	private double rightEncoderTicks, leftEncoderTicks;
	private double rightSpeedSetPoint, leftSpeedSetPoint;	//read from joystick
															//-1 to +1 
	private double rightPIDSpeed, leftPIDSpeed;
	
	public double getRightEncoderTicks() {
		return rightEncoderTicks;
	}

	public void setRightEncoderTicks(double val) {
		this.rightEncoderTicks = val;
	}

	public double getLeftEncoderTicks() {
		return leftEncoderTicks;
		
	}

	public void setLeftEncoderTicks(double val) {
		this.leftEncoderTicks = val;
	}

	public double getRightSpeedSetPoint(){
		return rightSpeedSetPoint;
	}
	
	public void setRightSpeedSetPoint(double val){
		rightSpeedSetPoint = val;
	}
	
	public double getLeftSpeedSetPoint(){
		return leftSpeedSetPoint;
	}
	
	public void setLeftSpeedSetPoint(double val){
		leftSpeedSetPoint = val;
	}
	
	public double getRightPIDSpeed(){
		return rightPIDSpeed;
	}
	
	public void setRightPIDSpeed(double val){
		rightPIDSpeed = val;
	}
	
	public double getLeftPIDSpeed(){
		return leftPIDSpeed;
	}
	
	public void setLeftPIDSpeed(double val){
		leftPIDSpeed = val;
	}
	
	public double getRightEncoder(){
		return rightEncoder;
	}
	
	public void setRightEncoder(double val){
		rightEncoder = val;
	}
	
	public double getLeftEncoder(){
		return leftEncoder;
	}
	
	public void setLeftEncoder(double val){
		leftEncoder = val;
	}
}