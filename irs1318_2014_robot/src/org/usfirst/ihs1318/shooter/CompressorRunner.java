package org.usfirst.ihs1318.shooter;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;
import edu.wpi.first.wpilibj.Compressor;

public class CompressorRunner extends RobotComponentBase {
	Compressor compressor; 
	
	public Compressor getNewCompressor(){
		return new Compressor(PortRef.SHOOTER_PRESSURE_SWITCH_CHANNEL, PortRef.SHOOTER_PRESSURE_SWITCH_SLOT, PortRef.COMPRESSOR_RELAY_SLOT, PortRef.COMPRESSOR_RELAY_CHANNEL);
	}
	
	public void robotInit() {
		compressor = getNewCompressor();
		compressor.start();
	}
	
}
