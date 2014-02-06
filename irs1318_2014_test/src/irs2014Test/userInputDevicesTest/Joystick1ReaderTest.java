package irs2014Test.userInputDevicesTest;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.first.wpilibj.Joystick;
import irs2014.generalData.ReferenceData;
import irs2014.userInputDevices.Joystick1Reader;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class Joystick1ReaderTest {
	
	Joystick1Reader reader;
	Joystick1Reader readerSpy;
	
	Joystick mockJoystick;
	
	@Before
	public void setUpBefore(){
		reader = new Joystick1Reader();
		readerSpy = spy(reader);
		
		mockJoystick = mock(Joystick.class);
	}
	
	@Test
	public void goForwardTest(){
		doReturn(mockJoystick).when(readerSpy).getNewJoystick();
		when(mockJoystick.getY()).thenReturn(0.0);
		when(mockJoystick.getX()).thenReturn(1.0);
		
		readerSpy.robotInit();
		readerSpy.teleopPeriodic();
		
		System.out.println("from test");
		System.out.println(ReferenceData.getInstance().getUserInputData().getJoystickY());
		System.out.println(ReferenceData.getInstance().getUserInputData().getJoystickX());
		
		assertEquals(1.0, ReferenceData.getInstance().getUserInputData().getJoystickY(), .01);
		assertEquals(0.0, ReferenceData.getInstance().getUserInputData().getJoystickX(), .01);
	}

}
