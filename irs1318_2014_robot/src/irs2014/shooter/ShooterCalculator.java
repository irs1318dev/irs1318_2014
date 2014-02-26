package irs2014.shooter;

import irs2014.collector.CollectorRef;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Utility;
import irs2014.networkTable.IRSTable;
import irs2014.shooter.ShooterData;


public class ShooterCalculator extends RobotComponentBase {
	
	private boolean collectorExtended; 
	
	private String rootKey = "spid.";
	private String upKey = "up";
	private String downKey = "dw";
	private String overrideKey = "nto";
	
	public void robotInit(){
		IRSTable.putBoolean(rootKey + overrideKey, false);
		IRSTable.putNumber(rootKey + upKey, new Double(0));
		IRSTable.putNumber(rootKey + downKey, new Double(0));
		ReferenceData.getInstance().getShooterData().setPulseExtendTime(2);
		ReferenceData.getInstance().getShooterData().setPulseRetractTime(2);
	}
	
	public void teleopPeriodic(){
		collectorExtended = (ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState() == CollectorRef.EXTEND);
		
		if(IRSTable.getBoolean(rootKey + overrideKey)){
			try{
				ReferenceData.getInstance().getShooterData().setPulseExtendTime((int)(IRSTable.getNumber(rootKey + upKey)));
			}catch (Exception e){
				ReferenceData.getInstance().getShooterData().setPulseExtendTime(2);
			}
			try{
				ReferenceData.getInstance().getShooterData().setPulseRetractTime((int)(IRSTable.getNumber(rootKey + downKey)));
			}catch (Exception e){
				ReferenceData.getInstance().getShooterData().setPulseRetractTime(2);
			}
		}
		
		if(ReferenceData.getInstance().getShooterData().getNextSetShoot()){
			ReferenceData.getInstance().getShooterData().setNextSetShoot(false);
			ReferenceData.getInstance().getUserInputData().setShoot5Pistons(true);
		}
		
		if((ReferenceData.getInstance().getUserInputData().getShoot5Pistons() || ReferenceData.getInstance().getUserInputData().getShoot3Pistons() || 
				ReferenceData.getInstance().getUserInputData().getShootPulse() || ReferenceData.getInstance().getUserInputData().getShoot1Piston() || 
				ReferenceData.getInstance().getUserInputData().getShoot4Pistons()) && collectorExtended){
			if(ReferenceData.getInstance().getUserInputData().getShoot5Pistons()){
				ReferenceData.getInstance().getShooterData().setNumPistons(5);
//				ReferenceData.getInstance().getShooterData().setTimeLastShot(Utility.getFPGATime());
			}else if(ReferenceData.getInstance().getUserInputData().getShoot3Pistons()){
				ReferenceData.getInstance().getShooterData().setNumPistons(3);
				ReferenceData.getInstance().getShooterData().setTimeLastShot(Utility.getFPGATime());
			}else if(ReferenceData.getInstance().getUserInputData().getShoot1Piston()){
				ReferenceData.getInstance().getShooterData().setNumPistons(1);
				ReferenceData.getInstance().getShooterData().setTimeLastShot(Utility.getFPGATime());	
			}else if(ReferenceData.getInstance().getUserInputData().getShoot4Pistons()){
				ReferenceData.getInstance().getShooterData().setNumPistons(4);
				ReferenceData.getInstance().getShooterData().setTimeLastShot(Utility.getFPGATime());
			}else if(ReferenceData.getInstance().getUserInputData().getShootPulse()){
				ReferenceData.getInstance().getShooterData().setPrePulse(true);
				ReferenceData.getInstance().getShooterData().setPulseState(ShooterData.PRE_PULSE);
			}
			ReferenceData.getInstance().getShooterData().setDesiredShooterState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setTimeLastShot(Utility.getFPGATime());

			ReferenceData.getInstance().getShooterData().setInShot(true);
		}else if(ReferenceData.getInstance().getShooterData().getPrePulse() && !ReferenceData.getInstance().getUserInputData().getShootPulse()){
			ReferenceData.getInstance().getShooterData().setPrePulse(false);
			ReferenceData.getInstance().getShooterData().setPulse(true);
//			ReferenceData.getInstance().getShooterData().setNumPistons(5);
			ReferenceData.getInstance().getShooterData().setTimeLastShot(Utility.getFPGATime());
//			ReferenceData.getInstance().getShooterData().setDesiredShooterState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setInShot(true);
			ReferenceData.getInstance().getShooterData().setCounter(0);
			ReferenceData.getInstance().getShooterData().setPulseState(ShooterData.EXTEND_1);
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
				switch (ReferenceData.getInstance().getShooterData().getPulseState()){
				case ShooterData.PRE_PULSE: 
					break;
				case ShooterData.EXTEND_1:
					if(ReferenceData.getInstance().getShooterData().getCounter() < ReferenceData.getInstance().getShooterData().getPulseExtendTime()){
						ReferenceData.getInstance().getShooterData().setNumPistons(5);
						ReferenceData.getInstance().getShooterData().setDesiredShooterState(ShooterRef.EXTEND);
						System.out.println("in EXTEND_1");
					}else{
						ReferenceData.getInstance().getShooterData().setPulseState(ShooterData.RETRACT);
						ReferenceData.getInstance().getShooterData().setCounter(-1);
					}
					break;
				case ShooterData.RETRACT:
					if(ReferenceData.getInstance().getShooterData().getCounter() < ReferenceData.getInstance().getShooterData().getPulseRetractTime()){
						ReferenceData.getInstance().getShooterData().setNumPistons(5);
						ReferenceData.getInstance().getShooterData().setDesiredShooterState(ShooterRef.RETRACT);
						System.out.println("in RETRACT");
					}else{
						ReferenceData.getInstance().getShooterData().setPulseState(ShooterData.EXTEND_2);
						ReferenceData.getInstance().getShooterData().setCounter(-1);
					}
					break;
				case ShooterData.EXTEND_2:
//					ReferenceData.getInstance().getUserInputData().setShoot5Pistons(true);
					ReferenceData.getInstance().getShooterData().setNextSetShoot(true);
					ReferenceData.getInstance().getShooterData().setPulseState(ShooterData.AFTER);
					ReferenceData.getInstance().getShooterData().setPulse(false);
					ReferenceData.getInstance().getShooterData().setInShot(false);
					System.out.println("int EXTEND_2");
					break;
				}
				ReferenceData.getInstance().getShooterData().setCounter(ReferenceData.getInstance().getShooterData().getCounter() + 1);
			}
		}
	}
	
}

