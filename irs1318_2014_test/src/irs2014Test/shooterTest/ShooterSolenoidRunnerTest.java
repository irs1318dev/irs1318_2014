package irs2014Test.shooterTest;

import irs2014.generalData.ReferenceData;
import irs2014.shooter.PressureSensorReader;
import irs2014.shooter.ShooterData;
import irs2014.shooter.ShooterRef;
import irs2014.shooter.ShooterRunner;


import org.junit.Before;
import org.junit.Test;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import static org.mockito.Mockito.*;

public class ShooterSolenoidRunnerTest {
	
	ShooterRunner runner;
	ShooterRunner runnerSpy; 
	
	DoubleSolenoid mockMiddleSolenoid;
	DoubleSolenoid mockInnerSolenoids;
	DoubleSolenoid mockOuterSolenoids;
	DoubleSolenoid mockShooterAngleSolenoid;
	
	@Before
	public void setUpBefore() {	
		runner = new ShooterRunner();
		runnerSpy = spy(runner);
		
		mockMiddleSolenoid = mock(DoubleSolenoid.class);
		mockInnerSolenoids = mock(DoubleSolenoid.class);
		mockOuterSolenoids = mock(DoubleSolenoid.class); 
		mockShooterAngleSolenoid = mock(DoubleSolenoid.class);
		
		doReturn(mockMiddleSolenoid).when(runnerSpy).getMiddleSolenoid();
		doReturn(mockInnerSolenoids).when(runnerSpy).getInnerSolenoids();
		doReturn(mockOuterSolenoids).when(runnerSpy).getOuterSolenoids();
		doReturn(mockShooterAngleSolenoid).when(runnerSpy).getShooterAngleSolenoid(); 
	}
	
	@Test 
	public void extendMiddleSolenoidTest() {
		ReferenceData.getInstance().getShooterData().setCurrentMiddleSolenoidState(ShooterRef.EXTEND);
		runnerSpy.teleopPeriodic();
		verify(mockMiddleSolenoid, times(1)).set(Value.kForward);
	}
	
	public void retractMiddleSolenoidTest() {
		ReferenceData.getInstance().getShooterData().setCurrentMiddleSolenoidState(ShooterRef.RETRACT);
		runnerSpy.teleopPeriodic();
		verify(mockMiddleSolenoid, times(1)).set(Value.kReverse);
	}
	
	public void middleSolenoidOffTest() {
		ReferenceData.getInstance().getShooterData().setCurrentMiddleSolenoidState(ShooterRef.RETRACT);
	}
	
	public void extendInnerSolenoidsTest() {
		
	}
	
	public void retractInnerSolenoidsTest() {
		
	}
	
	public void innerSolenoidsOffTest() {
		
	}
	
	public void extendOuterSolenoidsTest() {
		
	}
	
	public void retractOuterSolenoidsTest() {
		
	}
	
	public void outerSolenoidsOffTest() {
		
	}
	

}