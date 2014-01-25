package org.usfirst.frc1318;

import org.junit.*;
import org.usfirst.frc1318.shared.data.*;

public abstract class RobotTest {
	
	@BeforeClass@AfterClass
	public static void clearSharedData() {
		synchronized(ReferenceData.getInstance()) {
			ReferenceData.clear();
		}
		synchronized (KinematicData.getInstance()) {
			KinematicData.clear();
		}	
	}
}
