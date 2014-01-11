package org.usfirst.ihs1318.autonomous;

import org.usfirst.ihs1318.pid.PIDGain;
import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.data.Distance;
/**
 * Mimics joystick values to move the robot to a certain x, y coordinate.
 * 
 * @author dowork
 *
 */
public class WheelDistancePID {
	private Distance currentDistance;
	private Distance setDistance;
	
	private PIDGain gains;
	
	private boolean setPointReached;
	
	public void init() {
		currentDistance = new Distance();
		setDistance = new Distance();
		gains = new PIDGain();
		gains.setAll(.1, .0001, 0);
	}
	
	public void calculateDistance(Distance setDistance) {
		setPointReached = false;
		setDistance.copyValuesTo(this.setDistance);
		
		synchronized(KinematicData.getInstance()){
			KinematicData.getInstance().getDistance().copyValuesTo(currentDistance);
		}
		if(setDistance.equals(currentDistance)){
			setPointReached = true;
			return;
		}
		
		double jx = gains.getKp() * (setDistance.getX() - currentDistance.getY());
		double jy = gains.getKd() * (setDistance.getY() - currentDistance.getY());
		
		synchronized(ReferenceData.getInstance()){
			ReferenceData.getInstance().getInputVelocity().setDesiredJx(jx);
			ReferenceData.getInstance().getInputVelocity().setDesiredJy(jy);
		}
	}
	
	public void calculateYDistance(double inches) {
		setPointReached = false;
		synchronized(KinematicData.getInstance()){
			KinematicData.getInstance().getDistance().copyValuesTo(currentDistance);
		}
		if(Math.abs(inches - currentDistance.getY()) < Distance.EPSILON) {
			setPointReached = true;
			return;
		}
		double jy = gains.getKp() * (inches - currentDistance.getY());
		
		synchronized(ReferenceData.getInstance()){
			ReferenceData.getInstance().getInputVelocity().setDesiredJy(jy);
		}		
	}
	
	public void calculateXDistance(double inches) {
		setPointReached = false;
		synchronized(KinematicData.getInstance()){
			KinematicData.getInstance().getDistance().copyValuesTo(currentDistance);
		}
		if(Math.abs(inches - currentDistance.getX()) < Distance.EPSILON) {
			setPointReached = true;
			return;
		}
		
		double jx = gains.getKp() * (inches - currentDistance.getX());
		
		synchronized(ReferenceData.getInstance()){
			ReferenceData.getInstance().getInputVelocity().setDesiredJx(jx);
		}
	}
	
	public boolean isSetPointReached() {
		return setPointReached;
	}
	
	
}
