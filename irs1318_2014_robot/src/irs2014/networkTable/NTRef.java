package irs2014.networkTable;

public class NTRef
{
	//DriveTrain===================================================================
	public static final String DriveTrain_RightEncoder = "dt.re";
	public static final String DriveTrain_LeftEncoder = "dt.le";
	public static final String DriveTrain_RightEncoderVelocity = "dt.rev";
	public static final String DriveTrain_LeftEncoderVelocity = "dt.lev";
	public static final String DriveTrain_RightSetPoint = "dt.rsp";
	public static final String DriveTrain_LeftSetPoint = "dt.lsp";
	public static final String DriveTrain_RightPIDSpeed = "dt.rps";
	public static final String DriveTrain_LeftPIDSpeed = "dt.lps";
	//Shooter======================================================================
	public static final String Shooter_ShotExtended = "s.se";
	public static final String Shooter_AngleExtended = "s.ae";
	//Collector====================================================================
	public static final String Collector_BallPresent = "cl.bp";
	public static final String Collector_MotorSpeed = "cl.ms";
	public static final String Collector_DesiredSolenoidState = "cl.dss";
	public static final String Collector_CurrentSolenoidState = "cl.css";
	//UserInput====================================================================
		//Joysticks
	public static final String Input_JoystickX = "i.jx";
	public static final String Input_JoystickY = "i.jy";
		//Buttons
	public static final String Input_ExtendCollector = "i.ec";
	public static final String Input_RetractCollector = "i.rc";
	public static final String Input_CollectorMotorIn = "i.cmi";
	public static final String Input_CollectorMotorOut = "i.cmo";
		//Macros
	public static final String Input_GoForward = "i.gf";
	public static final String Input_CollectBall = "i.cb";
	public static final String Input_EjectBall = "i.eb";
		//Other
	public static final String Input_ShooterSet = "i.ss";
	//RobotGeneral=================================================================
	public static final String Robot_State = "r.s";
	//Timer=======================================================================
	public static final String Timer_Message = "t.m";
	//Pressure Sensor==============================================================
	public static final String PressureSensor_State = "ps.s";
	public static final String PressureSensor_Time = "ps.t";
	public static final String AnalogPressureSensor_Value = "ps.av";

	//=============================================================================
}