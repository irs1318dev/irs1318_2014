package org.usfirst.ihs1318.shooter;

import irs2014.generalData.ReferenceData;

public class ShooterCalculator {
	
	public void robotInit() {
		
	}
	
	public void teleopPeriodic() {
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendAllShooterSolenoids()) {
			ReferenceData.getInstance().getShooterData().setMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setOuterSolenoidsState(ShooterRef.EXTEND);
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getRetractShooter()) {
			ReferenceData.getInstance().getShooterData().setMiddleSolenoidState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setInnerSolenoidsState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setOuterSolenoidsState(ShooterRef.RETRACT);
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
			ReferenceData.getInstance().getShooterData().setMiddleSolenoidState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setOuterSolenoidsState(ShooterRef.RETRACT);
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendInnerThreeShooterSolenoids()) {
			ReferenceData.getInstance().getShooterData().setMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setOuterSolenoidsState(ShooterRef.RETRACT); 
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendMiddleShooterSolenoid()) {
			ReferenceData.getInstance().getShooterData().setMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setInnerSolenoidsState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setOuterSolenoidsState(ShooterRef.RETRACT); 
		}
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendShooterAngle()) {
			ReferenceData.getInstance().getShooterData().setShooterAngleSolenoidState(ShooterRef.EXTEND);
		}
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getRetractShooterAngle()) {
			ReferenceData.getInstance().getShooterData().setShooterAngleSolenoidState(ShooterRef.RETRACT);
		}
		
	}
		
		
}
