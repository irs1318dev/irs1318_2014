package org.usfirst.frc1318.vision;

import static com.googlecode.javacv.cpp.opencv_core.cvRect;

import com.googlecode.javacv.cpp.opencv_core.CvRect;

public class ViewBand {
	
	private int maxX;
	private int maxY;
	private int topOfBand;
	private int bottomOfBand;

	public int getMaxX() {
		return maxX;
	}
	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}
	public int getMaxY() {
		return maxY;
	}
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}
	public int getTopOfBand() {
		return topOfBand;
	}
	public void setTopOfBand(int topOfBand) {
		this.topOfBand = topOfBand;
	}
	public int getBottomOfBand() {
		return bottomOfBand;
	}
	public void setBottomOfBand(int bottomOfBand) {
		this.bottomOfBand = bottomOfBand;
	}

	public int getHeight() {
		return getTopOfBand()-getBottomOfBand();
	}
	
	private CvRect cvRect=null;;
	public CvRect getCvRect() {
		if (cvRect==null) {
			// cvRect(0, 260, 640, 420-260)
			cvRect = cvRect(0,getBottomOfBand(),getMaxX(),getHeight());
		}
		return cvRect;
	}
	
	public static ViewBand buildViewBand(int maxX, int maxY, int topOfBand, int bottomOfBand) {
		ViewBand viewBand = new ViewBand();
		viewBand.setMaxX(maxX);
		viewBand.setMaxY(maxY);
		if (topOfBand>maxY) topOfBand = maxY;
		viewBand.setTopOfBand(topOfBand);
		if (bottomOfBand<0) bottomOfBand = 0;
		viewBand.setBottomOfBand(bottomOfBand);
		return viewBand;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("vb=(%s, %s)",getBottomOfBand(), getTopOfBand()));
		return sb.toString();

	}

}
