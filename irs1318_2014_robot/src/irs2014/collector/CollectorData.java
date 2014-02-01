package irs2014.collector;

public class CollectorData {
	CollectorMotorData motorData = new CollectorMotorData();
	CollectorSolenoidData solenoidData = new CollectorSolenoidData();
	CollectorLimitSwitchData limitSwitchData = new CollectorLimitSwitchData();
	
	public CollectorMotorData getMotorData (){
		return motorData;
	}
	
	public CollectorSolenoidData getSolenoidData (){
		return solenoidData;
	}
	
	public CollectorLimitSwitchData getLimitSwitchData() {
		return limitSwitchData;
	}
}
