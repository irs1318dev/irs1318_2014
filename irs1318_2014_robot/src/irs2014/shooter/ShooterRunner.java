package irs2014.shooter;

import irs2014.components.RobotComponentBase;
import irs2014.dipSwitch.DipSwitchRef;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Utility;

public class ShooterRunner extends RobotComponentBase {
	
	private DoubleSolenoid middleSolenoid;
	private DoubleSolenoid innerSolenoidL;
	private DoubleSolenoid innerSolenoidR;
	private DoubleSolenoid outerSolenoidL;
	private DoubleSolenoid outerSolenoidR;
		
	public void robotInit() {
		middleSolenoid = getNewMiddleSolenoid();
		innerSolenoidL = getNewInnerSolenoidsL();
		innerSolenoidR = getNewInnerSolenoidsR();
		outerSolenoidL = getNewOuterSolenoidsL();
		outerSolenoidR = getNewOuterSolenoidsR();
		
		System.out.println("DoubleSolenoids robotInit()");
		 
		
	}
	
	public void teleopPeriodic() {
		
//		System.out.println((ReferenceData.getInstance().getShooterData().getDesiredShooterState() != ReferenceData.getInstance().getShooterData().getCurrentShooterState()));
		
		if(ReferenceData.getInstance().getShooterData().getDesiredShooterState() == true &&
				ReferenceData.getInstance().getShooterData().getCurrentShooterState() == false ){
			setAllOuterSolenoids(Value.kForward);
			middleSolenoid.set(Value.kForward);
			ReferenceData.getInstance().getShooterData().setCurrentShooterState(true);
			ReferenceData.getInstance().getShooterData().setInShot(true);
			ReferenceData.getInstance().getShooterData().setTimeLastShot(Utility.getFPGATime());
		}else if(ReferenceData.getInstance().getShooterData().getDesiredShooterState() == false &&
				ReferenceData.getInstance().getShooterData().getCurrentShooterState() == true ){
			setAllOuterSolenoids(Value.kReverse);
			middleSolenoid.set(Value.kReverse);
			ReferenceData.getInstance().getShooterData().setCurrentShooterState(false);
		}
		
	}


	public DoubleSolenoid getNewMiddleSolenoid(){
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, PortRef.COMPETITION_SHOOTER_ANGLE_SOLENOID_EXTENDER_PORT, PortRef.COMPETITION_SHOOTER_MIDDLE_SOLENOID_RETRACTOR_PORT);
		}else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, PortRef.PRACTICE_SHOOTER_MIDDLE_SOLENOID_EXTENDER_PORT, PortRef.PRACTICE_SHOOTER_MIDDLE_SOLENOID_RETRACTOR_PORT);
		}
		return null;
	}
	
	public DoubleSolenoid getNewInnerSolenoidsL(){
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, PortRef.COMPETITION_SHOOTER_INNER_SOLENOIDS_EXTENDER_PORT, PortRef.COMPETITION_SHOOTER_INNER_SOLENOIDS_RETRACTOR_PORT);
		}else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, PortRef.PRACTICE_SHOOTER_INNER_SOLENOIDS_EXTENDER_PORT_L, PortRef.PRACTICE_SHOOTER_INNER_SOLENOIDS_RETRACTOR_PORT_L);
		}
		return null;
	}
	
	public DoubleSolenoid getNewInnerSolenoidsR(){
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, PortRef.COMPETITION_SHOOTER_INNER_SOLENOIDS_EXTENDER_PORT, PortRef.COMPETITION_SHOOTER_INNER_SOLENOIDS_RETRACTOR_PORT);
		}else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, PortRef.PRACTICE_SHOOTER_INNER_SOLENOIDS_EXTENDER_PORT_R, PortRef.PRACTICE_SHOOTER_INNER_SOLENOIDS_RETRACTOR_PORT_R);
		}
		return null;
	}
	
	public DoubleSolenoid getNewOuterSolenoidsL(){
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, PortRef.COMPETITION_SHOOTER_OUTER_SOLENOIDS_EXTENDER_PORT_L, PortRef.COMPETITION_SHOOTER_OUTER_SOLENOIDS_RETRACTOR_PORT_L);
		}else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, PortRef.PRACTICE_SHOOTER_OUTER_SOLENOIDS_EXTENDER_PORT_L, PortRef.PRACTICE_SHOOTER_OUTER_SOLENOIDS_RETRACTOR_PORT_L);
		}
		return null;
	}
	
	public DoubleSolenoid getNewOuterSolenoidsR(){
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, PortRef.COMPETITION_SHOOTER_OUTER_SOLENOIDS_EXTENDER_PORT_L, PortRef.COMPETITION_SHOOTER_OUTER_SOLENOIDS_RETRACTOR_PORT_L);
		}else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, PortRef.PRACTICE_SHOOTER_OUTER_SOLENOIDS_EXTENDER_PORT_R, PortRef.PRACTICE_SHOOTER_OUTER_SOLENOIDS_RETRACTOR_PORT_R);
		}
		return null;
	}
	
	public DoubleSolenoid getNewShooterAngleSolenoid(){
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, PortRef.COMPETITION_SHOOTER_ANGLE_SOLENOID_EXTENDER_PORT, PortRef.COMPETITION_SHOOTER_ANGLE_SOLENOID_RETRACTOR_PORT);
		}else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, PortRef.PRACTICE_SHOOTER_ANGLE_SOLENOID_EXTENDER_PORT, PortRef.PRACTICE_SHOOTER_ANGLE_SOLENOID_RETRACTOR_PORT);
		}
		return null;
	}
	
	public DoubleSolenoid getMiddleSolenoid() {
		return middleSolenoid;
	}
//	
//	public DoubleSolenoid getInnerSolenoids() {
//		return innerSolenoidL; 
//	}
//	
//	public DoubleSolenoid getOuterSolenoids() {
//		return outerSolenoids; 
//	}
//	
	
	public void setAllOuterSolenoids(Value v){
		innerSolenoidL.set(v);
		innerSolenoidR.set(v);
		outerSolenoidL.set(v);
		outerSolenoidR.set(v);
	}
}
