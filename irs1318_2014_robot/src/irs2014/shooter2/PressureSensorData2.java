package irs2014.shooter2;

public class PressureSensorData2 {
	
	private boolean isPressurized;
	private boolean wasPressurized; 
	
	public boolean getIsPressurized() {
		return isPressurized;
	}
	
	public boolean getWasPressurized() {
		return wasPressurized; 
	}
	
	public void setIsPressurized(boolean isPressurized) {
		setWasPressurized(this.isPressurized);
		this.isPressurized = isPressurized; 
	}
	
	public void setWasPressurized(boolean wasPressurized) {
		this.wasPressurized = wasPressurized; 
	}

}
