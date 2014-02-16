package irs2014Test.shooterTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import irs2014.generalData.ReferenceData;
import irs2014.pressure.PressureSensorCalculator;

public class PressureSensorCalculatorTest {
	PressureSensorCalculator calculator; 
	
	@Before
	public void setUpBefore() {
		calculator = new PressureSensorCalculator();
		ReferenceData.getInstance().getPressureSensorTimerData().setShouldStartTimer(false);
	}
	
	@Test 
	public void startTimerTest() {
		ReferenceData.getInstance().getPressureSensorData().setIsPressurized(true);
		ReferenceData.getInstance().getPressureSensorData().setWasPressurized(false);
		calculator.teleopPeriodic();
		assertEquals(true, ReferenceData.getInstance().getPressureSensorTimerData().getShouldStartTimer());
		assertEquals(false, ReferenceData.getInstance().getPressureSensorTimerData().getShouldStopTimer());
	}
	
	@Test 
	public void stopTimerTest() {
		ReferenceData.getInstance().getPressureSensorData().setIsPressurized(false);
		ReferenceData.getInstance().getPressureSensorData().setWasPressurized(true);
		calculator.teleopPeriodic();
		assertEquals(false, ReferenceData.getInstance().getPressureSensorTimerData().getShouldStartTimer());
		assertEquals(true, ReferenceData.getInstance().getPressureSensorTimerData().getShouldStopTimer());
	}
}
