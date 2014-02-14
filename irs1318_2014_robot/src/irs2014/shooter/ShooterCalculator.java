package irs2014.shooter;

import irs2014.collector.CollectorRef;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Utility;
import irs2014.shooter.ShooterData;


public class ShooterCalculator extends RobotComponentBase {
	
	public void teleopPeriodic(){
		if(ReferenceData.getInstance().getUserInputData().getShoot5Pistons()){
			ReferenceData.getInstance().getShooterData().setDesiredShooterState(true);
		}else if(ReferenceData.getInstance().getUserInputData().getRetract5Pistons()){
			ReferenceData.getInstance().getShooterData().setDesiredShooterState(false);
		}
		
		if(ReferenceData.getInstance().getShooterData().getInShot()){
			if((ReferenceData.getInstance().getShooterData().getTimeLastShot() + ReferenceData.getInstance().getShooterData().SHOT_INTERVAL) >= Utility.getFPGATime()){
				ReferenceData.getInstance().getShooterData().setDesiredShooterState(false);
				ReferenceData.getInstance().getShooterData().setInShot(true);
			}
		}
	}
	
}

