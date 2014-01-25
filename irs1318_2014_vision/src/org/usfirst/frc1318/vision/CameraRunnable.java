package org.usfirst.frc1318.vision;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc1318.data.Point;
import org.usfirst.frc1318.data.Trapezoid;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.*;

import static java.lang.String.*;
import static java.lang.Math.*;

public class CameraRunnable implements Runnable {

	private String outputPath = "c:\temp";
	private LookUp distanceLookUp;

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	@Override
	public void run() {

		long then = System.currentTimeMillis();
		int startCount = 0;
		while (true) {
			long now = System.currentTimeMillis();
			System.out.println(format("Restart number=%s, deltaTime=%s",
					startCount, now - then));
			then = now;
			try {
				processImage();
			} catch (Exception e) {
				e.printStackTrace();
			}
			startCount++;
		}
	}

	public void processImage() throws Exception {
		FrameGrabber grabber = new OpenCVFrameGrabber(
				"http://10.13.18.11/mjpg/video.mjpg?resolution=640x480&req_fps=30&.mjpg");
		grabber.start();
		CanvasFrame frame = new CanvasFrame("Rectangle pipeline");

		IplImage image = null;

		while ((image = grabber.grab()) != null) {
			IplImage undistortImage = undistort(image);

			IplImage finalHsv = performHsvFilter(undistortImage);
			List<Point> cornerPoints = findCorners(image, finalHsv);
//			float scale = 0.25f;
//			IplImage scaled = JavaCVUtils.resizeImage(image,
//					(int) (image.width() * scale),
//					(int) (image.height() * scale), true);
			PointsToTrapezoid p2t = new PointsToTrapezoid();
			Trapezoid trap = p2t.getOuterTrapezoid(cornerPoints);
			if (trap != null) {
				System.out.println(format("c2b=%s", trap.findThetaField()));
			}
			if (trap != null) {
				System.out.println(String.valueOf(distanceLookUp.getValue(trap.findMidpoint().getY())));
			}


// undistort is the original image
			
//			cvReleaseImage(scaled);
//			scaled=null;


			cvReleaseImage(finalHsv);
			finalHsv = null;

			frame.showImage(image);
//			if (true) continue;


		}
		grabber.stop();
		grabber.release();
		
		frame.dispose();

	}

	CvMat intrinsic = null;
	CvMat distortion = null;
	IplImage mapx = null;
	IplImage mapy = null;

	private IplImage undistort(IplImage image) {
		if (mapx == null || mapy == null) {
			intrinsic = JavaCVUtils.build640x480Intrinsic();
			distortion = JavaCVUtils.buildDistortion();

			mapx = cvCreateImage(cvGetSize(image), IPL_DEPTH_32F, 1);
			mapy = cvCreateImage(cvGetSize(image), IPL_DEPTH_32F, 1);
			cvInitUndistortMap(intrinsic, distortion, mapx, mapy);
			cvReleaseMat(intrinsic);
			cvReleaseMat(distortion);
		}

		IplImage t = cvCloneImage(image);
		cvRemap(t, image, mapx, mapy, CV_INTER_LINEAR | CV_WARP_FILL_OUTLIERS,
				cvScalarAll(0));
		cvReleaseImage(t);

		// JavaCVUtils.saveImage(getOutputPath(),
		// "01_undistort.jpg", image);
		return image;

	}

	IplImage imageSingleDepth = null;
	IplImage ihsv = null;
	IplImage hue = null;
	IplImage sat = null;
	IplImage val = null;

	private IplImage performHsvFilter(IplImage image) {
		if (imageSingleDepth == null) {
			imageSingleDepth = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
			// hsv
			ihsv = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 3);
			hue = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
			sat = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
			val = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);

		}

		cvZero(imageSingleDepth);
		cvZero(ihsv);
		cvZero(hue);
		cvZero(sat);
		cvZero(val);
		cvCvtColor(image, ihsv, CV_BGR2HSV); // hsv
		cvSplit(ihsv, hue, sat, val, null); // single channel

		// JavaCVUtils.saveImage(getOutputPath(),
		// "041_hue.jpg", hue);
		// JavaCVUtils.saveImage(getOutputPath(),
		// "042_sat.jpg", sat);
		// JavaCVUtils.saveImage(getOutputPath(),
		// "043_val.jpg", val);

		IplImage hueFilter = cvCloneImage(imageSingleDepth);
		cvInRangeS(hue, cvScalarAll(75), cvScalarAll(100), hueFilter);
		// JavaCVUtils.saveImage(getOutputPath(),
		// "045_hueFilter.jpg", hueFilter);

		IplImage satFilter = cvCloneImage(imageSingleDepth);
		cvInRangeS(sat, cvScalarAll(147), cvScalarAll(255), satFilter);
		// JavaCVUtils.saveImage(getOutputPath(),
		// "046_satFilter.jpg", satFilter);

		IplImage valFilter = cvCloneImage(imageSingleDepth);
		cvInRangeS(val, cvScalarAll(240), cvScalarAll(255), valFilter);
		// JavaCVUtils.saveImage(getOutputPath(),
		// "047_valFilter.jpg", valFilter);

		IplImage finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
		cvZero(finalHSV);

		cvAnd(hueFilter, valFilter, finalHSV, null);
		cvAnd(finalHSV, satFilter, finalHSV, null);
		// JavaCVUtils.saveImage(getOutputPath(),
		// "048_finalHSV.jpg", finalHSV);

		// release
		cvReleaseImage(hueFilter);
		cvReleaseImage(satFilter);
		cvReleaseImage(valFilter);

		return finalHSV;
	}

	IplImage eig = null;
	IplImage temp = null;

	int MAX_POINTS = 8;
	CvPoint2D32f corners = null;
	int[] count = null;
	double CORNER_QUALITY = 0.05;
	double CORNER_MIN_DISTANCE = 10.0;
	CvArr MASK = null;
	int CORNER_BLOCK_SIZE = 6;
	int USE_HARRIS = 0;
	double K = 0.04;

	private List<Point> findCorners(IplImage image, IplImage finalHsv) {
		// clone for overlay
		// -------------------------------------------------- find corners
		// oreilly learning opencv p333

		if (eig == null) {
			eig = cvCreateImage(cvGetSize(image), IPL_DEPTH_32F, 1);
			temp = cvCreateImage(cvGetSize(image), IPL_DEPTH_32F, 1);
			corners = new CvPoint2D32f(MAX_POINTS);
			count = new int[] { MAX_POINTS };
		}

		cvZero(eig);
		cvZero(temp);
		// reinitalize corners and count.

		cvGoodFeaturesToTrack(finalHsv, eig, temp, corners, count,
				CORNER_QUALITY, CORNER_MIN_DISTANCE, MASK, CORNER_BLOCK_SIZE,
				USE_HARRIS, K);

		List<Point> points = new ArrayList<Point>();
		System.out.println(String.format("result edge count=%s", count[0]));
		for (int i = 0; i < count[0]; i++) {
			Point point = new Point(Math.round(corners.position(i).x()),
					Math.round(corners.position(i).y()));
			points.add(point);

			CvPoint p0 = cvPoint(Math.round(corners.position(i).x()),
					Math.round(corners.position(i).y()));
			System.out.println(String.format("x=%s, y=%s", p0.x(), p0.y()));
			cvCircle(finalHsv, p0, 5, CvScalar.BLUE, 1, 8, 0);
			cvCircle(image, p0, 5, CvScalar.BLUE, 1, 8, 0);
			// TODO:: release CvPoint
		}
		return points;
	}

}
