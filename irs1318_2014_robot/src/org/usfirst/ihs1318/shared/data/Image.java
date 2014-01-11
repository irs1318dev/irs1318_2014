package org.usfirst.ihs1318.shared.data;

import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

public class Image {

	
	private ParticleAnalysisReport[] reports;
	private ColorImage image;
	
	
	public ParticleAnalysisReport[] getParticleReport() {
		return reports;
	}
	public void setParticleReport(ParticleAnalysisReport[] reports) {
		this.reports = reports;
	}
	public ColorImage getImage() {
		return image;
	}
	public void setImage(ColorImage image)
	{
		this.image = image;
	}
	public void copyValuesTo(Image dest) {
		if (dest==null) 
			throw new RuntimeException("dest must not be null.");
		dest.setParticleReport(reports);
		dest.setImage(image);
	}
	
}
