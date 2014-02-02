package org.usfirst.ihs1318.shooter;

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
		if(ReferenceData.getInstance().getShooterData().getMiddleSolenoidState() == ShooterRef.EXTEND) {
			middleSolenoid.set(Value.kForward);
		} else {
			middleSolenoid.set(Value.kReverse);
		}
		if(ReferenceData.getInstance().getShooterData().getInnerSolenoidsState() == ShooterRef.EXTEND){
			innerSolenoids.set(Value.kForward);
		} else {
			innerSolenoids.set(Value.kReverse);
		}
		if(ReferenceData.getInstance().getShooterData().getOuterSolenoidsState() == ShooterRef.EXTEND){
			outerSolenoids.set(Value.kForward);
		} else {
			outerSolenoids.set(Value.kReverse);
		}
		if(ReferenceData.getInstance().getShooterData().getShooterAngleSolenoidState() == ShooterRef.EXTEND) {
			shooterAngleSolenoid.set(Value.kForward);
		} else {
			shooterAngleSolenoid.set(Value.kReverse);
		}
	}
}
