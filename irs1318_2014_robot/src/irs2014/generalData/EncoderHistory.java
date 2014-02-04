package irs2014.generalData;

import java.util.Hashtable;

public class EncoderHistory 
{
	public static final String WHITE_TO_ALLIANCE = "wta";
	public static final String ALLIANCE_TO_GOAL = "atg";
	public static final String LOW_GOAL_BUMP = "lgb";
	public static final String HIGH_GOAL_BUMP = "hgb";
	public static final String CAMERA_REF = "cr";
	
	private Hashtable distanceFromReferencePoints = null; 
	
	/*This is like a list, but instead of making the index 1, 
	 *makes it something more meaningful, like a string name.
	 **/
	public Hashtable getDistanceFromReferencePoints() {
		if (distanceFromReferencePoints==null) {
			distanceFromReferencePoints = new Hashtable();
			distanceFromReferencePoints.put(WHITE_TO_ALLIANCE, new Double(20));
			distanceFromReferencePoints.put(ALLIANCE_TO_GOAL, new Double(3));
			distanceFromReferencePoints.put(LOW_GOAL_BUMP, new Double(3.5));
			distanceFromReferencePoints.put(HIGH_GOAL_BUMP, new Double(0));
			// nothing for camera.
			
		}
		return distanceFromReferencePoints;
	}
	
	/////////// > > Sets
	public void setWhiteToAlliance(double newTicks)
	{
		getDistanceFromReferencePoints().put(WHITE_TO_ALLIANCE, newTicks);
	}
	public void setAllianceToGoal(double newTicks)
	{
		getDistanceFromReferencePoints().put(ALLIANCE_TO_GOAL, newTicks);
	}
	public void setLowGoalBump(double newTicks)
	{
		getDistanceFromReferencePoints().put(LOW_GOAL_BUMP, newTicks);
	}
	public void setHighGoalBump(double newTicks)
	{
		getDistanceFromReferencePoints().put(HIGH_GOAL_BUMP, newTicks);
	}
	public void setCameraRef(double newTicks)
	{
		getDistanceFromReferencePoints().put(CAMERA_REF, newTicks);
	}
}
