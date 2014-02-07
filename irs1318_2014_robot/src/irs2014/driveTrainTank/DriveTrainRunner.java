package irs2014.driveTrainTank;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.Talon;

public class DriveTrainRunner extends RobotComponentBase {
	//two motors
	private Talon rightTalon, leftTalon;
	
	//read speed from a data class and update
	
	public void robotInit(){
		rightTalon = getNewRightTalon();//new Talon(PortRef.SIDECAR_SLOT, PortRef.TALON_R);
		leftTalon = getNewLeftTalon();//new Talon(PortRef.SIDECAR_SLOT, PortRef.TALON_L);
	}
	
	public Talon getNewRightTalon(){
		return new Talon(PortRef.SIDECAR_SLOT, PortRef.TALON_R);
	}

	public Talon getNewLeftTalon(){
		return new Talon(PortRef.SIDECAR_SLOT, PortRef.TALON_L);
	}
	
	public Talon getRightTalon() {
		return rightTalon;
	}
	
	public Talon getLeftTalon() {
		return leftTalon;
	}
	
	public void teleopPeriodic(){
		double rightVal = ReferenceData.getInstance().getDriveTrainData().getRightPIDData().getPIDVelocity();
//		System.out.println("rightVal: " + rightVal);
		rightVal = Math.min(rightVal, 1);
		rightVal = Math.max(rightVal, -1);
		System.out.println("rightVal: " + rightVal);
		getRightTalon().set(rightVal);
				
		double leftVal = ReferenceData.getInstance().getDriveTrainData().getLeftPIDData().getPIDVelocity();
//		System.out.println("leftVal: " + leftVal);
		leftVal = Math.min(leftVal, 1);
		leftVal = Math.max(leftVal, -1);
		System.out.println("leftVal: " + leftVal);
		getLeftTalon().set(leftVal);


	}
}	

