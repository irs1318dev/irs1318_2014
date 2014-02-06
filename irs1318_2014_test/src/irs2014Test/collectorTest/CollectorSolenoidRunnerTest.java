package irs2014Test.collectorTest;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import irs2014.collector.CollectorRef;
import irs2014.collector.CollectorSolenoidRunner;
import irs2014.generalData.ReferenceData;

public class CollectorSolenoidRunnerTest {
	CollectorSolenoidRunner runner;
	CollectorSolenoidRunner runnerSpy;
	DoubleSolenoid mockDoubleSolenoid;
	
	@Before
	public void setUpCollectorSolenoidRunnerTest(){
		runner = new CollectorSolenoidRunner();
		mockDoubleSolenoid = mock(DoubleSolenoid.class);
		
		runnerSpy = spy(runner);
		doReturn(mockDoubleSolenoid).when(runnerSpy).getCollectorSolenoid();
	}
	
	@Test
	public void extendSolenoidTest(){
		ReferenceData.getInstance().getCollectorData().getSolenoidData().setDesiredSolenoidState(CollectorRef.EXTEND);
		runnerSpy.teleopPeriodic();
		ReferenceData.getInstance().getCollectorData().getSolenoidData().setDesiredSolenoidState(CollectorRef.EXTEND);
		runnerSpy.teleopPeriodic();
		verify(mockDoubleSolenoid, times(1)).set(Value.kForward);
	} 
	
	@Test
	public void retractSolenoidTest(){
		ReferenceData.getInstance().getCollectorData().getSolenoidData().setDesiredSolenoidState(CollectorRef.EXTEND);
		runnerSpy.teleopPeriodic();
		ReferenceData.getInstance().getCollectorData().getSolenoidData().setDesiredSolenoidState(CollectorRef.RETRACT);
		runnerSpy.teleopPeriodic();
		ReferenceData.getInstance().getCollectorData().getSolenoidData().setDesiredSolenoidState(CollectorRef.RETRACT);
		runnerSpy.teleopPeriodic();
		verify(mockDoubleSolenoid, times(1)).set(Value.kReverse);
	} 
}
