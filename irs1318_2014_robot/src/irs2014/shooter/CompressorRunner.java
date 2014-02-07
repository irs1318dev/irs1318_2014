package irs2014.shooter;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;
import edu.wpi.first.wpilibj.Compressor;

public class CompressorRunner extends RobotComponentBase {
	Compressor compressor; 
	
	public Compressor getCompressor(){
		return compressor;
	}
	
	public void robotInit() {
		compressor = getCompressor();
		compressor.start();
	}
	
}
