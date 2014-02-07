package irs2014.driveTrainTank;

public class EncoderData {
	private double ticks;
	private double velocity;
	
	public double getTicks(){
		return ticks;
	}
	
	public void setTicks(double ticks){
		this.ticks = ticks;
	}
	
	public double getVelocity(){
		return velocity;
	}
	
	public void setVelocity(double velocity){
		this.velocity = velocity;
	}
}
