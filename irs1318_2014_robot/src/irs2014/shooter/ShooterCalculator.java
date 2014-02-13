package irs2014.shooter;

import irs2014.collector.CollectorRef;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.Utility;
import irs2014.shooter.ShooterData;


public class ShooterCalculator extends RobotComponentBase {
	
	public void teleopPeriodic(){
		if(ReferenceData.getInstance().getUserInputData().getShoot5Pistons()){
			ReferenceData.getInstance().getShooterData().setDesiredShooterState(true);
		}else if(ReferenceData.getInstance().getUserInputData().getRetract5Pistons()){
			ReferenceData.getInstance().getShooterData().setCurrentShooterAngle(false);
		}
		
		
		if(ReferenceData.getInstance().getUserInputData().getExtendShooterAngle()){
			ReferenceData.getInstance().getShooterData().setDesiredShooterAngle(true);
		}else if(ReferenceData.getInstance().getUserInputData().getRetractShooterAngle()){
			ReferenceData.getInstance().getShooterData().setDesiredShooterAngle(false);
		}
		
	}
	
}

