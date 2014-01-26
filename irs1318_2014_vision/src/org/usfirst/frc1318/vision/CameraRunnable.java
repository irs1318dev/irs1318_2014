package org.usfirst.frc1318.vision;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc1318.data.Point;
import org.usfirst.frc1318.data.Trapezoid;

import com.googlecode.javacv.CanvasFrame;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.OpenCVFrameGrabber;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;
import com.googlecode.javacv.cpp.opencv_core.*;

import static java.lang.String.*;
import static java.lang.Math.*;

public class CameraRunnable implements Runnable {

	private String outputPath = "C:\\movies";
	
	private boolean movieMode = false;
	public boolean getMovieMode() {
		return movieMode;
	}
	
	
	public void setMovieMode(boolean movieMode) {
		this.movieMode = movieMode;
	}
	private ViewBand viewBand;
	private HueBand hueBand;

	public ViewBand getViewBand() {
		if (viewBand==null) {
			viewBand = ViewBand.buildViewBand(640, 480, 390, 110); // default
		}
		return viewBand;
	}

	public void setViewBand(ViewBand viewBand) {
		this.viewBand = viewBand;
	}

	public HueBand getHueBand() {
		if (hueBand==null) {
			hueBand = HueBand.buildHueBand("default",75, 100, 147, 255, 240, 255);
		}
		return hueBand;
	}

	public void setHueBand(HueBand hueBand) {
		this.hueBand = hueBand;
	}

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


	private boolean showFrame = true;
	public boolean getShowFrame() {
		return showFrame;
	}
	public void setShowFrame(boolean showFrame) {
		this.showFrame = showFrame;
	}
	
	private boolean printPictures = true;
	public boolean getPrintPictures() {
		return printPictures;
	}
	public void setPrintPictures(boolean printPictures) {
		this.printPictures = printPictures;
	}
	
	private int resolution;
	public int getResolution() {
		if (resolution==0) {
			setResolution(640);
		}
		return resolution;
	}
	public void setResolution(int resolution) {
		if (!(resolution==320||resolution==640)) {
			throw new IllegalArgumentException("320 or 640 resolution allowed.");
		}
		this.resolution = resolution;
	}
	
	private int framesPerSecond;
	public int getFramesPerSecond() {
		if (framesPerSecond==0) {
			setFramesPerSecond(15);
		}
		return framesPerSecond;
	}
	public void setFramesPerSecond(int framesPerSecond) {
		if (framesPerSecond>30) {
			throw new IllegalArgumentException("30 fps or less allowed");
		}
		this.framesPerSecond = framesPerSecond;
	}
	
	private String getWidthXHeight() {
		if (getResolution()==640) {
			return "640x480";
		}
		return "320x240";
	}
	
	int frameCount = 0;
	long now = 0;
	
	CanvasFrame frame =null;
	CanvasFrame hsvFrame =null;

	public void processImage() throws Exception {
		String url = format("http://10.13.18.11/mjpg/video.mjpg?resolution=%s&req_fps=%s&.mjpg",getWidthXHeight(),getFramesPerSecond());
		System.out.println(url);
		
		FrameGrabber grabber = new OpenCVFrameGrabber(url);
		grabber.start();
		if (showFrame) {
			frame = new CanvasFrame("Raw Image");
			hsvFrame = new CanvasFrame("HSV pipeline");
		}
		IplImage image = null;
		HsvAnalysis result = null;

		now = System.currentTimeMillis();
		while ((image = grabber.grab()) != null) {
			if (getMovieMode()) {
				// skip some frames to avoid disk access
				JavaCVUtils.saveImage(outputPath, now+"_raw_"
						+ frameCount + ".jpg", image);
			}
//			result = processImage(image);

			if (showFrame) {
				frame.showImage(image);
//				System.out.println(String.format("live %s, %s",getHueBand(),getViewBand()));
//				System.out.println(String.format("live %s: %s",
//						result.getName(),
//						result.getTrapezoid()
//						));
			}
//			if (!result.getTrapezoid().hasError()) {
//				PublishValues.publishValues(resolution, frameCount, result.getTrapezoid());
//			} else { // adjust hue band and view band for the next image
//				// skip frames if autosharpened less than 3 seoncds worth 3 * fps frames
//				AutoSharpen.autoSharpen(this, frameCount, image); // already undistorted
//			}
			frameCount++;
		}
		grabber.stop();
		grabber.release();

		if (showFrame) {
			frame.dispose();
		}

	}
	
	public HsvAnalysis processImage(IplImage image) {
		IplImage undistortImage = undistort(image); // this is image transformed in place
		IplImage finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
		cvSetImageROI(finalHSV, getViewBand().getCvRect());
		performHsvFilter(undistortImage,finalHSV, getHueBand());
		HsvAnalysis result = findImagePoints(finalHSV);
		result.analyzePoints();
		if (showFrame) {
			hsvFrame.showImage(finalHSV);
		}
		if (getMovieMode()) {
			JavaCVUtils.saveImage(outputPath, now+"_hsv_"
					+ frameCount + ".jpg", finalHSV);
		}
		cvReleaseImage(finalHSV);
		return result;
	}

	CvMat intrinsic = null;
	CvMat distortion = null;
	IplImage mapx = null;
	IplImage mapy = null;

	public IplImage undistort(IplImage image) {
		if (mapx == null || mapy == null) {
			if (getResolution()==640){
				intrinsic = JavaCVUtils.build640x480Intrinsic();
			}
			else {
				intrinsic = JavaCVUtils.build320x240Intrinsic();
			}
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

		return image;

	}

	IplImage imageSingleDepth = null;
	IplImage ihsv = null;
	IplImage hue = null;
	IplImage sat = null;
	IplImage val = null;

	public void performHsvFilter(IplImage image,IplImage finalHSV, HueBand hueBandParam) {
		if (imageSingleDepth == null) {
			imageSingleDepth = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
			cvZero(imageSingleDepth); 
			// hsv
			ihsv = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 3);
			hue = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
			sat = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
			val = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);
		}

		CvRect roi = cvGetImageROI(finalHSV);
		CvRect isdRoi = cvGetImageROI(ihsv);
		cvSetImageROI(finalHSV,isdRoi);

		// work with full image
		cvCvtColor(image, ihsv, CV_BGR2HSV); // hsv
		cvSplit(ihsv, hue, sat, val, null); // single channel

		IplImage hueFilter = cvCloneImage(imageSingleDepth);
		cvInRangeS(hue, hueBandParam.getHueLow(), hueBandParam.getHueHi(), hueFilter);

		IplImage satFilter = cvCloneImage(imageSingleDepth);
		cvInRangeS(sat, hueBandParam.getSatLow(), hueBandParam.getSatHi(), satFilter);

		IplImage valFilter = cvCloneImage(imageSingleDepth);
		cvInRangeS(val, hueBandParam.getValLow(), hueBandParam.getValHi(), valFilter);

		cvAnd(hueFilter, valFilter, finalHSV, null);
		cvAnd(finalHSV, satFilter, finalHSV, null);
		
		// blank out everything except the region of interest.
		int topY = 0;
		int topH = roi.y();
		CvRect topBlank = cvRect(0,topY,roi.width(),topH);
		int bottomY = roi.y()+roi.height();
		int bottomH = imageSingleDepth.height() - bottomY;
		CvRect bottomBlank = cvRect(0,bottomY,roi.width(),bottomH);

		if (topH>0) {
			cvSetImageROI(finalHSV,topBlank);
			cvSetImageROI(imageSingleDepth,topBlank);
			cvAnd(finalHSV,imageSingleDepth,finalHSV,null);
		}

		if (bottomH>0) {
			cvSetImageROI(finalHSV,bottomBlank);
			cvSetImageROI(imageSingleDepth,bottomBlank);
			cvAnd(finalHSV,imageSingleDepth,finalHSV,null);
		}

		cvSetImageROI(finalHSV,roi);
		cvSetImageROI(imageSingleDepth,isdRoi);
		
		
		// release
		cvReleaseImage(hueFilter);
		cvReleaseImage(satFilter);
		cvReleaseImage(valFilter);
	}

	public HsvAnalysis findImagePoints(IplImage image) {
		ByteBuffer bb = image.getByteBuffer();
		byte[] dst = new byte[bb.remaining()];
		bb.get(dst);
		
		HsvAnalysis hsvAnalysis = new HsvAnalysis();
		hsvAnalysis.setResolution(resolution);
		
		List<Point> points = new ArrayList<Point>();
		for (int i=0; i<dst.length; i++){
			if (dst[i]!=0){
				int y = (int) (floor(i/image.width()));
				int x = i%image.width();
				hsvAnalysis.recordPoint(x,y);
			}
		}
		
		
		return hsvAnalysis;
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

	// not required
	public List<Point> findCorners(IplImage image, IplImage finalHsv) {
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
