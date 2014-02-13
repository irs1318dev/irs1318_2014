package irs2014.shooter;

import irs2014.collector.CollectorRef;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.Utility;
import irs2014.shooter.ShooterData;


public class ShooterCalculator extends RobotComponentBase {
	boolean hasExtended;
	boolean hasRetracted;

	public Boolean getHasExtended() {
		return hasExtended;
	}
	
	public Boolean getHasRetracted() {
		return hasRetracted;
	}
	
	public void teleopPeriodic() {
		if(ReferenceData.getInstance().getUserInputData().getExtendAllShooterSolenoids() && ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState() == CollectorRef.EXTEND) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.EXTEND);
		}
		else {
			if (ReferenceData.getInstance().getUserInputData().getExtendAllShooterSolenoids()) {
				System.out.println("Extend collector before shooting.");
			}
		}
		
		if(ReferenceData.getInstance().getUserInputData().getRetractShooter() && ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState() == CollectorRef.EXTEND) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT);
		}
		
			
		if(ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids() && ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState() == CollectorRef.EXTEND) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT);
		}
		else {
			if (ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
				System.out.println("Extend collector before shooting.");
			}
		}
		
		if(ReferenceData.getInstance().getUserInputData().getExtendInnerThreeShooterSolenoids() && ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState() == CollectorRef.EXTEND) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT); 
		}
		else {
			if (ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
			System.out.println("Extend collector before shooting.");
			}
		}
		
		if(ReferenceData.getInstance().getUserInputData().getExtendMiddleShooterSolenoid() && ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState() == CollectorRef.EXTEND) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT); 
		}
		else {
			if (ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
			System.out.println("Extend collector before shooting.");
			}
		}
			
		if(ReferenceData.getInstance().getUserInputData().getExtendShooterAngle() && ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState() == CollectorRef.EXTEND) {
			ReferenceData.getInstance().getShooterData().setDesiredShooterAngleSolenoidState(ShooterRef.EXTEND);
		}
		else {
			if (ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
			System.out.println("Extend collector before shooting.");
			}
		}
		
		if(ReferenceData.getInstance().getUserInputData().getRetractShooterAngle() && ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState() == CollectorRef.EXTEND) {
			ReferenceData.getInstance().getShooterData().setDesiredShooterAngleSolenoidState(ShooterRef.RETRACT);
			hasRetracted = true;
		}
		else {
			if (ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
			System.out.println("Extend collector before shooting.");
			}
		}
		
		if(ReferenceData.getInstance().getUserInputData().getShooterPulse() && ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState() == CollectorRef.EXTEND)  {
			ReferenceData.getInstance().getShooterData().setStartTime(getFPGATime());
			ReferenceData.getInstance().getShooterData().setIsShooting(true);
		}
		
		if(ReferenceData.getInstance().getShooterData().getIsShooting()){
			if(Utility.getFPGATime() < ReferenceData.getInstance().getShooterData().getStartTime() + ReferenceData.getInstance().getShooterData().getPulseTime()){
				this.setDesiredMiddleSolenoidStateExtend();
				this.setDesiredInnerSolenoidStateExtend();
				this.setDesiredOuterSolenoidStateExtend();
				hasExtended = true;
				
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

