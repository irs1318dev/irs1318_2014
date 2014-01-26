package irs2014Test.autonomousTest;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import irs2014.BotVector;
import irs2014.autonomous.AutoTaskRunner;
import irs2014.driveTrainTank.DriveTrain1JoystickCalculator;
import irs2014.driveTrainTank.DriveTrainEncoderReader;
import irs2014.driveTrainTank.DriveTrainNoPIDCalculator;
import irs2014.driveTrainTank.DriveTrainPIDCalculator;
import irs2014.driveTrainTank.DriveTrainRunner;
import irs2014.generalData.PortRef;
import irs2014.generalOpperations.EncoderAngularVelocity;
import irs2014.userInputDevices.Joystick1Reader;
import static org.mockito.Mockito.*;

public class GoForwardTest {
	
	Joystick1Reader reader;
	Joystick1Reader readerSpy;
	DriveTrainRunner runner;
	DriveTrainRunner runnerSpy;
	DriveTrainEncoderReader encoderReader;
	DriveTrainEncoderReader encoderReaderSpy;
	
	Joystick mockJoystick;
	Talon mockLeftTalon;
	Talon mockRightTalon;
	EncoderAngularVelocity mockLeftEncoder;
	EncoderAngularVelocity mockRightEncoder;
	
	AutoTaskRunner auto = new AutoTaskRunner();
	DriveTrain1JoystickCalculator driveTrainJoystickCalculator = new DriveTrain1JoystickCalculator();
	DriveTrainNoPIDCalculator driveTrainCalculator = new DriveTrainNoPIDCalculator();

	
	@Before
	public void setUpBefore(){
		reader = new Joystick1Reader();
		runner = new DriveTrainRunner();
		encoderReader = new DriveTrainEncoderReader();
		
		mockJoystick = mock(Joystick.class);
		mockLeftTalon = mock(Talon.class);
		mockRightTalon = mock(Talon.class);
		mockLeftEncoder = mock(EncoderAngularVelocity.class);
		mockRightEncoder = mock(EncoderAngularVelocity.class);
		
		readerSpy = spy(reader);
		runnerSpy = spy(runner);
		encoderReaderSpy = spy(encoderReader);
		
		doReturn(mockJoystick).when(readerSpy).getNewJoystick();
		doReturn(mockLeftTalon).when(runnerSpy).getNewLeftTalon();
		doReturn(mockRightTalon).when(runnerSpy).getNewRightTalon();
		doReturn(mockLeftEncoder).when(encoderReaderSpy).getNewLeftEncoder();
		doReturn(mockRightEncoder).when(encoderReaderSpy).getNewRightEncoder();
	}
	
	@Test
	public void goForwardTest(){
		doReturn(true).when(readerSpy).getGoForward();
		
		readerSpy.robotInit();
		auto.robotInit();
		driveTrainJoystickCalculator.robotInit();
		driveTrainCalculator.robotInit();
		runner.robotInit();
		
		readerSpy.teleopPeriodic();
		auto.teleopPeriodic();
		driveTrainJoystickCalculator.teleopPeriodic();
		driveTrainCalculator.teleopPeriodic();
		runner.teleopPeriodic();
		
		readerSpy.robotInit();
		
		verify(mockRightTalon,times(1)).set(0.5);
		verify(mockLeftTalon,times(1)).set(0.5);
	}
}