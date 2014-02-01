package irs2014Test.shooterTest;

import org.usfirst.ihs1318.shooter.CompressorRunner;
import org.junit.Before;
import org.junit.Test;

import edu.wpi.first.wpilibj.Compressor;
import static org.mockito.Mockito.*;


public class StartCompressorTest extends Throwable{
	CompressorRunner runner;
	CompressorRunner runnerSpy;
	
	Compressor mockCompressor; 
	
	@Before
	public void setUpBefore() {
		runner = new CompressorRunner();
		mockCompressor = mock(Compressor.class);
		runnerSpy = spy(runner);
		
		doReturn(mockCompressor).when(runnerSpy).getNewCompressor(); 
		 
	}
	
	@Test
	
	public void startCompressorTest() {
	    runnerSpy.robotInit();
	    doThrow(Throwable.class).when(mockCompressor).start();
	}
	
}
