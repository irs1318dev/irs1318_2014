package irs2014.generalOpperations;

import irs2014.generalData.PortRef;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;

public class EncoderAngularVelocity extends Encoder
{

	String name;
	
	public void setName(String name) {
		this.name= name;
	}
	public String getName(){
		return name;
	}
	
    Timer timer;
    double previousTicks = 0, previousTime = 0, currentTime = 0, currentTicks = 0, 
    		dt = 0, previousOmega = 0;
    
    public EncoderAngularVelocity(int slotA, int sourceA, int slotB, int sourceB) {
    	super(slotA, sourceA, slotB, sourceB);
    	init();
    }
    
    public EncoderAngularVelocity(int sourceA, int sourceB) {
    	//super(sourceA, sourceB); old testing new
    	super(PortRef.SIDECAR_SLOT, sourceA, PortRef.SIDECAR_SLOT, sourceB);
    	init();
    }

	public EncoderAngularVelocity(int slotA, int slotB, boolean reversed,
			EncodingType encodingType) {
		super(slotA, slotB, reversed, encodingType);
		init();
	}

	private void init() {
		timer = new Timer();
    	timer.start();
    	this.start();
    	
	}
    
    public double getRate() {
    	if(this.getStopped()) {
    		this.start();
    	}
    	//getRate cannot be called in short time periods for the same encoder because
    	//the time will be too small.
    	currentTime = timer.get();
    	currentTicks = getDistance();
    	dt = currentTime - previousTime;
//    	dt = 0.02;
    	if (dt<0.0015) { // prevent too fast loop that gets within the time resolution.
    		return previousOmega;
    	}
    	double omega = (currentTicks - previousTicks) / (dt);
    	if(Double.isNaN(omega)) {
    		//dt could be zero, so you could get some sketchy stuff
    		System.err.println(getName()+": omega is NaN. returning previouse omega" +
    				", prevTime="+previousTime+", curTime="+currentTime+", dt="+dt);
    		return previousOmega;
    	}else {
    		previousOmega = omega;
	    	previousTicks = currentTicks;
	    	previousTime = currentTime;
//    		System.err.println(getName()+": encoder rate " +omega + ", ticks= " + currentTicks
//    				+", prevTime="+previousTime+", curTime="+currentTime+", dt="+dt);
//	    	System.err.println("******" + getName() + " ticks = " + currentTicks);
	    	return omega;
    	}
    }
    
    public double getValue() {
    	if(this.getStopped())
    		this.start();
    	return this.get();
    }
    
}
