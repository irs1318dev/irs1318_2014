package irs2014.driveTrainTank;

import irs2014.components.RobotComponentBase;
import irs2014.dipSwitch.DipSwitchRef;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import irs2014.generalOperations.EncoderAngularVelocity;
import irs2014.generalOperations.EncoderAngularVelocityFPGA;

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
//		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT) {
//			return new EncoderAngularVelocity(PortRef.COMPETITION_ENCODER_L_A, PortRef.COMPETITION_ENCODER_L_B);
//		} else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT) {
//			return new EncoderAngularVelocity(PortRef.PRACTICE_ENCODER_L_A, PortRef.PRACTICE_ENCODER_L_B);
//		}
//		return null;
		return new EncoderAngularVelocity(PortRef.PRACTICE_ENCODER_L_A, PortRef.PRACTICE_ENCODER_L_B);
	}

	public EncoderAngularVelocity getNewRightEncoder() {
//		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT) {
//			return new EncoderAngularVelocity(PortRef.COMPETITION_ENCODER_R_A, PortRef.COMPETITION_ENCODER_R_B);
//		} else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT) {
//			return new EncoderAngularVelocity(PortRef.PRACTICE_ENCODER_R_A, PortRef.PRACTICE_ENCODER_R_B);
//		}
//		return null;
		return new EncoderAngularVelocity(PortRef.PRACTICE_ENCODER_R_A, PortRef.PRACTICE_ENCODER_R_B);
	}

	double count;
	
	public void teleopPeriodic(){

		double rencv = encoderR.getRate();
		double lencv = encoderL.getRate();
		
		ReferenceData.getInstance().getDriveTrainData().getRightEncoderData().setVelocity(encoderR.getRate());
		ReferenceData.getInstance().getDriveTrainData().getLeftEncoderData().setVelocity(encoderL.getRate());
		ReferenceData.getInstance().getDriveTrainData().getRightEncoderData().setTicks(encoderR.getDistance());
		ReferenceData.getInstance().getDriveTrainData().getLeftEncoderData().setTicks(encoderL.getDistance());
		
	}
	
	public void setEncoders(EncoderAngularVelocity eR, EncoderAngularVelocity eL){
		encoderR = eR;
		encoderL = eL;
	}

}
 