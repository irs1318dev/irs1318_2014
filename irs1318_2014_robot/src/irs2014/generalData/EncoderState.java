package irs2014.generalData;

import java.util.Hashtable;

public class EncoderState 
{
	public static final String WHITE_TO_ALLIANCE = "wta";
	public static final String ALLIANCE_TO_GOAL = "atg";
	public static final String LOW_GOAL_BUMP = "lgb";
	public static final String HIGH_GOAL_BUMP = "hgb";
	public static final String CAMERA_REF = "cr";
	
	public static final boolean VELOCITY_PID = false;
	public static final boolean POSITION_PID = true;
	
	private Hashtable distanceFromReferencePoints = null; 

	private double launchTickRight; 
	private double launchTickLeft;

	private boolean PIDType = VELOCITY_PID;
	
	/*This is like a list, but instead of making the index 1, 
	 *makes it something more meaningful, like a string name.
	 **/
	private Hashtable getDistanceFromReferencePoints() {
		if (distanceFromReferencePoints==null) {
			distanceFromReferencePoints = new Hashtable();
			distanceFromReferencePoints.put(WHITE_TO_ALLIANCE, new EncoderVal(0, 0));
			distanceFromReferencePoints.put(ALLIANCE_TO_GOAL, new EncoderVal(0, 0));
			distanceFromReferencePoints.put(LOW_GOAL_BUMP, new EncoderVal(0, 0));
			distanceFromReferencePoints.put(HIGH_GOAL_BUMP, new EncoderVal(0, 0));
			// nothing for camera.
			
		}
		return distanceFromReferencePoints;
	}
	
	
	
	/////////// > > Gets and Sets
	public void setEncoderState(String key, EncoderVal newTicks)
	{
		getDistanceFromReferencePoints().put(key, newTicks);
	}
	
	public EncoderVal getEncoderState(String key)
	{
		EncoderVal value = (EncoderVal) getDistanceFromReferencePoints().get(key);
		if(value == null)
			value = new EncoderVal(0, 0);
		return value;
	}
	
	public double getLaunchTickRight() {
		return launchTickRight;
	}

	public void setLaunchTickRight(double launchTickRight) {
		this.launchTickRight = launchTickRight;
	}
	
	public double getLaunchTickLeft() {
		return launchTickLeft;
	}

	public void setLaunchTickLeft(double launchTickLeft) {
		this.launchTickLeft = launchTickLeft;
	}
	
	public void setPIDType(boolean PIDType)
	{
		this.PIDType = PIDType;
	}
	
	public boolean getPIDType()
	{
		return PIDType;
	}
}
