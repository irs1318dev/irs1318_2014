package org.usfirst.frc1318.vision;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.googlecode.javacv.cpp.opencv_core.IplImage;

import static com.googlecode.javacv.cpp.opencv_core.*;

public class TestHsvExtract {

	@Test
	public void testHsvFilter() throws Exception {
		CalcRunnable cr = new CalcRunnable();
		// cvLoad image from c: drive.
		 BufferedImage img =  ImageIO.read(new File("C:\\img.jpg") );
		 IplImage image = IplImage.createFrom(img);
		IplImage finalHSV = cvCreateImage(cvGetSize(image), IPL_DEPTH_8U, 1);

		HueBand hueBand = cr.getHueBand();
		cr.performHsvFilter(image, finalHSV, hueBand);
		// look at results by saving finalHSV using JavaCVUtils..
	}

	@Test 
	public void testHsvAnalysis() throws Exception {
//		HsvAnalysis hsvResult = cr.processImage(image);
	}
}
