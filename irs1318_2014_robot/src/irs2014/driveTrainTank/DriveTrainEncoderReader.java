package irs2014.driveTrainTank;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import irs2014.generalOpperations.EncoderAngularVelocity;

public class DriveTrainEncoderReader extends RobotComponentBase{
	
	EncoderAngularVelocity encoderR;
	EncoderAngularVelocity encoderL;
	
	public void robotInit(){
		encoderR = getNewRightEncoder();//new EncoderAngularVelocity(PortRef.ENCODER_R_A, PortRef.ENCODER_R_B);
		encoderR.setName("RightMotor");
		encoderL = getNewLeftEncoder();//new EncoderAngularVelocity(PortRef.ENCODER_L_A, PortRef.ENCODER_L_B);
		encoderL.setName("LeftMotor");
	}
	
	public EncoderAngularVelocity getNewLeftEncoder() {
		return new EncoderAngularVelocity(PortRef.ENCODER_L_A, PortRef.ENCODER_L_B);
	}

	public EncoderAngularVelocity getNewRightEncoder() {
		return new EncoderAngularVelocity(PortRef.ENCODER_R_A, PortRef.ENCODER_R_B);
	}

	double count;
	
	public void teleopPeriodic(){

		double rencv = encoderR.getRate();
		double lencv = encoderL.getRate();
		
		ReferenceData.getInstance().getDriveTrainData().setRightEncoder(encoderR.getRate());
		ReferenceData.getInstance().getDriveTrainData().setLeftEncoder(encoderL.getRate());
		ReferenceData.getInstance().getDriveTrainData().setRightEncoderTicks(encoderR.getDistance());
		ReferenceData.getInstance().getDriveTrainData().setLeftEncoderTicks(encoderL.getDistance());
		
	}
	
	public void setEncoders(EncoderAngularVelocity eR, EncoderAngularVelocity eL){
		encoderR = eR;
		encoderL = eL;
	}

}
 