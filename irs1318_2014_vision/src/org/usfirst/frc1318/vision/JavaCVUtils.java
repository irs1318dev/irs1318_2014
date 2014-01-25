package org.usfirst.frc1318.vision;
import static com.googlecode.javacv.cpp.opencv_core.CV_32FC1;
import static com.googlecode.javacv.cpp.opencv_core.IPL_DEPTH_8U;
import static com.googlecode.javacv.cpp.opencv_core.cvCopy;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateMat;
import static com.googlecode.javacv.cpp.opencv_core.cvRect;
import static com.googlecode.javacv.cpp.opencv_core.cvResetImageROI;
import static com.googlecode.javacv.cpp.opencv_core.cvSetImageROI;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_INTER_AREA;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_INTER_LINEAR;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvResize;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.googlecode.javacv.cpp.opencv_core.CvMat;
import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.CvSize;
import com.googlecode.javacv.cpp.opencv_core.IplImage;


public class JavaCVUtils {
	
	public static void printMatrix(String label, CvMat matrix) {
		int rows = matrix.rows();
		int cols = matrix.cols();
		
		System.out.println("matrix "+label);
		for (int i=0; i<rows; i++) {
			String rowStr = "(";
			for (int j=0; j<cols; j++) {
				rowStr += matrix.get(i,j);
				if (j<cols-1) rowStr+=",";
			}
			rowStr += ")";
			System.out.println(rowStr);
			
		}
		
		
	}
	
	public static CvMat build320x240Intrinsic() {
		CvMat intrinsic_matrix = cvCreateMat(3, 3, CV_32FC1);
		intrinsic_matrix.put(0, 0, 160); // focal length x
		intrinsic_matrix.put(0, 1, 0.0);
		intrinsic_matrix.put(0, 2, 160.0); // center x

		intrinsic_matrix.put(1, 0, 0.0);
		intrinsic_matrix.put(1, 1, 120); // focal length y [= x * h / w]
		intrinsic_matrix.put(1, 2, 120.0); // center y

		intrinsic_matrix.put(2, 0, 0.0);
		intrinsic_matrix.put(2, 1, 0.0);
		intrinsic_matrix.put(2, 2, 1.0); // flat z
		JavaCVUtils.printMatrix("intrisic matrix",intrinsic_matrix);
		return intrinsic_matrix;
		
	}
	
	public static CvMat build640x480Intrinsic() {
		CvMat intrinsic_matrix = cvCreateMat(3, 3, CV_32FC1);
		intrinsic_matrix.put(0, 0, 320); // focal length x
		intrinsic_matrix.put(0, 1, 0.0);
		intrinsic_matrix.put(0, 2, 320.0); // center x

		intrinsic_matrix.put(1, 0, 0.0);
		intrinsic_matrix.put(1, 1, 240); // focal length y [= x * h / w]
		intrinsic_matrix.put(1, 2, 240.0); // center y

		intrinsic_matrix.put(2, 0, 0.0);
		intrinsic_matrix.put(2, 1, 0.0);
		intrinsic_matrix.put(2, 2, 1.0); // flat z
//		JavaCVUtils.printMatrix("intrisic matrix",intrinsic_matrix);
		return intrinsic_matrix;
		
	}
	public static CvMat buildDistortion() {
		CvMat distortion_coeffs = cvCreateMat(4, 1, CV_32FC1);
		double p_factor = 0.00;
		
		distortion_coeffs.put(0, 0, -0.055); // k1 * r^2
		distortion_coeffs.put(1, 0, 0.0); // k2 * r^4
		distortion_coeffs.put(2, 0, -p_factor); // tangential p1
		distortion_coeffs.put(3, 0, p_factor); // tangential p2

//		JavaCVUtils.printMatrix("distortion coeffs",distortion_coeffs);
		return distortion_coeffs;
		
	}
	
	public static void saveImage(String path, String name, IplImage image) {
		try {
			// retrieve image
			BufferedImage bi = image.getBufferedImage();
			File outputfile = new File(path,name);
			ImageIO.write(bi, "jpg", outputfile);
		} catch (IOException e) {
		}
		
	}

	//https://gesture.googlecode.com/svn-history/r168/trunk/RPS/src/com/android/rps/backend/GestureRecognizer.java
	public static IplImage resizeImage(IplImage origImg, int newWidth, int newHeight,
			boolean keepAspectRatio) {	
		IplImage outImg = null;
		int origWidth = 0;
		int origHeight = 0;
		if (!origImg.isNull()) {
			origWidth = origImg.width();
			origHeight = origImg.height();
		}
		if (newWidth <= 0 || newHeight <= 0 || origImg.isNull()
				|| origWidth <= 0 || origHeight <= 0) {
			return null;
		}

		if (keepAspectRatio) {
			// Resize the image without changing its aspect ratio,
			// by cropping off the edges and enlarging the middle section.
			CvRect r;
			// input aspect ratio
			float origAspect = (origWidth / (float) origHeight);
			// output aspect ratio
			float newAspect = (newWidth / (float) newHeight);
			// crop width to be origHeight * newAspect
			if (origAspect > newAspect) {
				int tw = (origHeight * newWidth) / newHeight;
				r = cvRect((origWidth - tw) / 2, 0, tw, origHeight);
			} else { // crop height to be origWidth / newAspect
				int th = (origWidth * newHeight) / newWidth;
				r = cvRect(0, (origHeight - th) / 2, origWidth, th);
			}		
			IplImage croppedImg = cropImage(origImg, r);

			// call this function again, with the new aspect ratio image.
			// will do a scaled image resize with the correct aspect ratio.
			outImg = resizeImage(croppedImg, newWidth, newHeight, false);

		} else {

			// scale the image to the new dimensions,
			// even if the aspect ratio will be changed.
			outImg = cvCreateImage(cvSize(newWidth, newHeight),
					origImg.depth(), origImg.nChannels());
			if (newWidth > origImg.width() && newHeight > origImg.height()) {
				// make the image larger
				cvResetImageROI((IplImage) origImg);
				// CV_INTER_LINEAR: good at enlarging.
				// CV_INTER_CUBIC: good at enlarging.
				cvResize(origImg, outImg, CV_INTER_LINEAR);
			} else {
				// Make the image smaller
				cvResetImageROI((IplImage) origImg);
				// CV_INTER_AREA: good at shrinking (decimation) only.
				cvResize(origImg, outImg, CV_INTER_AREA);
			}

		}
		return outImg;
	}

	public static IplImage cropImage(IplImage img, CvRect region) {
		IplImage imageCropped;
		CvSize size = new CvSize();

		if (img.width() <= 0 || img.height() <= 0 || region.width() <= 0
				|| region.height() <= 0) {
			return null;
		}

		if (img.depth() != IPL_DEPTH_8U) {
			return null;
		}

		// set the desired region of interest.
		cvSetImageROI((IplImage) img, region);
		// copy region of interest into a new iplImage and return it.
		size.width(region.width());
		size.height(region.height());
		imageCropped = cvCreateImage(size, img.depth(), img.nChannels());
		cvCopy(img, imageCropped); // copy just the region.
			
		return imageCropped;
	}
		
}
