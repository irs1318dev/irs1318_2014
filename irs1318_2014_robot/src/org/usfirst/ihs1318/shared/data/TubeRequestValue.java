package org.usfirst.ihs1318.shared.data;

//Checked for NPEs. Only uses primitive types.
public class TubeRequestValue {

	public static final String OFF = "o";
	public static final String WHITE = "w";
	public static final String BLUE = "b";
	public static final String RED = "r";
	
	boolean whiteCircle;
	boolean blueSquare;
	boolean redTriangle;
	
	
	
	public String getSetting() {
		if (isWhiteCircle()) return WHITE;
		else if (isBlueSquare()) return BLUE;
		else if (isRedTriangle()) return RED;
		return OFF;
	}
	
	public boolean isWhiteCircle() {
		return whiteCircle;
	}
	public void setWhiteCircle(boolean whiteCircle) {
		this.whiteCircle = whiteCircle;
	}
	public boolean isBlueSquare() {
		return blueSquare;
	}
	public void setBlueSquare(boolean blueSquare) {
		this.blueSquare = blueSquare;
	}
	public boolean isRedTriangle() {
		return redTriangle;
	}
	public void setRedTriangle(boolean readTriangle) {
		this.redTriangle = readTriangle;
	}
	
	public void copyValues(TubeRequestValue dest) {
		if (dest==null) {
			throw new RuntimeException("dest must not be null");
		}
		dest.setBlueSquare(isBlueSquare());
		dest.setRedTriangle(isRedTriangle());
		dest.setWhiteCircle(isWhiteCircle());
	}
	
	
}
