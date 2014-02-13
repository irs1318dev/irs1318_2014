package irs2014.shooter2;

import irs2014.collector.CollectorRef;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.Utility;
import irs2014.shooter.ShooterData;


public class ShooterCalculator2 extends RobotComponentBase {
	boolean hasExtended;
	boolean hasRetracted;
	
	public boolean getHasExtended() {
		return hasExtended;
	}
	
	public boolean getHasRetracted() {
		return hasRetracted;
	}
	
	public void teleopPeriodic() {
		
		boolean extendAllShooters = ReferenceData.getInstance().getUserInputData2().getExtendAllShooterSolenoids();
		boolean collectorExtended = (ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState() == CollectorRef.EXTEND);
		boolean retractShooter = ReferenceData.getInstance().getUserInputData2().getRetractShooter();
		boolean extendInnerSolenoids = ReferenceData.getInstance().getUserInputData2().getExtendInnerShooterSolenoids();
		boolean extendInnerThreeSolenoids = ReferenceData.getInstance().getUserInputData2().getExtendInnerThreeShooterSolenoids();
		boolean extendMiddleSolenoid = ReferenceData.getInstance().getUserInputData2().getExtendMiddleShooterSolenoid();
		boolean extendShooterAngle = ReferenceData.getInstance().getUserInputData2().getExtendShooterAngle();
		boolean retractShooterAngle = ReferenceData.getInstance().getUserInputData2().getRetractShooterAngle();
		boolean shooterPulse = ReferenceData.getInstance().getUserInputData2().getShooterPulse();
		boolean isShooting = ReferenceData.getInstance().getShooterData2().getIsShooting();
		
//		System.out.println("extendAllShooters: " + extendAllShooters + "\n collectorExtended: " + collectorExtended + "\n retractShooter: " + retractShooter + 
//							"\n extendInnerSolenoids: " + extendInnerSolenoids + "\n extendInnerThreeSolenoids: " + extendInnerThreeSolenoids + 
//							"\n extendMiddleSolenoid: " + extendMiddleSolenoid + "\n extendShooterAngle: " + extendShooterAngle + 
//							"\n retractShooterAngle: " + retractShooterAngle + "\n shooterPulse: " + shooterPulse + "\n isShooting: " + isShooting);
		
		if(extendAllShooters && collectorExtended) {
			ReferenceData.getInstance().getShooterData2().setDesiredMiddleSolenoidState(ShooterRef2.EXTEND);
			ReferenceData.getInstance().getShooterData2().setDesiredInnerSolenoidsState(ShooterRef2.EXTEND);
			ReferenceData.getInstance().getShooterData2().setDesiredOuterSolenoidsState(ShooterRef2.EXTEND);
//			System.out.println("extendAllShooters called");
		} else {
			if (ReferenceData.getInstance().getUserInputData2().getExtendAllShooterSolenoids()) {
//				System.out.println("Extend collector before shooting.");
			}
		}
		
		if(retractShooter && collectorExtended) {
			ReferenceData.getInstance().getShooterData2().setDesiredMiddleSolenoidState(ShooterRef2.RETRACT);
			ReferenceData.getInstance().getShooterData2().setDesiredInnerSolenoidsState(ShooterRef2.RETRACT);
			ReferenceData.getInstance().getShooterData2().setDesiredOuterSolenoidsState(ShooterRef2.RETRACT);
			System.out.println("in retractShooter");
		}
			
		if(extendInnerSolenoids && collectorExtended) {
			ReferenceData.getInstance().getShooterData2().setDesiredMiddleSolenoidState(ShooterRef2.RETRACT);
			ReferenceData.getInstance().getShooterData2().setDesiredInnerSolenoidsState(ShooterRef2.EXTEND);
			ReferenceData.getInstance().getShooterData2().setDesiredOuterSolenoidsState(ShooterRef2.RETRACT);
//			System.out.println("in extendInnerSolenoids");
		} else {
			if (ReferenceData.getInstance().getUserInputData2().getExtendInnerShooterSolenoids()) {
//				System.out.println("Extend collector before shooting.");
			}
		}
		
		if(extendInnerThreeSolenoids && collectorExtended) {
			ReferenceData.getInstance().getShooterData2().setDesiredMiddleSolenoidState(ShooterRef2.EXTEND);
			ReferenceData.getInstance().getShooterData2().setDesiredInnerSolenoidsState(ShooterRef2.EXTEND);
			ReferenceData.getInstance().getShooterData2().setDesiredOuterSolenoidsState(ShooterRef2.RETRACT); 
//			System.out.println("in extendInnerThreeSolenoids");
		} else {
			if (ReferenceData.getInstance().getUserInputData2().getExtendInnerShooterSolenoids()) {
//			System.out.println("Extend collector before shooting.");
			}
		}
		
		if( extendMiddleSolenoid && collectorExtended) {
			ReferenceData.getInstance().getShooterData2().setDesiredMiddleSolenoidState(ShooterRef2.EXTEND);
			ReferenceData.getInstance().getShooterData2().setDesiredInnerSolenoidsState(ShooterRef2.RETRACT);
			ReferenceData.getInstance().getShooterData2().setDesiredOuterSolenoidsState(ShooterRef2.RETRACT); 
//			System.out.println("in extendMiddleSolenoid");
		} else {
			if (ReferenceData.getInstance().getUserInputData2().getExtendInnerShooterSolenoids()) {
//			System.out.println("Extend collector before shooting.");
			}
		}
			
		if( extendShooterAngle && collectorExtended) {
			ReferenceData.getInstance().getShooterData2().setDesiredShooterAngleSolenoidState(ShooterRef2.EXTEND);
//			System.out.println("in extendShooterAngle");
		} else {
			if (ReferenceData.getInstance().getUserInputData2().getExtendInnerShooterSolenoids()) {
//			System.out.println("Extend collector before shooting.");
			}
		}
		
		if( retractShooterAngle && collectorExtended) {
			ReferenceData.getInstance().getShooterData2().setDesiredShooterAngleSolenoidState(ShooterRef2.RETRACT);
			hasRetracted = true;
//			System.out.println("in retractShooterAngle");
		} else {
			if (ReferenceData.getInstance().getUserInputData2().getExtendInnerShooterSolenoids()) {
//			System.out.println("Extend collector before shooting.");
			}
		}
		
		if(shooterPulse && collectorExtended)  {
			ReferenceData.getInstance().getShooterData2().setStartTime(getFPGATime());
			ReferenceData.getInstance().getShooterData2().setIsShooting(true);
//			System.out.println("in shooterPulse");
		}
		
		if(isShooting){
			if(Utility.getFPGATime() < ReferenceData.getInstance().getShooterData2().getStartTime() + ReferenceData.getInstance().getShooterData2().getPulseTime()){
				this.setDesiredMiddleSolenoidStateExtend();
				this.setDesiredInnerSolenoidStateExtend();
				this.setDesiredOuterSolenoidStateExtend();
				hasExtended = true;
//				System.out.println("in isShooting");
				
			}else{
				ReferenceData.getInstance().getShooterData2().setIsShooting(false);
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
		ReferenceData.getInstance().getShooterData2().setDesiredInnerSolenoidsState(ShooterRef2.EXTEND);
		hasExtended = true;
	}
	
	public void setDesiredMiddleSolenoidStateExtend() {
		ReferenceData.getInstance().getShooterData2().setDesiredMiddleSolenoidState(ShooterRef2.EXTEND);
		hasExtended = true;
	}
	
	public void setDesiredOuterSolenoidStateExtend() {
		ReferenceData.getInstance().getShooterData2().setDesiredOuterSolenoidsState(ShooterRef2.EXTEND);
		hasExtended = true;
	}
	
	public void setDesiredInnerSolenoidStateRetract() {
		ReferenceData.getInstance().getShooterData2().setDesiredInnerSolenoidsState(ShooterRef2.RETRACT);
		hasRetracted = true;
	}
	
	public void setDesiredMiddleSolenoidStateRetract() {
		ReferenceData.getInstance().getShooterData2().setDesiredMiddleSolenoidState(ShooterRef2.RETRACT);
		hasRetracted = true;
	}
	
	public void setDesiredOuterSolenoidStateRetract() {
		ReferenceData.getInstance().getShooterData2().setDesiredOuterSolenoidsState(ShooterRef2.RETRACT);
		hasRetracted = true;
	}
	
}

