package irs2014.dipSwitch;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.DigitalInput;

public class DipSwitchReader extends RobotComponentBase{
	private DigitalInput dipSwitch; 
	
	public void robotInit(){
		dipSwitch = getNewDipSwitch();
		boolean value = dipSwitch.get();
		ReferenceData.getInstance().getDipSwitchData().setDipSwitchState(value);
		System.out.println("The dip switch state is " + value);
	}
		
	public DigitalInput getNewDipSwitch() {
		return new DigitalInput(PortRef.DIGITAL_IO, PortRef.DIP_SWITCH);
	}
}
