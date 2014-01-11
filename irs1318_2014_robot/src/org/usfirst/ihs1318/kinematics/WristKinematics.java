package org.usfirst.ihs1318.kinematics;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ButtonRef;
import org.usfirst.ihs1318.shared.data.WristValues;

//Checked for NPE. Lazy getters are used.
public class WristKinematics {
	private WristValues wrist;
	// config numbers start at closed and go to open (config1 is closed)
	boolean wrist4, wrist3, wrist2, wrist1;

	public void init() {
		allClose();
	}

	public void calculateWristValues() {
		// obtain button values from button data
		synchronized (ReferenceData.getInstance()) {
			wrist4 = ReferenceData
					.getInstance()
					.getButtonValues()
					.getButtonValue(ButtonRef.WRIST_JOYSTICK,
							ButtonRef.WRIST_BUTTON_OPEN);
			wrist3 = ReferenceData
					.getInstance()
					.getButtonValues()
					.getButtonValue(ButtonRef.WRIST_JOYSTICK,
							ButtonRef.WRIST_BUTTON_MED);
			wrist2 = ReferenceData
					.getInstance()
					.getButtonValues()
					.getButtonValue(ButtonRef.WRIST_JOYSTICK,
							ButtonRef.WRIST_BUTTON_LOW);
			wrist1 = ReferenceData
					.getInstance()
					.getButtonValues()
					.getButtonValue(ButtonRef.WRIST_JOYSTICK,
							ButtonRef.WRIST_BUTTON_CLOSED);
		}

		// calculate wrist settings given wrist settings given button values

		if (wrist4) {

			open();

		} else if (wrist3) {

			mid();

		}  else if (wrist2) {

			low();

		}  else if (wrist1) {

			allClose();
		}
		// write wrist setting to kinematics data
		synchronized (KinematicData.getInstance()) {
			getWrist().copyValues(
			KinematicData.getInstance().getWristValue()
					);
		}
	}

	public void open() {
		getWrist().setWristPosition(WristValues.WRIST_OPEN);
	}

	public void mid() {
		getWrist().setWristPosition(WristValues.WRIST_MID);
	}

	public void low() {
		getWrist().setWristPosition(WristValues.WRIST_LOW);
	}

	public void allClose() {
		getWrist().setWristPosition(WristValues.WRIST_CLOSED);
	}

	public WristValues getWrist() {
		if (wrist == null) {
			wrist = new WristValues();
		}
		return wrist;
	}

	public void setWrist(WristValues wrist) {
		this.wrist = wrist;
	}
}
