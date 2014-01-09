package irs2014.driveTrainTank;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;
import irs2014.generalOpperations.JoystickFilter;
import irs2014.generalOpperations.PID;

public class DriveTrainPIDCalculator extends RobotComponentBase{
	
	//TODO wright test
	
	PID rightPID;
	PID leftPID;
	long count;
	
	public void robotInit(){
//		rightPID = new PID(0, 0, 0, 1);
		//flip sign if not work right after pull
		rightPID = new PID(0, 0, -0.001, 0);
//		rightPID = new PID(0, 0, 0.0, 0);
		rightPID.setKScale(2800); 
		
//		all left negative
//		leftPID = new PID(0, 0, 0, -1);
		leftPID = new PID(0, 0, -0.001, 0);
//		leftPID = new PID(0, 0, 0.0, 0);
		leftPID.setKScale(2800);
		//TODO ask James about values
		count = 0;
	}
	
	public void teleopPeriodic(){
		
		double rset = ReferenceData.getInstance().getDriveTrainData().getRightSpeedSetPoint();
		double rencv = ReferenceData.getInstance().getDriveTrainData().getRightEncoder();
		double rkf = 0.1;
		double rkd = -0.0004; // practice bot
//		double rkd = 0.00020; // competition bot
		double rkscale = 2800;
		double rightPIDVal = rkf * rset + rkd*(rkscale * rset - rencv);

		double lset = - ReferenceData.getInstance().getDriveTrainData().getLeftSpeedSetPoint();
		double lencv = ReferenceData.getInstance().getDriveTrainData().getLeftEncoder();
		double lkf = 0.1;
		double lkd = -0.0005;		// practice bot
//		double lkd = 0.00022;		// competition bot
		double lkscale = 2800;

		double leftPIDVal = lkf * lset + lkd*(lkscale * lset - lencv);

		JoystickFilter.Speed motorSpeed = new JoystickFilter.Speed();
		motorSpeed.speedL = leftPIDVal;
		motorSpeed.speedR = rightPIDVal;
//		JoystickFilter.Speed clampedSpeed = JoystickFilter.applyClamp(motorSpeed, 1.0);
		
		if (count%1000==0) {
			System.out.println("lset="+lset+" , rset="+rset);
			System.out.println("lencv="+lencv+" , rencv="+rencv);
			System.out.println("LPID="+ReferenceData.getInstance().getDriveTrainData().getLeftPIDSpeed()
					+", RPID="+ReferenceData.getInstance().getDriveTrainData().getRightPIDSpeed()
					);
//			System.out.println("lmotor="+clampedSpeed.speedL+" , rmotor="+clampedSpeed.speedR);
		}
		count++;

		ReferenceData.getInstance().getDriveTrainData().setRightPIDSpeed(rightPIDVal);
		ReferenceData.getInstance().getDriveTrainData().setLeftPIDSpeed(leftPIDVal);

//		ReferenceData.getInstance().getDriveTrainData().setRightPIDSpeed(clampedSpeed.speedR);
//		ReferenceData.getInstance().getDriveTrainData().setLeftPIDSpeed(clampedSpeed.speedL);

//		System.out.println("LPIDConst:"+leftPID.toString()
//				+", RPIDConst:"+rightPID.toString()
//				);

	}
	
	//PID.setSetpoint(what you want the encoder to read = what the joystick says it should read) 
	//PID.input(acutal angular velocity)
	//PID.getOutPut() = value to give motors 
}
