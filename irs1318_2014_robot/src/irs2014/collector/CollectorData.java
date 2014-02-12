package irs2014.collector;

public class CollectorData {
	private CollectorMotorData motorData = new CollectorMotorData();
	private CollectorSolenoidData solenoidData = new CollectorSolenoidData();
	private CollectorLimitSwitchData limitSwitchData = new CollectorLimitSwitchData();
	
	public CollectorMotorData getMotorData (){
		if(motorData == null){
			motorData = new CollectorMotorData();
		}
		return motorData;
	}
	
	public CollectorSolenoidData getSolenoidData (){
		if(solenoidData == null){
			solenoidData = new CollectorSolenoidData();
		}
		return solenoidData;
	}
	
	public CollectorLimitSwitchData getLimitSwitchData() {
		if(limitSwitchData == null){
			limitSwitchData = new CollectorLimitSwitchData();
		}
		return limitSwitchData;
	}
}
