package irs2014.shooter2;

import irs2014.components.RobotComponentBase;
import irs2014.dipSwitch.DipSwitchRef;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;

public class ShooterRunner2 extends RobotComponentBase {
	
	private DoubleSolenoid middleSolenoid;
	private DoubleSolenoid innerSolenoidL;
	private DoubleSolenoid innerSolenoidR;
	private DoubleSolenoid outerSolenoidL;
	private DoubleSolenoid outerSolenoidR;
	private DoubleSolenoid shooterAngleSolenoid; 
		
	public void robotInit() {
		middleSolenoid = getNewMiddleSolenoid();
		innerSolenoidL = getNewInnerSolenoidsL();
		innerSolenoidR = getNewInnerSolenoidsR();
		outerSolenoidL = getNewOuterSolenoidsL();
		outerSolenoidR = getNewOuterSolenoidsR();
		shooterAngleSolenoid = getNewShooterAngleSolenoid();
		
		System.out.println("DoubleSolenoids robotInit()");
		 
		
	}
	
	public void teleopPeriodic() {
		
		boolean desiredMiddleSolenoidState = ReferenceData.getInstance().getShooterData2().getDesiredMiddleSolenoidState();
		boolean currentMiddleSolenoidState = ReferenceData.getInstance().getShooterData2().getCurrentMiddleSolenoidState();
		boolean middleSolenoidEtended = (ReferenceData.getInstance().getShooterData2().getDesiredMiddleSolenoidState() == ShooterRef2.EXTEND);
		
//		System.out.println("desiredMiddleSolenoidState: " + desiredMiddleSolenoidState + 
//							"\n currentMiddleSolenoidState: " + currentMiddleSolenoidState + 
//							"\n middleSolenoidExtended: " + middleSolenoidEtended + 
//							"\n desired compared to current for middle: " + (desiredMiddleSolenoidState != currentMiddleSolenoidState));
		
		if(desiredMiddleSolenoidState != currentMiddleSolenoidState) {
			System.out.println("desire is not current for middle");
			if(middleSolenoidEtended) {
				getMiddleSolenoid().set(Value.kForward);
//				System.out.println("middleSolenoid forward");
			} else {
				getMiddleSolenoid().set(Value.kReverse);
				System.out.println("middleSolenoid reverse");
			}
			ReferenceData.getInstance().getShooterData2().setCurrentMiddleSolenoidState(!currentMiddleSolenoidState);
		}
		
		boolean desiredInnerSolenoidState = ReferenceData.getInstance().getShooterData2().getDesiredInnerSolenoidsState();
		boolean currentInnerSolenoidState = ReferenceData.getInstance().getShooterData2().getCurrentInnerSolenoidsState();
		boolean innerSolenoidExtended = (ReferenceData.getInstance().getShooterData2().getDesiredInnerSolenoidsState() == ShooterRef2.EXTEND);
		
		if(desiredInnerSolenoidState != currentInnerSolenoidState) {
			if(innerSolenoidExtended){
//				getInnerSolenoids().set(Value.kForward);
				innerSolenoidL.set(Value.kForward);
				innerSolenoidR.set(Value.kForward);
			} else {
//				getInnerSolenoids().set(Value.kReverse);
				innerSolenoidL.set(Value.kReverse);
				innerSolenoidR.set(Value.kReverse);
			}
			ReferenceData.getInstance().getShooterData2().setCurrentInnerSolenoidsState(!currentInnerSolenoidState);
		}
		
		boolean desiredOuterSolenoidState = ReferenceData.getInstance().getShooterData2().getDesiredOuterSolenoidsState();
		boolean currentOuterSolenoidState = ReferenceData.getInstance().getShooterData2().getCurrentOuterSolenoidsState();
		boolean outerSolenoidExtended = (ReferenceData.getInstance().getShooterData2().getDesiredOuterSolenoidsState() == ShooterRef2.EXTEND);
		
		if(desiredOuterSolenoidState != currentOuterSolenoidState) {
			if(outerSolenoidExtended){
//				getOuterSolenoids().set(Value.kForward);
				outerSolenoidL.set(Value.kForward);
				outerSolenoidR.set(Value.kForward);
			} else {
//				getOuterSolenoids().set(Value.kReverse);
				outerSolenoidL.set(Value.kReverse);
				outerSolenoidR.set(Value.kReverse);
			}
			ReferenceData.getInstance().getShooterData2().setCurrentOuterSolenoidsState(!currentOuterSolenoidState);
		}
		
		boolean desiredShooterAngle = ReferenceData.getInstance().getShooterData2().getDesiredShooterAngleSolenoidState();
		boolean currentShooterAngle = ReferenceData.getInstance().getShooterData2().getCurrentShooterAngleSolenoidState();
		boolean shooterAngleExtended = (ReferenceData.getInstance().getShooterData2().getDesiredShooterAngleSolenoidState() == ShooterRef2.EXTEND);
		
		if(desiredShooterAngle != currentShooterAngle) {
			if(shooterAngleExtended) {
				getShooterAngleSolenoid().set(Value.kForward);
			} else {
				getShooterAngleSolenoid().set(Value.kReverse);
			}
			ReferenceData.getInstance().getShooterData2().setCurrentShooterAngleSolenoidState(!currentShooterAngle);
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
	public DoubleSolenoid getShooterAngleSolenoid() {
		return shooterAngleSolenoid;
	}
}
