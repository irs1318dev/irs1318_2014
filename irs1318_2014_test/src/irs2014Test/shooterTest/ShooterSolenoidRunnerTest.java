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

import static org.junit.Assert.*;
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
//	@Test
//	public void defaultValueIsFalseTest() {
//		assertEquals(false, ReferenceData.getInstance().getShooterData().getCurrentMiddleSolenoidState());
//	}
	@Test 
	public void extendMiddleSolenoidTest() {
		ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
		runnerSpy.teleopPeriodic();
		verify(mockMiddleSolenoid, times(1)).set(Value.kForward);
	}
	
	@Test
	public void retractMiddleSolenoidTest() {
		ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND);
		runnerSpy.teleopPeriodic();
		ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.RETRACT);
		runnerSpy.teleopPeriodic(); 
		ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.EXTEND); //checks that even with change of value set() method is only called once
		runnerSpy.teleopPeriodic(); 
		verify(mockMiddleSolenoid, times(1)).set(Value.kReverse);
	}
	
	@Test
	public void extendInnerSolenoidsTest() {
		ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
		runnerSpy.teleopPeriodic();
		verify(mockInnerSolenoids, times(1)).set(Value.kForward);
	}
	
	@Test
	public void innerSolenoidsOffTest() {
		ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.EXTEND);
		runnerSpy.teleopPeriodic();
		ReferenceData.getInstance().getShooterData().setDesiredInnerSolenoidsState(ShooterRef.RETRACT);
		runnerSpy.teleopPeriodic();
		verify(mockInnerSolenoids, times(1)).set(Value.kOff);
	}
	
	@Test
	public void extendOuterSolenoidsTest() {
		ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.EXTEND);
		runnerSpy.teleopPeriodic();
		verify(mockOuterSolenoids, times(1)).set(Value.kForward);
	}
	
	@Test
	public void outerSolenoidsOffTest() {
		ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.EXTEND);
		runnerSpy.teleopPeriodic();
		ReferenceData.getInstance().getShooterData().setDesiredOuterSolenoidsState(ShooterRef.RETRACT);
		runnerSpy.teleopPeriodic();
		verify(mockOuterSolenoids, times(1)).set(Value.kOff);
	}
	

}