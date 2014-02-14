package irs2014.shooter;

import irs2014.collector.CollectorRef;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Utility;
import irs2014.shooter.ShooterData;


public class ShooterCalculator extends RobotComponentBase {
	
	private boolean collectorExtended; 
	
	public void teleopPeriodic(){
		collectorExtended = (ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState() == CollectorRef.EXTEND);
		
		if(ReferenceData.getInstance().getUserInputData().getShoot5Pistons() && collectorExtended){
			ReferenceData.getInstance().getShooterData().setDesiredShooterState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setInShot(true);
			ReferenceData.getInstance().getShooterData().setTimeLastShot(Utility.getFPGATime());
		}else if(ReferenceData.getInstance().getUserInputData().getRetract5Pistons()){
			ReferenceData.getInstance().getShooterData().setDesiredShooterState(ShooterRef.RETRACT);
		}
		
		if(ReferenceData.getInstance().getShooterData().getInShot() && collectorExtended){
			if((ReferenceData.getInstance().getShooterData().getTimeLastShot() + ReferenceData.getInstance().getShooterData().SHOT_INTERVAL) <= Utility.getFPGATime()){
				ReferenceData.getInstance().getShooterData().setDesiredShooterState(ShooterRef.RETRACT);
				ReferenceData.getInstance().getShooterData().setInShot(false);
			}
		}
	}
	
}

