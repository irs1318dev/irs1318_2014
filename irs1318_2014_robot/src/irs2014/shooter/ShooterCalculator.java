package irs2014.shooter;

import irs2014.collector.CollectorRef;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.Utility;
import irs2014.shooter.ShooterData;


public class ShooterCalculator extends RobotComponentBase {
	boolean hasExtended;
	boolean hasRetracted;
	
	public boolean getHasExtended() {
		return hasExtended;
	}
	
	public boolean getHasRetracted() {
		return hasRetracted;
	}
	
	public void teleopPeriodic() {
		
//		
	}
	
	
	public long getFPGATime() {
		return Utility.getFPGATime();
	}
	
	public void setDesiredInnerSolenoidStateExtend() {
		ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
		hasExtended = true;
	}
	
	public void setDesiredMiddleSolenoidStateExtend() {
		ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
		hasExtended = true;
	}
	
	public void setDesiredOuterSolenoidStateExtend() {
		ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.EXTEND);
		hasExtended = true;
	}
	
	public void setDesiredInnerSolenoidStateRetract() {
		ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.RETRACT);
		hasRetracted = true;
	}
	
	public void setDesiredMiddleSolenoidStateRetract() {
		ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.RETRACT);
		hasRetracted = true;
	}
	
	public void setDesiredOuterSolenoidStateRetract() {
		ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT);
		hasRetracted = true;
	}
	
}

