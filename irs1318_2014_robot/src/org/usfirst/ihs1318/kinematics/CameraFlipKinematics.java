package org.usfirst.ihs1318.kinematics;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ButtonRef;
import org.usfirst.ihs1318.shared.data.CameraFlipValue;

//Checked for NPE. Lazy getters are used.
public class CameraFlipKinematics {
	private CameraFlipValue cameraFlipValue; 

	private boolean prevToggle;
	private boolean servo;
	
	public void calculateServo(){
		boolean changeToggle = false;
		synchronized (ReferenceData.getInstance()) {
			changeToggle = ReferenceData.getInstance().getButtonValues()
			          .getButtons()[ButtonRef.JOYSTICK_SERVO][ButtonRef.SERVO_ROTATE];
		}
		if (changeToggle) {
			if (!prevToggle) {
				// flip to opposite value, the first time only
				servo = !servo;			
				prevToggle = true;
			}
		}
		else {
			prevToggle = false;
		}
		synchronized (KinematicData.getInstance()) {
			KinematicData.getInstance().getServoDirection().setState(servo);
		}	
	}
	public CameraFlipValue getCameraFlipValue() {
		if(cameraFlipValue == null){
			cameraFlipValue = new CameraFlipValue();
		}
		return cameraFlipValue;
	}
	public void setCameraFlipValue(CameraFlipValue cameraflipValue) {
		this.cameraFlipValue = cameraflipValue;
	}
}
