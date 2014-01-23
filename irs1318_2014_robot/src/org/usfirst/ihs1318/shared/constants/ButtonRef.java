package org.usfirst.ihs1318.shared.constants;

public class ButtonRef {

	public final static int NUM_JOYSTICKS = 3;
	public final static int NUM_BUTTONS = 14;//There are actually 12, but this is just to be safe.
	
	//IMPORTANT
	//add one to all designations to determine labels on actual joystick
	//example: designation 3 is labeled button 4 on the joystick
	
	//this is modified to allow for tests of solenoid buttons with only two joysticks
	//rotation and arm values are swapped
	public static final int JOYSTICK_VELOCITY = 0;
	public static final int JOYSTICK_ROTATION = 1;
	
	public static final int JOYSTICK_ARM = 2;
	
	// Translation Joystick controls
	
	public static final int SPRINT_JS = JOYSTICK_VELOCITY;
	public static final int SPRINT_BUTTON = 0;
	

	public static final int TUBE_REQUEST_JS = JOYSTICK_VELOCITY; //TODO: need to figure out the real values!!
	public static final int TUBE_REQUEST_BUTTON_RED = 1;  
	public static final int TUBE_REQUEST_BUTTON_WHITE = 4;
	public static final int TUBE_REQUEST_BUTTON_BLUE = 3;

	public static final int JOYSTICK_SERVO  = JOYSTICK_VELOCITY;
	public static final int SERVO_ROTATE = 5; //RANDOMLY CHOSEN MAY CONFLICT
	
	public static final int JOYSTICK_AUTO_PLACE = JOYSTICK_VELOCITY;
	public static final int AUTO_PLACE_BUTTON = 9;

	
	// Rotation Joystick controls

	public static final int MINIBOT_DEPLOY_JS = JOYSTICK_ROTATION;
	public static final int MINIBOT_DEPLOY_BUTTON = 6;
	
	public static final int GYRO_RESET_JS = JOYSTICK_ROTATION;
	public static final int GYRO_RESET_BUTTON = 1;
	
	public static final int GYRO_TOGGLE_JS = JOYSTICK_ROTATION;
	public static final int GYRO_TOGGLE_BUTTON = 9;
	
	public static final int AIR_JS = JOYSTICK_ROTATION;
	public static final int AIR_ENABLE = 10;
	
	//Manual Gyro buttons
	public static final int degrees90 = 4;  
	public static final int degrees180 = 1;
	public static final int degrees270 = 3;
	public static final int degrees0 = 2;
	
	// Arm Joystick controls
	
	public static final int CLAW_JS = JOYSTICK_ARM;
	public static final int CLAW_BUTTON = 0;
	
	public static final int WRIST_JOYSTICK = JOYSTICK_ARM;
	public static final int WRIST_BUTTON_OPEN = 1; //Lower--2
	public static final int WRIST_BUTTON_MED = 2; // not used--5
	public static final int WRIST_BUTTON_LOW = 3; //
	public static final int WRIST_BUTTON_CLOSED = 4; //
	
	public static final int DESIRED_HEIGHT_JS = JOYSTICK_ARM;
	//TODO Height set points are the 6 buttons on the base of the arm joystick.
	public static final int HEIGHT1 = 5;
	public static final int HEIGHT2 = 6;
	public static final int HEIGHT3 = 7;
	public static final int HEIGHT4 = 8;
	public static final int HEIGHT5 = 9;
	public static final int HEIGHT6 = 10;
	

	
	
}
