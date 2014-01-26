package org.usfirst.frc1318.data;

import static java.lang.Math.acos;




public class Trapezoid implements DataStructure{
	private String errorMessage=null;
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public boolean hasError() {
		return !("".equals(errorMessage) || errorMessage==null);
	}
	

	private int pointCount;;
	private int lineWidth;
	private Point topRight;
	private Point topLeft;
	private Point lowRight;
	private Point lowLeft;
	
	public Trapezoid() {
		
	}
	
	public Trapezoid(int pointCount, int lineWidth, double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4){
		this.pointCount = pointCount;
		this.lineWidth = lineWidth; 
		this.topLeft = new Point(x1, y1);
		this.topRight = new Point(x2, y2);
		this.lowLeft = new Point(x3, y3);
		this.lowRight = new Point(x4, y4);
	}
	
	public Trapezoid(Point topRight, Point topLeft, Point bottomLeft, Point bottomRight){
		this.topLeft = topLeft;
		this.topRight = topRight;
		lowLeft = bottomLeft;
		lowRight = bottomRight;
	}
	
	public Trapezoid(Trapezoid that) {
		this(that.getPointCount(),that.getLineWidth(),that.topRight.getX(), that.topRight.getY(), that.topLeft.getX(), that.topLeft.getY(),
				that.lowLeft.getX(), that.lowLeft.getY(), that.lowRight.getX(), that.lowRight.getY());
	}

	public Point getTopRight() {
		return topRight;
	}

	public Point getTopLeft() {
		return topLeft;
	}
	public Point getLowRight() {
		return lowRight;
	}

	public Point getLowLeft() {
		return lowLeft;
	}

	public boolean copyTo(DataStructure otherData) {
		if(otherData == null)
			return false;
		if(otherData.getClass() != this.getClass())
			return false;
		Trapezoid that = (Trapezoid) otherData;
		Trapezoid temp = new Trapezoid(that);
		boolean success = true;
		success &= this.getLowLeft().copyTo(that.getLowLeft());
		success &= this.getLowRight().copyTo(that.getLowRight());
		success &= this.getTopLeft().copyTo(that.getTopLeft());
		success &= this.getTopRight().copyTo(that.getTopRight());
		if(!success) {
			//Revert to previous copy so as not to corrupt
			temp.copyTo(that);
		}			
		return success;
	}
	
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj.getClass() != this.getClass())
			return false;
		Trapezoid that = (Trapezoid) obj;
		return this.getLowLeft().equals(that.getLowLeft())
		&& this.getTopLeft().equals(that.getTopLeft())
		&& this.getLowLeft().equals(that.getLowLeft())
		&& this.getLowRight().equals(that.getLowRight());
	}
	
	public Point findMidpoint() {
		double sumX = getLowLeft().getX()
			      + getLowRight().getX()
			      + getTopLeft().getX()
			      + getTopRight().getX();
  	    double sumY = getLowLeft().getY()
			      + getLowRight().getY()
			      + getTopLeft().getY()
			      + getTopRight().getY();

		Point sum = new Point(sumX,sumY);
				
		sum.div(4, 4);
		return sum;
	}
	
	/**
	 * 
	 * @param trap
	 * @return
	 */
	public double findWidth() {
		double width = (getLowLeft().distanceX(getLowRight()) + getTopLeft().distanceX(getTopRight())) / 2;
		//averaged for accuracy
		if(width<0){
			width *= -1; // absolute value
		}
		return width;
	}
	
	public double findHeight() {
		double height = (getTopLeft().distanceY(getLowLeft()) + getTopRight().distanceY(getLowRight())) / 2;
		//averaged for accuracy
		if(height<0){
			height *= -1; // absolute value
		}
		return height;
	}

	// look for range within 1.33 +/- .05
	public double findAspectRatio() {
		return this.findWidth() / this.findHeight();
	}

	// too sensitive.
	public double findTheta() {
		//equation is theta = arccos(percieved height / percieved width * actual width / actual height)
		double theta =  findAspectRatio() * (3.0 / 4.0);
		theta = acos(theta); //the division of pi is to put theta into terms of pi
		return theta * 180.0 /Math.PI;
	}
	
	public int findFieldSide() {
		int side = 0;
		if (findAspectRatio()>1.25) return side;
		double leftSideHeight = getTopLeft().distanceY(getLowLeft());
		double rightSideHeight = getTopRight().distanceY(getLowRight());
		if(leftSideHeight > rightSideHeight) {
			side = -1; // if we are on the left side of the field, the left side of the triangle will appear bigger
		}
		else {
			side = 1;
		}
		return side;
		
	}

	public double findThetaField() {
		double result = findTheta();
		int side = findFieldSide();
		if (side!=0) result *= side;
		return result;
	}
	
	public double getFieldOfViewOffset() {
		return findMidpoint().getX() - (320);
	}
	
	public String toString() {
		if (hasError()) return getErrorMessage();
		
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("tl=%s, tr=%s, ",getTopLeft(), getTopRight()));
		sb.append(String.format("ll=%s, lr=%s, ",getLowLeft(), getLowRight()));
		sb.append(String.format("pointCount=%s, lineWidth=%s, width=%.0f, height=%.0f, midPoint=%s, AR=%1.2f, fov=%.0f, t=%.3f",getPointCount(), getLineWidth(), findWidth(), findHeight(), findMidpoint(),findAspectRatio(),getFieldOfViewOffset(),findThetaField()));
		return sb.toString();

	}
	public int getLineWidth() {
		return lineWidth;
	}
	public int getPointCount() {
		return pointCount;
	}

}
