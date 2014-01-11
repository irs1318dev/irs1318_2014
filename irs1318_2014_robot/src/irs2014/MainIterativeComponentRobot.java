package irs2014;

import irs2014.driveTrainTank.DriveTrain1JoystickCalculator;
import irs2014.driveTrainTank.DriveTrainEncoderReader;
import irs2014.driveTrainTank.DriveTrainNoPIDCalculator;
import irs2014.driveTrainTank.DriveTrainPIDCalculator;
import irs2014.driveTrainTank.DriveTrainRunner;
import irs2014.helloWorld.HelloWorldRunner;
import irs2014.userInputDevices.Joystick1Reader;

public class MainIterativeComponentRobot extends IterativeComponentRobot {

	public BotVector currentRobotComponents() {
		//return tankDriveTrainTest();
		return helloWorld();
	}
	
	protected static BotVector helloWorld(){
		BotVector b = new BotVector();
		b.add(new HelloWorldRunner());
		return b;
	}
	
	protected static BotVector tankDriveTrainTest(){
		BotVector b = new BotVector();
		b.add(new Joystick1Reader());
		//b.add(new DriveTrainEncoderReader());
		b.add(new DriveTrain1JoystickCalculator());
		b.add(new DriveTrainNoPIDCalculator());
		//b.add(new DriveTrainPIDCalculator());
		b.add(new DriveTrainRunner());
		return b;
	}
}
