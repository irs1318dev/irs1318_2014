package org.usfirst.ihs1318.kinematics;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.EncoderRef;
import org.usfirst.ihs1318.shared.data.Distance;
import org.usfirst.ihs1318.shared.data.Orientation;
import org.usfirst.ihs1318.shared.data.WheelMotorEncoderTicks;

/**
 * Reads the current encoder values and then determines the vertical distance traveled from
 * the start of autonomous.
 * 
 * @author dowork
 *
 */
public class DistanceCalculator {
	private WheelMotorEncoderTicks currentTicks;
	private Distance distance;
	
	public void init() {
		currentTicks = new WheelMotorEncoderTicks();
		distance = new Distance();
	}
	public void calculateDistances() {
		Orientation angle = new Orientation();
		synchronized(ReferenceData.getInstance()){
			ReferenceData.getInstance().getWheelEncoderTicks().copyValues(currentTicks);
			ReferenceData.getInstance().getOrientation().copyValues(angle);
		}
		
		accountForTheta(angle);
		accountForVerticalMotion();
		accountForHorizontalMotion();
		
		synchronized(KinematicData.getInstance()) {
			distance.copyValuesTo(KinematicData.getInstance().getDistance());
		}
	}

	private void accountForHorizontalMotion() {
		double ticksToCenter = -currentTicks.getMax()/2;
		currentTicks.add(ticksToCenter);	
		distance.setX(EncoderRef.convertTicksToInches(currentTicks.getLf()));		
	}
	
	private void accountForVerticalMotion() {
		double offsetTicks = -currentTicks.getMin();
		currentTicks.add(offsetTicks);
		distance.setY(EncoderRef.convertTicksToInches(offsetTicks));
		
		
	}
	private void accountForTheta(Orientation angle) {
		double offsetTicks = -EncoderRef.convertRadiansToTicks(angle.getTheta());		
		currentTicks.add(offsetTicks);
		
	}
	
}
