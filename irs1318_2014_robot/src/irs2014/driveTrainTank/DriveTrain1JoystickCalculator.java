package irs2014.driveTrainTank;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;

public class DriveTrain1JoystickCalculator extends RobotComponentBase {
	
	//???eden: K vals help rep speed to be fed into robot ???
	
	private double K1 = 2.5;
	private double K2 = 0.9;
	
	private double K3 = K1;
	private double K4 = -K2;

	public void robotInit() {
	}
	
	public void setXScaleFactor(double xScaleFactor) {
		K1 = xScaleFactor;
		K3 = xScaleFactor;
	}
	public void setYScaleFactor(double yScaleFactor) {
		K2 = yScaleFactor;
		K4 = -yScaleFactor;
	}
	
	public void teleopPeriodic(){
		
		//ReferenceData.getInstance().getDriveTrainData().setLeftSpeedSetPoint()
//		System.out.println("***********Left Encoder: " + ReferenceData.getInstance().getDriveTrainData().getLeftEncoder());
//		System.out.println("***********Left Encoder ticks: " + ReferenceData.getInstance().getDriveTrainData().getLeftEncoderTicks());
//		System.out.println("***********Right Encoder: " + ReferenceData.getInstance().getDriveTrainData().getRightEncoder());
//		System.out.println("***********Right Encoder ticks: " + ReferenceData.getInstance().getDriveTrainData().getRightEncoderTicks());
//
		
		double y = ReferenceData.getInstance().getUserInputData().getJoystickY();
		double x = ReferenceData.getInstance().getUserInputData().getJoystickX();
		
//		System.out.println("*****Joystick X: " + ReferenceData.getInstance().getUserInputData().getJoystickX());
//		System.out.println("*****Joystick Y: " + ReferenceData.getInstance().getUserInputData().getJoystickY());
		
		double speedL = (K1 * x) + (K2 * y);
		double speedR = (K3 * x) + (K4 * y);
		
		ReferenceData.getInstance().getDriveTrainData().setLeftSpeedSetPoint(speedL);
		ReferenceData.getInstance().getDriveTrainData().setRightSpeedSetPoint(speedR);
		
		
		
	}
	
	private int sgn(double num) {
		if (num >= 0) {
			return 1;
		} else {
			return -1;
		}
	}

}
