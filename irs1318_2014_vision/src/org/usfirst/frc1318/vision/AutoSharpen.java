package org.usfirst.frc1318.vision;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import com.googlecode.javacv.cpp.opencv_core.*;

public class AutoSharpen {
	
	String hsvPath = "C:\\dev\\frc2012\\range\\integration\\hsv";
	
	public static void autoSharpen(CameraRunnable cr, CalcRunnable calcRunnable, int frameCount, IplImage undistortImage) {
		IplImage finalHSV = null;
		ViewBand vb = ViewBand.buildViewBand(640, 480, 390, 110); // default
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
//						if (!"1_1_3".equals(setting))		continue;

						hueBand = HueBand.buildHueBandDegreePercent(
								setting, hueLow, hueHigh, satLow,
								satHigh, valLow, valHigh);

						finalHSV = cvCreateImage(cvGetSize(undistortImage),
								IPL_DEPTH_8U, 1);
						cvSetImageROI(finalHSV, vb.getCvRect());
						calcRunnable.performHsvFilter(undistortImage, finalHSV,
								hueBand);
						HsvAnalysis hsvAnalysis = calcRunnable
								.findImagePoints(finalHSV);
						hsvAnalysis.setName("" + frameCount);
						hsvAnalysis.analyzePoints();

//						JavaCVUtils.saveImage(hsvPath, "hsv_temp_"
//								+ frameCount + "_" + hueBand.getName()
//								+ ".jpg", finalHSV);

						if (hsvAnalysis.getTrapezoid().hasError()
								&& hsvAnalysis.getTrapezoid()
										.getErrorMessage()
										.contains("ViewLow")
								&& hsvAnalysis.getDataAspectRatio() > 1.6 // filter
																			// outliers
																			// out.
								&& viewPortAdjustment // do one
														// adjustment
														// only.
						) {

							// JavaCVUtils.saveImage(hsvPath, "hsv_"
							// + hueBand.getName()
							// + "_"
							// + hsvAnalysis.getTrapezoid()
							// .getErrorMessage() + "_"
							// + file.getName(), finalHSV);

							vb = ViewBand
									.buildViewBand(
											640,
											480,
											hsvAnalysis.getMaxY() + 60,
											hsvAnalysis.getMaxY()
													+ 60
													- (hsvAnalysis
															.getDataWidth() + 120));
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
			return;
		}
		System.out.println(String.format("sharpened %s, %s",hueBand,vb));
		System.out.println(String.format("sharpened %s: %s",
				bestAnalysis.getName(),
				bestAnalysis.getTrapezoid()
				));
		calcRunnable.setViewBand(vb);
		calcRunnable.setHueBand(bestHueBand);
		bestHueBand.setName(frameCount+"_"+ bestHueBand.getName());

	}

}
