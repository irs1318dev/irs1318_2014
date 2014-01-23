package org.usfirst.ihs1318.autonomous;

import org.usfirst.ihs1318.pid.PIDGain;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.data.ArmEncoderValues;

public class ArmDistancePID {
	private ArmEncoderValues current;
	private PIDGain gains;
	private boolean setPointReached;
	
	public static final double EPSILON = 75.0;
	public void init() {
		current = new ArmEncoderValues();
		
		gains = new PIDGain();
		gains.setAll(.1, 0, 0);
	}
	/**
	 * Goes to the setTicks encoder value, where 0 =  the lowest posible value.
	 * @param setTicks
	 */
	public void goToSetTicks(double setTicks) {
		setPointReached = false;
		synchronized(ReferenceData.getInstance()) {
			ReferenceData.getInstance().getArmEncoderValues().copyValues(current);
		}
		if(Math.abs(setTicks - current.getEncoderValue()) < 75)  {
			setPointReached = true;
			return;
		}
		
		double jArm = gains.getKp() * (setTicks - current.getEncoderValue());
		
		synchronized(ReferenceData.getInstance()) {
			ReferenceData.getInstance().getArmInput().setDesiredJy(jArm);
		}
	}
	
	public boolean isSetPointReached() {
		return setPointReached;
	}
	public void setSetPointReached(boolean setPointReached) {
		this.setPointReached = setPointReached;
	}
	
}
