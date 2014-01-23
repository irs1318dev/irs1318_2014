package org.usfirst.ihs1318;

import org.usfirst.ihs1318.kinematics.ArmCalculator;
import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.pid.ArmPID;
import org.usfirst.ihs1318.pid.CANJaguarIHS1318;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.TimerUtil;
import org.usfirst.ihs1318.shared.constants.ConnectionRef;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANJaguar.ControlMode;
import edu.wpi.first.wpilibj.CANJaguar.PositionReference;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
/**
 * If the joysticks aren't working, you can run the system test bot for joysticks.
 * This is not meant to test PID. Just to read encoder values.
 * 
 * @author patrick
 *
 */
public class SystemTestBotCAN extends IterativeRobot {
	
	private double lastReadValue = 0;
	private double lastReadRate = 0;
	CANJaguar j;
	Timer time;
	private final double targetRevs = 20;
	private double currentRevs = 0;
	private JoystickReader jsr;
	private ArmCalculator armCalc;
	private ArmPID armPID;
	private double max_time = 3; // micro seconds
	private DashboardOutput dash;
	
	public void robotInit() {
		System.out.println("RobotInit()");
    	TimerUtil.setStartTime();
		try {
			j = new CANJaguar(ConnectionRef.ARM_MOTOR_CAN);
			System.out.println("CAn constructed");
		} catch (CANTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		time = new Timer();
		time.start();
		jsr = new  JoystickReader();
		jsr.init();
		try {
			j.enableControl();
			j.configEncoderCodesPerRev(90);// 360 / 4 because of quadrature encoder
			j.setPositionReference(PositionReference.kQuadEncoder);// no regular encoder choice
			
			System.out.println("init");
		} catch (CANTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dash = new DashboardOutput();
		
	}

	
	public void disabledInit() {
		// TODO Auto-generated method stub
	}

	
	public void autonomousInit() {
		time.reset();
		
//		try {
//			j.enableControl();
//			System.out.println("init");
//			j.changeControlMode(ControlMode.kVoltage);
//		} catch (CANTimeoutException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	
	public void teleopInit() {
		
//		System.out.println(ControlMode.kCurrent);
//		System.out.println(ControlMode.kPercentVbus);
//		System.out.println(ControlMode.kPosition);
//		System.out.println(ControlMode.kSpeed);
//		System.out.println(ControlMode.kVoltage);
		
	}

	
	public void disabledPeriodic() {
		// TODO Auto-generated method stub
	}

	
	public void autonomousPeriodic() {
		
		try {
			currentRevs = Math.abs(j.getPosition());
			if(currentRevs < (targetRevs / 2)){
				j.setX(1);
			}else if(currentRevs < (targetRevs / 1.5)){
				j.setX(.5);
			}else{
				j.setX(.3);
			}
				
		} catch (CANTimeoutException e) {

		}
		dash.sendData();
	}

	
	public void teleopPeriodic() {
		try {
			j.setX(jsr.getArmStick().getY());
			System.out.println("position :" + j.getPosition());
			
		} catch (CANTimeoutException e) {

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
