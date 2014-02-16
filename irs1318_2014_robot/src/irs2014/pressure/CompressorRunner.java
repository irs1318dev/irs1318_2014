package irs2014.pressure;

import irs2014.components.RobotComponentBase;
import irs2014.dipSwitch.DipSwitchRef;
import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;

public class CompressorRunner extends RobotComponentBase {
	Compressor compressor; 
	DigitalInput input;
	
	public Compressor getNewCompressor(){
		if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT){
			//return new Compressor(PortRef.COMPETITION_PRESSURE_SWITCH_SLOT, PortRef.COMPETITION_PRESSURE_SWITCH_CHANNEL, PortRef.COMPETITION_COMPRESSOR_RELAY_SLOT, PortRef.COMPETITION_COMPRESSOR_RELAY_CHANNEL);
			return new Compressor(PortRef.SIDECAR_SLOT, PortRef.COMPETITION_PRESSURE_SWITCH_SLOT, PortRef.DIGITAL_IO, PortRef.COMPETITION_COMPRESSOR_RELAY_CHANNEL);
		} else if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT){
			//return new Compressor(PortRef.PRACTICE_PRESSURE_SWITCH_SLOT, PortRef.PRACTICE_PRESSURE_SWITCH_CHANNEL, PortRef.PRACTICE_COMPRESSOR_RELAY_SLOT, PortRef.PRACTICE_COMPRESSOR_RELAY_CHANNEL);
			System.out.println("in getNewCompressor() for PracticeBot");
			return new Compressor(PortRef.SIDECAR_SLOT, PortRef.PRACTICE_PRESSURE_SWITCH_SLOT, PortRef.DIGITAL_IO, PortRef.PRACTICE_COMPRESSOR_RELAY_CHANNEL);
		}
		return null;
	}
	
	public void robotInit() {
		compressor = getNewCompressor();
		compressor.start();
//		input = new DigitalInput(6);
	}
	
	public Compressor getCompressor(){
		return compressor;
	}
	
//	public void teleopPeriodic(){
//		System.out.println(input.get());
//	}
	
}
