package org.usfirst.frc1318.vision;

import static java.lang.Math.*;


//Knue
public class ToHoop 
{
	
	/**
	 * @param distance Distance from the camera to the backboard
	 * @param offset Angle from where the camera is looking to the Backboard
	 * @param angle Inner angle from the backboard to the camera
	 * @param turretToCamera Distance from the turret to the camera
	 * @param BackboardToHoop Distance from the Backboard to the hoop
	 * @return Returns the distance from the turret to the hoop
	 */
	public double getDistance(double distance, double offset, double angle, double turretToCamera, double BackboardToHoop)
	{
		//converts the input to the variables of the slideshow
		double C = distance;
		double o = offset;
		double theta = angle;
		double R = turretToCamera;
		
		double X = (C * Math.sin(o))/
				    Math.sin(Math.PI-theta-o);
		
		double K = X * Math.sin(Math.PI-o-theta);
		
		double E = X* Math.cos(Math.PI-o-theta);
		
		double Y = (C*Math.sin(theta))/
				   Math.sin(Math.PI-o-theta);
		
		double v = atan2(K, R+Y-E);
		
		double l = Math.PI - o;
		
		double s = Math.PI - v- (Math.PI-o-theta) - theta;
		
		double T = 0;
		if(v != 0)
		{
		T = (C* Math.sin(l))/
				   Math.sin(v);
		}
		else
			T = C + R;
		
		double Q = BackboardToHoop;
		
		double H = Q* Math.sin(Math.PI/2 - (theta+ s));
		double B = 0;
		if (H!=Q)
		 B = Math.sqrt((Q*Q)-(H*H));
		
		double a = atan2(H, T-B);
		
		double F = 0;
		if(a != 0)
		{
		F = (Q*Math.sin(Math.PI/2-(theta+s)))/
				   Math.sin(a);
		}
		else
			F = C+R;
		
		return F;
	}
}
