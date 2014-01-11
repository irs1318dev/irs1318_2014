package org.usfirst.ihs1318.discrete;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.constants.ConnectionRef;

import edu.wpi.first.wpilibj.Compressor;

//Checked for NPE. Uses lazy getters.
public class AirCompressorController {

	private Compressor compressor = null; 
	
	public void init(){
		compressor = new Compressor(ConnectionRef.AIR_PRESSURE_SWITCH_CHANNEL, ConnectionRef.AIR_RELAY_CHANNEL);
	}
	
	/** 
	 * toggle the air compressor based on the KinematicData's calculations.
	 */
	public void determineStatus() {
		boolean enabled = false;
		synchronized (KinematicData.getInstance()) {
			enabled = KinematicData.getInstance()
			          .getAirCompressor().getState();
		}
		if (enabled) {
			getCompressor().start();
		}
		else {
			getCompressor().stop();
		}
	}
	
	public boolean isEnabled(){
		return getCompressor().enabled();
	}
	public Compressor getCompressor(){
		return compressor;
	}
}
