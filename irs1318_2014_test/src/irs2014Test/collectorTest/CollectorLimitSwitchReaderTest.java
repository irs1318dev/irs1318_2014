package irs2014Test.collectorTest;

import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import edu.wpi.first.wpilibj.DigitalInput;
import irs2014.collector.CollectorLimitSwitchReader;
import irs2014.generalData.ReferenceData;

public class CollectorLimitSwitchReaderTest {
	CollectorLimitSwitchReader reader;
	CollectorLimitSwitchReader readerSpy;
	DigitalInput mockLimitSwitch;
	
	@Before
	public void setUpCollectorLimitSwitchReaderTest() {
		reader = new CollectorLimitSwitchReader();
		mockLimitSwitch = mock(DigitalInput.class);
		
		readerSpy = spy(reader);
		doReturn(mockLimitSwitch).when(readerSpy).getCollectorLimitSwitch();
	}
	
	@Test
	public void limitSwitchIsPressedTest() {
		doReturn(true).when(mockLimitSwitch).get();
		readerSpy.teleopPeriodic();
		assertEquals(true, ReferenceData.getInstance().getCollectorData().getLimitSwitchData().getBallPresent());
	}
	
	@Test
	public void limitSwitchIsNotPressedTest() {
		doReturn(false).when(mockLimitSwitch).get();
		readerSpy.teleopPeriodic();
		assertEquals(false, ReferenceData.getInstance().getCollectorData().getLimitSwitchData().getBallPresent());
	}
}
