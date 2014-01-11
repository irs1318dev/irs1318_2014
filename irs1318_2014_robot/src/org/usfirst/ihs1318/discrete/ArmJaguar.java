package org.usfirst.ihs1318.discrete;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.AnalogChannel;

/**
 * Links a jaguar to two limit switches, one mounted at the lower and one at the upper end of the arm.
 * Overrides the set method to 
 * @author walkjas12
 */
public class ArmJaguar extends Jaguar 
{
	private static final float limitThreshold = 2.5f; // The voltage threshold of a limit switch's being thrown or not
	
	private AnalogChannel lowerLimitSwitch; // The limit switch that prevents the arm from going too far down
	private AnalogChannel upperLimitSwitch; // The limit switch that prevents the arm from going too far up
	
	// Make sure that the update method is being called. Set to true in the update method. Will not allow
	// arm to be set until update is called.
	private boolean continuousCalled = false; 
	
	/**
	 * Initialize self to the given slot and channel and initialize the limit switches to the given channel.
	 * @param slot Slot that the digital module in plugged into. All components should be in same slot.
	 * @param channel Channel that the jaguar is plugged into.
	 * @param lowerLimitSwitch Channel that the lower limit switch is plugged into.
	 * @param upperLimitSwitch Channel that the upper limit switch is plugged into.
	 */
	public ArmJaguar(int slot, int channel, int lowerLimitSwitch, int upperLimitSwitch)
	{
		super(slot, channel);
		this.lowerLimitSwitch = new AnalogChannel(slot, lowerLimitSwitch);
		this.upperLimitSwitch = new AnalogChannel(slot, upperLimitSwitch);
	}

	/**
	 * Check if the arm is going up or down.
	 * @param speed The speed to be checked.
	 * @return 1 is up, -1 is down, 0 is stopped.
	 */
	private int getOrientation(double speed)
	{
		if(speed < 0) return 1;
		else if(speed > 0) return -1;
		else return 0;
	}
	
	/**
	 * Check if a given switch is triggered.
	 * @param limitSwitch The limit switch to analyze.
	 * @return True if triggered, false if not.
	 */
	private boolean isTriggered(AnalogChannel limitSwitch)
	{
		if(limitSwitch.getVoltage() < limitThreshold) return true;
		else return false;
	}
	
	/**
	 * Set the motor to the given speed, assuming that it clears the limit switches. If it does not,
	 * stop the motor.
	 * @param speed The speed to attempt to set it to.
	 */
	public void set(double speed)
	{
		if(continuousCalled == false)
		{
			System.out.println("Update method not being called.");
			speed = 0;
		}
		if(isTriggered(lowerLimitSwitch) && getOrientation(speed) == -1)
		{
			speed = 0;
		}
		else if(isTriggered(upperLimitSwitch) && getOrientation(speed) == 1)
		{
			speed = 0;
		}
		super.set(speed);
	}

	/**
	 * Check that the arm can continue its current motion. Meant to be called in teleop continuous.
	 */
	public void update()
	{
		continuousCalled = true;
		set(get());
	}
}