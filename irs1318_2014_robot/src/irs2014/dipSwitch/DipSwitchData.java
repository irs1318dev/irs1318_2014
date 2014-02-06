package irs2014.dipSwitch;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;
import edu.wpi.first.wpilibj.DigitalInput;



public class DipSwitchData {
	private boolean dipSwitchState;
	
	public boolean getDipSwitchState() {
		return dipSwitchState;
	}
	public void setDipSwitchState(boolean dipSwitchState) {
		this.dipSwitchState = dipSwitchState;
	}
}
