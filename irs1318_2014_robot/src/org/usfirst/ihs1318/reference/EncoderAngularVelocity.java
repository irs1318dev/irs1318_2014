package org.usfirst.ihs1318.reference;

import edu.wpi.first.wpilibj.*;

public class EncoderAngularVelocity extends Encoder
{
    
    Timer timer;
    double previousTicks = 0, previousTime = 0, currentTime = 0, currentTicks = 0;
    
    public EncoderAngularVelocity(int slot, int channel) {
    	super(slot, channel);
    	timer = new Timer();
    	timer.start();
    }
    
    public double getRate() {
    	//getRate cannot be called in short time periods for the same encoder because
    	//the time will be too small.
    	currentTime = timer.get();
    	currentTicks = getDistance();
    	double omega = (currentTicks - previousTicks) / (currentTime - previousTime);
    	//System.out.println("Timer:"+(currentTime-previousTime));
    	previousTicks = currentTicks;
    	previousTime = currentTime;
    	return omega;
    }
    
    public double getValue() {
    	return getDistance();
    }
    
}
