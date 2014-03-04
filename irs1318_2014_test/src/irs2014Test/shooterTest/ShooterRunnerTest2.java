package irs2014Test.shooterTest;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import irs2014.generalData.ReferenceData;
import irs2014.shooter.ShooterRef;
import irs2014.shooter.ShooterRunner;
import static org.mockito.Mockito.*;

public class ShooterRunnerTest2 {
	ShooterRunner runner;
	ShooterRunner runnerSpy;
	
	DoubleSolenoid mockMiddleSolenoid; 
	
	@Before
	public void setUpBefore() {
		runner = new ShooterRunner();
		runnerSpy = spy(runner);
		
		mockMiddleSolenoid = mock(DoubleSolenoid.class);
		
		doReturn(mockMiddleSolenoid).when(runnerSpy).getMiddleSolenoid();
		
		ReferenceData.getInstance().getShooterData().setDesiredMiddleSolenoidState(ShooterRef.RETRACT);	
	}
	
	@Test
	public void extendMiddleSolenoidTest() {
		ReferenceData.getInstance().getUserInputData().setExtendMiddleShooterSolenoid(ShooterRef.EXTEND);
		
		runnerSpy.teleopPeriodic(); 
		verify(mockMiddleSolenoid, times(1)).set(Value.kForward);
	}

}
