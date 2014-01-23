package org.usfirst.ihs1318;

import org.usfirst.ihs1318.discrete.WristController;
import org.usfirst.ihs1318.kinematics.WristKinematics;
import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.shared.TimerUtil;

import edu.wpi.first.wpilibj.IterativeRobot;

public class SystemTestBotWrist extends IterativeRobot {
	JoystickReader jsr;
	WristKinematics wristKin;
	WristController wristController;
	DashboardOutput dash;
	
	
	public void robotInit() {
		System.out.println("RobotInit()");
		TimerUtil.setStartTime();
		jsr = new JoystickReader();
		jsr.init();
		wristKin = new WristKinematics();
		wristKin.init();
		wristController = new WristController();
		wristController.init();
		dash = new DashboardOutput();
	}

	
	public void disabledInit() {
		// TODO Auto-generated method stub
		super.disabledInit();
	}

	
	public void autonomousInit() {
		// TODO Auto-generated method stub
		super.autonomousInit();
	}

	
	public void teleopInit() {
		System.out.println("Wrist Test Bot:");
		dash.sendData();
	}

	
	public void disabledPeriodic() {
		// TODO Auto-generated method stub
		super.disabledPeriodic();
	}

	
	public void autonomousPeriodic() {

	}

	
	public void teleopPeriodic() {
		jsr.readJoysticks();
		wristKin.calculateWristValues();
		wristController.setAsCurrent();
	}

	
	public void disabledContinuous() {
		// TODO Auto-generated method stub
		super.disabledPeriodic();
	}

	
	public void autonomousContinuous() {
		// TODO Auto-generated method stub
		super.autonomousPeriodic();
	}

	
	public void teleopContinuous() {
		// TODO Auto-generated method stub
		super.teleopPeriodic();
	}

}
