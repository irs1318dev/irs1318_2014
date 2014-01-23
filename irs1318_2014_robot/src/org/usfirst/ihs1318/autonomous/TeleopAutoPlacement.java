package org.usfirst.ihs1318.autonomous;

import org.usfirst.ihs1318.kinematics.MecanumDrive;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ButtonRef;

//this class reads a specific button and runs autonomous code if enabled
public class TeleopAutoPlacement extends AutonomousController {

	private boolean straightLine;
	private boolean forkedLine;
	private boolean straightLineLastPress;
	private boolean forkedLineLastPress;

	public boolean isStraightLineLastPress() {
		return straightLineLastPress;
	}

	public void setStraightLineLastPress(boolean straightLineLastPress) {
		this.straightLineLastPress = straightLineLastPress;
	}

	public boolean isForkedLineLastPress() {
		return forkedLineLastPress;
	}

	public void setForkedLineLastPress(boolean forkedLineLastPress) {
		this.forkedLineLastPress = forkedLineLastPress;
	}

	public boolean isStraightLine() {
		return straightLine;
	}

	public boolean isForkedLine() {
		return forkedLine;
	}

	public void run() {
		synchronized (ReferenceData.getInstance()) {
			setStraightLine(ReferenceData
					.getInstance()
					.getButtonValues()
					.getButtonValue(ButtonRef.JOYSTICK_AUTO_PLACE,
							ButtonRef.AUTO_PLACE_BUTTON));
		}
		if (isStraightLine()) {
			super.run();
			MecanumDrive.setEnableOffsetTheta(false);
		}
		//else if(){
			
		//}
		else{
			MecanumDrive.setEnableOffsetTheta(true);
		}
	}

	private void setStraightLine(boolean buttonValue) {
		if(straightLine != straightLineLastPress){
			straightLine = buttonValue;
		}
	}
	private void setForkedLine(boolean buttonValue) {
		if(forkedLine != straightLineLastPress){
			forkedLine = buttonValue;
		}
	}
}
