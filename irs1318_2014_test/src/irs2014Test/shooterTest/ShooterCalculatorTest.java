package irs2014Test.shooterTest;

import irs2014.generalData.ReferenceData;
import irs2014.shooter.ShooterCalculator;
import irs2014.shooter.ShooterRef;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ShooterCalculatorTest {
	ShooterCalculator calculator;
	
	@Before
	public void setUpShooterCalculatorTest() {
		calculator = new ShooterCalculator();
	}
	
	@Test
	public void shooterPulseTest() {
		ReferenceData.getInstance().getUserInputData().setShooterPulse(true);
		ReferenceData.getInstance().getPressureSensorData().setIsPressurized(true);
		ReferenceData.getInstance().getShooterData().setPulseTime(30);
		ReferenceData.getInstance().getShooterData().setIsShooting(true);
		
		
		while(ReferenceData.getInstance().getShooterData().getIsShooting() == true) {
			calculator.teleopPeriodic();
		}
		
		assertEquals(false, ReferenceData.getInstance().getShooterData().getIsShooting());
		assertEquals(true, calculator.getHasExtended());
		assertEquals(true, calculator.getHasRetracted());
		
		
		
	}
	

}
