package irs2014.shooter;

import irs2014.collector.CollectorRef;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.Utility;
import irs2014.shooter.ShooterData;

public class ShooterCalculator extends RobotComponentBase {
	
	public void teleopPeriodic() {
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendAllShooterSolenoids() && !ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState()) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.EXTEND);
		}
		else {
			if (ReferenceData.getInstance().getUserInputData().getExtendAllShooterSolenoids()) {
				System.out.println("Retract collector before shooting.");
			}
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getRetractShooter()) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT);
		}
		
			
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && !ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState()) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT);
		}
		else {
			if (ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
				System.out.println("Retract collector before shooting.");
			}
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendInnerThreeShooterSolenoids() && !ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState()) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT); 
		}
		else {
			if (ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
			System.out.println("Retract collector before shooting.");
			}
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendMiddleShooterSolenoid() && !ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState()) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT); 
		}
		else {
			if (ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
			System.out.println("Retract collector before shooting.");
			}
		}
			
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendShooterAngle() && !ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState()) {
			ReferenceData.getInstance().getShooterData().setDesiredShooterAngleSolenoidState(ShooterRef.EXTEND);
		}
		else {
			if (ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
			System.out.println("Retract collector before shooting.");
			}
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getRetractShooterAngle() && !ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState()) {
			ReferenceData.getInstance().getShooterData().setDesiredShooterAngleSolenoidState(ShooterRef.RETRACT);
		}
		else {
			if (ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
			System.out.println("Retract collector before shooting.");
			}
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getShooterPulse() && ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState() == CollectorRef.EXTEND)  {
			ReferenceData.getInstance().getShooterData().setStartTime(getFPGATime());
			ReferenceData.getInstance().getShooterData().setIsShooting(true);
		}
		
		if(ReferenceData.getInstance().getShooterData().getIsShooting()){
			if(Utility.getFPGATime() < ReferenceData.getInstance().getShooterData().getStartTime() + ReferenceData.getInstance().getShooterData().getPulseTime()){
				ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
				ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
				ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.EXTEND);
			}else{
				ReferenceData.getInstance().getShooterData().setIsShooting(false);
			}
		} else {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT);
		}
	}
	
	public long getFPGATime() {
		return Utility.getFPGATime();
	}
}
