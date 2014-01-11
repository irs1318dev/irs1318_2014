package org.usfirst.ihs1318.shared.constants;

public class MessageRef {
	public static final String AIR_COMPRESSOR_STATUS = "Air";
	public static final String MINIBOT_DEPLOY = "bot"; // boolean
	public static final String CAMERA_FLIP = "cFlip"; // boolean

	public static final String DESIRED_JX = "jX"; // double
	public static final String DESIRED_JY = "jY"; // double
	public static final String DESIRED_JRY = "jrY"; // double
	public static final String DESIRED_OMEGA = "omega"; // double

	public static final String THETA = "theta"; // double

	public static final String ARM_ENC_VALUE = "armEncV"; // double
	public static final String ARM_ENC_RATE = "armEncR"; // double


	public static final String LINE_LEFT = "lineL"; // boolean
	public static final String LINE_CENTER = "lineC"; // boolean
	public static final String LINE_RIGHT = "lineR"; // boolean
	public static final String LINE_STATE = "lineS"; // int
	public static final String LINE_STATE_DISPLAY = "lineD"; // int

	public static final String WHEEL_LF = "wLF"; // double
	public static final String WHEEL_LR = "wLR"; // double
	public static final String WHEEL_RR = "wRR"; // double
	public static final String WHEEL_RF = "wRF"; // double

	public static final String WHEEL_ENC_LF = "weLF"; // double
	public static final String WHEEL_ENC_LR = "weLR"; // double
	public static final String WHEEL_ENC_RR = "weRR"; // double
	public static final String WHEEL_ENC_RF = "weRF"; // double

	public static final String WHEEL_ENC_TICK_LF = "wetLF"; // double
	public static final String WHEEL_ENC_TICK_LR = "wetLR"; // double
	public static final String WHEEL_ENC_TICK_RR = "wetRR"; // double
	public static final String WHEEL_ENC_TICK_RF = "wetRF"; // double

	public static final String CLAW = "claw"; // boolean
	public static final String WRIST_MSG = "wrist"; // int 1-4
	public static final String WRIST_DISTAL = "wristD"; // boolean
	public static final String WRIST_PROXIMAL = "wristP"; // boolean
	
	public static final String DESIRED_ARM_VELOCITY = "armV"; // int 1-6
	public static final String DESIRED_ARM_HEIGHT = "armH"; // int 1-6

	public static final String ARM_DISTANCE = "armD"; // double
	public static final String CALC_ARM_VELOCITY = "armCV"; // double

	public static final String TUBE_REQUEST = "Tube"; // String (b,r,w)

	public static final String GYRO_STATUS = "gyro"; // boolean

	public static final String CALC_HEIGHT = "cHeight"; // double from
														// kinematics
	public static final String ACTUAL_HEIGHT = "aHeight"; // double from encoder

}
