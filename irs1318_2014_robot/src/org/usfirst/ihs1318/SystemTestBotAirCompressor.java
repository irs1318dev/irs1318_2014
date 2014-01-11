package org.usfirst.ihs1318;

import org.usfirst.ihs1318.discrete.AirCompressorController;
import org.usfirst.ihs1318.kinematics.AirCompressorKinematics;
import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.shared.TimerUtil;

import edu.wpi.first.wpilibj.IterativeRobot;

public class SystemTestBotAirCompressor extends IterativeRobot {
	private JoystickReader jsr;
	private AirCompressorKinematics airKin;
	private AirCompressorController airComp;
	private DashboardOutput dash;
	
	public void robotInit() {
		System.out.println("RobotInit()");
		TimerUtil.setStartTime();
		jsr = new JoystickReader();
		jsr.init();
		
		airKin = new AirCompressorKinematics();

		airComp = new AirCompressorController();
		airComp.init();
		
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
		System.out.println("Air Compressor Test Bot");
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
		airKin.calculateAirCompressorKinematics();
		airComp.determineStatus();
		
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
