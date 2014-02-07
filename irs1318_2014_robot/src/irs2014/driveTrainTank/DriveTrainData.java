package irs2014.driveTrainTank;

public class DriveTrainData {
	private EncoderData rightEncoderData = new EncoderData();
	private EncoderData leftEncoderData = new EncoderData();
	
	private PIDData rightPIDData = new PIDData();
	private PIDData leftPIDData = new PIDData();
	
	public EncoderData getRightEncoderData (){
		return rightEncoderData;
	}
	
	public EncoderData getLeftEncoderData(){
		return leftEncoderData;
	}
	
	public PIDData getRightPIDData(){
		return rightPIDData;
	}
	
	public PIDData getLeftPIDData(){
		return leftPIDData;
	}
}