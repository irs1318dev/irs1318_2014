package org.usfirst.frc1318.vision;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import com.googlecode.javacv.cpp.opencv_core.*;

public class HueBand {
	private String name="NoName";
	private CvScalar hueLow;
	private CvScalar hueHi;
	private CvScalar satLow;
	private CvScalar satHi;
	private CvScalar valLow;
	private CvScalar valHi;
	
	private int hueLowDegree;
	private int hueHiDegree;
	private int satLowPercent;
	private int satHiPercent; 
	private int valLowPercent;
	private int valHiPercent;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CvScalar getHueLow() {
		return hueLow;
	}
	public void setHueLow(CvScalar hueLow) {
		this.hueLow = hueLow;
	}
	public CvScalar getHueHi() {
		return hueHi;
	}
	public void setHueHi(CvScalar hueHi) {
		this.hueHi = hueHi;
	}
	public CvScalar getSatLow() {
		return satLow;
	}
	public void setSatLow(CvScalar satLow) {
		this.satLow = satLow;
	}
	public CvScalar getSatHi() {
		return satHi;
	}
	public void setSatHi(CvScalar satHi) {
		this.satHi = satHi;
	}
	public CvScalar getValLow() {
		return valLow;
	}
	public void setValLow(CvScalar valLow) {
		this.valLow = valLow;
	}
	public CvScalar getValHi() {
		return valHi;
	}
	public void setValHi(CvScalar valHi) {
		this.valHi = valHi;
	}
	
	public int getHueLowDegree() {
		return hueLowDegree;
	}
	public void setHueLowDegree(int hueLowDegree) {
		this.hueLowDegree = hueLowDegree;
	}
	public int getHueHiDegree() {
		return hueHiDegree;
	}
	public void setHueHiDegree(int hueHiDegree) {
		this.hueHiDegree = hueHiDegree;
	}
	public int getSatLowPercent() {
		return satLowPercent;
	}
	public void setSatLowPercent(int satLowPercent) {
		this.satLowPercent = satLowPercent;
	}
	public int getSatHiPercent() {
		return satHiPercent;
	}
	public void setSatHiPercent(int satHiPercent) {
		this.satHiPercent = satHiPercent;
	}
	public int getValLowPercent() {
		return valLowPercent;
	}
	public void setValLowPercent(int valLowPercent) {
		this.valLowPercent = valLowPercent;
	}
	public int getValHiPercent() {
		return valHiPercent;
	}
	public void setValHiPercent(int valHiPercent) {
		this.valHiPercent = valHiPercent;
	}
	

	public static HueBand buildHueBand(String name,int hueLow, int hueHi, int satLow, int satHi, int valLow, int valHi) {
		HueBand hueBand = new HueBand();
		hueBand.setName(name);
		hueBand.setHueLow(cvScalarAll(hueLow));
		hueBand.setHueHi(cvScalarAll(hueHi));
		hueBand.setSatLow(cvScalarAll(satLow));
		hueBand.setSatHi(cvScalarAll(satHi));
		hueBand.setValLow(cvScalarAll(valLow));
		hueBand.setValHi(cvScalarAll(valHi));
		return hueBand;
	}
	
	public static HueBand buildHueBandDegreePercent(String name,int hueLowDegree, int hueHiDegree, int satLowPercent, int satHiPercent, int valLowPercent, int valHiPercent) {
		HueBand hueBand = new HueBand();
		hueBand.setName(name);
		hueBand.setHueLowDegree(hueLowDegree);
		hueBand.setHueHiDegree(hueHiDegree);
		hueBand.setSatLowPercent(satLowPercent);
		hueBand.setSatHiPercent(satHiPercent);
		hueBand.setValLowPercent(valLowPercent);
		hueBand.setValHiPercent(valHiPercent);
		

		hueBand.setHueLow(cvScalarAll((int)Math.floor(255*255*hueLowDegree/(360*360))));
		hueBand.setHueHi(cvScalarAll((int)Math.ceil(255*255*hueHiDegree/(360*360))));
		hueBand.setSatLow(cvScalarAll((int)Math.floor(255*satLowPercent/100)));
		hueBand.setSatHi(cvScalarAll((int)Math.ceil(255*satHiPercent/100)));
		hueBand.setValLow(cvScalarAll((int)Math.floor(255*valLowPercent/100)));
		hueBand.setValHi(cvScalarAll((int)Math.ceil(255*valHiPercent/100)));
		return hueBand;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("HueBand=%s, ",getName()));
		sb.append(String.format("h=(%s, %s), s=(%s, %s), v=(%s, %s) ",getHueLowDegree(),getHueHiDegree(),getSatLowPercent(),getSatHiPercent(),getValLowPercent(),getValHiPercent()));
		return sb.toString();

	}
}
