package irs2014.shooter;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;

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
			System.out.println("Retract collector before shooting.Huglow");
			}
		}
		
	}
}
