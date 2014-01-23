package org.usfirst.ihs1318.shared.data;
/**
 * This data structure stores a 
 * @author Patrick
 *
 */
//Checked for NPEs. Only uses primitive types.

public class ArmData {
	private double armDistance;
	private double armVelocity;

	public double getArmDistance() {
		return armDistance;
	}

	public void setArmDistance(double desiredArmDistance) {
		this.armDistance = desiredArmDistance;
	}

	public double getArmVelocity() {
		return armVelocity;
	}

	public void setArmVelocity(double desiredArmVelocity) {
		this.armVelocity = desiredArmVelocity;
	}
	
	public void copyValuesTo(ArmData dest) {
		if (dest==null) 
			throw new RuntimeException("dest must not be null.");
		dest.setAll(armDistance, armVelocity);
	}

	public void setAll(double desiredDistance, double desiredVelocity) {
		setArmDistance(desiredDistance);
		setArmVelocity(desiredVelocity);
		
	}

	

}
