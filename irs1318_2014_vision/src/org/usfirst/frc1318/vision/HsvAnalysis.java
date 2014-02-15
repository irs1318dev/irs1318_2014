package org.usfirst.frc1318.vision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.usfirst.frc1318.data.Point;
import org.usfirst.frc1318.data.Trapezoid;

public class HsvAnalysis {

	private String name;
	public void setName(String name) {
		this.name=name;
	}
	public String getName()  {
		return name;
	}
	
	private int resolution;
	public int getResolution() {
		if (resolution==0) {
			throw new IllegalStateException("please set the resolution");
		}
		return resolution;
	}
	public void setResolution(int resolution) {
		this.resolution = resolution;
	}
	
	private int minX=10000;
	private int minY=10000;
	private int maxX=0;
	private int maxY=0;
	private int avgX=0;
	private int avgY=0;
	private int sumX=0;
	public int getSumX() {
		return sumX;
	}
	public void setSumX(int sumX) {
		this.sumX = sumX;
	}
	public int getSumY() {
		return sumY;
	}
	public void setSumY(int sumY) {
		this.sumY = sumY;
	}
	private int sumY=0;
	
	private Map<Integer, Set<Integer>> xPoints = null;
	private Map<Integer, Set<Integer>> yPoints = null;
	
 	private List<Point> points = new ArrayList<Point>();

	public Point getCentroid() {
		return null;
	}
	
	public int getMinX() {
		return minX;
	}

	public void checkX(int x) {
		if (this.minX>x) {
			this.minX = x;
		}
		if (this.maxX<x) {
			this.maxX = x;
		}
		sumX+=x;
	}

	public void checkY(int y) {
		if (this.minY>y) {
			this.minY = y;
		}
		if (this.maxY<y) {
			this.maxY = y;
		}
		sumY+=y;
	}

	public int getMinY() {
		return minY;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}


	public List<Point> getPoints() {
		return points;
	}

	public int getAvgX() {
		return avgX;
	}
	public void setAvgX(int avgX) {
		this.avgX = avgX;
	}
	public int getAvgY() {
		return avgY;
	}
	public void setAvgY(int avgY) {
		this.avgY = avgY;
	}
	public void setPoints(List<Point> points) {
		this.points = points;
	}
	

	int countTop;
	int sumTop;
	int countBottom;
	int sumBottom;
	int countLeft;
	int sumLeft;
	int countRight;
	int sumRight;
	int numTop;
	int numBottom;
	int numLeft;
	int numRight;
	
	private Trapezoid boundingTrapezoid;
	public Trapezoid getBoundingTrapezoid() {
		if (boundingTrapezoid==null) {
			boundingTrapezoid = new Trapezoid();
		}
		return boundingTrapezoid;
	}
	
	
	private Trapezoid trapezoid;
	public Trapezoid getTrapezoid() {
		if (trapezoid==null) {
			trapezoid = new Trapezoid();
		}
		return trapezoid;
	}

	/**
	 * Find bounding box for top rectangle
	 * @return
	 */
	public Trapezoid analyzePoints2() {
		
		buildVerticalLines2();
		// find top two adjacent vertical lines, height > 35 pixels
		//   tops no more than 70 pixels vertical separation
		//   no more than 250 pixels horizontal separation
		
		for (Integer x : getXPoints().keySet()) {
			if (getXPoints().get(x).size()>35) {
				System.out.println(String.format("%s\t%s\t%s",x,getXPoints().get(x).size(),getXPoints().get(x).iterator().next()));
			}
		}
		

		
		
		return trapezoid;
	}
	
	private void buildVerticalLines2() {
		// get center of mass in x.
		double centerX = (double)sumX / (double)points.size();
		// find tiltAngle
		double tiltAngle = findTiltAngle(centerX)*Math.PI/180;
		
		for (Point p : points) {
			double x = p.getX();
			double y = p.getY();
			
			double tiltX = y * Math.sin(tiltAngle);

			Integer xInt = new Double(x).intValue();
			Integer xTiltInt = new Double(x + tiltX).intValue();
			Integer yInt = new Double(y).intValue();
			
			Set yPointSet = getXPoints().get(xTiltInt);
			if (yPointSet==null) {
				yPointSet = new TreeSet<Integer>();
				getXPoints().put(xTiltInt,yPointSet);
			}
			getXPoints().get(xTiltInt).add(yInt);

			Set xPointSet = getYPoints().get(yInt);
			if (xPointSet==null) {
				xPointSet = new TreeSet<Integer>();
				getYPoints().put(yInt,xPointSet);
			}
			getYPoints().get(yInt).add(xTiltInt);
		}
	}
	public GoalDistance analysePoints2014() {
		buildVerticalLines();
		for (int x : getXPoints().keySet()) {
			
		}
	
		for (int y : getYPoints().keySet()) {
			
		}
		return null;
	}	
	
	
	
	

	/**
	 * Analyze points within bounding box for top rectangle.
	 * @return
	 */
	public Trapezoid analyzePoints() {
		buildVerticalLines();
		
		trapezoid = new Trapezoid();
		sumTop=0;
		sumBottom=0;
		sumLeft=0;
		sumRight=0;
		numTop=0;
		numBottom=0;
		numLeft=0;
		numRight=0;
		
		int leftX=0;
		int innerLeftX=0;
		int leftWidth=0;
		int rightX=0;
		int maxRightX=0;
		int rightWidth=0;
		int topY=0;
		int bottomY=0;
		
		for (int x : getXPoints().keySet()) {
			if (getXPoints().get(x).size()>35) {
				if (leftX==0) {
					leftX = x;
					innerLeftX = x;
				}
				else if (x>leftX+35 && rightX==0) {
//					System.out.printf("\n");
					rightX = x;
				}
				
				
				if (leftX>0 && rightX==0) {
					innerLeftX = x;
					leftWidth = innerLeftX - leftX;
					numLeft++;
					sumLeft+=x;
//					System.out.printf("%s, ",x);
				} else if (leftX>0 && rightX>0) {
					maxRightX = x;
					rightWidth = maxRightX - rightX;
					numRight++;
					sumRight+=x;
//					System.out.printf("%s, ",x);
				}
			}
		}
		for (int y : getYPoints().keySet()) {
			if (getYPoints().get(y).size()>35) {
				if (topY==0) {
					topY = y;
				}
				else if (y>topY+35 && bottomY==0) {
//					System.out.printf("\n");
					bottomY = y;
				}
				
				if (topY>0 && bottomY==0) {
					numTop++;
					sumTop+=y;
//					System.out.printf("%s, ",y);
				} else if (topY>0 && bottomY>0) {
					numBottom++;
					sumBottom+=y;
//					System.out.printf("%s, ",y);
				}
			}
		}
		
		if (Math.abs(maxX-minX)>250) {
			String errorMessage = String.format("Xnoise"); 
			trapezoid.setErrorMessage(errorMessage);		
		}
		else if (Math.abs(maxY-minY)>200) {
			String errorMessage = String.format("Ynoise"); 
			trapezoid.setErrorMessage(errorMessage);
		}
		else if (numTop>0 && numBottom==0) {
			if (topY<maxY+35) {
				String errorMessage = String.format("ViewLow"); 
				trapezoid.setErrorMessage(errorMessage);
			} else {
				String errorMessage = String.format("ViewHigh"); 
				trapezoid.setErrorMessage(errorMessage);
			}
		}
		else if (numTop==0 || numBottom==0 || numLeft==0 || numRight==0) {
			String errorMessage = String.format("Invalid"); 
			trapezoid.setErrorMessage(errorMessage);
		}
		else {
			int xleft = sumLeft/numLeft;
			int xright = sumRight/numRight;
			int ytop = sumTop/numTop;
			int ybottom = sumBottom/numBottom;
			
			int width = (leftWidth + rightWidth) / 2; 
			
			trapezoid = new Trapezoid(points.size(),width, xleft,ytop,xright,ytop,xleft,ybottom,xright,ybottom);
			
		}
		
		return trapezoid;
		
		// case 1. full rectangle
		// for each x, find a sequence of 3 to 5 x points whose yTreeSet.size()>35 (an edge).
		// for each intervening x whose yTreeSet.size()<35  find the center points (high and low. Store in center point list.
		// perform least squares estimation on a sample of center points.  use slope and intercept to determine high and low lines.
		// keep track of number of holes, keep track of hollow middle (hue adjustment).
		// Repeat for y values.
		// Find four corners.
		// compare four corners to aspect ratios 4:3 with angle shrinkage
		// add to previous 5 samples for a moving average.
		// calculate range and theta
		
		// ensure four sides... otherwise, adjust sat and val
		// account for hoop.
		// compare side widths to height... adjust hueBand appropriately
	}

	public void recordPoint(int x, int y) {
		//TODO initialize data structures ahead of time.
		checkX(x);
		checkY(y);

		Point p = new Point(x,y);
		points.add(p);
		
	}
	
	private void buildVerticalLines() {
		for (Point p : points) {
			double x = p.getX();
			double y = p.getY();
			
			Integer xInt = new Double(x).intValue();
			Integer yInt = new Double(y).intValue();
			
		 	Set yPointSet = getXPoints().get(xInt);
			if (yPointSet==null) {
				yPointSet = new TreeSet<Integer>();
				getXPoints().put(xInt,yPointSet);
			}
			getXPoints().get(xInt).add(yInt);

			Set xPointSet = getYPoints().get(yInt);
			if (xPointSet==null) {
				xPointSet = new TreeSet<Integer>();
				getYPoints().put(yInt,xPointSet);
			}
			getYPoints().get(yInt).add(xInt);
		}
		
	}

	private static double MAX_TILT_ANGLE = 12.0; 
	
	public double findTiltAngle(double centerX) {
		double tiltAngle=0;
		
		double halfFrame = (double)getResolution()/2.0;
		double centerLineOffset = centerX - halfFrame;
		tiltAngle = MAX_TILT_ANGLE * centerLineOffset / halfFrame;
		return tiltAngle;
		
	}
	
	public Map<Integer, Set<Integer>> getXPoints() {
		if (xPoints==null) {
			xPoints = new TreeMap<Integer, Set<Integer>>();
		}
		return xPoints;
	}

	public Map<Integer, Set<Integer>> getYPoints() {
		if (yPoints==null) {
			yPoints = new TreeMap<Integer, Set<Integer>>();
		}
		return yPoints;
	} 
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("HueAnalysis=%s, ",getName()));
		sb.append(String.format("p=% ",getPoints().size()));
		return sb.toString();
	}
	public double getDataAspectRatio() {
		if (maxY-minY==0) return 10000;
		return ((double)maxX-(double)minX)/((double)maxY-(double)minY);
	}
	
	public int getDataHeight() {
		return  (maxY-minY);
	}

	public int getDataWidth() {
		return (maxX-minX);
	}
}
