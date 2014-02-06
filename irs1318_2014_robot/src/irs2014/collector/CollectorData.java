package irs2014.collector;

public class CollectorData {
	private CollectorMotorData motorData = new CollectorMotorData();
	private CollectorSolenoidData solenoidData = new CollectorSolenoidData();
	private CollectorLimitSwitchData limitSwitchData = new CollectorLimitSwitchData();
	
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
