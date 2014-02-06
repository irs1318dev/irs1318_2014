package irs2014.shooter;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;

public class ShooterCalculator extends RobotComponentBase {
	
	public void robotInit() {
		
	}
	
	public void teleopPeriodic() {
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendAllShooterSolenoids()) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.EXTEND);
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getRetractShooter()) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT);
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendInnerShooterSolenoids()) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT);
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendInnerThreeShooterSolenoids()) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT); 
		}
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendMiddleShooterSolenoid()) {
			ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.RETRACT);
			ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT); 
		}
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendShooterAngle()) {
			ReferenceData.getInstance().getShooterData().setDesiredShooterAngleSolenoidState(ShooterRef.EXTEND);
		}
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getRetractShooterAngle()) {
			ReferenceData.getInstance().getShooterData().setDesiredShooterAngleSolenoidState(ShooterRef.RETRACT);
		}
		
	}
		
		
}
