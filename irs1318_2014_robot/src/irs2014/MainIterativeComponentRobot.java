package irs2014;

import irs2014.autonomous.AutoTaskRunner;
import irs2014.collector.CollectorCalculator;
import irs2014.collector.CollectorLimitSwitchReader;
import irs2014.collector.CollectorMotorRunner;
import irs2014.collector.CollectorSolenoidRunner;
import irs2014.dipSwitch.DipSwitchReader;
import irs2014.driveTrainMecanum.MecMotorRunner;
import irs2014.driveTrainTank.DriveTrain1JoystickCalculator;
import irs2014.driveTrainTank.DriveTrainEncoderReader;
import irs2014.driveTrainTank.DriveTrainNoPIDCalculator;
import irs2014.driveTrainTank.DriveTrainPIDCalculator;
import irs2014.driveTrainTank.DriveTrainRunner;
import irs2014.driveTrainTank.MotorRunner;
import irs2014.helloWorld.HelloWorldRunner;
import irs2014.networkTable.GetValuesTest;
import irs2014.networkTable.NetworkTableRunner;
import irs2014.shooter.CompressorRunner;
import irs2014.shooter.PressureSensorReader;
import irs2014.shooter.ShooterCalculator;
import irs2014.shooter.ShooterRunner;
import irs2014.simpleRIAB.GamePadReaderRIAB;
import irs2014.simpleRIAB.SimpleRAIBCalculator;
import irs2014.simpleRIAB.TalonRunner;
import irs2014.userInputDevices.Joystick1Reader;
import irs2014.userInputDevices.JoystickPortTest;

public class MainIterativeComponentRobot extends IterativeComponentRobot {

	public BotVector currentRobotComponents() {
		return networkTableTest();
	}
	
	protected static BotVector initialRobot(){
		BotVector b = new BotVector();
		b.add(new CompressorRunner()); //Test: StartCompressorTest
		b.add(new Joystick1Reader()); //Joystick1ReaderTest
		b.add(new DriveTrainEncoderReader()); //NONE
		b.add(new DipSwitchReader()); //DipSwitchReaderTest
		b.add(new PressureSensorReader()); //NONE
		b.add(new CollectorLimitSwitchReader()); //CollectorLimitSwitchReaderTest
		b.add(new AutoTaskRunner()); //NONE
		b.add(new CollectorCalculator()); //CollectorCalculatorTest
		b.add(new ShooterCalculator()); //NONE
		b.add(new DriveTrain1JoystickCalculator()); //NONE
		b.add(new DriveTrainPIDCalculator()); //NONE
		b.add(new NetworkTableRunner()); //NONE
		b.add(new DriveTrainRunner()); //DriveTrainRunnerTest
		b.add(new CollectorMotorRunner()); //CollectorMotorRunnerTest
		b.add(new CollectorSolenoidRunner()); //CollectorSolenoidRunnerTest
		b.add(new ShooterRunner()); //ShooterSolenoidRunnerTest
		return b;
	}
	
	protected static BotVector networkTableTest(){
		BotVector b = new BotVector();
		b.add(new GetValuesTest());
		return b;
	}
	
	protected static BotVector pressureSensorTest(){
		BotVector b = new BotVector();
		b.add(new PressureSensorReader());
		return b;
	}
	
	protected static BotVector joystickTest(){
		BotVector b = new BotVector();
		b.add(new JoystickPortTest());
		return b;
	}
	
	protected static BotVector simpleMecanumTest(){
		BotVector b = new BotVector();
		b.add(new MecMotorRunner());
		return b;
	}
	
	private BotVector simpleRAIBTest() {
		BotVector b = new BotVector();
		b.add(new GamePadReaderRIAB());
		b.add(new SimpleRAIBCalculator());
		b.add(new TalonRunner());
		return b;
	}

	protected static BotVector motorRunnerTest (){
		BotVector b = new BotVector();
		b.add(new MotorRunner());
		return b;
	}
	
	protected static BotVector helloWorld(){
		BotVector b = new BotVector();
		b.add(new HelloWorldRunner());
		return b;
	}
	
	protected static BotVector tankDriveTrainTest(){
		BotVector b = new BotVector();
		b.add(new Joystick1Reader());
		b.add(new DriveTrainEncoderReader());
		b.add(new DriveTrain1JoystickCalculator());
		//b.add(new DriveTrainNoPIDCalculator());
		b.add(new DriveTrainPIDCalculator());
		b.add(new DriveTrainRunner());
		return b;
	}
}
