package irs2014.generalOpperations;

import edu.wpi.first.wpilibj.Joystick;

public class GamePad extends Joystick{
	public static final byte AXIS_X1 = 0x1;
	public static final byte AXIS_X2 = 0x3;
	public static final byte AXIS_Y1 = 0x2;
	public static final byte AXIS_Y2 = 0x4;
	public static final byte AXIS_DPAD_Y = 0x6;
	public static final byte AXIS_DPAD_X = 0x5;
	
	public interface ButtonVals {
		/**
		 * left button
		 */
		int X = 1;
		/**
		 * bottom button
		 */
		int A = 2;
		/**
		 * Top button
		 */
		int Y = 4;
		/**
		 * right button
		 */
		int B = 3;
		/**
		 * R1
		 */
		int RB = 6;
		/**
		 * R2
		 */
		int RT = 8;
		/**
		 * L1
		 */
		int LB = 5;
		/**
		 * L2
		 */
		int LT = 7;
		/**
		 * Select
		 */
		int Back = 9;
		/**
		 * Start
		 */
		int Start = 10;
		
		int RIGHT_STICK_CLICK = 12;
		int LEFT_STICK_CLICK = 11;
	}
		
	public static class DPad {
		public static final DPad UP = new DPad(0, "up");
		public static final DPad DOWN = new DPad(1, "down");
		public static final DPad LEFT = new DPad(2, "left");
		public static final DPad RIGHT = new DPad(3, "right");
		public static final DPad CENTER = new DPad(-1, "center");
		/**
		 * Can be used in enumerations.
		 */
		public final int val;
		public final String label; 
		private DPad(int val, String lbl) {
			this.val = val;
			label = lbl;
		}
		
		public String toString() {
			return label;
		}
	}
	
	private static final int NUM_JS = 6;
	public static final int NUM_BUTTONS = 12;
	public static final int NUM_AXES = 6;
	
	public static GamePad create(int port) {
		return new GamePad(port, NUM_AXES, NUM_BUTTONS);
	}
	
	protected GamePad(int port, int numAxisTypes, int numButtonTypes) {
		super(port, numAxisTypes, numButtonTypes);
	}
	/**
	 * Gets the X value of the left joystick
	 * @return -1.0 to 1.0
	 */
	public double getXLeft() {
		return getRawAxis(AXIS_X1);
	}
	/**
	 * Gets the X value of the right joystick
	 * @return -1.0 to 1.0
	 */
	public double getXRight() {
		return getRawAxis(AXIS_X2);
	}
	/**
	 * Gets the Y value of the left joystick
	 * @return -1.0 to 1.0
	 */
	public double getYLeft() {
		return -getRawAxis(AXIS_Y1);//y reads backward for some reason
	}
	/**
	 * Gets the Y value of the right joystick
	 * @return -1.0 to 1.0 
	 */
	public double getYRight() {
		return -getRawAxis(AXIS_Y2);//y reads backward for some reason
	}
	
	/**
	 * Reads the X of the d-pad
	 * @return -1.0 or 1.0; left and right, respectively
	 */
	private int getDX() {
		if(getRawAxis(AXIS_DPAD_X) > 0)
			return 1;
		if(getRawAxis(AXIS_DPAD_X) < 0)
			return -1;
		return 0;
	}
	
	/**
	 * Reads the Y of the d-pad
	 * @return -1.0 or 1.0; left and right, respectively
	 */
	private int getDY() {
		//y values are inverted
		if(getRawAxis(AXIS_DPAD_Y) > 0)
			return -1;
		if(getRawAxis(AXIS_DPAD_Y) < 0)
			return 1;
		return 0;
	}

	/**
	 * Reads the current state of the DPAD
	 * @return A DPad enumerated value.
	 */
	public DPad getDPad() {
		if(getDY() > 0 && getDX() == 0) {
			return DPad.UP;
		}else if(getDY() < 0 && getDX() == 0){
			return DPad.DOWN;
		}else if(getDY() == 0 && getDX() > 0) {
			return DPad.RIGHT;
		}else if(getDY() == 0 && getDX() < 0) {
			return DPad.LEFT;
		}		
		//both are zero otherwise
		return DPad.CENTER;
	}
}