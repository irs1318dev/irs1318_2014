package org.usfirst.ihs1318.shared.data;

import org.usfirst.ihs1318.shared.ReferenceData;

public class ManualGyroValue {
	private double manualGyroAngle;

	public double getManualGyroAngle() {
		return manualGyroAngle;
	}

	int count =0;
	public void setManualGyroAngle(double manualGyroAngle) {
		this.manualGyroAngle = manualGyroAngle;
		
		if (ReferenceData.DEBUG) {
			if (count%20==0) {
				System.out.println("Manual angle(deg) = " + this.manualGyroAngle);
				count=0;
			}
			count++;
		}
	}
	public void copyValue(ManualGyroValue dest){
		if(dest == null)throw new RuntimeException("dest must not be null.");
		dest.setManualGyroAngle(getManualGyroAngle());
	}

}
