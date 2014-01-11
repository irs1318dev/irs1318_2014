package org.usfirst.ihs1318;

import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.TimerUtil;
import org.usfirst.ihs1318.shared.data.InputVelocity;

import edu.wpi.first.wpilibj.Dashboard;
import edu.wpi.first.wpilibj.IterativeRobot;

public class SystemTestBotJoysticks extends IterativeRobot {
	private JoystickReader jsr;
	private DashboardOutput dash;

	public void robotInit() {
		System.out.println("RobotInit()");
		TimerUtil.setStartTime();
		jsr = new JoystickReader();
		jsr.init();
		dash = new DashboardOutput();

	}

	public void disabledInit() {
		// TODO Auto-generated method stub

	}


	public void autonomousInit() {
		// TODO Auto-generated method stub

	}


	public void teleopInit() {
		System.out.println("Joystick Test Bot");

	}


	public void disabledPeriodic() {
		// TODO Auto-generated method stub

	}


	public void autonomousPeriodic() {
		// TODO Auto-generated method stub

	}


	public void teleopPeriodic() {
		jsr.readJoysticks();
		synchronized (ReferenceData.getInstance()) {
			InputVelocity iv = ReferenceData.getInstance().getInputVelocity();
			System.out.println("Jx: " + iv.getDesiredJx() + "    Jy: " + iv.getDesiredJy() + "   Jry: " + iv.getDesiredJRY() + "   JrX: " + iv.getDesiredW());
		}
		dash.sendData();
	}


	public void disabledContinuous() {
		// TODO Auto-generated method stub

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
