package irs2014.generalData;

import java.util.Hashtable;

public class EncoderState 
{
	public static final String WHITE_TO_ALLIANCE = "wta";
	public static final String ALLIANCE_TO_GOAL = "atg";
	public static final String LOW_GOAL_BUMP = "lgb";
	public static final String HIGH_GOAL_BUMP = "hgb";
	public static final String CAMERA_REF = "cr";
	
	public static final String PID_VELOCITY = "vpid";
	public static final String PID_POSITION = "ppid";
	
	
	private Hashtable distanceFromReferencePoints = null; 
	private double launchTick; // this is the calculated destination tick we need to get to.
	private String PIDType = PID_VELOCITY; 

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
	
	public double getLaunchTick() {
		return launchTick;
	}

	public void setLaunchTick(double launchTick) {
		this.launchTick = launchTick;
	}
	
	public void setPIDType(String PIDType)
	{
		this.PIDType = PIDType;
	}
	
	public String getPIDType()
	{
		return PIDType;
	}
}
