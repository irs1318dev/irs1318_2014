package org.usfirst.ihs1318.shared.data;

public class Distance {
	private double xDistance;//inches
	private double yDistance;//inches
	public static final double EPSILON = 1;//inch

	public double getX() {
		return xDistance;
	}
	
	public void setX(double distance){
		this.xDistance = distance;
	}
	
	public double getY() {
		return yDistance;
	}

	public void setY(double yDistance) {
		this.yDistance = yDistance;
	}

	public void copyValuesTo(Distance dest) {
		dest.setAll(getX(), getY());
	}
	
	public void setAll(double xDistance, double yDistance){
		setX(xDistance);
		setY(yDistance);
	}
	
	public boolean equals(Object that) {
		if(!(that instanceof Distance))
			return false;
		return(compareDoubles(((Distance) that).getX(), this.getX()) && compareDoubles(((Distance) that).getY(), this.getY()));
		
	}

	
	private boolean compareDoubles(double x, double y){
		return Math.abs(x - y) <= EPSILON;
	}
	
	
}
