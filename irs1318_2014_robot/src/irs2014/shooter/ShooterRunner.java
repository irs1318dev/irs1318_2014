package irs2014.shooter;

import irs2014.components.RobotComponentBase;
import irs2014.dipSwitch.DipSwitchRef;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ShooterRunner extends RobotComponentBase {
	
	private DoubleSolenoid middleSolenoid;
	private DoubleSolenoid innerSolenoids;
	private DoubleSolenoid outerSolenoids;
	private DoubleSolenoid shooterAngleSolenoid; 
	
	public void robotInit() {
		middleSolenoid = getNewMiddleSolenoid();
		innerSolenoids = getNewInnerSolenoids();
		outerSolenoids = getNewOuterSolenoids();
		shooterAngleSolenoid = getNewShooterAngleSolenoid();
		
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
	
	public DoubleSolenoid getNewMiddleSolenoid(){
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.COMPETITION_SHOOTER_ANGLE_SOLENOID_EXTENDER_PORT, PortRef.COMPETITION_SHOOTER_MIDDLE_SOLENOID_RETRACTOR_PORT);
		}else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.PRACTICE_SHOOTER_ANGLE_SOLENOID_EXTENDER_PORT, PortRef.PRACTICE_SHOOTER_MIDDLE_SOLENOID_RETRACTOR_PORT);
		}
		return null;
	}
	
	public DoubleSolenoid getNewInnerSolenoids(){
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.COMPETITION_SHOOTER_INNER_SOLENOIDS_EXTENDER_PORT, PortRef.COMPETITION_SHOOTER_INNER_SOLENOIDS_RETRACTOR_PORT);
		}else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.PRACTICE_SHOOTER_INNER_SOLENOIDS_EXTENDER_PORT, PortRef.PRACTICE_SHOOTER_INNER_SOLENOIDS_RETRACTOR_PORT);
		}
		return null;
	}
	
	public DoubleSolenoid getNewOuterSolenoids(){
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.COMPETITION_SHOOTER_OUTER_SOLENOIDS_EXTENDER_PORT, PortRef.COMPETITION_SHOOTER_OUTER_SOLENOIDS_RETRACTOR_PORT);
		}else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.PRACTICE_SHOOTER_OUTER_SOLENOIDS_EXTENDER_PORT, PortRef.PRACTICE_SHOOTER_OUTER_SOLENOIDS_RETRACTOR_PORT);
		}
		return null;
	}
	
	public DoubleSolenoid getNewShooterAngleSolenoid(){
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.COMPETITION_SHOOTER_ANGLE_SOLENOID_EXTENDER_PORT, PortRef.COMPETITION_SHOOTER_ANGLE_SOLENOID_RETRACTOR_PORT);
		}else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.PRACTICE_SHOOTER_ANGLE_SOLENOID_EXTENDER_PORT, PortRef.PRACTICE_SHOOTER_ANGLE_SOLENOID_RETRACTOR_PORT);
		}
		return null;
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