package org.usfirst.frc1318.vision;

import static org.junit.Assert.*;

import org.junit.Test;

public class HsvAnalysisTest {

	@Test
	public void testTiltAngle() {
		
		HsvAnalysis hsvAnalysis = new HsvAnalysis();
		hsvAnalysis.setResolution(640);
		double tiltAngle = hsvAnalysis.findTiltAngle(160);
		assertEquals(-6.0,tiltAngle,0.1);
	}
	
	@Test
	public void trigTest() {
		
		
		assertEquals(1,Math.sin(90*Math.PI/180),0.1);
	}

}
