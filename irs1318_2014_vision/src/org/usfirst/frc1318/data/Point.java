package org.usfirst.frc1318.data;

import org.usfirst.frc1318.utils.Utils;
import static java.lang.Math.*;


public class Point implements DataStructure{
	private double x;
	private double y;

	public Point(double x, double y) {
		setAll(x, y);
	}

	private Point() {
	}

	public void setAll(double x, double y) {
		setX(x);
		setY(y);	
	}

	public void add(Point p) {
		add(p.getX(), p.getY());
	}

	public void add(double dx, double dy) {
		setAll(x + dx, y + dy);
	}

	public void mult(Point p) {
		mult(p.getX(), p.getY());
	}

	public void mult(double sclX, double sclY) {
		setAll(x * sclX, y* sclY);
	}

	public void subt(Point p) {
		subt(p.getX(), p.getY());
	}

	public void subt(double dx, double dy) {
		setAll(x - dx, y - dy);
	}

	public void div(Point p2) {
		div(p2.getX(), p2.getY());
	}

	public void div(double sclX, double sclY) {
		double x = this.x;
		double y = this.y;
		if(sclX != 0.0)
			x /= sclX;
		if(sclY != 0.0)
			y /= sclY;
		setAll(x, y);
	}
	
	public double distance(Point p) {
		return Math.sqrt(pow(distanceX(p), 2) + pow(distanceY(p), 2));
	}
	
	public double distanceX(Point p) {
		return getX() - p.getX();
	}
	
	public double distanceY(Point p) {
		return getY() - p.getY();
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean copyTo(DataStructure otherData) {
		if(otherData == null)
			return false;
		if(otherData.getClass() !=  this.getClass())
			return false;
		Point that = (Point) otherData;
		that.setAll(this.x, this.y);
		return true;
	}
	
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj.getClass() != this.getClass())
			return false;
		Point that = (Point) obj;
		return Utils.compareDoubles(this.x, that.x, .01) 
		&& Utils.compareDoubles(this.y, that.y, .01);
	}

	public void scale(double magnitude) {
		setAll(x * magnitude, y * magnitude);
		}

	public void rotate(Point rotationPoint, double magnitude) {
		//Translate to origin, find angle formed
		double translatedX = x - rotationPoint.getX();
		double translatedY = y - rotationPoint.getY();
		
		//do rotation at origin, then translate so that rotation is about rotation point
		double xprime = translatedX * Math.cos(Math.toRadians(magnitude)) - translatedY * Math.sin(Math.toRadians(magnitude));
		double yprime = translatedX * Math.sin(Math.toRadians(magnitude)) + translatedY * Math.cos(Math.toRadians(magnitude));

		setAll(xprime + rotationPoint.getX(), yprime + rotationPoint.getY());
	}
	
	public String toString() {
		return String.format("(%.0f,%.0f)",getX(),getY());
	}
}
