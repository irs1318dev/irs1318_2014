package irs2014.shooter;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import irs2014.components.RobotComponentBase;
import irs2014.dipSwitch.DipSwitchRef;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;

public class AngleRunner extends RobotComponentBase{

	private DoubleSolenoid shooterAngleSolenoid; 
	
	public void robotInit(){
		shooterAngleSolenoid = getNewShooterAngleSolenoid();
	}
	
	public void teleopPeriodic(){
		if(ReferenceData.getInstance().getAngleData().getDesiredShooterAngle() == ShooterRef.EXTEND &&
				ReferenceData.getInstance().getAngleData().getCurrentShooterAngle() == ShooterRef.RETRACT ){
			shooterAngleSolenoid.set(Value.kForward);
			ReferenceData.getInstance().getAngleData().setCurrentShooterAngle(ShooterRef.EXTEND);
			
		}else if(ReferenceData.getInstance().getAngleData().getDesiredShooterAngle() == ShooterRef.RETRACT &&
				ReferenceData.getInstance().getAngleData().getCurrentShooterAngle() == ShooterRef.EXTEND ){
			shooterAngleSolenoid.set(Value.kReverse);
			ReferenceData.getInstance().getAngleData().setCurrentShooterAngle(ShooterRef.RETRACT);
		}
	}
	
	public DoubleSolenoid getNewShooterAngleSolenoid(){
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, PortRef.COMPETITION_SHOOTER_ANGLE_SOLENOID_EXTENDER_PORT, PortRef.COMPETITION_SHOOTER_ANGLE_SOLENOID_RETRACTOR_PORT);
		}else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT){
			return new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, PortRef.PRACTICE_SHOOTER_ANGLE_SOLENOID_EXTENDER_PORT, PortRef.PRACTICE_SHOOTER_ANGLE_SOLENOID_RETRACTOR_PORT);
		}
		return null;
	}
	
}
