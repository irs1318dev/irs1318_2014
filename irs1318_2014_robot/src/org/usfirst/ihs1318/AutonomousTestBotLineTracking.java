package org.usfirst.ihs1318;

import org.usfirst.ihs1318.autonomous.AutonomousController;
import org.usfirst.ihs1318.kinematics.MecanumDrive;
import org.usfirst.ihs1318.pid.WheelSpeedPID;
import org.usfirst.ihs1318.reference.GyroReader;
import org.usfirst.ihs1318.reference.LineSensorReader;

import edu.wpi.first.wpilibj.IterativeRobot;

public class AutonomousTestBotLineTracking extends IterativeRobot {
	private MecanumDrive md;
	private AutonomousController auto;
	private LineSensorReader lr;
	private GyroReader gr;
	private WheelSpeedPID wheelPID;
	
	public void robotInit() {
		System.out.println("Initializing Line Tracking Test");
		
		gr = new GyroReader();
		gr.init();
		
		lr = new LineSensorReader();
		lr.init();
		
		auto = new AutonomousController();
		auto.init();

		
		md = new MecanumDrive();
		md.init();
		
		wheelPID = new WheelSpeedPID();
		wheelPID.init();
		
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
		// TODO Auto-generated method stub
		super.teleopInit();
	}

	
	public void disabledPeriodic() {
		// TODO Auto-generated method stub
		super.disabledPeriodic();
	}

	
	public void autonomousPeriodic() {
		gr.readAngle();
		lr.readSensors();
		auto.trackLine();
		md.calculateWheelSpeeds();
		wheelPID.calculateCurrentWheelSpeed();
		wheelPID.sendWheelSpeeds();
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
