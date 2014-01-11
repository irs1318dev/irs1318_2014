package org.usfirst.ihs1318;

import org.usfirst.ihs1318.discrete.ArmController;
import org.usfirst.ihs1318.kinematics.ArmCalculator;
import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.pid.ArmPID;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.shared.TimerUtil;

import edu.wpi.first.wpilibj.IterativeRobot;

public class SystemTestBotArmSetPoints extends IterativeRobot {
	private JoystickReader jsr;
	private ArmCalculator armCalc;
	private ArmController armController;
	private ArmPID armPID;
	private DashboardOutput dash;
	
	public void robotInit() {
		System.out.println("Robot init");
    	TimerUtil.setStartTime();
		jsr = new JoystickReader();
		jsr.init();
		
		armCalc = new ArmCalculator();
		
		armController = new ArmController();
		armController.init();
		
		armPID = new ArmPID();
		armPID.init();
		
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

	}

	
	public void disabledPeriodic() {
		// TODO Auto-generated method stub
		super.disabledPeriodic();
	}

	
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub
		super.autonomousPeriodic();
	}

	
	public void teleopPeriodic() {
		jsr.readJoysticks();
		armPID.readEncoder();
		armCalc.calculateDesiredArmVelocity();
		
		armController.run();
		armPID.calculateArmSpeed();
		armPID.sendSpeed();
		
		dash.sendData();
	}

	
	public void disabledContinuous() {
		// TODO Auto-generated method stub
		super.disabledContinuous();
	}

	
	public void autonomousContinuous() {
		// TODO Auto-generated method stub
		super.autonomousContinuous();
	}

	
	public void teleopContinuous() {
		// TODO Auto-generated method stub
		super.teleopContinuous();
	}

}
