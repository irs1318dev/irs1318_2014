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
		
		if((ReferenceData.getInstance().getUserInputData().getShoot5Pistons() || ReferenceData.getInstance().getUserInputData().getShoot3Pistons() || ReferenceData.getInstance().getUserInputData().getShootPulse()) && collectorExtended){
			if(ReferenceData.getInstance().getUserInputData().getShoot5Pistons()){
				ReferenceData.getInstance().getShooterData().setNumPistons(5);
//				ReferenceData.getInstance().getShooterData().setTimeLastShot(Utility.getFPGATime());
			}else if(ReferenceData.getInstance().getUserInputData().getShoot3Pistons()){
				ReferenceData.getInstance().getShooterData().setNumPistons(3);
//				ReferenceData.getInstance().getShooterData().setTimeLastShot(Utility.getFPGATime());
			}else if(ReferenceData.getInstance().getUserInputData().getShootPulse() /*&& !ReferenceData.getInstance().getShooterData().getPulse()*/){
				ReferenceData.getInstance().getShooterData().setPulse(true);
				ReferenceData.getInstance().getShooterData().setNumPistons(5);
//				ReferenceData.getInstance().getShooterData().setTimeLastShot(Utility.getFPGATime());
			}
			ReferenceData.getInstance().getShooterData().setDesiredShooterState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setTimeLastShot(Utility.getFPGATime());

			ReferenceData.getInstance().getShooterData().setInShot(true);
		}else if(ReferenceData.getInstance().getUserInputData().getRetract5Pistons()){
			ReferenceData.getInstance().getShooterData().setDesiredShooterState(ShooterRef.RETRACT);
		}
		
		if(ReferenceData.getInstance().getShooterData().getInShot() && collectorExtended){
			if(!ReferenceData.getInstance().getShooterData().getPulse()){
				if((ReferenceData.getInstance().getShooterData().getTimeLastShot() + ReferenceData.getInstance().getShooterData().SHOT_INTERVAL) <= Utility.getFPGATime()){
					ReferenceData.getInstance().getShooterData().setDesiredShooterState(ShooterRef.RETRACT);
					ReferenceData.getInstance().getShooterData().setInShot(false);
				}
			}else{
				if((ReferenceData.getInstance().getShooterData().getTimeLastShot() + ReferenceData.getInstance().getShooterData().PULSE_INTERVAL) <= Utility.getFPGATime()){
					ReferenceData.getInstance().getShooterData().setDesiredShooterState(ShooterRef.RETRACT);
					ReferenceData.getInstance().getShooterData().setInShot(false);
					ReferenceData.getInstance().getShooterData().setPulse(false);
				}
			}
		}
	}
	
}

