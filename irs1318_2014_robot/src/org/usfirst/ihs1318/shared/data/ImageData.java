package org.usfirst.ihs1318.shared.data;

import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

public class ImageData {

	
	private ParticleAnalysisReport[] reports;
	private int rvn = 0;
	
	
	public ParticleAnalysisReport[] getParticleReport() {
		if (reports == null)
			reports = new ParticleAnalysisReport[18];
		return reports;
	}
	
	
	public int getRVN() {
		return rvn;
	}
	public void setRVN(int r) {
		rvn = r;
	}
	public void setParticleReport(ParticleAnalysisReport[] reports) {
		this.reports = reports;
	}

	public void copyValuesTo(ImageData dest) {
		if (dest==null) 
			throw new RuntimeException("dest must not be null.");
		
		for ( int i = 0; i < 18; i++) {
			dest.getParticleReport()[i] = reports[i];
		}
			dest.setRVN(rvn);
	}
	
}
