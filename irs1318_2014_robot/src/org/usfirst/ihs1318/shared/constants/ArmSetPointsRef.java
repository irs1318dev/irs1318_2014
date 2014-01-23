package org.usfirst.ihs1318.shared.constants;

import org.usfirst.ihs1318.shared.data.WristValues;

/**
 * This class has set values for each of the 6 different peg heights: Wrist position, 
 * Arm Encoder distance, and Distance back from the end of the line.
 * 
 * @author violpat12
 *
 */
public class ArmSetPointsRef {
	//NOTE: NEGATIVE NUMBERS ARE UP
	public final static double ARM_MOTOR_NO_SET_POINT = -1;
	public final static double ARM_MOTOR_TICKS_PER_REV = 500;
	public final static double ARM_MOTOR_LOW1 = -1300 / ARM_MOTOR_TICKS_PER_REV;
	public final static double ARM_MOTOR_LOW2 = -1650  / ARM_MOTOR_TICKS_PER_REV;
	public final static double ARM_MOTOR_MIDDLE1 = ARM_MOTOR_LOW1;
	public final static double ARM_MOTOR_MIDDLE2 = ARM_MOTOR_LOW2;
	public final static double ARM_MOTOR_HIGH1 = -5700  / ARM_MOTOR_TICKS_PER_REV;
	public final static double ARM_MOTOR_HIGH2 = -6500  / ARM_MOTOR_TICKS_PER_REV;
	
	public final static double WHEEL_DISTANCE_NO_SET_POINT = 0;//inches
	public final static double WHEEL_DISTANCE_LOW1 = 18;//inches
	public final static double WHEEL_DISTANCE_LOW2 = 20;//inches
	public final static double WHEEL_DISTANCE_MIDDLE1 = 23;//inches
	public final static double WHEEL_DISTANCE_MIDDLE2 = 20;//inches
	public final static double WHEEL_DISTANCE_HIGH1 = 24;//inches
	public final static double WHEEL_DISTANCE_HIGH2 = 18;//inches
	
	public static WristValues WRIST_LOW1;
	public static WristValues WRIST_LOW2;
	public static WristValues WRIST_MIDDLE1;
	public static WristValues WRIST_MIDDLE2;
	public static WristValues WRIST_HIGH1;
	public static WristValues WRIST_HIGH2;	
	
	public static WristValues getWRIST_LOW1() {
		WRIST_LOW1 = null;
		WRIST_LOW1 = new WristValues();
		WRIST_LOW1.setWristPosition(WristValues.WRIST_CLOSED);
		return WRIST_LOW1;
	}
	public static WristValues getWRIST_LOW2() {
		WRIST_LOW2 = null;
		WRIST_LOW2 = new WristValues();
		WRIST_LOW2.setWristPosition(WristValues.WRIST_CLOSED);
		return WRIST_LOW2;
	}
	public static WristValues getWRIST_MIDDLE1() {
		WRIST_MIDDLE1 = null;
		WRIST_MIDDLE1 = new WristValues();
		WRIST_MIDDLE1.setWristPosition(WristValues.WRIST_LOW);
		return WRIST_MIDDLE1;
	}
	public static WristValues getWRIST_MIDDLE2() {
		WRIST_MIDDLE2 = null;
		WRIST_MIDDLE2 = new WristValues();
		WRIST_MIDDLE2.setWristPosition(WristValues.WRIST_LOW);
		return WRIST_MIDDLE2;
	}
	public static WristValues getWRIST_HIGH1() {
		WRIST_HIGH1 = null;
		WRIST_HIGH1 = new WristValues();
		WRIST_HIGH1.setWristPosition(WristValues.WRIST_OPEN);
		return WRIST_HIGH1;
	}
	public static WristValues getWRIST_HIGH2() {
		WRIST_HIGH2 = null;
		WRIST_HIGH2 = new WristValues();
		WRIST_HIGH2.setWristPosition(WristValues.WRIST_OPEN);
		return WRIST_HIGH2;
	}
	
}
