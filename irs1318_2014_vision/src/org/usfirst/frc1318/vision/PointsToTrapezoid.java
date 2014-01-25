package org.usfirst.frc1318.vision;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc1318.data.Point;
import org.usfirst.frc1318.data.Trapezoid;

public class PointsToTrapezoid {

	public List<Point> getOuterPoints(List<Point> list) {
		if (list==null || list.size()<4) {
			return new ArrayList<Point>();
		}
		List<Point> pointList = new ArrayList<Point>();
		for(int i = 0; i < list.size(); i++) {
			pointList.add(list.get(i));
		}
		Point lowest; // set to the first point to give something to compare to
		Point secondLowest;
		Point highest;
		Point secondHighest;
		lowest = getLowest(pointList, pointList.get(0));
		pointList.remove(lowest);
		secondLowest = getLowest(pointList, pointList.get(0));
		pointList.add(0, lowest);
		highest = getHighest(pointList, pointList.get(0));
		pointList.remove(highest);
		secondHighest = getHighest(pointList, pointList.get(0));
		pointList.add(0, highest);
		List<Point> result = new ArrayList<Point>();
		// adds the points in the order top right, top left, bottom left, bottom right 
		if(highest.getX() > secondHighest.getX()) {
			result.add(highest);
			result.add(secondHighest);
		} else {
			result.add(secondHighest);
			result.add(highest);
		}
		if(lowest.getX() < secondLowest.getX()) {
			result.add(lowest);
			result.add(secondLowest);
		} else {
			result.add(secondLowest);
			result.add(lowest);
		}
		return result;
	}
	
	public Point startGetLowest(List<Point> list, Point currentLowest) {
		return getLowest(list, currentLowest);
	}
	
	public Point startGetHighest(List<Point> list, Point currentHighest) {
		return getHighest(list, currentHighest);
	}
	
	private Point getLowest(List<Point> list, Point currentLowest) {
		for(Point compared: list) {
			if(compared.getY() < currentLowest.getY()) {
				return getLowest(list, compared);
			}
		}
		return currentLowest;
	}
	
	private Point getHighest(List<Point> list, Point currentHighest) {
		for(Point compared: list) {
			if(compared.getY() > currentHighest.getY()) {
				return getHighest(list, compared);
			}
		}
		return currentHighest;
	}
	
	public Trapezoid getTrapezoid(List<Point> points) {
		if (points==null || points.size()<4){
			return null;
		}
		Trapezoid result = new Trapezoid(points.get(0), points.get(1), points.get(2), points.get(3));
		return result;
	}
	
	public Trapezoid getOuterTrapezoid(List<Point> pointList) {
		return getTrapezoid(getOuterPoints(pointList));
	}

}
