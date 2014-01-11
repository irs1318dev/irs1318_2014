package org.usfirst.ihs1318.kinematics;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ButtonRef;
import org.usfirst.ihs1318.shared.data.ButtonValues;
import org.usfirst.ihs1318.shared.data.ManualGyroValue;

public class ManualGyroKinematics {
	private ButtonValues buttons;
	private ManualGyroValue manualGyroValue;

	public void calculateManualGyroKinematics(){
		
		boolean changed = false;
		
		synchronized (ReferenceData.getInstance()) {
			if (ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.JOYSTICK_ROTATION][ButtonRef.degrees0]) {
				changed = true;
				getManualGyroValue().setManualGyroAngle(0);
			}else if(ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.JOYSTICK_ROTATION][ButtonRef.degrees90]){
				changed = true;
				getManualGyroValue().setManualGyroAngle(90);
			}else if(ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.JOYSTICK_ROTATION][ButtonRef.degrees180]){
				changed = true;
				getManualGyroValue().setManualGyroAngle(180);
			}else if(ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.JOYSTICK_ROTATION][ButtonRef.degrees270]){
				changed = true;
				getManualGyroValue().setManualGyroAngle(270);
			}
		}
		
		if (changed) {
			synchronized (KinematicData.getInstance()) {
				getManualGyroValue().copyValue(
				KinematicData.getInstance().getManualGyroValue());
			}
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

	public void setManualGyroValue(ManualGyroValue manualGyroValue) {
		this.manualGyroValue = manualGyroValue;
	}

	public ManualGyroValue getManualGyroValue() {
		if (manualGyroValue == null) {
			manualGyroValue = new ManualGyroValue();
		}
		return manualGyroValue;
	}

}

