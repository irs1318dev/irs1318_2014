package org.usfirst.frc1318.data;

import static java.lang.Math.acos;




public class Trapezoid implements DataStructure{
	private Point topRight;
	private Point topLeft;
	private Point lowRight;
	private Point lowLeft;
	
	public Trapezoid(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4){
		topLeft = new Point(x1, y1);
		topRight = new Point(x2, y2);
		lowLeft = new Point(x3, y3);
		lowRight = new Point(x4, y4);
	}
	
	public Trapezoid(Point topRight, Point topLeft, Point bottomLeft, Point bottomRight){
		this.topLeft = topLeft;
		this.topRight = topRight;
		lowLeft = bottomLeft;
		lowRight = bottomRight;
	}
	
	public Trapezoid(Trapezoid that) {
		this(that.topRight.getX(), that.topRight.getY(), that.topLeft.getX(), that.topLeft.getY(),
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
	
	public double findTheta() {
		//equation is theta = arccos(percieved height / percieved width * actual width / actual height)
		double theta = this.findWidth() / this.findHeight() * 3 / 4;
		theta = acos(theta);
		return theta;
	}

	public double findThetaField() {
		double result = findTheta();
		double leftSideHeight = getTopLeft().distanceY(getLowLeft());
		double rightSideHeight = getTopRight().distanceY(getLowRight());
		if(leftSideHeight > rightSideHeight) {
			result *= -1; // if we are on the left side of the field, the left side of the triangle will appear bigger
			result += 2 * Math.PI; //this is to convert the negative number we made into 0 through 2 pi radians
		}
		return result;
	}	

}
