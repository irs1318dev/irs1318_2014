package org.usfirst.ihs1318.shared.data;

//Checked for NPEs. Only uses primitive types.
public class LineSensorInput {
	
	public static final int NONE_ON = 1;
	public static final int ALL_ON = 2;
	public static final int LEFT_ONLY_ON = 3;
	public static final int CENTER_ONLY_ON = 4;
	public static final int RIGHT_ONLY_ON = 5;
	public static final int LEFT_AND_CENTER_ON = 6;
	public static final int RIGHT_AND_CENTER_ON = 7;
	public static final int RIGHT_AND_LEFT_ON = 8;
	
	private boolean left;
	private boolean center;
	private boolean right;
	private boolean lastKnownLineOnRight;
	private boolean lastKnownLineOnLeft;
	private int sensorState;
	
	
	/**
	 * 
	 * @param destination Copies all of this data into the dest's data. This includes sensorState, 
	 * sensor values, and the direction of the last known line.
	 */
	public void copyValuesTo(LineSensorInput destination) {
		if(destination == null)
			throw new RuntimeException("Destination should never be null");
		
		destination.left = this.left;
		destination.right = this.right;
		destination.center = this.center;
		destination.sensorState = this.sensorState;
		destination.lastKnownLineOnLeft = this.lastKnownLineOnLeft;
		destination.lastKnownLineOnRight = this.lastKnownLineOnRight;
	}
	
	/**
	 * Determines whether two LineSensorInput instances are equal by checking to see that their 
	 * sensor values (right, center and left) are all equal.
	 */
	public boolean equals(Object that) {
		if(!(that instanceof LineSensorInput)) return false;

		boolean hasSameValues = isLeft() == ((LineSensorInput) that).isLeft() 
		&& isRight() == ((LineSensorInput)that).isRight() 
		&& isCenter() == ((LineSensorInput)that).isCenter();
		return hasSameValues;		
	}
	
	
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isCenter() {
		return center;
	}
	public void setCenter(boolean center) {
		this.center = center;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	

	
	/////////////////////////----Checks----////////////////////
	/**
	 * @return sensorState The current sensorState. Compare with the LineSensorInput class's constants
	 * to turn it into something meaningful.
	 */
	public int getLineSensorState() {
		return sensorState;
	}
	
	
	public static final String ALL_ON_DISPLAY = "*";
	public static final String CENTER_ONLY_ON_DISPLAY = "-";
	
	public String getLineSensorDisplay() {
		 
		switch (getLineSensorState()) {
		case ALL_ON:
			return ALL_ON_DISPLAY;
		case CENTER_ONLY_ON:
			return CENTER_ONLY_ON_DISPLAY;
		default:
				
		}
		return "No data";
	}
	/**
	 * Sets the sensorState depending on the current configuration of the lineSensor booleans (right, left,
	 * and center).
	 */
	public void setLineSensorState() {
		if (!left && !center && !right) {
			sensorState = NONE_ON;
		}
		else if (left && center && right) {
            setLastKnownLineOnLeft();
            sensorState = ALL_ON;
		}
		else if (left && !center && !right) {
            setLastKnownLineOnLeft();
            sensorState = LEFT_ONLY_ON;
		}
		else if (!left && center && !right) {
			sensorState = CENTER_ONLY_ON;
		}
		else if (!left && !center && right) {
            setLastKnownLineOnRight();
            sensorState = RIGHT_ONLY_ON;
		}
		else if (left && center && !right) {
            setLastKnownLineOnLeft();
            sensorState = LEFT_AND_CENTER_ON;
		}
		else if (!left && center && right) {
            setLastKnownLineOnRight();
            sensorState = RIGHT_AND_CENTER_ON;
		}
		else if (left && !center && right) {
			sensorState = RIGHT_AND_LEFT_ON;
		}
		
	}

	/**
	 * @return lastKnownLineOnRight True if the last sensor to be on was the right one, false if not.
	 */
    public boolean lastKnownLineOnRight() {
        return lastKnownLineOnRight;
    }

    /**
     * 
     * @return lastKnownLineOnLeft True if the last sensor to be on was the left one, false if not.
     */
    public boolean lastKnownLineOnLeft() {
        return lastKnownLineOnLeft;
    }

    /**
     * The last sensor to be on was on the left, and therefore the rightmost one was not.
     */
    private void setLastKnownLineOnLeft() {
            lastKnownLineOnRight = false;
            lastKnownLineOnLeft = true;
    }

    /**
     * The last sensor to be on was on the right, and therefore the leftmost one was not.
     */
    private void setLastKnownLineOnRight() {
            lastKnownLineOnRight = true;
            lastKnownLineOnLeft = false;
    }
    
    
    /**
     * Sets all values of the instance based on the three sensor values (left, center, and right).
     * This will set sensorState and lastKnownOn.. variables as well.
     * 
     * @param left The true/false value of the leftmost sensor.
     * @param center The true/false value of the center sensor.
     * @param right The true/false value of the rightmost sensor.
     */
	public void setAll(boolean left, boolean center, boolean right) {
		this.left = left;
		this.center = center;
		this.right = right;
		setLineSensorState();
	}


}
