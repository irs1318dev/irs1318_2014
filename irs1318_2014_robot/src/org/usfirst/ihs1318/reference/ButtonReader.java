package org.usfirst.ihs1318.reference;

import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ButtonRef;

public class ButtonReader {
	private boolean joystickButtons[][] = new boolean[ButtonRef.NUM_BUTTONS][ButtonRef.NUM_BUTTONS];

	/**
	 * stores button values in two-dimensional array. First element is joystick
	 * number, second is button number
	 * 
	 * @param joySticks
	 */
	public void setButtonValues(JoystickReader joySticks) {
		
		//gets triggers
		joystickButtons[ButtonRef.JOYSTICK_VELOCITY][0] = joySticks.getVelocityStick().getTrigger();
		joystickButtons[ButtonRef.JOYSTICK_ROTATION][0] = joySticks.getRotationStick().getTrigger();
		joystickButtons[ButtonRef.JOYSTICK_ARM][0] = joySticks.getArmStick().getTrigger();
		
		//gets remaining buttons
		for (int i = 0; i < ButtonRef.NUM_BUTTONS; i++) {
			// button values will only change if different than last value
			joystickButtons[ButtonRef.JOYSTICK_VELOCITY][i] = joySticks.getVelocityStick().getRawButton(i+1);
			joystickButtons[ButtonRef.JOYSTICK_ROTATION][i] = joySticks.getRotationStick().getRawButton(i+1);
			joystickButtons[ButtonRef.JOYSTICK_ARM][i] = joySticks.getArmStick().getRawButton(i+1);
		}
		
		
		synchronized (ReferenceData.getInstance()) {
			// array copy is missing from CLDC JVM
			for (int i=0; i<ButtonRef.NUM_JOYSTICKS;i++) {
				for (int j=0; j<ButtonRef.NUM_BUTTONS; j++) {
					if (ReferenceData.DEBUG && joystickButtons[i][j]) {
						System.out.println("pushed "+i+","+j);
					}
					ReferenceData.getInstance().getButtonValues().getButtons()[i][j] = joystickButtons[i][j];
//					System.out.println("airb="+joystickButtons[ButtonRef.AIR_JS][ButtonRef.AIR_ENABLE]);
				}
			}
		}
	}
	
	public void setButtons(GamePadReader pad) {
		
	}

}
