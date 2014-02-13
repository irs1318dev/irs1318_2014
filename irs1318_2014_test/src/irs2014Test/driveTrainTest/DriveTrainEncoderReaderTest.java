package irs2014Test.driveTrainTest;

import irs2014.driveTrainTank.DriveTrainEncoderReader;
import irs2014.generalData.ReferenceData;
import irs2014.generalOperations.EncoderAngularVelocity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DriveTrainEncoderReaderTest {
	DriveTrainEncoderReader reader;
	DriveTrainEncoderReader readerSpy;
	
	EncoderAngularVelocity encoderR;
	EncoderAngularVelocity encoderL;
	
	EncoderAngularVelocity mockEncoderR;
	EncoderAngularVelocity mockEncoderL;
	
	@Before
	public void setUpDriveTrainEncoderTest() {
		reader = new DriveTrainEncoderReader();
		mockEncoderR = mock(EncoderAngularVelocity.class);
		mockEncoderL = mock(EncoderAngularVelocity.class);
		
		readerSpy = spy(reader);
		
		doReturn(mockEncoderR).when(readerSpy).getNewRightEncoder();
		doReturn(mockEncoderL).when(readerSpy).getNewLeftEncoder();
		
		readerSpy.robotInit();
	}
	
	@Test
	public void aDriveTrainEncoderReaderTest(){
		doReturn(mockEncoderR).when(readerSpy).getNewRightEncoder();
		doReturn(mockEncoderL).when(readerSpy).getNewRightEncoder();
		when(mockEncoderR.getRate()).thenReturn(1.5);
		when(mockEncoderL.getRate()).thenReturn(1.5);
		
		readerSpy.teleopPeriodic();

		assertEquals(1.5, ReferenceData.getInstance().getDriveTrainData().getRightEncoderData().getVelocity(), 0.01);
		assertEquals(1.5, ReferenceData.getInstance().getDriveTrainData().getLeftEncoderData().getVelocity(), 0.01);
	}
	
	
}
