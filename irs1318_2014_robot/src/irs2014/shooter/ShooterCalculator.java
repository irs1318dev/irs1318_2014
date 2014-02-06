package irs2014.shooter;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;

public class ShooterCalculator extends RobotComponentBase {
	
	public void robotInit() {
		
	}
	
	public void teleopPeriodic() {
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendAllShooterSolenoids()) {
			ReferenceData.getInstance().getShooterData().setCurrentMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setCurrentInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setCurrentOuterSolenoidsState(ShooterRef.EXTEND);
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getRetractShooter()) {
			ReferenceData.getInstance().getShooterData().setCurrentMiddleSolenoidState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setCurrentInnerSolenoidsState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setCurrentOuterSolenoidsState(ShooterRef.RETRACT);
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
			ReferenceData.getInstance().getShooterData().setCurrentMiddleSolenoidState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setCurrentInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setCurrentOuterSolenoidsState(ShooterRef.RETRACT);
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendInnerThreeShooterSolenoids()) {
			ReferenceData.getInstance().getShooterData().setCurrentMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setCurrentInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setCurrentOuterSolenoidsState(ShooterRef.RETRACT); 
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendMiddleShooterSolenoid()) {
			ReferenceData.getInstance().getShooterData().setCurrentMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setCurrentInnerSolenoidsState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setCurrentOuterSolenoidsState(ShooterRef.RETRACT); 
		}
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendShooterAngle()) {
			ReferenceData.getInstance().getShooterData().setCurrentShooterAngleSolenoidState(ShooterRef.EXTEND);
		}
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getRetractShooterAngle()) {
			ReferenceData.getInstance().getShooterData().setCurrentShooterAngleSolenoidState(ShooterRef.RETRACT);
		}
		
	}
		
		
}
