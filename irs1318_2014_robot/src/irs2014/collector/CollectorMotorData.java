package irs2014.collector;

public class CollectorMotorData {
	private double collectorMotorSpeed;
	
	
	public double getCollectorMotorSpeed(){
		return collectorMotorSpeed;
	}
	
	
	public void setCollectorMotorIn(){
		collectorMotorSpeed = CollectorRef.IN * CollectorRef.MOTOR_SPEED;
	}
	
	public void setCollectorMotorOut(){
		collectorMotorSpeed = CollectorRef.OUT * CollectorRef.MOTOR_SPEED;
	}
	
	public void setCollectorMotorOff(){
		collectorMotorSpeed = 0;
	}
}
