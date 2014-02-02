package irs2014.networkTable;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;


public class NetworkTableRunner extends RobotComponentBase
{
	int val = 0;
	public void robotInit()
	{
		System.out.println("Network Table is ready!");
		IRSTable.putString(NTRef.Robot_State, "Init");
	}
	
	public void teleopInit()
	{
		IRSTable.putString(NTRef.Robot_State, "Teleop");
	}
	
	public void disabledInit()
	{
		IRSTable.putString(NTRef.Robot_State, "Disabled");
	}
	
	public void teleopPeriodic() 
	{
		if(val++ % 10 == 0)
		{
			driveTrainData();
			shooterData();
			collectorData();
			userInputData();
		}
	}
	
	private void driveTrainData()
	{
		IRSTable.putNumber(NTRef.DriveTrain_RightEncoder, ReferenceData.getInstance().getDriveTrainData().getRightEncoder());
		IRSTable.putNumber(NTRef.DriveTrain_LeftEncoder, ReferenceData.getInstance().getDriveTrainData().getLeftEncoder());
		IRSTable.putNumber(NTRef.DriveTrain_RightSetPoint, ReferenceData.getInstance().getDriveTrainData().getRightSpeedSetPoint());
		IRSTable.putNumber(NTRef.DriveTrain_LeftSetPoint, ReferenceData.getInstance().getDriveTrainData().getLeftSpeedSetPoint());
		IRSTable.putNumber(NTRef.DriveTrain_RightPIDSpeed, ReferenceData.getInstance().getDriveTrainData().getRightPIDSpeed());
		IRSTable.putNumber(NTRef.DriveTrain_LeftPIDSpeed, ReferenceData.getInstance().getDriveTrainData().getLeftPIDSpeed());
	}

 	private void shooterData()
 	{

 	}
 	
 	private void collectorData(){
 		IRSTable.putBoolean(NTRef.Collector_BallPresent, ReferenceData.getInstance().getCollectorData().getLimitSwitchData().getBallPresent());
 		IRSTable.putNumber(NTRef.Collector_MotorSpeed, ReferenceData.getInstance().getCollectorData().getMotorData().getCollectorMotorSpeed());
 		IRSTable.putBoolean(NTRef.Collector_SolenoidState, ReferenceData.getInstance().getCollectorData().getSolenoidData().getSolenoidState());
 	}
 	
 	private void userInputData()
 	{
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
 		
 	}
}