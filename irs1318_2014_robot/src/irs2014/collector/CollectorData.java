package irs2014.collector;

public class CollectorData {
	CollectorMotorData motorData = new CollectorMotorData();
	CollectorPistonData pistonData = new CollectorPistonData();
	
	public CollectorMotorData getMotorData (){
		return motorData;
	}
	
	public CollectorPistonData getPistonData (){
		return pistonData;
	}
}
