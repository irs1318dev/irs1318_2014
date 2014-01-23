package org.usfirst.ihs1318.reference;

import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ConnectionRef;
import org.usfirst.ihs1318.shared.data.LimitSwitchValue;

public class LimitSwitchReader {
	private LimitSwitch lowerLimitSwitch;
	private LimitSwitch upperLimitSwitch;
	private LimitSwitchValue lowerValue;
	private LimitSwitchValue upperValue;
	
	public void init() {
		lowerLimitSwitch = new LimitSwitch();
		lowerLimitSwitch.initLimitSwitch(ConnectionRef.LIMIT_SWITCH_SLOT, ConnectionRef.LIMIT_SWITCH_LOWER_CHANNEL);
		
		upperLimitSwitch = new LimitSwitch();
		upperLimitSwitch.initLimitSwitch(ConnectionRef.LIMIT_SWITCH_SLOT, ConnectionRef.LIMIT_SWITCH_UPPER_CHANNEL);
		
		lowerValue = new LimitSwitchValue();
		upperValue = new LimitSwitchValue();
	}
	
	public void readLimitSwitch() {
		getLowerValue().setTriggered(getLowerSwitch().isSwitchTriggered());
		getUpperValue().setTriggered(getUpperSwitch().isSwitchTriggered());
		synchronized (ReferenceData.getInstance()) {
			getLowerValue().copyValuesTo(ReferenceData.getInstance().getLowerLimitSwitchValue());
			getUpperValue().copyValuesTo(ReferenceData.getInstance().getUpperLimitSwitchValue());
		}
	}

	private LimitSwitchValue getUpperValue() {
		if(upperValue == null)
			upperValue = new LimitSwitchValue();
		return upperValue;
	}

	private LimitSwitchValue getLowerValue() {
		if(lowerValue == null)
			lowerValue = new LimitSwitchValue();
		return lowerValue;
	}

	public LimitSwitch getUpperSwitch() {
		if(upperLimitSwitch == null){
			upperLimitSwitch = new LimitSwitch();
		}
		return upperLimitSwitch;
	}
	
	public LimitSwitch getLowerSwitch() {
		if(lowerLimitSwitch == null){
			lowerLimitSwitch = new LimitSwitch();
		}
		return lowerLimitSwitch;
	}
}
