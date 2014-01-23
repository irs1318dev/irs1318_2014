package irs2014.simpleRIAB;

import edu.wpi.first.wpilibj.Talon;
import irs2014.components.RobotComponentBase;


public class TalonRunner extends RobotComponentBase{
	
	Talon talon;
	
	public void robotInit(){
		talon = new Talon(PortRef.SIDECAR_SLOT, PortRef.TALON);
	}

	int debugCount=0;
	
	public void teleopPeriodic(){
		double val = GamePadData.getInstance().getYValue();
		val = Math.min(val, 1);
		val = Math.max(val, -1);
		if (debugCount%100==0) {
	    	System.out.println("TalonRunner.teleopPeriodic() talon="+val);
		}
		talon.set(val);
	}

}
