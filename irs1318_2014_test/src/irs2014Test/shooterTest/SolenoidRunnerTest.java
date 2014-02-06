package irs2014Test.shooterTest;


import irs2014.shooter.PressureSensorReader;
import irs2014.shooter.ShooterRunner;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import static org.mockito.Mockito.*;

public class SolenoidRunnerTest {
	PressureSensorReader reader;
	PressureSensorReader readerSpy;
	
	ShooterRunner runner;
	ShooterRunner runnerSpy; 
	
	DoubleSolenoid mockMiddleSolenoid;
	DoubleSolenoid mockInnerSolenoids;
	DoubleSolenoid mockOuterSolenoids;
	
	@Before
	public void setUpBefore() {
	
		reader = new PressureSensorReader();
		readerSpy = spy(reader);
		
		runner = new ShooterRunner();
		runnerSpy = spy(runner);
		
		mockMiddleSolenoid = mock(DoubleSolenoid.class);
		mockInnerSolenoids = mock(DoubleSolenoid.class);
		mockOuterSolenoids = mock(DoubleSolenoid.class); 
	}
	
	@Test
	public void SolenoidRunnerTest() {
		readerSpy.robotInit(); 
		runnerSpy.robotInit();
		
//		assertEquals(true, mockMiddleSolenoid.getMiddleSolenoidState());		
		
	}

}
