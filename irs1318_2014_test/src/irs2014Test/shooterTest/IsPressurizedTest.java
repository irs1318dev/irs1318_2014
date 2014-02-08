package irs2014Test.shooterTest;

import org.junit.Before;
import org.junit.Test;

import irs2014.generalData.ReferenceData;
import irs2014.shooter.PressureSensorReader;
import edu.wpi.first.wpilibj.DigitalInput;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IsPressurizedTest {
	
	PressureSensorReader reader;
	PressureSensorReader readerSpy;
	DigitalInput mockPressureSensor;
	
	@Before
	public void setUpBefore() {
		reader = new PressureSensorReader();
		mockPressureSensor = mock(DigitalInput.class); 
		readerSpy = spy(reader);
		
		doReturn(mockPressureSensor).when(readerSpy).getPressureSensor(); 
	}
	
	@Test
	public void isPressurizedTest() {
		doReturn(true).when(mockPressureSensor).get();
		readerSpy.teleopPeriodic();
		assertEquals(true, ReferenceData.getInstance().getPressureSensorData().getIsPressurized());
	}
		
	@Test
	public void isNotPressuirzedTest() {
		doReturn(false).when(mockPressureSensor).get();
		readerSpy.teleopPeriodic();
		assertEquals(false, ReferenceData.getInstance().getPressureSensorData().getIsPressurized());
	}

}
