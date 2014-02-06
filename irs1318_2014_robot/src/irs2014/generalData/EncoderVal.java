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
	
	/**
	 * returns ticks traveled from the start of the reference.
	 */
	public double getTicksTraveled()
	{
		calculateCenterOfTicks();
		return distaneTraveled;
	}
	
	private void calculateCenterOfTicks()
	{
		double difLeft = startLeft - ReferenceData.getInstance().getDriveTrainData().getLeftEncoder();
		double difRight = startRight - ReferenceData.getInstance().getDriveTrainData().getRightEncoder();
		distaneTraveled = (difLeft + difRight) / 2.0;
	}

}
