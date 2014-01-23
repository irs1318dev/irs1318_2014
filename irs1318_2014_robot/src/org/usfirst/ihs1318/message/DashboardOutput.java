package org.usfirst.ihs1318.message;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.MessageRef;

//import edu.wpi.first.wpilibj.SmartDashboard;

public class DashboardOutput {

	public void sendData() {
//
//		synchronized (ReferenceData.getInstance()) {
//			// calculated theta in degrees for display purposes
//			double theta = ReferenceData.getInstance().getOrientation().getTheta();
//			double angle = Math.floor((theta * 180/Math.PI));
////			System.out.println("raw: "+angle);
//			angle += 180;
////			System.out.println("pi: "+angle);
//			if (angle>360) angle -= 360;
////			System.out.println("norm: "+angle);
//			SmartDashboard.log(angle, MessageRef.THETA);
//
//			//arm encoder values
//			SmartDashboard.log(ReferenceData.getInstance().getArmEncoderValues()
//					.getEncoderRate(), MessageRef.ARM_ENC_RATE);
//			SmartDashboard.log(ReferenceData.getInstance().getArmEncoderValues()
//					.getEncoderValue(), MessageRef.ARM_ENC_VALUE);
//			
//			//line sensor values
//			SmartDashboard.log(ReferenceData.getInstance().getLineSensorValues()
//					.getLineSensorDisplay(), MessageRef.LINE_STATE_DISPLAY);
//			SmartDashboard.log(ReferenceData.getInstance().getLineSensorValues()
//					.isLeft(), MessageRef.LINE_LEFT);
//			SmartDashboard.log(ReferenceData.getInstance().getLineSensorValues()
//					.isCenter(), MessageRef.LINE_CENTER);
//			SmartDashboard.log(ReferenceData.getInstance().getLineSensorValues()
//					.isRight(), MessageRef.LINE_RIGHT);
//			
//			// wheel joystick inputs
//			SmartDashboard.log(ReferenceData.getInstance().getInputVelocity()
//					.getDesiredJx(), MessageRef.DESIRED_JX);
//			SmartDashboard.log(ReferenceData.getInstance().getInputVelocity()
//					.getDesiredJy(), MessageRef.DESIRED_JY);
//			SmartDashboard.log(ReferenceData.getInstance().getInputVelocity()
//					.getDesiredJRY(), MessageRef.DESIRED_JRY);
//			SmartDashboard.log(ReferenceData.getInstance().getInputVelocity()
//					.getDesiredW(), MessageRef.DESIRED_OMEGA);
//
//			// gyro status
//			SmartDashboard.log(ReferenceData.getInstance().getOrientation()
//					.isGyroActive(), MessageRef.GYRO_STATUS);
//
//			// wheel encoder values
//			SmartDashboard.log(ReferenceData.getInstance()
//					.getWheelEncoderRates().getLf(),
//					MessageRef.WHEEL_ENC_LF);
//			SmartDashboard.log(ReferenceData.getInstance()
//					.getWheelEncoderRates().getLr(),
//					MessageRef.WHEEL_ENC_LR);
//			SmartDashboard.log(ReferenceData.getInstance()
//					.getWheelEncoderRates().getRf(),
//					MessageRef.WHEEL_ENC_RF);
//			SmartDashboard.log(ReferenceData.getInstance()
//					.getWheelEncoderRates().getRr(),
//					MessageRef.WHEEL_ENC_RR);
//
//			SmartDashboard.log(ReferenceData.getInstance()
//					.getWheelEncoderTicks().getLf(),
//					MessageRef.WHEEL_ENC_TICK_LF);
//			SmartDashboard.log(ReferenceData.getInstance()
//					.getWheelEncoderTicks().getLr(),
//					MessageRef.WHEEL_ENC_TICK_LR);
//			SmartDashboard.log(ReferenceData.getInstance()
//					.getWheelEncoderTicks().getRf(),
//					MessageRef.WHEEL_ENC_TICK_RF);
//			SmartDashboard.log(ReferenceData.getInstance()
//					.getWheelEncoderTicks().getRr(),
//					MessageRef.WHEEL_ENC_TICK_RR);
//
//
//			// arm reference
//			SmartDashboard.log(ReferenceData.getInstance().getArmInput()
//					.getDesiredJy(), MessageRef.DESIRED_ARM_VELOCITY);
//			SmartDashboard.log(ReferenceData.getInstance().getArmInput()
//					.getDesiredSetPoint(), MessageRef.DESIRED_ARM_HEIGHT);
//
//		}
//		synchronized (KinematicData.getInstance()) {
//			// wheel operation
//			SmartDashboard.log(KinematicData.getInstance()
//					.getWmSpeed().getLf(),
//					MessageRef.WHEEL_LF);
//			SmartDashboard.log(KinematicData.getInstance()
//					.getWmSpeed().getLr(),
//					MessageRef.WHEEL_LR);
//			SmartDashboard.log(KinematicData.getInstance()
//					.getWmSpeed().getRf(),
//					MessageRef.WHEEL_RF);
//			SmartDashboard.log(KinematicData.getInstance()
//					.getWmSpeed().getRr(),
//					MessageRef.WHEEL_RR);
//			
//			// minibot deploy
//			SmartDashboard.log(KinematicData.getInstance().getMiniBotValue()
//					.getState(), MessageRef.MINIBOT_DEPLOY);
//
////			// camera flip
////			SmartDashboard.log(KinematicData.getInstance().getServoDirection()
////					.getServoDisplay(), MessageRef.CAMERA_FLIP);
//
//			// inner tube request
////			SmartDashboard.log(KinematicData.getInstance()
////					.getTubeRequestValue().getSetting(),
////					MessageRef.TUBE_REQUEST);
//
//			// arm operation
//			SmartDashboard.log(KinematicData.getInstance().getArmData()
//					.getArmDistance(), MessageRef.ARM_DISTANCE);
//			SmartDashboard.log(KinematicData.getInstance().getArmData()
//					.getArmVelocity(), MessageRef.CALC_ARM_VELOCITY);
//			SmartDashboard.log(KinematicData.getInstance().getClawValue()
//					.getState(), MessageRef.CLAW);
//
//			SmartDashboard.log(KinematicData.getInstance().getWristValue()
//					.getWristPosition(), MessageRef.WRIST_MSG);
//			SmartDashboard.log(KinematicData.getInstance().getWristValue()
//					.isDistalWristBool(), MessageRef.WRIST_DISTAL);
//			SmartDashboard.log(KinematicData.getInstance().getWristValue()
//					.isProximalWristBool(), MessageRef.WRIST_PROXIMAL);
//
//			// air compressor operation
//			SmartDashboard.log(KinematicData.getInstance().getAirCompressor()
//					.getState(), MessageRef.AIR_COMPRESSOR_STATUS);
//		}

	}
}
