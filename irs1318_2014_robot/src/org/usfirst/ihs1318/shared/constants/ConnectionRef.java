package org.usfirst.ihs1318.shared.constants;

import edu.wpi.first.wpilibj.SensorBase;

public class ConnectionRef {
	public static final int SIDECAR_ONE_SLOT = SensorBase.getDefaultDigitalModule();	
	
	public static final int WHEEL_ENCODER_SLOT = SIDECAR_ONE_SLOT;
	public static final int WHEEL_ENCODER_RF_SOURCE_1 = 1;
	public static final int WHEEL_ENCODER_RF_SOURCE_2 = 2;
	public static final int WHEEL_ENCODER_LF_SOURCE_1 = 3;
	public static final int WHEEL_ENCODER_LF_SOURCE_2 = 4;
	public static final int WHEEL_ENCODER_LR_SOURCE_1 = 5;	
	public static final int WHEEL_ENCODER_LR_SOURCE_2 = 6;
	public static final int WHEEL_ENCODER_RR_SOURCE_1 = 7;
	public static final int WHEEL_ENCODER_RR_SOURCE_2 = 8;
	
	public static final int AIR_PRESSURE_SWITCH_SLOT = SIDECAR_ONE_SLOT;
	public static final int AIR_PRESSURE_SWITCH_CHANNEL = 9;
	public static final int AIR_RELAY_SLOT = SIDECAR_ONE_SLOT;
	public static final int AIR_RELAY_CHANNEL = 1;

	public static final int LINE_SENSOR_SLOT = SIDECAR_ONE_SLOT;
	public static final int LINE_SENSOR_RIGHT_CHANNEL = 10;
	public static final int LINE_SENSOR_CENTER_CHANNEL = 11;
	public static final int LINE_SENSOR_LEFT_CHANNEL = 12;
	
	public static final int MOTOR_SLOT_RF = SIDECAR_ONE_SLOT;
	public static final int MOTOR_SLOT_LF = SIDECAR_ONE_SLOT;
	public static final int MOTOR_SLOT_LR = SIDECAR_ONE_SLOT;	
	public static final int MOTOR_SLOT_RR = SIDECAR_ONE_SLOT;
	
	public static final int MOTOR_CHANNEL_RF = 1;
	public static final int MOTOR_CHANNEL_LF = 2;
	public static final int MOTOR_CHANNEL_LR = 4;	//TODO 1/18/2014 - switch ports for LR and RR
	public static final int MOTOR_CHANNEL_RR = 3;
	
//	public static final int ARM_ENCODER_SLOT = SIDECAR_ONE_SLOT;
//	public static final int ARM_ENCODER_CHANNEL1 = 9;
//	public static final int ARM_ENCODER_CHANNEL2 = 10;
//	public static final int ARM_ENCODER_CHANNEL3 = 3;

//	public static final int ARM_MOTOR_SLOT = SIDECAR_ONE_SLOT;
//	public static final int ARM_MOTOR_CHANNEL = 5;
	
	public static final int SERVO_SLOT = SIDECAR_ONE_SLOT;
	public static final int SERVO_CHANNEL = 6;
	
	
	////NON-SIDECAR THINGS
	public static final int SOLENOID_SLOT = 2;
	public static final int SOLENOID_CONTROLLER_CLAW_OPEN_CHANNEL = 1;
	public static final int SOLENOID_CLAW_CLOSED_CHANNEL = 2;
	public static final int SOLENOID_DISTAL_WRIST_OPEN_CHANNEL = 3;
	public static final int SOLENOID_DISTAL_WRIST_CLOSED_CHANNEL = 4;	
	public static final int SOLENOID_PROXIMAL_WRIST_OPEN_CHANNEL = 5;
	public static final int SOLENOID_PROXIMAL_WRIST_CLOSED_CHANNEL = 6;
	public static final int SOLENOID_MINI_EXTENDED = 7;
	public static final int SOLENOID_MINI_RETRACTED = 8;
		
	public static final int LIMIT_SWITCH_SLOT = 0;//TODO Figure out where this is linked up.
	public static final int LIMIT_SWITCH_LOWER_CHANNEL = 0;
	public static final int LIMIT_SWITCH_UPPER_CHANNEL = 0;

	public static final int ARM_MOTOR_CAN = 2;//TODO Figure out what this is

	public static final int GYRO_CHANNEL = 1;
	public static final int GYRO_SLOT = SensorBase.getDefaultAnalogModule();


	
	
}
