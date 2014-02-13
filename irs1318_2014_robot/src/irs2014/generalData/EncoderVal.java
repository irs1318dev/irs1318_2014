package irs2014.generalData;

public class EncoderVal 
{
	private double startLeft;
	private double startRight;
	
	private double distaneTraveled;
	
	private EncoderVal(){ }
	public EncoderVal(double startLeft, double startRight)
	{
		this.startLeft = startLeft;
		this.startRight = startRight;
	}
	
	public double getStartLeft()
	{
		return startLeft;
	}
	
	public double getStartRight()
	{
		return startRight;
	}
	
	/*
	private void calculateCenterOfTicks()
	{
		double difLeft = startLeft - ReferenceData.getInstance().getDriveTrainData().getLeftEncoderData().getTicks();
		double difRight = startRight - ReferenceData.getInstance().getDriveTrainData().getRightEncoderData().getTicks();
		distaneTraveled = (difLeft + difRight) / 2.0;
	}
	*/
}
