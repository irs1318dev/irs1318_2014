package irs2014.networkTable;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;


public class NetworkTableRunner extends RobotComponentBase{
	int val = 0;
	public void robotInit(){
		System.out.println("Network Table is ready!");
		IRSTable.putString(NTRef.Robot_State, "Init");
		IRSTable.putString(NTRef.Timer_Message, "no message from timer");
	}
	
	public void teleopInit(){
		IRSTable.putString(NTRef.Robot_State, "Teleop");
	}
	
	public void disabledInit(){
		IRSTable.putString(NTRef.Robot_State, "Disabled");
	}
	
	public void teleopPeriodic() {
		if(val++ % 10 == 0){
			driveTrainData();
			shooterData();
			collectorData();
			userInputData();
			pressureSensor();
		}
	}
	
	private void driveTrainData(){
		IRSTable.putNumber(NTRef.DriveTrain_RightEncoder, ReferenceData.getInstance().getDriveTrainData().getRightEncoderData().getTicks());
		IRSTable.putNumber(NTRef.DriveTrain_LeftEncoder, ReferenceData.getInstance().getDriveTrainData().getLeftEncoderData().getTicks());
		IRSTable.putNumber(NTRef.DriveTrain_RightEncoderVelocity, ReferenceData.getInstance().getDriveTrainData().getRightEncoderData().getVelocity());
		IRSTable.putNumber(NTRef.DriveTrain_LeftEncoderVelocity, ReferenceData.getInstance().getDriveTrainData().getLeftEncoderData().getVelocity());
		IRSTable.putNumber(NTRef.DriveTrain_RightSetPoint, ReferenceData.getInstance().getDriveTrainData().getRightPIDData().getVelocitySetpoint());
		IRSTable.putNumber(NTRef.DriveTrain_LeftSetPoint, ReferenceData.getInstance().getDriveTrainData().getLeftPIDData().getVelocitySetpoint());
		IRSTable.putNumber(NTRef.DriveTrain_RightPIDSpeed, ReferenceData.getInstance().getDriveTrainData().getRightPIDData().getPIDVelocity());
		IRSTable.putNumber(NTRef.DriveTrain_LeftPIDSpeed, ReferenceData.getInstance().getDriveTrainData().getLeftPIDData().getPIDVelocity());
	}

 	private void shooterData(){
 		IRSTable.putBoolean(NTRef.Shooter_ShotExtended, ReferenceData.getInstance().getShooterData().getCurrentShooterState());
 		IRSTable.putBoolean(NTRef.Shooter_AngleExtended, ReferenceData.getInstance().getAngleData().getCurrentShooterAngle());
 	}
 	
 	private void collectorData(){
 		IRSTable.putBoolean(NTRef.Collector_BallPresent, ReferenceData.getInstance().getCollectorData().getLimitSwitchData().getBallPresent());
 		IRSTable.putNumber(NTRef.Collector_MotorSpeed, ReferenceData.getInstance().getCollectorData().getMotorData().getCollectorMotorSpeed());
 		IRSTable.putBoolean(NTRef.Collector_DesiredSolenoidState, ReferenceData.getInstance().getCollectorData().getSolenoidData().getDesiredSolenoidState());
 		IRSTable.putBoolean(NTRef.Collector_CurrentSolenoidState, ReferenceData.getInstance().getCollectorData().getSolenoidData().getCurrentSolenoidState());
 	}
 	
 	private void userInputData(){
 		//Joysticks
 		IRSTable.putNumber(NTRef.Input_JoystickX, ReferenceData.getInstance().getUserInputData().getJoystickX());
 		IRSTable.putNumber(NTRef.Input_JoystickY, ReferenceData.getInstance().getUserInputData().getJoystickY());
 		//Buttons
 		IRSTable.putBoolean(NTRef.Input_ExtendCollector, ReferenceData.getInstance().getUserInputData().getExtendCollector());
 		IRSTable.putBoolean(NTRef.Input_RetractCollector, ReferenceData.getInstance().getUserInputData().getRetractCollector());
 		IRSTable.putBoolean(NTRef.Input_CollectorMotorIn, ReferenceData.getInstance().getUserInputData().getCollectorMotorIn());
 		IRSTable.putBoolean(NTRef.Input_CollectorMotorOut, ReferenceData.getInstance().getUserInputData().getStopCollectorMotor());
 		//Macros
 		IRSTable.putBoolean(NTRef.Input_GoForward, ReferenceData.getInstance().getUserInputData().getGoForward());
 		IRSTable.putBoolean(NTRef.Input_CollectBall, ReferenceData.getInstance().getUserInputData().getCollectBall());
 		IRSTable.putBoolean(NTRef.Input_EjectBall, ReferenceData.getInstance().getUserInputData().getEjectBall());
 		//Other
 		IRSTable.putNumber(NTRef.Input_ShooterSet, ReferenceData.getInstance().getUserInputData().getTriggerSet());
 	}
 	
 	public void pressureSensor(){
 		IRSTable.putBoolean(NTRef.PressureSensor_State, ReferenceData.getInstance().getPressureSensorData().getIsPressurized());
 		IRSTable.putNumber(NTRef.PressureSensor_Time, (ReferenceData.getInstance().getPressureSensorTimerData().getTimerTime() / 1000000));
 	}
}
