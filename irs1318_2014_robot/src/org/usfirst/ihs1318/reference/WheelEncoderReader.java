package org.usfirst.ihs1318.reference;

import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ConnectionRef;
import org.usfirst.ihs1318.shared.data.WheelMotorEncoderRates;
import org.usfirst.ihs1318.shared.data.WheelMotorEncoderTicks;

public class WheelEncoderReader implements Runnable {
    private WheelMotorEncoderRates wmer; // local values
    private WheelMotorEncoderTicks wheelTicks;

    EncoderAngularVelocity rfEncoder;
    EncoderAngularVelocity lfEncoder; // ports swappedJaguar rfMotor = new Jaguar(1);
    EncoderAngularVelocity lbEncoder;
    EncoderAngularVelocity rbEncoder;
    
    boolean enabled;
    
    public void run() {
		while(true) {
			if (isEnabled()) {
    	    	readWheelEncoders();
			}
			try {
				Thread.currentThread().sleep(5); // fast loop
			} catch (InterruptedException e) {
				// notify will wake up early.
			}
			
		}
    }
	public void init() {
		rfEncoder = new EncoderAngularVelocity(ConnectionRef.WHEEL_ENCODER_RF_SOURCE_1, ConnectionRef.WHEEL_ENCODER_RF_SOURCE_2);
		lfEncoder = new EncoderAngularVelocity(ConnectionRef.WHEEL_ENCODER_LF_SOURCE_1, ConnectionRef.WHEEL_ENCODER_LF_SOURCE_2);
		lbEncoder = new EncoderAngularVelocity(ConnectionRef.WHEEL_ENCODER_LR_SOURCE_1, ConnectionRef.WHEEL_ENCODER_LR_SOURCE_2);
		rbEncoder = new EncoderAngularVelocity(ConnectionRef.WHEEL_ENCODER_RR_SOURCE_1, ConnectionRef.WHEEL_ENCODER_RR_SOURCE_2);
		
		//start encoders
		rfEncoder.start();
		lfEncoder.start();
		lbEncoder.start();
		rbEncoder.start();
		
		wmer = new WheelMotorEncoderRates();
		wheelTicks = new WheelMotorEncoderTicks();
		
	}
    
    public void readWheelEncoders() {
    	// converted to angular velocity
    	getWmev().setLf(lfEncoder.getRate());
    	getWmev().setRf(rfEncoder.getRate());
    	getWmev().setLr(lbEncoder.getRate());
    	getWmev().setRr(rbEncoder.getRate());
    	
    	getWheelTicks().setAll(lfEncoder.getValue(), rfEncoder.getValue(), 
    			lbEncoder.getValue(), rbEncoder.getValue());
    	synchronized(ReferenceData.getInstance()) {
    		getWmev().copyValues(ReferenceData.getInstance().getWheelEncoderRates());
    		wheelTicks.copyValues(ReferenceData.getInstance().getWheelEncoderTicks());
    	}
    }

	public WheelMotorEncoderTicks getWheelTicks() {
		if(wheelTicks == null)
			wheelTicks = new WheelMotorEncoderTicks();
		return wheelTicks;
	}
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public WheelMotorEncoderRates getWmev() {
		if (wmer==null) {
			wmer = new WheelMotorEncoderRates();
		}
		return wmer;
	}

	public void setWmev(WheelMotorEncoderRates wmev) {
		this.wmer = wmev;
	}


}
