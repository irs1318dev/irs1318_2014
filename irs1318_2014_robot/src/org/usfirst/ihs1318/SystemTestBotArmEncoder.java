package org.usfirst.ihs1318;

import org.usfirst.ihs1318.kinematics.ArmCalculator;
import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.pid.ArmPID;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.TimerUtil;

import edu.wpi.first.wpilibj.IterativeRobot;
/**
 * If the joysticks aren't working, you can run the system test bot for joysticks.
 * This is not meant to test PID. Just to read encoder values.
 * 
 * @author patrick
 *
 */
public class SystemTestBotArmEncoder extends IterativeRobot {
	
	private double lastReadValue = 0;
	private double lastReadRate = 0;
	
	private JoystickReader jsr;
	private ArmCalculator armCalc;
	private ArmPID armPID;
	
	private DashboardOutput dash;
	
	public void robotInit() {
		System.out.println("RobotInit()");
		TimerUtil.setStartTime();
		
		jsr = new JoystickReader();
		jsr.init();
		armCalc = new ArmCalculator();
		armPID = new ArmPID();
		
		dash = new DashboardOutput();
	}

	
	public void disabledInit() {
		// TODO Auto-generated method stub
	}

	
	public void autonomousInit() {
		// TODO Auto-generated method stub
	}

	
	public void teleopInit() {
		System.out.println("Arm Encoder Test Bot");
	}

	
	public void disabledPeriodic() {
		// TODO Auto-generated method stub
	}

	
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub
	}

	
	public void teleopPeriodic() {
		jsr.readJoysticks();
		armPID.readEncoder();
		
		armPID.calculateArmSpeed();
		armPID.sendSpeed();
		double[][] values = new double[2][1000];
		int numValuesRead = 0;
		int numRatesRead = 0;
		
		synchronized( ReferenceData.getInstance()){
			double currentReadValue = ReferenceData.getInstance().getArmEncoderValues().getEncoderValue();
		//	double currentReadRate = ReferenceData.getInstance().getArmEncoderValues().getEncoderRate();
			
			if(numValuesRead % 50 == 0) {
				System.out.println(currentReadValue);
			}
			numRatesRead++;
//			if(Math.abs(lastReadValue - currentReadValue) < 10) {
//				System.out.println("Current total ticks:  " + Math.floor(currentReadValue));
//				if(numValuesRead < 1000) {
//					values[0][numValuesRead] = currentReadValue;
//					numValuesRead++;
//				}
//			}
//			if(Math.abs(lastReadRate - currentReadRate) < 10) {
//				System.out.println("Current Encoder Rate:   " + Math.floor(currentReadRate));
//				if(numValuesRead < 1000) {
//					values[1][numValuesRead] = currentReadRate;
//					numRatesRead++;
//				}
//			}
			
		
		}
		dash.sendData();
	 
	}

	
	public void disabledContinuous() {
		// TODO Auto-generated method stub
	}

	
	public void autonomousContinuous() {
		// TODO Auto-generated method stub
	}

	
	public void teleopContinuous() {
		// TODO Auto-generated method stub
	}
	

}
