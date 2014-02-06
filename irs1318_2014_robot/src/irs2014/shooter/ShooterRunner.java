package irs2014.shooter;

import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ShooterRunner {
	
	private DoubleSolenoid middleSolenoid;
	private DoubleSolenoid innerSolenoids;
	private DoubleSolenoid outerSolenoids;
	private DoubleSolenoid shooterAngleSolenoid; 
	
	public void robotInit() {
		middleSolenoid = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.SHOOTER_MIDDLE_SOLENOID_EXTENDER_PORT, PortRef.SHOOTER_MIDDLE_SOLENOID_RETRACTOR_PORT);
		innerSolenoids = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.SHOOTER_INNER_SOLENOIDS_EXTENDER_PORT, PortRef.SHOOTER_INNER_SOLENOIDS_RETRACTOR_PORT);
		outerSolenoids = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.SHOOTER_OUTER_SOLENOIDS_EXTENDER_PORT, PortRef.SHOOTER_OUTER_SOLENOIDS_RETRACTOR_PORT);
		shooterAngleSolenoid = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.SHOOTER_ANGLE_SOLENOID_EXTENDER_PORT, PortRef.SHOOTER_ANGLE_SOLENOID_RETRACTOR_PORT);
		
		System.out.println("DoubleSolenoids robotInit()");
		
	}
	
	public void teleopPeriodic() {
		if(ReferenceData.getInstance().getShooterData().getDesiredMiddleSolenoidState() != ReferenceData.getInstance().getShooterData().getCurrentMiddleSolenoidState()) {
			if(ReferenceData.getInstance().getShooterData().getDesiredMiddleSolenoidState() == ShooterRef.EXTEND) {
				getMiddleSolenoid().set(Value.kForward);
			} else {
				getMiddleSolenoid().set(Value.kReverse);
			}
		}
		
		if(ReferenceData.getInstance().getShooterData().getDesiredInnerSolenoidsState() != ReferenceData.getInstance().getShooterData().getCurrentInnerSolenoidsState()) {
			if(ReferenceData.getInstance().getShooterData().getDesiredInnerSolenoidsState() == ShooterRef.EXTEND){
				getInnerSolenoids().set(Value.kForward);
			} else {
				getInnerSolenoids().set(Value.kOff);
			}
		}
		if(ReferenceData.getInstance().getShooterData().getDesiredOuterSolenoidsState() != ReferenceData.getInstance().getShooterData().getCurrentOuterSolenoidsState()) {
			if(ReferenceData.getInstance().getShooterData().getDesiredOuterSolenoidsState() == ShooterRef.EXTEND){
				getOuterSolenoids().set(Value.kForward);
			} else {
				getOuterSolenoids().set(Value.kOff);
			}
		}
		if(ReferenceData.getInstance().getShooterData().getDesiredShooterAngleSolenoidState() != ReferenceData.getInstance().getShooterData().getCurrentShooterAngleSolenoidState()) {
			if(ReferenceData.getInstance().getShooterData().getDesiredShooterAngleSolenoidState() == ShooterRef.EXTEND) {
				getShooterAngleSolenoid().set(Value.kForward);
			} else {
				getShooterAngleSolenoid().set(Value.kReverse);
			}
		}
	}
	
	public DoubleSolenoid getMiddleSolenoid() {
		return middleSolenoid;
	}
	
	public DoubleSolenoid getInnerSolenoids() {
		return innerSolenoids; 
	}
	
	public DoubleSolenoid getOuterSolenoids() {
		return outerSolenoids; 
	}
	
	public DoubleSolenoid getShooterAngleSolenoid() {
		return shooterAngleSolenoid;
	}
}
