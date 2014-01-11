package org.usfirst.ihs1318.shared.data;

public class LimitSwitchValue {
	boolean triggered = false;
	
	public void copyValuesTo(LimitSwitchValue dest) {
		dest.setAll(isTriggered());
	}

	public void setAll(boolean triggered) {
		setTriggered(triggered);
		
	}

	public void setTriggered(boolean triggered) {
		this.triggered = triggered;
	}

	public boolean isTriggered() {
		return triggered;
	}
}
