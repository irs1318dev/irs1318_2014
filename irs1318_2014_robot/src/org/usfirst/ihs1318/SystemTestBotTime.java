package org.usfirst.ihs1318;

import java.util.TimerTask;

import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.shared.TimerUtil;

import edu.wpi.first.wpilibj.IterativeRobot;

public class SystemTestBotTime extends IterativeRobot {
	private TimerTask test;
	private TimerTask printTime;
	private DashboardOutput dash;
	
	
	public void robotInit() {
		System.out.println("robotInit()");
		TimerUtil.setStartTime();
		
		test = new TimerTask() {
			public void run() {
				System.out.println("Hello World");
			}
		};
		TimerUtil.getTimer().schedule(test, 1000);
		
		printTime = new TimerTask() {
			public void run() {
				System.out.println(TimerUtil.getSeconds());
				
			}
		};
		TimerUtil.getTimer().schedule(printTime, 0, 1000);
		
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
		System.out.println("Timer Test Bot");
		dash.sendData();
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
