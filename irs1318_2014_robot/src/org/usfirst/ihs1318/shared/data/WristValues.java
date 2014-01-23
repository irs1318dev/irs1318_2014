package org.usfirst.ihs1318.shared.data;

//Checked for NPEs. Only uses primitive types.
public class WristValues {
	public static final int WRIST_CLOSED = 1;
	public static final int WRIST_LOW = 2;
	public static final int WRIST_MID = 3;
	public static final int WRIST_OPEN = 4;
	
	private int wristPosition = WRIST_CLOSED;
	
	private boolean proximalWristBool;
	private boolean distalWristBool;

	public boolean isProximalWristBool() {
		return proximalWristBool;
	}

	private void setProximalWristBool(boolean proximalWristBool) {
		this.proximalWristBool = proximalWristBool;
	}

	public boolean isDistalWristBool() {
			return distalWristBool;
	}

	private void setDistalWristBool(boolean distalWristBool) {
		this.distalWristBool = distalWristBool;
	}

	public void copyValues(WristValues dest) {
		if (dest == null)
			throw new RuntimeException("dest must not be null.");
		dest.setDistalWristBool(isDistalWristBool());
		dest.setProximalWristBool(isProximalWristBool());
	}
	//wrist position for dashboard
	public int getWristPosition() {
		return wristPosition;
	}

	/**
	 * Sets the position of the wrist.
	 * 
	 * WRIST_CLOSED = distal and proximal closed
	 * WRIST_LOW = distal closed, proximal open
	 * WRIST_MID = distal open, proximal closed
	 * WRIST_HIGH = distal and proximal closed
	 * 
	 * @param wristPosition
	 * @throws Wrist value must be one of the four constants.
	 */
	public void setWristPosition(int wristPosition) {
		switch(wristPosition) {
		case WRIST_CLOSED:
			setDistalWristBool(false);
			setProximalWristBool(false);
			this.wristPosition = wristPosition;
			break;
		case WRIST_LOW:
			setDistalWristBool(false);
			setProximalWristBool(true);
			this.wristPosition = wristPosition;
			break;
		case WRIST_MID:
			setDistalWristBool(true);
			setProximalWristBool(false);
			this.wristPosition = wristPosition;
			break;
		case WRIST_OPEN:
			setDistalWristBool(true);
			setProximalWristBool(true);
			this.wristPosition = wristPosition;
			break;
			
			default:
				throw new RuntimeException(wristPosition + " is not a valid wrist positon.");
		}
		
	}
}
