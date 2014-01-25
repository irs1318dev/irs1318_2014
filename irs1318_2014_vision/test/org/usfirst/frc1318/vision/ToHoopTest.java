package org.usfirst.frc1318.vision;

import static org.junit.Assert.*;
import org.junit.*;

import java.lang.Math;

public class ToHoopTest 
{
	ToHoop toHoop;
	@Before
	public void initil()
	{
		toHoop = new ToHoop();
	}
	
	@Test
	public void straight()
	{
		double theta = Math.PI/2;
		double o = 0;
		assertEquals(101,toHoop.getDistance(100, o , theta, 1, 13), .001);
		
		theta = 0;
		o = 0;
		assertEquals(101.88319694480774,toHoop.getDistance(100, o , theta, 1, 13), .1);
	}
	
	@Test
	public void not()
	{
		double theta = (Math.PI/2)- Math.atan2(100,13);
		double o = 0;
		assertEquals(101,toHoop.getDistance(100.83319694480773787821772011086, o , theta, 1, 13), .1);
	}
	
}
