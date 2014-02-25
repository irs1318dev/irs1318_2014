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
import irs2014.lineSensor.SimpleLineSensorReader;
import irs2014.networkTable.GetValuesTest;
import irs2014.networkTable.NetworkTableRunner;
import irs2014.pressure.CompressorRunner;
import irs2014.pressure.PressureSensorCalculator;
import irs2014.pressure.PressureSensorReader;
import irs2014.pressure.PressureSensorTimerRunner;
import irs2014.shooter.AngleCalculator;
import irs2014.shooter.AngleRunner;
import irs2014.shooter.OneSolenoidRunner;
import irs2014.shooter.ShooterCalculator;
import irs2014.shooter.ShooterRunner;
import irs2014.simpleRIAB.GamePadReaderRIAB;
import irs2014.simpleRIAB.SimpleRAIBCalculator;
import irs2014.simpleRIAB.TalonRunner;
import irs2014.test.ShooterTest;
import irs2014.timer.TimeMeasureRunner;
import irs2014.timer.TimerRunner;
import irs2014.userInputDevices.Joystick1Reader;
import irs2014.userInputDevices.JoystickPortTest;
import irs2014.userInputDevices.SmartDashReader;
import irs2014.userInputDevices.UserInputCalculator;

public class MainIterativeComponentRobot extends IterativeComponentRobot {

	public BotVector currentRobotComponents() {
		return initialRobot();
//		return autonomousTesting();
	}
	
	protected static BotVector autonomousTesting()
	{//This is just testing the task set in Auto Input Map
		BotVector b = new BotVector();
		b.add(new DipSwitchReader());
		b.add(new CompressorRunner());
		b.add(new Joystick1Reader());
		b.add(new DriveTrainEncoderReader());
		//Auto stuff goes after readers, before calculators.
		b.add(new AutoTaskRunner());
		//
		b.add(new CollectorCalculator());
		b.add(new ShooterCalculator());
		b.add(new DriveTrain1JoystickCalculator());
		b.add(new DriveTrainPIDCalculator());
		b.add(new AngleCalculator());
		b.add(new NetworkTableRunner());
		b.add(new DriveTrainRunner());
		b.add(new CollectorMotorRunner());
		b.add(new CollectorSolenoidRunner());
		b.add(new AngleRunner());
		b.add(new ShooterRunner());
		b.add(new TimerRunner());
		return b;
	}
	
	protected static BotVector isolatedTest(){
		BotVector b = new BotVector();
		b.add(new CompressorRunner());
		b.add(new ShooterTest());
		return b;
	}
	
	protected static BotVector oneSolenoidTest(){
		BotVector b = new BotVector();
		b.add(new CompressorRunner());
		b.add(new OneSolenoidRunner());
		return b;
	}
	
	protected static BotVector compressorTest(){
		BotVector b = new BotVector();
		b.add(new CompressorRunner());
		return b;
	}
	
	protected static BotVector initialRobot(){
		BotVector b = new BotVector();
		b.add(new DipSwitchReader());
		b.add(new CompressorRunner());
		b.add(new Joystick1Reader());
		b.add(new DriveTrainEncoderReader());
		b.add(new PressureSensorReader());
//		b.add(new SimpleLineSensorReader());
		b.add(new UserInputCalculator());
		b.add(new CollectorCalculator());
		b.add(new ShooterCalculator());
		b.add(new DriveTrain1JoystickCalculator());
//		b.add(new DriveTrainPIDCalculator());
		b.add(new DriveTrainNoPIDCalculator());
		b.add(new AngleCalculator());
		b.add(new PressureSensorCalculator());
		b.add(new NetworkTableRunner());
		b.add(new DriveTrainRunner());
		b.add(new CollectorMotorRunner());
		b.add(new CollectorSolenoidRunner());
		b.add(new AngleRunner());
		b.add(new PressureSensorTimerRunner());
		b.add(new ShooterRunner());
		b.add(new TimerRunner());
//		b.add(new TimeMeasureRunner());
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
		b.add(new CompressorRunner());
		b.add(new Joystick1Reader());
		b.add(new DriveTrainEncoderReader());
		b.add(new DriveTrain1JoystickCalculator());
		b.add(new CollectorCalculator());
		//b.add(new DriveTrainNoPIDCalculator());
		b.add(new DriveTrainPIDCalculator());
		b.add(new NetworkTableRunner());
		b.add(new CollectorMotorRunner());
		b.add(new DriveTrainRunner());
		return b;
	}
	
	protected static BotVector autonomousTest(){
		BotVector b = new BotVector();
		b.add(new AutoTaskRunner());
		return b;
	}

	protected static BotVector collectorTest(){
		BotVector b = new BotVector();
		b.add(new CompressorRunner());
		b.add(new Joystick1Reader());
		b.add(new CollectorCalculator());
//		b.add(new CollectorLimitSwitchReader());
//		b.add(new CollectorMotorRunner());
		b.add(new CollectorSolenoidRunner());
		return b;
	}
	
	protected static BotVector dipSwitchTest(){
		BotVector b = new BotVector();
		b.add(new DipSwitchReader());
		return b;
	}
	
	protected static BotVector shooterTest(){
		BotVector b = new BotVector();
		b.add(new CompressorRunner());
		b.add(new Joystick1Reader());
//		b.add(new PressureSensorReader());
//		b.add(new UserInputCalculator());
		b.add(new ShooterCalculator());
		b.add(new CollectorCalculator());
		b.add(new NetworkTableRunner());
		b.add(new CollectorSolenoidRunner());
		b.add(new ShooterRunner());
		return b;
	}
	
	protected static BotVector timmerTest(){ //lol
		BotVector b = new BotVector();
		b.add(new TimeMeasureRunner());
		b.add(new TimerRunner());
		return b;
	}
	
	protected static BotVector userInputDevicesTest(){ //idk if we need this
		BotVector b = new BotVector();
		b.add(new Joystick1Reader());
		b.add(new SmartDashReader());
		b.add(new UserInputCalculator());
		return b;
	}
}

