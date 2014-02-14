package irs2014.shooter;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;

public class AngleCalculator extends RobotComponentBase{

	public void teleopPeriodic(){
		if(ReferenceData.getInstance().getUserInputData().getExtendShooterAngle()){
			ReferenceData.getInstance().getAngleData().setDesiredShooterAngle(ShooterRef.EXTEND);
		}else if(ReferenceData.getInstance().getUserInputData().getRetractShooterAngle()){
			ReferenceData.getInstance().getAngleData().setDesiredShooterAngle(ShooterRef.RETRACT);
		}
	}
	
}
