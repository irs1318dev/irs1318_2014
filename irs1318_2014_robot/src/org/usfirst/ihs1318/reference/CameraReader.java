package org.usfirst.ihs1318.reference;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.TimerUtil;
import org.usfirst.ihs1318.shared.data.ImageData;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
public class CameraReader {

	public final static double PARTICLE_LIMIT = .0002; // percentile area limit that renders the
	// point as ambiguous to camera error.
	public final static int PEGS = 18; // number of pegs
	private AxisCamera cam;
	private ColorImage image;
	private BinaryImage maskedImage;
	private ParticleAnalysisReport[] reports;
	private ImageData imageData;
	private int rvn = 0;
	
	public ImageData getImageData() {
		if (imageData==null) {
			imageData = new ImageData();
		}
		return imageData;
	}

	public void setImageData(ImageData imageData) {
		this.imageData = imageData;
	}

	public void init() {
		cam = AxisCamera.getInstance();
		cam.writeCompression(0);
		cam.writeBrightness(10);
		cam.writeResolution(AxisCamera.ResolutionT.k320x240);
		// tracker.setFirstColorThresholds(8,12,50,100,30,60); //hsl, for
		// different shades of orange.
		// tracker.setSecondColorThresholds(8,12,50,100,30,60); //hsl, for
		// different shades of orange.
		// tracker.setSecondColorThresholds();
		Timer.delay(5.0);
	}
	
	public void readCameraImage() throws NIVisionException, AxisCameraException {
		
		if (cam.freshImage()) {
			image = cam.getImage();
			BinaryImage maskedImage = image.thresholdRGB(245, 255, 245, 255, 245, 255); //range of white
			image.free();
			ParticleAnalysisReport[] reports = maskedImage
			.getOrderedParticleAnalysisReports(PEGS);
			maskedImage.free();
			getImageData().setParticleReport(reports);
			rvn++;
			getImageData().setRVN(rvn);
			
			synchronized (ReferenceData.getInstance()) {
				getImageData().copyValuesTo(			
				ReferenceData.getInstance().getImageData());	
			}
		
		}

		}
	
}