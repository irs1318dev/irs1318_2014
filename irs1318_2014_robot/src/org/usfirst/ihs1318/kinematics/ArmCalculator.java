package org.usfirst.ihs1318.kinematics;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ArmSetPointsRef;
import org.usfirst.ihs1318.shared.constants.ButtonRef;
import org.usfirst.ihs1318.shared.constants.EncoderRef;
import org.usfirst.ihs1318.shared.data.ArmData;
import org.usfirst.ihs1318.shared.data.ArmInput;
import org.usfirst.ihs1318.shared.data.WheelMotorEncoderTicks;

//Checked for NPE. Lazy getters are used.
/**
 * Writes actual encoder tick values to KinematicData for a set point.
 * Relays the joystick values from ReferenceData to Kinematic data.
 */
public class ArmCalculator {
	//Thses are in encoder ticks. Zero is the absolute lowest the arm can go.
	//Zero is +/- 50 ticks. Distances can be calibrated the day of if possible.	
	//NOTE: NEGATIVE NUMBERS ARE UP
	private ArmInput armInput;
	private ArmData desiredData;
	
	/**
	 * Reads the ReferenceData's ArmInput data structure. Applies a gain factor to the velocity value and
	 * validates the setPoint value. Writes the results to the KinematicData instance.
	 */
	public void calculateDesiredArmVelocity() {
		int height = getHeightSetPoint();
		readReferenceData(height);
		writeReferenceData(height);
//		calcultateDesiredWheelTicks(height).copyValues(getDesiredWheelTicks());
		getDesiredData().setArmVelocity(getArmInput().getDesiredJy());	
		getDesiredData().setArmDistance(calculatePresetHeight(getHeightSetPoint()));
		
		writeKinematicData();
	}
	/**
	 * Returns a desired change in ticks based on the selected set point.
	 * 
	 * @param height
	 * @return ticks A desired WheelMotorEncoderTicks object. This is the goal CHANGE in ticks.
	 */
	private WheelMotorEncoderTicks calcultateDesiredWheelTicks(int height) {
		double ticks;
		WheelMotorEncoderTicks wheelTicks = new WheelMotorEncoderTicks();
		switch(height){
		case ButtonRef.HEIGHT1:
			ticks = EncoderRef.convertToWheelTicks(ArmSetPointsRef.WHEEL_DISTANCE_LOW1);
			break;
		case ButtonRef.HEIGHT2:
			ticks = EncoderRef.convertToWheelTicks(ArmSetPointsRef.WHEEL_DISTANCE_LOW2);
			break;
		case ButtonRef.HEIGHT3:
			ticks = EncoderRef.convertToWheelTicks(ArmSetPointsRef.WHEEL_DISTANCE_MIDDLE1);	
			break;
		case ButtonRef.HEIGHT4:
			ticks = EncoderRef.convertToWheelTicks(ArmSetPointsRef.WHEEL_DISTANCE_MIDDLE2);
			break;		
		case ButtonRef.HEIGHT5:
			ticks = EncoderRef.convertToWheelTicks(ArmSetPointsRef.WHEEL_DISTANCE_HIGH1);
			break;			
		case ButtonRef.HEIGHT6:
			ticks = EncoderRef.convertToWheelTicks(ArmSetPointsRef.WHEEL_DISTANCE_HIGH2);
			break;			
		default:
			ticks = EncoderRef.convertToWheelTicks(ArmSetPointsRef.WHEEL_DISTANCE_NO_SET_POINT);//0.0
		}	
		
		wheelTicks.setAll(-ticks, -ticks, -ticks, -ticks); //this is for moving backwards
		
		return wheelTicks;
	}

	/**
	 * 
	 * @param desiredSetPoint The (possibly un-scrubbed) desired set height number
	 * @return SET_POINT The actual distance of the set point passed into the function
	 */
	private double calculatePresetHeight(int desiredSetPoint) {
		switch(desiredSetPoint) {
		case ButtonRef.HEIGHT1:
			return ArmSetPointsRef.ARM_MOTOR_LOW1;
			
		case ButtonRef.HEIGHT2:
			return ArmSetPointsRef.ARM_MOTOR_LOW2;
			
		case ButtonRef.HEIGHT3:
			return ArmSetPointsRef.ARM_MOTOR_MIDDLE1;
			
		case ButtonRef.HEIGHT4:
			return ArmSetPointsRef.ARM_MOTOR_MIDDLE2;
			
		case ButtonRef.HEIGHT5:
			return ArmSetPointsRef.ARM_MOTOR_HIGH1;
			
		case ButtonRef.HEIGHT6:
			return ArmSetPointsRef.ARM_MOTOR_HIGH2;
			
		default:
			return ArmSetPointsRef.ARM_MOTOR_NO_SET_POINT;
		}
	}
	
	/**
	 * Copies the ReferenceData's ArmInput values to a local copy. Sets the heightSetPoint on the local ArmInput copy based on ReferenceData
	 * button values.
	 * 
	 */
	private void readReferenceData(int height){
		synchronized(ReferenceData.getInstance()){
			ReferenceData.getInstance().getArmInput().copyValuesTo(getArmInput());
		}
		
		getArmInput().setDesiredSetPoint(height);
	}
	
	/**
	 * @return The current preset height button pressed's number. 0 if no button is pressed.
	 */
	private int getHeightSetPoint() {
		boolean low1 = false;
		boolean low2 = false;
		boolean middle1 = false;
		boolean middle2 = false;
		boolean high1 = false;
		boolean high2 = false;
		synchronized (ReferenceData.getInstance()){
			low1 = ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.JOYSTICK_ARM][ButtonRef.HEIGHT1];
			low2 = ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.JOYSTICK_ARM][ButtonRef.HEIGHT2];
			middle1 = ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.JOYSTICK_ARM][ButtonRef.HEIGHT3];
			middle2 = ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.JOYSTICK_ARM][ButtonRef.HEIGHT4];
			high1 = ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.JOYSTICK_ARM][ButtonRef.HEIGHT5];
			high2 = ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.JOYSTICK_ARM][ButtonRef.HEIGHT6];
		}
		
		if(low1)
			return ButtonRef.HEIGHT1;
		else if(low2)
			return ButtonRef.HEIGHT2;
		else if(middle1)
			return ButtonRef.HEIGHT3;
		else if(middle2)
			return ButtonRef.HEIGHT4;
		else if(high1)
			return ButtonRef.HEIGHT5;
		else if(high2)
			return ButtonRef.HEIGHT6;
		return 0;
	}

	/**
	 * Writes the results from the "calculations" to the shared data block KinematicData.
	 */
	
	private void writeKinematicData() {
		synchronized(KinematicData.getInstance()) {
//			getDesiredWheelTicks().copyValues(KinematicData.getInstance().getChangeInTicks());
			getDesiredData().copyValuesTo(KinematicData.getInstance().getArmData());
		}
	}
	/**
	 * Sets the solenoid wrist values by "pressing" a button
	 *  so that the specified height can be reached. 
	 *  
	 * Preset heights include not only arm encoder
	 * distances but also wrist configurations.
	 * 
	 * @param height The preset height's button number.
	 */
	private void writeReferenceData(int height ) {
		synchronized(ReferenceData.getInstance()) {      
			if (height == ButtonRef.HEIGHT1) {
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_CLOSED] = true;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_LOW] = false;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_MED] = false;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_OPEN] = false;
			}
			else if (height == ButtonRef.HEIGHT2) {
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_CLOSED] = true;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_LOW] = false;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_MED] = false;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_OPEN] = false;
			}
			else if (height == ButtonRef.HEIGHT3) {
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_CLOSED] = false;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_LOW] = true;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_MED] = false;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_OPEN] = false;
			}
			else if (height == ButtonRef.HEIGHT4) {
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_CLOSED] = false;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_LOW] = true;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_MED] = false;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_OPEN] = false;
			}
			else if (height == ButtonRef.HEIGHT5) {
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_CLOSED] = false;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_LOW] = false;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_MED] = true;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_OPEN] = false;
			}
			else if (height == ButtonRef.HEIGHT6) {
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_CLOSED] = false;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_LOW] = false;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_MED] = false;
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.WRIST_JOYSTICK][ButtonRef.WRIST_BUTTON_OPEN] = true;
			}
		}
	}
	
	/**
	 * @return armInput The armInput data structure that the calculations are using.
	 */
		
	public ArmInput getArmInput() {
		if(armInput == null) {
			armInput = new ArmInput();
		}
		return armInput;
	}
	
	/**
	 * @return desiredData The calculated velocity and/or distance the robot needs to travel.
	 */
	
	public ArmData getDesiredData() {
		if(desiredData == null) {
			desiredData = new ArmData();
		}
		return desiredData;
	}


}
