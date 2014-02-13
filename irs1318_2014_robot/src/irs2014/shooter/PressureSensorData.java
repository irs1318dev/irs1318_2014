package irs2014.shooter;

public class PressureSensorData {
	
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
