package org.usfirst.ihs1318.shared.data;

import org.usfirst.ihs1318.shared.constants.ButtonRef;

//Checked for NPEs. Uses lazy getters.
public class ButtonValues {
	
	private boolean[][] buttons = null;

	public boolean[][] getButtons() {
		if (buttons==null) {
			buttons = new boolean[ButtonRef.NUM_JOYSTICKS][ButtonRef.NUM_BUTTONS];
		}
		return buttons;
	}

	public void setButtons(boolean[][] buttons) {
		this.buttons = buttons;
	}

	public boolean getButtonValue(int joyStick, int button) {
		return getButtons()[joyStick][button];
	}
	
	public void copyValues(ButtonValues dest){
		if (dest == null) 
			throw new RuntimeException("dest must not be null.");
		int i,j;
		for(i = 0; i < dest.getButtons().length; i++){
			for(j = 0; j < dest.getButtons()[i].length; j++){
				dest.getButtons()[i][j] = getButtons()[i][j];
			}
		}
	}
}
