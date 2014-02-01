package irs2014Test.driveTrainTest;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockitos.*;

import edu.wpi.first.wpilibj.Talon;
import irs2014.driveTrainTank.DriveTrainRunner;
import irs2014.generalData.ReferenceData;

public class DriveTrainRunnerTest {
	DriveTrainRunner runner;
	DriveTrainRunner runnerSpy;
	Talon mockRightTalon;
	Talon mockLeftTalon;
	
	@Before
	public void setUpDriveTrainRunnerTest(){
		runner = new DriveTrainRunner();
		mockRightTalon = mock(Talon.class);
		mockLeftTalon = mock(Talon.class);
		
		runnerSpy = spy(runner);
		doReturn(mockRightTalon).when(runnerSpy).getRightTalon();
		doReturn(mockLeftTalon).when(runnerSpy).getLeftTalon();
	}


	@Test
	public void right0Test(){
		ReferenceData.getInstance().getDriveTrainData().setRightPIDSpeed(0.0);
		ReferenceData.getInstance().getDriveTrainData().setLeftPIDSpeed(0.0);
		runnerSpy.teleopPeriodic();
		verify(mockRightTalon,times(1)).set(0.0);
		verify(mockLeftTalon, times(1)).set(0.0);
	}
	
	@Test
	public void rightPositive1Test(){
		ReferenceData.getInstance().getDriveTrainData().setRightPIDSpeed(1);
		ReferenceData.getInstance().getDriveTrainData().setLeftPIDSpeed(0.0);
		runnerSpy.teleopPeriodic();
		verify(mockRightTalon, times(1)).set(-1);
		verify(mockLeftTalon, times(1)).set(0.0);
	}
	
	@Test
	public void rightNegitive1test(){
		ReferenceData.getInstance().getDriveTrainData().setRightPIDSpeed(-1);
		ReferenceData.getInstance().getDriveTrainData().setLeftPIDSpeed(0.0);
		runnerSpy.teleopPeriodic();
		verify(mockRightTalon, times(1)).set(.573);
		verify(mockLeftTalon, times (1)).set(.178);
	}	
		
	@Test
	public void positiveInBoundsTest(){
		ReferenceData.getInstance().getDriveTrainData().setLeftPIDSpeed(.178);
		ReferenceData.getInstance().getDriveTrainData().setRightPIDSpeed(.573);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void left0pTest(){
		ReferenceData.getInstance().getDriveTrainData().setRightPIDSpeed(0.0);
		ReferenceData.getInstance().getDriveTrainData().setLeftPIDSpeed(0.0);
		runnerSpy.teleopPeriodic();
		verify(mockRightTalon, times(1)).set(0.0);
		verify(mockLeftTalon, times(1)).set(1);
	}
	
	@Test
	public void leftPositive1Test(){
		ReferenceData.getInstance().getDriveTrainData().setRightPIDSpeed(0.0);
		ReferenceData.getInstance().getDriveTrainData().setLeftPIDSpeed(1);
		runnerSpy.teleopPeriodic();
		verify(mockRightTalon, times(1)).set (0.0);
		verify(mockLeftTalon, times(1)).set(1);
	}
	
	@Test
	public void leftNegative1(){
		ReferenceData.getInstance().getDriveTrainData().setRightPIDSpeed(0.0);
		ReferenceData.getInstance().getDriveTrainData().setLeftPIDSpeed(-1);
		runnerSpy.teleopPeriodic();
		verify(mockRightTalon, time(1)).set(0.0);
		verify(mockLeftTalon, time (-1))
	}

	@Test
	public void negitiveInBoundsTest(){
		ReferenceData.getInstance().getDriveTrainData().setRightPIDSpeed(-.178);
		ReferenceData.getInstance().getDriveTrainData().setLeftPIDSpeed(-.573);
		runnerSpy.teleopPeriodic();
		verify(mockRightTalon, times(1)).set(.573);
		verify(mockLefttalon, times(1)).set(-.178)
	}
	
}	











