package irs2014.shooter;

import irs2014.components.RobotComponentBase;
import irs2014.dipSwitch.DipSwitchRef;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.Compressor;

public class CompressorRunner extends RobotComponentBase {
	Compressor compressor; 
	
	public Compressor getNewCompressor(){
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT){
			return new Compressor(PortRef.COMPETITION_PRESSURE_SWITCH_SLOT, PortRef.COMPETITION_PRESSURE_SWITCH_CHANNEL, PortRef.COMPETITION_COMPRESSOR_RELAY_SLOT, PortRef.COMPETITION_COMPRESSOR_RELAY_CHANNEL);
		} else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT){
			return new Compressor(PortRef.PRACTICE_PRESSURE_SWITCH_SLOT, PortRef.PRACTICE_PRESSURE_SWITCH_CHANNEL, PortRef.COMPETITION_COMPRESSOR_RELAY_SLOT, PortRef.COMPETITION_COMPRESSOR_RELAY_CHANNEL);
		}
		return null;
	}
	
	public void robotInit() {
		compressor = getNewCompressor();
		compressor.start();
	}
	
	public Compressor getCompressor(){
		return compressor;
	}
	
}
