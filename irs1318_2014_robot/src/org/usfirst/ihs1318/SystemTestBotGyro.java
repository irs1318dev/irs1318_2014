package org.usfirst.ihs1318;

import java.util.Vector;

import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.reference.GyroReader;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.TimerUtil;

import edu.wpi.first.wpilibj.IterativeRobot;

public class SystemTestBotGyro extends IterativeRobot {
	private JoystickReader jsr; //The buttons should work too
	private GyroReader gyroReader;
	private Vector debug;
	
	private DashboardOutput dash;
	
	private DashboardOutput dashboardOutput; 
	public void robotInit() {
		
		TimerUtil.setStartTime();
		System.out.println("RobotInit()");
		jsr = new JoystickReader();
		jsr.init();
		gyroReader = new GyroReader();
		gyroReader.init();
		
		dash = new DashboardOutput();
		//dashboardOutput = new DashboardOutput();
		
		
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
		System.out.println("Gyro Test Bot");
		gyroReader.resetGyro();
	}

	
	public void disabledPeriodic() {
//		for(int i = 0; i < debug.size(); i++) {
//			System.out.println( ((Double) (debug.elementAt(i))).doubleValue() );
//		}
	}

	
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub
		super.autonomousPeriodic();
	}

	int count=0;
	public void teleopPeriodic() {
		jsr.readJoysticks();
		gyroReader.readAngle();
		System.out.println(gyroReader.getAngle() * 180 / Math.PI);
		double readAngle;
		synchronized (ReferenceData.getInstance()){
			readAngle = ReferenceData.getInstance().getOrientation().getTheta();
		}
		if (ReferenceData.DEBUG) {
			if (count%20==0) {
		//		System.out.println("Angle(deg)="+readAngle*180/Math.PI);
				count=0;
			}
			count++;
			
		}
		
		dash.sendData();
		//dashboardOutput.sendData();
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
