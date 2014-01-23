package org.usfirst.ihs1318.kinematics;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ButtonRef;
import org.usfirst.ihs1318.shared.data.MiniBotValue;

//Checked for NPE
public class MiniBotKinematics {
	private MiniBotValue miniBotValue;
	public static final int MINI_BOT_JOYSTICK = ButtonRef.MINIBOT_DEPLOY_JS;
	public static final int MINI_BOT_BUTTON = ButtonRef.MINIBOT_DEPLOY_BUTTON; 

	private boolean prevToggle;
	private boolean miniBot;
	
	public void calculateMiniBot(){
		boolean changeToggle = false;
		synchronized (ReferenceData.getInstance()) {
			changeToggle = ReferenceData.getInstance().getButtonValues()
			          .getButtons()[MINI_BOT_JOYSTICK][MINI_BOT_BUTTON];
		}
		if (changeToggle) {
			if (!prevToggle) {
				// flip to opposite value, the first time only
				miniBot = !miniBot;
				
				prevToggle = true;
			}
		}
		else {
			prevToggle = false;
		}
		synchronized (KinematicData.getInstance()) {
			KinematicData.getInstance().getMiniBotValue().setState(miniBot);
		}	
	}
	public MiniBotValue getMiniBotValue() {
		if(miniBotValue == null){
			miniBotValue = new MiniBotValue();
		}
		return miniBotValue;
	}
	public void setMiniBotValue(MiniBotValue miniBotValue) {
		this.miniBotValue = miniBotValue;
	}
}
