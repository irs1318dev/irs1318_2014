package irs2014.generalOperations;

import edu.wpi.first.wpilibj.AnalogChannel;

public class AnalogPressureSensor extends AnalogChannel{
	
	private double factor;
	
	public AnalogPressureSensor(int module, int channel, int maxPSI){
		super(module, channel);
		this.factor = maxPSI / 10.0;
	}
	
	public double getPSI(){
		return super.getVoltage() * factor;
	}

}
