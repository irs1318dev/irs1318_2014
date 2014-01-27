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
	
	int frameCount;
	public void processImage() throws Exception {
		String url = format("http://10.13.18.11/mjpg/video.mjpg?resolution=%s&req_fps=%s&.mjpg",getWidthXHeight(),getFramesPerSecond());
		System.out.println(url);
		
		final FrameGrabber grabber = new OpenCVFrameGrabber(url);
		grabber.start();
		IplImage image = null;
		long last=System.currentTimeMillis();
		while ((image = grabber.grab()) != null) {
			frameCount++;
			setLatestImage(image);
			if (frameCount%100==0) {
				long rightNow = System.currentTimeMillis();
				System.out.println(String.format("Delta=%s,grabbedFrameCount=%s",rightNow-last,frameCount));
				last=rightNow;
						
			}
		}
		grabber.stop();
		grabber.release();


	}

	private Object latestImageMonitor = new Object();
	private boolean latestImageReady=false;
	private boolean latestImageRequested=false;
	private int latestFrameNumber;
	
	public int getLatestFrameNumber() {
		return latestFrameNumber;
	}
	private void setLatestFrameNumber(int latestFrameNumber) {
		this.latestFrameNumber = latestFrameNumber;
	}


	//TODO perform wait and notify
	private IplImage latestImage;
	public IplImage getLatestImage() throws InterruptedException {
		// apply a request to the camera thread
		synchronized(latestImageMonitor) {
			latestImageRequested=true; // place request
			while (!latestImageReady) {
				latestImageMonitor.wait();
			}
			latestImageReady=false; // consume image
			return latestImage;
		}
	}
	
	private void setLatestImage(IplImage image) {
		// on a request, copy image into latest image and return it, notify the get thread
		if (!latestImageRequested) return;
		synchronized(latestImageMonitor) {
			
			if (latestImage!=null) { // free up image
				cvReleaseImage(latestImage);
			}
			latestImage = cvCloneImage(image);
			latestImageReady=true; // indicate the image is ready for consumption
			latestImageRequested=false; // fulfilled request
			latestFrameNumber = frameCount;
			latestImageMonitor.notify();
		}
	}
	
	
}
