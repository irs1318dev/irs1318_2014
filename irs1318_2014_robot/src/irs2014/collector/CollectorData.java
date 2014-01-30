package irs2014.collector;

public class CollectorData {
	CollectorMotorData motorData = new CollectorMotorData();
	CollectorSolenoidData pistonData = new CollectorSolenoidData();
	CollectorLimitSwitchData limitSwitchData = new CollectorLimitSwitchData();
	
	public CollectorMotorData getMotorData (){
		return motorData;
	}
	
	public CollectorSolenoidData getPistonData (){
		return pistonData;
	}
	
	public CollectorLimitSwitchData getLimitSwitchData() {
		return limitSwitchData;
	}
}
