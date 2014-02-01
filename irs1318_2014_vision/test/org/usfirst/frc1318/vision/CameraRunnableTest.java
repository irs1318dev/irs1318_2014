package org.usfirst.frc1318.vision;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import com.googlecode.javacv.cpp.opencv_core.*;

import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;

import static java.lang.String.*;
import static java.lang.Math.*;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;
import org.usfirst.frc1318.data.Point;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

public class CameraRunnableTest {

	@Test
	public void testProcessImage() {
		// read an image
		File file = new File(getInputPath(), getSampleFile());
		IplImage image;
		try {
			image = cvLoadImage(file.getCanonicalPath());
			CalcRunnable cr = new CalcRunnable();
			cr.processImage(image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String outputPath = "C:\\dev\\frc2012\\cornerdetect\\processed";
	private String inputPath = "C:\\dev\\frc2012\\cornerdetect";

	private String sampleFile = "00_initial_image.jpg";

	public String getOutputPath() {
		return outputPath;
	}

	public String getInputPath() {
		return inputPath;
	}

	public String getSampleFile() {
		return sampleFile;
	}

	public List<Point> analyzeKnownImage(CalcRunnable cr) {

		IplImage temp = cvCreateImage(cvSize(640, 480), IPL_DEPTH_8U, 1);
		cvZero(temp);
		CvPoint p0 = cvPoint(320, 240);
		System.out.println(String.format("x=%s, y=%s", p0.x(), p0.y()));
		cvCircle(temp, p0, 5, CvScalar.BLUE, 1, 8, 0);
		// JavaCVUtils.saveImage(getOutputPath(),
		// "99_centerCircle.jpg", temp);
		return cr.findImagePoints(temp).getPoints();

	}

	@Test
	public void testCenterCircle() {
		CalcRunnable cr = new CalcRunnable();
		List<Point> points = analyzeKnownImage(cr);
		// x=320, y=240
		// width=640, height=480
		// count pos val x y
		Assert.assertEquals(320, (int) points.get(0).getX());
		Assert.assertEquals(235, (int) points.get(0).getY());
		Assert.assertEquals(317, (int) points.get(1).getX());
		Assert.assertEquals(236, (int) points.get(1).getY());
		Assert.assertEquals(318, (int) points.get(2).getX());
		Assert.assertEquals(236, (int) points.get(2).getY());
		Assert.assertEquals(319, (int) points.get(3).getX());
		Assert.assertEquals(236, (int) points.get(3).getY());
		Assert.assertEquals(321, (int) points.get(4).getX());
		Assert.assertEquals(236, (int) points.get(4).getY());
		Assert.assertEquals(322, (int) points.get(5).getX());
		Assert.assertEquals(236, (int) points.get(5).getY());
		Assert.assertEquals(323, (int) points.get(6).getX());
		Assert.assertEquals(236, (int) points.get(6).getY());
		Assert.assertEquals(316, (int) points.get(7).getX());
		Assert.assertEquals(237, (int) points.get(7).getY());
		Assert.assertEquals(324, (int) points.get(8).getX());
		Assert.assertEquals(237, (int) points.get(8).getY());
		Assert.assertEquals(316, (int) points.get(9).getX());
		Assert.assertEquals(238, (int) points.get(9).getY());
		Assert.assertEquals(324, (int) points.get(10).getX());
		Assert.assertEquals(238, (int) points.get(10).getY());
		Assert.assertEquals(316, (int) points.get(11).getX());
		Assert.assertEquals(239, (int) points.get(11).getY());
		Assert.assertEquals(324, (int) points.get(12).getX());
		Assert.assertEquals(239, (int) points.get(12).getY());
		Assert.assertEquals(315, (int) points.get(13).getX());
		Assert.assertEquals(240, (int) points.get(13).getY());
		Assert.assertEquals(325, (int) points.get(14).getX());
		Assert.assertEquals(240, (int) points.get(14).getY());
		Assert.assertEquals(316, (int) points.get(15).getX());
		Assert.assertEquals(241, (int) points.get(15).getY());
		Assert.assertEquals(324, (int) points.get(16).getX());
		Assert.assertEquals(241, (int) points.get(16).getY());
		Assert.assertEquals(316, (int) points.get(17).getX());
		Assert.assertEquals(242, (int) points.get(17).getY());
		Assert.assertEquals(324, (int) points.get(18).getX());
		Assert.assertEquals(242, (int) points.get(18).getY());
		Assert.assertEquals(316, (int) points.get(19).getX());
		Assert.assertEquals(243, (int) points.get(19).getY());
		Assert.assertEquals(324, (int) points.get(20).getX());
		Assert.assertEquals(243, (int) points.get(20).getY());
		Assert.assertEquals(317, (int) points.get(21).getX());
		Assert.assertEquals(244, (int) points.get(21).getY());
		Assert.assertEquals(318, (int) points.get(22).getX());
		Assert.assertEquals(244, (int) points.get(22).getY());
		Assert.assertEquals(319, (int) points.get(23).getX());
		Assert.assertEquals(244, (int) points.get(23).getY());
		Assert.assertEquals(321, (int) points.get(24).getX());
		Assert.assertEquals(244, (int) points.get(24).getY());
		Assert.assertEquals(322, (int) points.get(25).getX());
		Assert.assertEquals(244, (int) points.get(25).getY());
		Assert.assertEquals(323, (int) points.get(26).getX());
		Assert.assertEquals(244, (int) points.get(26).getY());
		Assert.assertEquals(320, (int) points.get(27).getX());
		Assert.assertEquals(245, (int) points.get(27).getY());
	}

	HueBand defaultHueBand = HueBand.buildHueBand("default", 75, 90, 147, 255,
			240, 255);
	HueBand allHueBand = HueBand.buildHueBandDegreePercent("all", 80, 95, 50,
			100, 80, 100);

	// HueBand near2HueBand = HueBand.buildHueBandDegreePercent("01near", 150,
	// 165, 80, 100, 90, 100); //0

	HueBand near3HueBand = HueBand.buildHueBandDegreePercent("02near", 130,
			160, 50, 100, 50, 100); // 0
	HueBand mid2HueBand = HueBand.buildHueBandDegreePercent("03mid", 140, 185,
			30, 90, 60, 95);
	HueBand mid3HueBand = HueBand.buildHueBandDegreePercent("04mid", 140, 185,
			25, 60, 50, 85);
	HueBand far2HueBand = HueBand.buildHueBandDegreePercent("05far", 140, 165,
			10, 40, 50, 85);
	HueBand far3HueBand = HueBand.buildHueBandDegreePercent("06far", 140, 165,
			10, 40, 40, 85);

	@Test
	public void testRawFootage() {
		String sourcePath = "C:\\dev\\frc2012\\rawfootage";
		String hsvPath = "C:\\dev\\frc2012\\rawfootage\\hsv";

		IplImage image;
		CalcRunnable cr = new CalcRunnable();
		cr.setResolution(640);

		int count = 0;
		for (File file : new File(sourcePath).listFiles()) {
			if (file.isDirectory())
				continue;

			try {
				image = cvLoadImage(file.getCanonicalPath());
				IplImage undistortImage = cr.undistort(image); // this is image
																// transformed
																// in place
				IplImage finalHSV = null;

				// finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
				// cr.performHsvFilter(undistortImage,finalHSV,near2HueBand);
				// JavaCVUtils.saveImage(hsvPath,
				// "hsv_"+near2HueBand.getName()+"_"+file.getName(), finalHSV);
				// cvReleaseImage(finalHSV);

				finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
				cr.performHsvFilter(undistortImage, finalHSV, near3HueBand);
				JavaCVUtils.saveImage(hsvPath, "hsv_" + near3HueBand.getName()
						+ "_" + file.getName(), finalHSV);
				cvReleaseImage(finalHSV);

				finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
				cr.performHsvFilter(undistortImage, finalHSV, mid2HueBand);
				JavaCVUtils.saveImage(hsvPath, "hsv_" + mid2HueBand.getName()
						+ "_" + file.getName(), finalHSV);
				cvReleaseImage(finalHSV);

				finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
				cr.performHsvFilter(undistortImage, finalHSV, mid3HueBand);
				JavaCVUtils.saveImage(hsvPath, "hsv_" + mid3HueBand.getName()
						+ "_" + file.getName(), finalHSV);
				cvReleaseImage(finalHSV);

				finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
				cr.performHsvFilter(undistortImage, finalHSV, far2HueBand);
				JavaCVUtils.saveImage(hsvPath, "hsv_" + far2HueBand.getName()
						+ "_" + file.getName(), finalHSV);
				cvReleaseImage(finalHSV);

				finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
				cr.performHsvFilter(undistortImage, finalHSV, far3HueBand);
				JavaCVUtils.saveImage(hsvPath, "hsv_" + far3HueBand.getName()
						+ "_" + file.getName(), finalHSV);
				cvReleaseImage(finalHSV);

				count++;
				// if (count>2) break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/*
	 * ticks meters feet 3500 2.8 9.1 7000 5.6 18.2 9000 7.2 23.4 11000 8.8 28.6
	 * 14000 11.2 36.4
	 */
	HueBand nearHueBand = HueBand.buildHueBandDegreePercent("01near", 170, 185,
			50, 100, 90, 100); // 0
	HueBand near1HueBand = HueBand.buildHueBandDegreePercent("02near", 155,
			185, 50, 100, 70, 100); // 0
	HueBand midHueBand = HueBand.buildHueBandDegreePercent("03mid", 140, 185,
			30, 90, 60, 95); // 102110
	HueBand mid1HueBand = HueBand.buildHueBandDegreePercent("04mid", 140, 185,
			25, 60, 50, 85); // 102110
	HueBand farHueBand = HueBand.buildHueBandDegreePercent("05far", 150, 185,
			10, 60, 50, 85);
	HueBand far1HueBand = HueBand.buildHueBandDegreePercent("06far", 150, 195,
			10, 60, 40, 85);

	@Test
	public void testSnapshotFootage() {
		String sourcePath = "C:\\dev\\frc2012\\snapshots";
		String hsvPath = "C:\\dev\\frc2012\\snapshots\\hsv";

		IplImage image;
		CalcRunnable cr = new CalcRunnable();
		cr.setResolution(640);

		Map<Integer, File> sortedFiles = new TreeMap<Integer, File>();
		for (File file : new File(sourcePath).listFiles()) {
			if (file.isDirectory())
				continue;

			String filename = file.getName();
			String tickStr = filename.split("_")[1].split("\\.")[0];
			int tickCount = Integer.parseInt(tickStr);
			sortedFiles.put(tickCount, file);

		}

		int count = 0;
		for (int tickCount : sortedFiles.keySet()) {
			File file = sortedFiles.get(tickCount);
			try {
				image = cvLoadImage(file.getCanonicalPath());
				IplImage undistortImage = cr.undistort(image); // this is image
																// transformed
																// in place
				IplImage finalHSV = null;

				if (tickCount == 22) {

					finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
					// cvSetImageROI(finalHSV,cvRect(0, 260, 640, 480-260));
					ViewBand vb = ViewBand.buildViewBand(640, 480, 480, 260);
					cvSetImageROI(finalHSV, vb.getCvRect());
					HueBand hueBand = midHueBand;
					cr.performHsvFilter(undistortImage, finalHSV, hueBand);
					HsvAnalysis hsvAnalysis = cr.findImagePoints(finalHSV);
					hsvAnalysis.setName("" + tickCount);
					hsvAnalysis.analyzePoints();
					JavaCVUtils.saveImage(hsvPath, "hsv_" + hueBand.getName()
							+ "_" + file.getName(), finalHSV);
					cvReleaseImage(finalHSV);
				} else if (tickCount < 7000) {

					finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
					// cvSetImageROI(finalHSV,cvRect(0, 260, 640, 480-260));
					ViewBand vb = ViewBand.buildViewBand(640, 480, 480, 260);
					cvSetImageROI(finalHSV, vb.getCvRect());
					cr.performHsvFilter(undistortImage, finalHSV, near1HueBand);
					HsvAnalysis hsvAnalysis = cr.findImagePoints(finalHSV);
					hsvAnalysis.setName("" + tickCount);
					hsvAnalysis.analyzePoints();
					JavaCVUtils.saveImage(
							hsvPath,
							"hsv_" + near1HueBand.getName() + "_"
									+ file.getName(), finalHSV);
					cvReleaseImage(finalHSV);
				} else if (tickCount < 9000) {

					finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
					// cvSetImageROI(finalHSV,cvRect(0, 260, 640, 420-260));
					ViewBand vb = ViewBand.buildViewBand(640, 480, 420, 260);
					cvSetImageROI(finalHSV, vb.getCvRect());
					cr.performHsvFilter(undistortImage, finalHSV, midHueBand);
					HsvAnalysis hsvAnalysis = cr.findImagePoints(finalHSV);
					hsvAnalysis.setName("" + tickCount);
					hsvAnalysis.analyzePoints();
					JavaCVUtils.saveImage(
							hsvPath,
							"hsv_" + midHueBand.getName() + "_"
									+ file.getName(), finalHSV);
					cvReleaseImage(finalHSV);
				} else if (tickCount < 11800) {

					finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
					// cvSetImageROI(finalHSV,cvRect(0, 260, 640, 400-260));
					ViewBand vb = ViewBand.buildViewBand(640, 480, 400, 260);
					cvSetImageROI(finalHSV, vb.getCvRect());
					cr.performHsvFilter(undistortImage, finalHSV, mid1HueBand);
					HsvAnalysis hsvAnalysis = cr.findImagePoints(finalHSV);
					hsvAnalysis.setName("" + tickCount);
					hsvAnalysis.analyzePoints();
					JavaCVUtils.saveImage(
							hsvPath,
							"hsv_" + mid1HueBand.getName() + "_"
									+ file.getName(), finalHSV);
					cvReleaseImage(finalHSV);
				} else if (tickCount < 14000) {

					finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
					// cvSetImageROI(finalHSV,cvRect(0, 260, 640, 330-260));
					ViewBand vb = ViewBand.buildViewBand(640, 480, 330, 260);
					cvSetImageROI(finalHSV, vb.getCvRect());
					cr.performHsvFilter(undistortImage, finalHSV, farHueBand);
					HsvAnalysis hsvAnalysis = cr.findImagePoints(finalHSV);
					hsvAnalysis.setName("" + tickCount);
					hsvAnalysis.analyzePoints();
					JavaCVUtils.saveImage(
							hsvPath,
							"hsv_" + farHueBand.getName() + "_"
									+ file.getName(), finalHSV);
					cvReleaseImage(finalHSV);
				} else {

					finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
					// cvSetImageROI(finalHSV,cvRect(0, 260, 640, 300-260));
					ViewBand vb = ViewBand.buildViewBand(640, 480, 300, 260);
					cvSetImageROI(finalHSV, vb.getCvRect());
					cr.performHsvFilter(undistortImage, finalHSV, far1HueBand);
					HsvAnalysis hsvAnalysis = cr.findImagePoints(finalHSV);
					hsvAnalysis.setName("" + tickCount);
					hsvAnalysis.analyzePoints();
					JavaCVUtils.saveImage(
							hsvPath,
							"hsv_" + far1HueBand.getName() + "_"
									+ file.getName(), finalHSV);
					cvReleaseImage(finalHSV);

				}

				count++;
				// if (count>2) break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Test
	public void testSnapshotCorners() {
		String sourcePath = "C:\\dev\\frc2012\\snapshots";
		String outputPath = "C:\\dev\\frc2012\\snapshots\\corners";

		IplImage image;
		CalcRunnable cr = new CalcRunnable();
		cr.setResolution(640);

		int count = 0;
		for (File file : new File(sourcePath).listFiles()) {
			if (file.isDirectory())
				continue;

			String filename = file.getName();
			String tickStr = filename.split("_")[1].split("\\.")[0];
			int tickCount = Integer.parseInt(tickStr);
			if (tickCount != 2325)
				continue;

			try {
				image = cvLoadImage(file.getCanonicalPath());
				IplImage undistortImage = cr.undistort(image); // this is image
																// transformed
																// in place
				IplImage finalHSV = null;

				finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
				cvSetImageROI(finalHSV, cvRect(0, 260, 640, 480 - 260));
				cr.performHsvFilter(undistortImage, finalHSV, nearHueBand);
				HsvAnalysis hsvAnalysis = cr.findImagePoints(finalHSV);
				List<Point> points = hsvAnalysis.getPoints();
				Assert.assertEquals(5317, hsvAnalysis.getPoints().size());
				System.out.printf("points=%s\n", points.size());
				System.out.printf("minx=%s, maxx=%s, miny=%s, maxy=%s\n",
						hsvAnalysis.getMinX(), hsvAnalysis.getMaxX(),
						hsvAnalysis.getMinY(), hsvAnalysis.getMaxY());
				hsvAnalysis.analyzePoints();

				JavaCVUtils.saveImage(
						outputPath,
						"corner_" + nearHueBand.getName() + "_"
								+ file.getName(), finalHSV);
				cvReleaseImage(finalHSV);

				count++;
				// if (count>2) break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Test
	public void testLookupTableCalibration() {
		String sourcePath = "C:\\dev\\frc2012\\range\\snapshots640_0deg";
		String hsvPath = "C:\\dev\\frc2012\\range\\snapshots640_0deg\\hsv";

		IplImage image;
		CalcRunnable cr = new CalcRunnable();
		cr.setResolution(640);

		Map<Integer, File> sortedFiles = new TreeMap<Integer, File>();
		for (File file : new File(sourcePath).listFiles()) {
			if (file.isDirectory())
				continue;

			String filename = file.getName();
			String tickStr = filename.split("\\.")[0];
			int tickCount = Integer.parseInt(tickStr);
			sortedFiles.put(tickCount, file);

		}

		HueBand bestHueBand = null;
		HsvAnalysis bestAnalysis = null;

		int count = 0;
		for (int tickCount : sortedFiles.keySet()) {
			File file = sortedFiles.get(tickCount);
			try {
				image = cvLoadImage(file.getCanonicalPath());
				IplImage undistortImage = cr.undistort(image); // this is image
																// transformed
																// in place
				IplImage finalHSV = null;

				if (tickCount == 22) {

					HueBand hueBand = null;
					for (int hueSetting = 0; hueSetting < 5; hueSetting++) {
						int hueLow = 0;
						int hueHigh = 0;
						switch (hueSetting) {
						case 0:
							hueLow = 130;
							hueHigh = 195;
							break;
						case 1:
							hueLow = 140;
							hueHigh = 185;
							break;
						case 2:
							hueLow = 150;
							hueHigh = 185;
							break;
						case 3:
							hueLow = 160;
							hueHigh = 185;
							break;
						case 4:
							hueLow = 170;
							hueHigh = 185;
							break;
						}

						for (int satSetting = 0; satSetting < 5; satSetting++) {
							int satLow = 0;
							int satHigh = 0;
							switch (satSetting) {
							case 0:
								satLow = 10;
								satHigh = 60;
								break;
							case 1:
								satLow = 25;
								satHigh = 60;
								break;
							case 2:
								satLow = 30;
								satHigh = 90;
								break;
							case 3:
								satLow = 50;
								satHigh = 100;
								break;
							case 4:
								satLow = 70;
								satHigh = 100;
								break;
							}
							for (int valSetting = 0; valSetting < 5; valSetting++) {
								int valLow = 0;
								int valHigh = 0;
								switch (valSetting) {
								case 0:
									valLow = 40;
									valHigh = 85;
									break;
								case 1:
									valLow = 50;
									valHigh = 85;
									break;
								case 2:
									valLow = 60;
									valHigh = 95;
									break;
								case 3:
									valLow = 70;
									valHigh = 100;
									break;
								case 4:
									valLow = 90;
									valHigh = 100;
									break;
								}
								String setting = String.format("%s_%s_%s",
										hueSetting, satSetting, valSetting);
								hueBand = HueBand.buildHueBandDegreePercent(
										setting, hueLow, hueHigh, satLow,
										satHigh, valLow, valHigh);

								finalHSV = cvCreateImage(cvGetSize(image),
										IPL_DEPTH_8U, 1);
								ViewBand vb = ViewBand.buildViewBand(640, 480,
										480, 260);
								cvSetImageROI(finalHSV, vb.getCvRect());
								cr.performHsvFilter(undistortImage, finalHSV,
										hueBand);
								HsvAnalysis hsvAnalysis = cr
										.findImagePoints(finalHSV);
								hsvAnalysis.setName("" + tickCount);
								hsvAnalysis.analyzePoints();
								System.out.println(hueBand);
								System.out.println(String.format("%s: %s",
										hsvAnalysis.getName(),
										hsvAnalysis.getTrapezoid()));

								if (bestAnalysis == null) {
									bestAnalysis = hsvAnalysis;
									bestHueBand = hueBand;
								} else {
									if (bestAnalysis.getTrapezoid()
											.getLineWidth() <= hsvAnalysis
											.getTrapezoid().getLineWidth()
											&& bestAnalysis.getTrapezoid()
													.getPointCount() <= hsvAnalysis
													.getTrapezoid()
													.getPointCount()) {
										bestAnalysis = hsvAnalysis;
										bestHueBand = hueBand;
									}
								}

								// JavaCVUtils.saveImage(hsvPath,
								// "hsv_"+hueBand.getName()+"_"+file.getName(),
								// finalHSV);
								cvReleaseImage(finalHSV);
							}

						}
					}

					hueBand = bestHueBand;
					hueBand.setName("00best_" + bestHueBand.getName());

					finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
					ViewBand vb = ViewBand.buildViewBand(640, 480, 480, 260);
					cvSetImageROI(finalHSV, vb.getCvRect());
					cr.performHsvFilter(undistortImage, finalHSV, hueBand);
					HsvAnalysis hsvAnalysis = cr.findImagePoints(finalHSV);
					hsvAnalysis.setName("" + tickCount);
					hsvAnalysis.analyzePoints();
					System.out.println(hueBand);
					System.out.println(String.format("%s: %s",
							hsvAnalysis.getName(), hsvAnalysis.getTrapezoid()));
					JavaCVUtils.saveImage(hsvPath, "hsv_" + hueBand.getName()
							+ "_" + file.getName(), finalHSV);
					cvReleaseImage(finalHSV);

				}

				count++;
				// if (count>2) break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Test
	public void testFullLookupTableCalibration() {
//		String sourcePath = "C:\\dev\\frc2012\\range\\bright640_middleOnly";
//		String hsvPath = "C:\\dev\\frc2012\\range\\bright640_middleOnly\\hsv";
		String sourcePath = "C:\\dev\\frc2012\\range\\bright640_full_field";
		String hsvPath = "C:\\dev\\frc2012\\range\\bright640_full_field\\hsv";
		
		IplImage image;
		CalcRunnable cr = new CalcRunnable();
		cr.setResolution(640);

		Map<String, File> sortedFiles = new TreeMap<String, File>();
		for (File file : new File(sourcePath).listFiles()) {
			if (file.isDirectory())
				continue;

			String filename = file.getName();
//			String tickStr = filename.split("_")[1].split("\\.")[0]; // time tick calibration
			String tickStr = filename.split("\\.")[0]; // feet calibration
//			int tickCount = Integer.parseInt(tickStr);
			sortedFiles.put(tickStr, file);

		}

		int count = 0;
		for (String tickCount : sortedFiles.keySet()) {
			File file = sortedFiles.get(tickCount);
			try {
				image = cvLoadImage(file.getCanonicalPath());
				IplImage undistortImage = cr.undistort(image); // this is image
																// transformed
																// in place

				boolean proceed = true;
//				proceed = tickCount == 16;
				if (!proceed)
					continue;

				IplImage finalHSV = null;
				ViewBand vb = ViewBand.buildViewBand(640, 480, 480, 0); // default
//				ViewBand vb = ViewBand.buildViewBand(640, 480, 390, 110); // default
				boolean viewPortAdjustment = true; // do once only

				HueBand bestHueBand = null;
				HsvAnalysis bestAnalysis = null;
				HueBand hueBand = null;

				// perform the search for each view port
				boolean adjustedOnce = false;
				newViewPort: do {
					for (int hueSetting = 0; hueSetting < 5; hueSetting++) {
						int hueLow = 0;
						int hueHigh = 0;
						switch (hueSetting) {
						case 0:
							hueLow = 130;
							hueHigh = 195;
							break;
						case 1:
							hueLow = 140;
							hueHigh = 185;
							break;
						case 2:
							hueLow = 150;
							hueHigh = 185;
							break;
						case 3:
							hueLow = 160;
							hueHigh = 185;
							break;
						case 4:
							hueLow = 170;
							hueHigh = 185;
							break;
						}

						for (int satSetting = 0; satSetting < 5; satSetting++) {
							int satLow = 0;
							int satHigh = 0;
							switch (satSetting) {
							case 0:
								satLow = 10;
								satHigh = 60;
								break;
							case 1:
								satLow = 20;
								satHigh = 100;
								break;
							case 2:
								satLow = 30;
								satHigh = 90;
								break;
							case 3:
								satLow = 50;
								satHigh = 100;
								break;
							case 4:
								satLow = 70;
								satHigh = 100;
								break;
							}
							for (int valSetting = 0; valSetting < 5; valSetting++) {
								int valLow = 0;
								int valHigh = 0;
								switch (valSetting) {
								case 0:
									valLow = 40;
									valHigh = 85;
									break;
								case 1:
									valLow = 50;
									valHigh = 85;
									break;
								case 2:
									valLow = 60;
									valHigh = 95;
									break;
								case 3:
									valLow = 70;
									valHigh = 100;
									break;
								case 4:
									valLow = 90;
									valHigh = 100;
									break;
								}
								String setting = String.format("%s_%s_%s",
										hueSetting, satSetting, valSetting);
								if (!"1_1_3".equals(setting))		continue;

								hueBand = HueBand.buildHueBandDegreePercent(
										setting, hueLow, hueHigh, satLow,
										satHigh, valLow, valHigh);

								finalHSV = cvCreateImage(cvGetSize(image),
										IPL_DEPTH_8U, 1);
								cvSetImageROI(finalHSV, vb.getCvRect());
								cr.performHsvFilter(undistortImage, finalHSV,
										hueBand);
								HsvAnalysis hsvAnalysis = cr
										.findImagePoints(finalHSV);
								hsvAnalysis.setName("" + tickCount);
								hsvAnalysis.analyzePoints();

								JavaCVUtils.saveImage(hsvPath, "hsv_temp_"
										+ tickCount + "_" + hueBand.getName()
										+ ".jpg", finalHSV);

								if (hsvAnalysis.getTrapezoid().hasError()
										&& hsvAnalysis.getTrapezoid()
												.getErrorMessage()
												.contains("ViewLow")
										&& hsvAnalysis.getDataAspectRatio() > 1.6 // filter
																					// outlines
																					// out.
										&& viewPortAdjustment // do one
																// adjustment
																// only.
								) {

									System.out.println(hueBand);
									System.out.println(String.format("%s: %s",
											hsvAnalysis.getName(),
											hsvAnalysis.getTrapezoid()));
									// JavaCVUtils.saveImage(hsvPath, "hsv_"
									// + hueBand.getName()
									// + "_"
									// + hsvAnalysis.getTrapezoid()
									// .getErrorMessage() + "_"
									// + file.getName(), finalHSV);

//									vb = ViewBand
//											.buildViewBand(
//													640,
//													480,
//													hsvAnalysis.getMaxY() + 60,
//													hsvAnalysis.getMaxY()
//															+ 60
//															- (hsvAnalysis
//																	.getDataWidth() + 120));
									if (!adjustedOnce) {
										adjustedOnce = true;
									} else {
										viewPortAdjustment = false;
									}
									continue newViewPort; // start for loop
															// again.

								} 

								if (bestAnalysis == null) {
									if (hsvAnalysis.getTrapezoid()
											.getLineWidth() > 0
											&& hsvAnalysis.getTrapezoid()
													.getLineWidth() <= 20) {
										bestAnalysis = hsvAnalysis;
										bestHueBand = hueBand;
									}
								} else {
									if (hsvAnalysis.getTrapezoid()
											.getLineWidth() > 0
											&& hsvAnalysis.getTrapezoid()
													.getLineWidth() <= 20
											&& bestAnalysis.getTrapezoid()
													.getLineWidth() <= hsvAnalysis
													.getTrapezoid()
													.getLineWidth()
											&& bestAnalysis.getTrapezoid()
													.getPointCount() <= hsvAnalysis
													.getTrapezoid()
													.getPointCount()) {
										bestAnalysis = hsvAnalysis;
										bestHueBand = hueBand;
									}
								}

								cvReleaseImage(finalHSV);
							} // for valSetting
						} // for satSetting
					} // for hueSetting
					viewPortAdjustment = false;
				} while (viewPortAdjustment);
				if (bestHueBand == null) {
					continue;
				}
				hueBand = bestHueBand;
				hueBand.setName("00best_" + bestHueBand.getName());

				finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
				cvSetImageROI(finalHSV, vb.getCvRect());
				cr.performHsvFilter(undistortImage, finalHSV, hueBand);
				HsvAnalysis hsvAnalysis = cr.findImagePoints(finalHSV);
				hsvAnalysis.setName("" + tickCount);
				hsvAnalysis.analyzePoints();
				System.out.println(hueBand);
				System.out.println(String.format("%s: %s",
						hsvAnalysis.getName(), hsvAnalysis.getTrapezoid()));
				JavaCVUtils.saveImage(hsvPath, "hsv_" + tickCount + "_"
						+ hueBand.getName() + ".jpg", finalHSV);
				cvReleaseImage(finalHSV);

				count++;
				// if (count>2) break;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	@Test
	public void testSkunkWorksCalibration() {
//		String sourcePath = "C:\\dev\\frc2012\\range\\bright640_middleOnly";
//		String hsvPath = "C:\\dev\\frc2012\\range\\bright640_middleOnly\\hsv";
		String sourcePath = "C:\\dev\\frc2012\\range\\bright640_full_field";
		String hsvPath = "C:\\dev\\frc2012\\range\\bright640_full_field\\hsv";
		
		IplImage image;
		CalcRunnable cr = new CalcRunnable();
		cr.setResolution(640);

		Map<String, File> sortedFiles = new TreeMap<String, File>();
		for (File file : new File(sourcePath).listFiles()) {
			if (file.isDirectory())
				continue;

			String filename = file.getName();
//			String tickStr = filename.split("_")[1].split("\\.")[0]; // time tick calibration
			String tickStr = filename.split("\\.")[0]; // feet calibration
//			int tickCount = Integer.parseInt(tickStr);
			sortedFiles.put(tickStr, file);

		}

		int count = 0;
		for (String tickCount : sortedFiles.keySet()) {
			File file = sortedFiles.get(tickCount);
			try {
				image = cvLoadImage(file.getCanonicalPath());
				IplImage undistortImage = cr.undistort(image); // this is image
																// transformed
																// in place

				boolean proceed = true;
				proceed = "10".equals(tickCount);
				if (!proceed)
					continue;

				IplImage finalHSV = null;
				ViewBand vb = ViewBand.buildViewBand(640, 480, 480, 0); // default
//				ViewBand vb = ViewBand.buildViewBand(640, 480, 390, 110); // default
				boolean viewPortAdjustment = true; // do once only

				HueBand bestHueBand = null;
				HsvAnalysis bestAnalysis = null;
				HueBand hueBand = null;

				// perform the search for each view port
				boolean adjustedOnce = false;
				newViewPort: do {
					for (int hueSetting = 0; hueSetting < 5; hueSetting++) {
						int hueLow = 0;
						int hueHigh = 0;
						switch (hueSetting) {
						case 0:
							hueLow = 130;
							hueHigh = 195;
							break;
						case 1:
							hueLow = 140;
							hueHigh = 185;
							break;
						case 2:
							hueLow = 150;
							hueHigh = 185;
							break;
						case 3:
							hueLow = 160;
							hueHigh = 185;
							break;
						case 4:
							hueLow = 170;
							hueHigh = 185;
							break;
						}

						for (int satSetting = 0; satSetting < 5; satSetting++) {
							int satLow = 0;
							int satHigh = 0;
							switch (satSetting) {
							case 0:
								satLow = 10;
								satHigh = 60;
								break;
							case 1:
								satLow = 20;
								satHigh = 100;
								break;
							case 2:
								satLow = 30;
								satHigh = 90;
								break;
							case 3:
								satLow = 50;
								satHigh = 100;
								break;
							case 4:
								satLow = 70;
								satHigh = 100;
								break;
							}
							for (int valSetting = 0; valSetting < 5; valSetting++) {
								int valLow = 0;
								int valHigh = 0;
								switch (valSetting) {
								case 0:
									valLow = 40;
									valHigh = 85;
									break;
								case 1:
									valLow = 50;
									valHigh = 85;
									break;
								case 2:
									valLow = 60;
									valHigh = 95;
									break;
								case 3:
									valLow = 70;
									valHigh = 100;
									break;
								case 4:
									valLow = 90;
									valHigh = 100;
									break;
								}
								String setting = String.format("%s_%s_%s",
										hueSetting, satSetting, valSetting);
								if (!"0_1_4".equals(setting))		continue;

								hueBand = HueBand.buildHueBandDegreePercent(
										setting, hueLow, hueHigh, satLow,
										satHigh, valLow, valHigh);

								finalHSV = cvCreateImage(cvGetSize(image),
										IPL_DEPTH_8U, 1);
								cvSetImageROI(finalHSV, vb.getCvRect());
								cr.performHsvFilter(undistortImage, finalHSV,
										hueBand);
								HsvAnalysis hsvAnalysis = cr
										.findImagePoints(finalHSV);
								hsvAnalysis.setName("" + tickCount);
								hsvAnalysis.analyzePoints2();

								JavaCVUtils.saveImage(hsvPath, "hsv_temp_"
										+ tickCount + "_" + hueBand.getName()
										+ ".jpg", finalHSV);

								if (hsvAnalysis.getTrapezoid().hasError()
										&& hsvAnalysis.getTrapezoid()
												.getErrorMessage()
												.contains("ViewLow")
										&& hsvAnalysis.getDataAspectRatio() > 1.6 // filter
																					// outlines
																					// out.
										&& viewPortAdjustment // do one
																// adjustment
																// only.
								) {

									System.out.println(hueBand);
									System.out.println(String.format("%s: %s",
											hsvAnalysis.getName(),
											hsvAnalysis.getTrapezoid()));
									// JavaCVUtils.saveImage(hsvPath, "hsv_"
									// + hueBand.getName()
									// + "_"
									// + hsvAnalysis.getTrapezoid()
									// .getErrorMessage() + "_"
									// + file.getName(), finalHSV);

//									vb = ViewBand
//											.buildViewBand(
//													640,
//													480,
//													hsvAnalysis.getMaxY() + 60,
//													hsvAnalysis.getMaxY()
//															+ 60
//															- (hsvAnalysis
//																	.getDataWidth() + 120));
									if (!adjustedOnce) {
										adjustedOnce = true;
									} else {
										viewPortAdjustment = false;
									}
									continue newViewPort; // start for loop
															// again.

								} 

								if (bestAnalysis == null) {
									if (hsvAnalysis.getTrapezoid()
											.getLineWidth() > 0
											&& hsvAnalysis.getTrapezoid()
													.getLineWidth() <= 20) {
										bestAnalysis = hsvAnalysis;
										bestHueBand = hueBand;
									}
								} else {
									if (hsvAnalysis.getTrapezoid()
											.getLineWidth() > 0
											&& hsvAnalysis.getTrapezoid()
													.getLineWidth() <= 20
											&& bestAnalysis.getTrapezoid()
													.getLineWidth() <= hsvAnalysis
													.getTrapezoid()
													.getLineWidth()
											&& bestAnalysis.getTrapezoid()
													.getPointCount() <= hsvAnalysis
													.getTrapezoid()
													.getPointCount()) {
										bestAnalysis = hsvAnalysis;
										bestHueBand = hueBand;
									}
								}

								cvReleaseImage(finalHSV);
							} // for valSetting
						} // for satSetting
					} // for hueSetting
					viewPortAdjustment = false;
				} while (viewPortAdjustment);
				if (bestHueBand == null) {
					continue;
				}
				hueBand = bestHueBand;
				hueBand.setName("00best_" + bestHueBand.getName());

				finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
				cvSetImageROI(finalHSV, vb.getCvRect());
				cr.performHsvFilter(undistortImage, finalHSV, hueBand);
				HsvAnalysis hsvAnalysis = cr.findImagePoints(finalHSV);
				hsvAnalysis.setName("" + tickCount);
				hsvAnalysis.analyzePoints2();
				System.out.println(hueBand);
				System.out.println(String.format("%s: %s",
						hsvAnalysis.getName(), hsvAnalysis.getTrapezoid()));
				JavaCVUtils.saveImage(hsvPath, "hsv_" + tickCount + "_"
						+ hueBand.getName() + ".jpg", finalHSV);
				cvReleaseImage(finalHSV);

				count++;
				// if (count>2) break;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
