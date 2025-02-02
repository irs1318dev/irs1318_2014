package irs2014Test.dipSwitchTest;

import irs2014.dipSwitch.DipSwitchReader;
import irs2014.generalData.ReferenceData;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.first.wpilibj.DigitalInput;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DipSwitchReaderTest {
	DipSwitchReader reader;
	DipSwitchReader readerSpy;
	
	DigitalInput mockDipSwitch;
	
	@Before
	public void setUpDipSwitchReaderTest() {
		reader = new DipSwitchReader();
		mockDipSwitch = mock(DigitalInput.class);
		
		readerSpy = spy(reader);
	}
	
	@Test
	public void dipSwitch() {
		doReturn(mockDipSwitch).when(readerSpy).getNewDipSwitch();
		when(mockDipSwitch.get()).thenReturn(true);
				
		readerSpy.robotInit();
		
		assertEquals(true, ReferenceData.getInstance().getDipSwitchData().getDipSwitchState());
	}
}
