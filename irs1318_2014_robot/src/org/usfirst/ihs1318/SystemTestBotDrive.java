package org.usfirst.ihs1318;

import org.usfirst.ihs1318.kinematics.MecanumDrive;
import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.pid.WheelSpeedPID;
import org.usfirst.ihs1318.reference.GyroReader;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.reference.WheelEncoderReader;
import org.usfirst.ihs1318.shared.TimerUtil;

import edu.wpi.first.wpilibj.IterativeRobot;

public class SystemTestBotDrive extends IterativeRobot {
	private WheelEncoderReader wer;
	private JoystickReader jsr;
	private GyroReader gyroReader;
	
	private MecanumDrive md;
	
	private WheelSpeedPID wheelPID;
	
	private DashboardOutput dash;
	
	
	public void robotInit() {
		System.out.println("RobotInit()");
		TimerUtil.setStartTime();
		wer = new WheelEncoderReader();
		wer.init();
		jsr = new JoystickReader();
		jsr.init();
		gyroReader = new GyroReader();
		gyroReader.init();
		
		md = new MecanumDrive();
		md.init();
		
		wheelPID = new WheelSpeedPID();
		wheelPID.init();
		
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
		System.out.println("Drive Test Bot");
	}

	
	public void disabledPeriodic() {

	}

	
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub
		super.autonomousPeriodic();
	}

	
	public void teleopPeriodic() {
		jsr.readJoysticks();
		wer.readWheelEncoders();
		gyroReader.readAngle();
		
		md.calculateWheelSpeeds();
		
		wheelPID.calculateCurrentWheelSpeed();
		wheelPID.sendWheelSpeeds();
		
		dash.sendData();
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
