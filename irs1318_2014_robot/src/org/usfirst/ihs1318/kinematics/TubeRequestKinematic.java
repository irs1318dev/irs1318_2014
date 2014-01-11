package org.usfirst.ihs1318.kinematics;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ButtonRef;
import org.usfirst.ihs1318.shared.data.TubeRequestValue;

//Checked for NPE. Lazy getters are used.
public class TubeRequestKinematic {
	private TubeRequestValue request;

	public static final int BUTTON_PANEL_JOYSTICK = ButtonRef.TUBE_REQUEST_JS;
	public static final int RED_BUTTON = ButtonRef.TUBE_REQUEST_BUTTON_RED;
	public static final int WHITE_BUTTON = ButtonRef.TUBE_REQUEST_BUTTON_WHITE;
	public static final int BLUE_BUTTON = ButtonRef.TUBE_REQUEST_BUTTON_BLUE;

	private boolean prevToggle;

	public void passValue() {
		boolean changeToggle = false;
		boolean redButton;
		boolean whiteButton;
		boolean blueButton;

		// gets value from ButtonValues
		synchronized (ReferenceData.getInstance()) {
			redButton = ReferenceData.getInstance().getButtonValues()
					.getButtons()[BUTTON_PANEL_JOYSTICK][RED_BUTTON];
			whiteButton = ReferenceData.getInstance().getButtonValues()
					.getButtons()[BUTTON_PANEL_JOYSTICK][WHITE_BUTTON];
			blueButton = ReferenceData.getInstance().getButtonValues()
					.getButtons()[BUTTON_PANEL_JOYSTICK][BLUE_BUTTON];
			changeToggle = redButton || whiteButton || blueButton; // at least one is true
		}
		
		// this code assumes all light buttons are released before settting
		// a new value.
		if (changeToggle) {
			if (!prevToggle) {
				if (redButton) {
					getRequest().setRedTriangle(true);
					getRequest().setBlueSquare(false);
					getRequest().setWhiteCircle(false);
				} else if (whiteButton) {
					getRequest().setRedTriangle(false);
					getRequest().setBlueSquare(false);
					getRequest().setWhiteCircle(true);
				} else if (blueButton) {
					getRequest().setRedTriangle(false);
					getRequest().setBlueSquare(true);
					getRequest().setWhiteCircle(false);
				}
				prevToggle = true;
			}
		} else {
			prevToggle = false;
		}
		// passes value to TubeRequestValue
		synchronized (KinematicData.getInstance()) {
			getRequest().copyValues(
			KinematicData.getInstance().getTubeRequestValue());
		}
	}

	public TubeRequestValue getRequest() {
		if (request == null) {
			request = new TubeRequestValue();
		}
		return request;
	}

	public void setRequest(TubeRequestValue request) {
		this.request = request;
	}

}
