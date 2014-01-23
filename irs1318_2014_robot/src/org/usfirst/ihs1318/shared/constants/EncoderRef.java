package org.usfirst.ihs1318.shared.constants;

public class EncoderRef {
	public static final double MIN_ARM_HEIGHT = 75;//Give a little wiggle room in case the zeroing is too high
	public static final double MAX_ARM_HEIGHT = -6800; //Just a little more than the highest that should be needed.
	
	public static final double ROBOTREVS_PER_RADIAN = 1/ (2 * Math.PI);
	public static double WHEEL_ENCODER_TICKS_PER_MOTORREV = 250;
	//TODO: RADIANS_PER_TICK is radians the robot rotates per encoder tick of the wheel, not radians of the
	//motor
	private static final double RADIANS_PER_TICK = 2*Math.PI / WHEEL_ENCODER_TICKS_PER_MOTORREV;
	public static double MOTORREVS_PER_WHEEL_REV = 2;
	public static double WHEEL_REVS_PER_INCH = 1/25.125;
	public static double RATIO_REAL_WORLD = 1.0;
	
	public static double convertToWheelTicks(double inches) {
		return inches * WHEEL_REVS_PER_INCH * MOTORREVS_PER_WHEEL_REV *  WHEEL_ENCODER_TICKS_PER_MOTORREV
		* RATIO_REAL_WORLD;
	}

	public static double convertTicksToInches(double ticks) {
		return ticks / WHEEL_ENCODER_TICKS_PER_MOTORREV / MOTORREVS_PER_WHEEL_REV / WHEEL_REVS_PER_INCH;
	}
	
	public static double convertTicksToRadians(double ticks) {
		return ticks * RADIANS_PER_TICK;
	}
	
	public static double convertRadiansToTicks(double radians) {
		return radians/RADIANS_PER_TICK;
	}
}
