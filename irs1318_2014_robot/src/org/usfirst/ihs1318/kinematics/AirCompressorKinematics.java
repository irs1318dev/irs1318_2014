package org.usfirst.ihs1318.kinematics;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ButtonRef;
import org.usfirst.ihs1318.shared.data.AirCompressorValue;

//Checked for NPE. Lazy getters are used.
public class AirCompressorKinematics {
	private AirCompressorValue airCompressorValue;

	private boolean prevToggle;
	private boolean air = false; // turn on by default with simulated buttons.

	
	
	/**
	 * Calculates whether the air compressor should be running or not based on a button push from 
	 */
	public void calculateAirCompressorKinematics(){
		boolean changeToggle = false;
		synchronized (ReferenceData.getInstance()) {
			changeToggle = ReferenceData.getInstance()
			          .getButtonValues().getButtons()[ButtonRef.AIR_JS][ButtonRef.AIR_ENABLE];
		}
		
//		System.out.println("airChange="+changeToggle);
		
		if (changeToggle) {
			if (!prevToggle) {
				if (air) {
					air = false;
				} else {
					air = true;
				}
				prevToggle = true;
			}
		}
		else {
			prevToggle = false;
		}
		
//		System.out.println("air="+air);
		getAirCompressorValue().setState(air);
		
		synchronized (KinematicData.getInstance()) {
			getAirCompressorValue().copyValues(
			KinematicData.getInstance().getAirCompressor());
		}
	}

	public AirCompressorValue getAirCompressorValue() {
		if(airCompressorValue == null) {
			airCompressorValue = new AirCompressorValue();
		}
		return airCompressorValue;
	}

	public void setAirCompressorValue(AirCompressorValue airCompressorValue) {
		this.airCompressorValue = airCompressorValue;
	}



}
