package org.usfirst.ihs1318.kinematics;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ButtonRef;
import org.usfirst.ihs1318.shared.data.ButtonValues;
import org.usfirst.ihs1318.shared.data.ClawValue;

//Checked for NPE. Lazy initialization getters are used.
public class ClawKinematics {
	private ButtonValues buttons;
	private ClawValue clawValue;

	public void calculateClawKinematics(){
		synchronized (ReferenceData.getInstance()) {
			ReferenceData.getInstance().getButtonValues().copyValues(getButtons());
		}
		if (getButtons().getButtons()[ButtonRef.CLAW_JS][ButtonRef.CLAW_BUTTON]) {
			getClawValue().setState(false);
		}
		else {
			getClawValue().setState(true);
		}
		
		synchronized (KinematicData.getInstance()) {
			KinematicData.getInstance().setClawValue(getClawValue());
		}
	}
	
	public ButtonValues getButtons() {
		if(buttons == null){
			buttons = new ButtonValues();
		}
		return buttons;
		
	}

	public void setButtons(ButtonValues buttons) {
		this.buttons = buttons;
	}

	public ClawValue getClawValue() {
		if(clawValue == null){
			clawValue = new ClawValue();
		}
		return clawValue;
	}

	public void setClawValue(ClawValue clawValue) {
		this.clawValue = clawValue;
	}

}
