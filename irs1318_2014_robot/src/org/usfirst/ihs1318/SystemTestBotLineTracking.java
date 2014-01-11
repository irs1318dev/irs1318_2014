package org.usfirst.ihs1318;

import org.usfirst.ihs1318.autonomous.AutonomousController;
import org.usfirst.ihs1318.kinematics.MecanumDrive;
import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.pid.WheelSpeedPID;
import org.usfirst.ihs1318.reference.WheelEncoderReader;
import org.usfirst.ihs1318.shared.TimerUtil;

import edu.wpi.first.wpilibj.IterativeRobot;

public class SystemTestBotLineTracking extends IterativeRobot {
	private AutonomousController controller;
	private MecanumDrive mecanumDrive;
	private WheelEncoderReader wer;
	private WheelSpeedPID wheelPID;
	
	private DashboardOutput dash;
	
	public void robotInit() {
		System.out.println("LineTracking Test Bot init");
		TimerUtil.setStartTime();
		
		controller = new AutonomousController();
		controller.init();
		
		wer = new WheelEncoderReader();
		wer.init();
		
		mecanumDrive = new MecanumDrive();
		mecanumDrive.init();
		
		wheelPID = new WheelSpeedPID();
		wheelPID.init();
		
		dash = new DashboardOutput();
	}

	
	public void disabledInit() {
		// TODO Auto-generated method stub
		super.disabledInit();
	}

	
	public void autonomousInit() {
		System.out.println("AutonomousInit");
	}

	
	public void teleopInit() {
		// TODO Auto-generated method stub
		super.teleopInit();
	}

	
	public void disabledPeriodic() {
		// TODO Auto-generated method stub
		super.disabledPeriodic();
	}
	
	public void autonomousPeriodic() {
		controller.trackLine();
		
		wer.readWheelEncoders();
		mecanumDrive.calculateWheelSpeeds();
		wheelPID.calculateCurrentWheelSpeed();
		wheelPID.sendWheelSpeeds();
		
		dash.sendData();
	}

	
	public void teleopPeriodic() {
		// TODO Auto-generated method stub
		super.teleopPeriodic();
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
