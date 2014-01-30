package irs2014.collector;

public class CollectorData {
	CollectorMotorData motorData = new CollectorMotorData();
	CollectorSolenoidData pistonData = new CollectorSolenoidData();
	
	public CollectorMotorData getMotorData (){
		return motorData;
	}
	
	public CollectorSolenoidData getPistonData (){
		return pistonData;
	}
}
