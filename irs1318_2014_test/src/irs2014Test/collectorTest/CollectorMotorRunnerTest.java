package irs2014Test.collectorTest;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import edu.wpi.first.wpilibj.Talon;
import irs2014.collector.CollectorMotorRunner;
import irs2014.collector.CollectorRef;
import irs2014.generalData.ReferenceData;

public class CollectorMotorRunnerTest {
	CollectorMotorRunner runner;
	CollectorMotorRunner runnerSpy;
	Talon mockTalon;
	
	@Before
	public void setUpCollectoMotorRunnerTest(){
		runner = new CollectorMotorRunner();
		mockTalon = mock(Talon.class);
		
		runnerSpy = spy(runner);
		doReturn(mockTalon).when(runnerSpy).getCollectorMotor();
	}
	
	@Test
	public void stopCollectorMotorTest(){
		ReferenceData.getInstance().getCollectorData().getMotorData().setCollectorMotorOff();
		runnerSpy.teleopPeriodic();
		verify(mockTalon, times(1)).set(0.0);
	}
	
	@Test
	public void outCollectorMotorTest(){
		ReferenceData.getInstance().getCollectorData().getMotorData().setCollectorMotorOut();
		runnerSpy.teleopPeriodic();
		verify(mockTalon, times(1)).set(CollectorRef.OUT*CollectorRef.MOTOR_SPEED);
	}
	
	@Test
	public void inCollectorMotorTest(){
		ReferenceData.getInstance().getCollectorData().getMotorData().setCollectorMotorIn();
		runnerSpy.teleopPeriodic();
		verify(mockTalon, times(1)).set(CollectorRef.IN*CollectorRef.MOTOR_SPEED);
	}
}
