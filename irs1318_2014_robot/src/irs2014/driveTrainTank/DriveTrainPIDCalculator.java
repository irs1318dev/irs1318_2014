package irs2014.driveTrainTank;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.EncoderState;
import irs2014.generalData.ReferenceData;
import irs2014.generalOperations.JoystickFilter;
import irs2014.generalOperations.PID;

public class DriveTrainPIDCalculator extends RobotComponentBase{
	
	//TODO write test
	
	long count;
	
	public void robotInit(){
		
	}
	
	public void teleopPeriodic(){
		if (ReferenceData.getInstance().getEncoderState().getPIDType() == EncoderState.VELOCITY_PID){
			velocityPIDCalculator();
		}else if(ReferenceData.getInstance().getEncoderState().getPIDType() == EncoderState.POSITION_PID){
			positionPIDCalculator();
		}
	}

	private void velocityPIDCalculator(){
		double rightVelocitySetpoint = ReferenceData.getInstance().getDriveTrainData().getRightPIDData().getVelocitySetpoint();
		double rightEncoderVelocity = ReferenceData.getInstance().getDriveTrainData().getRightEncoderData().getVelocity();
		double rightKf = ReferenceData.getInstance().getPIDConstantData().getRightKf();
		double rightKd = ReferenceData.getInstance().getPIDConstantData().getRightKd();
		double rightKscale = 2800;
		
		double rightPIDVal = rightKf * rightVelocitySetpoint + rightKd*(rightKscale * rightVelocitySetpoint - rightEncoderVelocity);

		double leftVelocitySetpoint = - ReferenceData.getInstance().getDriveTrainData().getLeftPIDData().getVelocitySetpoint();
		double leftEncoderVelocity = ReferenceData.getInstance().getDriveTrainData().getLeftEncoderData().getVelocity();
		double leftKf = ReferenceData.getInstance().getPIDConstantData().getLeftKf();
		double leftKd = ReferenceData.getInstance().getPIDConstantData().getLeftKd();
		double leftKscale = 2800;

		double leftPIDVal = leftKf * leftVelocitySetpoint + leftKd*(leftKscale * leftVelocitySetpoint - leftEncoderVelocity);

		JoystickFilter.Velocity motorVelocity = new JoystickFilter.Velocity();
		motorVelocity.leftVelocity = leftPIDVal;
		motorVelocity.rightVelocity = rightPIDVal;
//		JoystickFilter.Velocity clampedVelocity = JoystickFilter.applyClamp(motorVelocity, 1.0);
		
		if (count%1000==0) {
//			System.out.println("leftVelocitySetpoint=" + leftVelocitySetpoint + " , rightVelocitySetpoint=" + rightVelocitySetpoint);
//			System.out.println("leftEncoderVelocity=" + leftEncoderVelocity + " , rightEncoderVelocity=" + rightEncoderVelocity);
//			System.out.println("leftPIDVal=" + ReferenceData.getInstance().getDriveTrainData().getLeftPIDData().getPIDVelocity()
//					+ ", rightPIDVal=" + ReferenceData.getInstance().getDriveTrainData().getRightPIDData().getPIDVelocity());
//			System.out.println("leftVelocity="+clampedVelocity.leftVelocity+" , rightVelocity="+clampedVelocity.rightVelocity);
		}
		count++;

		ReferenceData.getInstance().getDriveTrainData().getRightPIDData().setPIDVelocity(rightPIDVal);
		ReferenceData.getInstance().getDriveTrainData().getLeftPIDData().setPIDVelocity(leftPIDVal);

//		ReferenceData.getInstance().getDriveTrainData().getRightPIDData().setPIDVelocity(clampedVelocity.rightVelocity);
//		ReferenceData.getInstance().getDriveTrainData().getLeftPIDData().setPIDVelocity(clampedVelocity.leftVelocity);

	}
	
	private void positionPIDCalculator(){
		double rightPostionSetpoint = ReferenceData.getInstance().getDriveTrainData().getRightPIDData().getPositionSetpoint();
		double rightEncoderTicks = ReferenceData.getInstance().getDriveTrainData().getRightEncoderData().getTicks();
		double rightEncoderVelocity = ReferenceData.getInstance().getDriveTrainData().getRightEncoderData().getVelocity();
		double rightKp = ReferenceData.getInstance().getPIDConstantData().getRightPositionalKp();
		double rightKd = ReferenceData.getInstance().getPIDConstantData().getRightPositionalKd();
		
		double rightPIDVal = rightKp * (rightPostionSetpoint - rightEncoderTicks) + rightKd * rightEncoderVelocity;
		rightPIDVal = Math.min(.2, rightPIDVal);
		rightPIDVal = Math.max(-.2, rightPIDVal);
		
		double leftPostionSetpoint = - ReferenceData.getInstance().getDriveTrainData().getLeftPIDData().getPositionSetpoint();
		double leftEncoderTicks = ReferenceData.getInstance().getDriveTrainData().getLeftEncoderData().getTicks();
		double leftEncoderVelocity = ReferenceData.getInstance().getDriveTrainData().getLeftEncoderData().getVelocity();
		double leftKp = ReferenceData.getInstance().getPIDConstantData().getLeftPositionalKp();
		double leftKd = ReferenceData.getInstance().getPIDConstantData().getLeftPositionalKd();
		
		double leftPIDVal = leftKp * (leftPostionSetpoint - leftEncoderTicks) + leftKd * leftEncoderVelocity;
		leftPIDVal = Math.min(.2, leftPIDVal);
		leftPIDVal = Math.max(-.2, leftPIDVal);
		
		JoystickFilter.Velocity motorVelocity = new JoystickFilter.Velocity();
		motorVelocity.leftVelocity = leftPIDVal;
		motorVelocity.rightVelocity = rightPIDVal;
		
		if (count%1000==0){
//			System.out.println("leftPostionSetpoint=" + leftPostionSetpoint + " , rightPostionSetpoint=" + rightPostionSetpoint);
//			System.out.println("leftEncoderTicks=" + leftEncoderTicks + " , rightEncoderTicks=" + rightEncoderTicks);
//			System.out.println("leftPIDVal=" + ReferenceData.getInstance().getDriveTrainData().getLeftPIDData().getPIDVelocity()
//					+ ", rightPIDVal=" + ReferenceData.getInstance().getDriveTrainData().getRightPIDData().getPIDVelocity());
		}
		count++;
		
		ReferenceData.getInstance().getDriveTrainData().getRightPIDData().setPIDVelocity(rightPIDVal);
		ReferenceData.getInstance().getDriveTrainData().getLeftPIDData().setPIDVelocity(leftPIDVal);
	}
}
