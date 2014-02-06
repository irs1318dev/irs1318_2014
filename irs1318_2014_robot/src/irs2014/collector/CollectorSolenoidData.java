package irs2014.collector;

public class CollectorSolenoidData {
	private boolean currentSolenoidState;
	private boolean desiredSolenoidState;
	
	public boolean getDesiredSolenoidState(){
		return desiredSolenoidState;
	}
	
	public boolean getCurrentSolenoidState() {
		return currentSolenoidState;
	}
	
	public void setDesiredSolenoidState(boolean desiredSolenoidState){
		setCurrentSolenoidState(this.desiredSolenoidState);
		this.desiredSolenoidState = desiredSolenoidState;
	}
	
	public void setCurrentSolenoidState(boolean currentSolenoidState) {
		this.currentSolenoidState = currentSolenoidState;
	}
}
