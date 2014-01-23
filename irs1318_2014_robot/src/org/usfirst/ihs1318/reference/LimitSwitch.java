package org.usfirst.ihs1318.reference;

import edu.wpi.first.wpilibj.AnalogChannel;

public class LimitSwitch 
{
	private static final float limitThreshold = 2.5f; // The voltage threshold of a limit switch's being thrown or not

	private AnalogChannel limitSwitch = null;
	
	/**
	 * Initialize the internal analog channel to the given channel.
	 * @param channel
	 */
	public void initLimitSwitch(int channel)
	{
		this.setLimitSwitch(new AnalogChannel(channel));
	}
	
	/**
	 * Initialize the internal analog channel to the given slot and channel.
	 * @param slot
	 * @param channel
	 */
	public void initLimitSwitch(int slot, int channel)
	{
		this.setLimitSwitch(new AnalogChannel(slot, channel));
	}
	
	/**
	 * Check if the given voltage will trigger a switch.
	 * @param voltage
	 * @return True if triggered, false if not.
	 */
	public static boolean isTriggered(double voltage)
	{
		if(voltage < limitThreshold) return true;
		else return false;
	}
		
	/**
	 * Return if the internal limit switch is triggered.
	 * @return
	 */
	public boolean isSwitchTriggered()
	{
		return isTriggered(limitSwitch.getVoltage());
	}
	

	public void setLimitSwitch(AnalogChannel limitSwitch) {
		this.limitSwitch = limitSwitch;
	}

	public AnalogChannel getLimitSwitch() {
		return limitSwitch;
	}	
}
