package irs2014.smartDashBoard;

public class NTRef
{
	//DriveTrain===================================================================
	public static final String DriveTrain_RightEncoder = "dt.re";
	public static final String DriveTrain_LeftEncoder = "dt.le";
	public static final String DriveTrain_RightSetPoint = "dt.rsp";
	public static final String DriveTrain_LeftSetPoint = "dt.ldp";
	public static final String DriveTrain_RightPIDSpeed = "dt.rps";
	public static final String DriveTrain_LeftPIDSpeed = "dt.lps";
	//Shooter======================================================================

	//Collector====================================================================
	public static final String Collector_BallPresent = "cl.bp";
	public static final String Collector_MotorSpeed = "cl.ms";
	public static final String Collector_SolenoidState = "cl.ss";
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
	//RobotGeneral=================================================================
	public static final String Robot_State = "r.s";
	//=============================================================================
}