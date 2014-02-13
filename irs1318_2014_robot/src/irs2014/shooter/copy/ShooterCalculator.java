package irs2014.shooter.copy;

import irs2014.collector.CollectorRef;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.Utility;
import irs2014.shooter.ShooterData;


public class ShooterCalculator extends RobotComponentBase {
	boolean hasExtended;
	boolean hasRetracted;
	
	public boolean getHasExtended() {
		return hasExtended;
	}
	
	public boolean getHasRetracted() {
		return hasRetracted;
	}
	
	public void teleopPeriodic() {
		
		boolean extendAllShooters = ReferenceData.getInstance().getUserInputData().getExtendAllShooterSolenoids();
		boolean collectorExtended = (ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState() == CollectorRef.EXTEND);
		boolean retractShooter = ReferenceData.getInstance().getUserInputData().getRetractShooter();
		boolean extendInnerSolenoids = ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids();
		boolean extendInnerThreeSolenoids = ReferenceData.getInstance().getUserInputData().getExtendInnerThreeShooterSolenoids();
		boolean extendMiddleSolenoid = ReferenceData.getInstance().getUserInputData().getExtendMiddleShooterSolenoid();
		boolean extendShooterAngle = ReferenceData.getInstance().getUserInputData().getExtendShooterAngle();
		boolean retractShooterAngle = ReferenceData.getInstance().getUserInputData().getRetractShooterAngle();
		boolean shooterPulse = ReferenceData.getInstance().getUserInputData().getShooterPulse();
		boolean isShooting = ReferenceData.getInstance().getShooterData().getIsShooting();
		
//		System.out.println("extendAllShooters: " + extendAllShooters + "\n collectorExtended: " + collectorExtended + "\n retractShooter: " + retractShooter + 
//							"\n extendInnerSolenoids: " + extendInnerSolenoids + "\n extendInnerThreeSolenoids: " + extendInnerThreeSolenoids + 
//							"\n extendMiddleSolenoid: " + extendMiddleSolenoid + "\n extendShooterAngle: " + extendShooterAngle + 
//							"\n retractShooterAngle: " + retractShooterAngle + "\n shooterPulse: " + shooterPulse + "\n isShooting: " + isShooting);
		
		if(extendAllShooters && collectorExtended) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.EXTEND);
//			System.out.println("extendAllShooters called");
		} else {
			if (ReferenceData.getInstance().getUserInputData().getExtendAllShooterSolenoids()) {
//				System.out.println("Extend collector before shooting.");
			}
		}
		
		if(retractShooter && collectorExtended) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT);
			System.out.println("in retractShooter");
		}
			
		if(extendInnerSolenoids && collectorExtended) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT);
//			System.out.println("in extendInnerSolenoids");
		} else {
			if (ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
//				System.out.println("Extend collector before shooting.");
			}
		}
		
		if(extendInnerThreeSolenoids && collectorExtended) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT); 
//			System.out.println("in extendInnerThreeSolenoids");
		} else {
			if (ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
//			System.out.println("Extend collector before shooting.");
			}
		}
		
		if( extendMiddleSolenoid && collectorExtended) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT); 
//			System.out.println("in extendMiddleSolenoid");
		} else {
			if (ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
//			System.out.println("Extend collector before shooting.");
			}
		}
			
		if( extendShooterAngle && collectorExtended) {
			ReferenceData.getInstance().getShooterData().setDesiredShooterAngleSolenoidState(ShooterRef.EXTEND);
//			System.out.println("in extendShooterAngle");
		} else {
			if (ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
//			System.out.println("Extend collector before shooting.");
			}
		}
		
		if( retractShooterAngle && collectorExtended) {
			ReferenceData.getInstance().getShooterData().setDesiredShooterAngleSolenoidState(ShooterRef.RETRACT);
			hasRetracted = true;
//			System.out.println("in retractShooterAngle");
		} else {
			if (ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
//			System.out.println("Extend collector before shooting.");
			}
		}
		
		if(shooterPulse && collectorExtended)  {
			ReferenceData.getInstance().getShooterData().setStartTime(getFPGATime());
			ReferenceData.getInstance().getShooterData().setIsShooting(true);
//			System.out.println("in shooterPulse");
		}
		
		if(isShooting){
			if(Utility.getFPGATime() < ReferenceData.getInstance().getShooterData().getStartTime() + ReferenceData.getInstance().getShooterData().getPulseTime()){
				this.setDesiredMiddleSolenoidStateExtend();
				this.setDesiredInnerSolenoidStateExtend();
				this.setDesiredOuterSolenoidStateExtend();
				hasExtended = true;
//				System.out.println("in isShooting");
				
			}else{
				ReferenceData.getInstance().getShooterData().setIsShooting(false);
			}
		} else {
			this.setDesiredMiddleSolenoidStateRetract();
			this.setDesiredInnerSolenoidStateRetract();
			this.setDesiredOuterSolenoidStateRetract();
			hasRetracted = true;
			
		}
	}
	
	
	public long getFPGATime() {
		return Utility.getFPGATime();
	}
	
	public void setDesiredInnerSolenoidStateExtend() {
		ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
		hasExtended = true;
	}
	
	public void setDesiredMiddleSolenoidStateExtend() {
		ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
		hasExtended = true;
	}
	
	public void setDesiredOuterSolenoidStateExtend() {
		ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.EXTEND);
		hasExtended = true;
	}
	
	public void setDesiredInnerSolenoidStateRetract() {
		ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.RETRACT);
		hasRetracted = true;
	}
	
	public void setDesiredMiddleSolenoidStateRetract() {
		ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.RETRACT);
		hasRetracted = true;
	}
	
	public void setDesiredOuterSolenoidStateRetract() {
		ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT);
		hasRetracted = true;
	}
	
}

