package org.usfirst.ihs1318;

import java.util.TimerTask;
import java.util.Vector;

import org.usfirst.ihs1318.kinematics.MecanumDrive;
import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.pid.WheelSpeedPID;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.reference.WheelEncoderReader;
import org.usfirst.ihs1318.shared.TimerUtil;

import edu.wpi.first.wpilibj.IterativeRobot;

public class SystemTestBotWheelEncoders extends IterativeRobot {
	private JoystickReader jsr;
	private WheelEncoderReader wheelEncoders;
	private MecanumDrive md;
	private WheelSpeedPID pid;
	private TimerTask recordTicks;
	private TimerTask recordRates;
	
	private Vector debugTicks;
	private Vector debugRates;
	
	private DashboardOutput dash;
	
	public void robotInit() {
		TimerUtil.setStartTime();
		System.out.println("RobotInit()");
		wheelEncoders = new WheelEncoderReader();
		wheelEncoders.init();
		jsr = new JoystickReader();
		jsr.init();
		md = new MecanumDrive();
		md.init();
		pid = new WheelSpeedPID();
		pid.init();
		
		dash = new DashboardOutput();
		
		
		
//		debugTicks = new Vector();
//		debugRates = new Vector();
//		recordTicks = new TimerTask() {
//			public void run() {
//				double[] wheelTicks = new double[4];
//				wheelTicks[0] = wheelEncoders.getTicks().getRf();
//				wheelTicks[1] = wheelEncoders.getTicks().getLf();
//				wheelTicks[2] = wheelEncoders.getTicks().getLr();
//				wheelTicks[3] = wheelEncoders.getTicks().getRr();
//				
//				debugTicks.addElement(wheelTicks);			
//			}
//		};
//		TimerUtil.getTimer().schedule(recordTicks, 1000, 100);
//		
//		recordRates = new TimerTask() {
//			public void run() {
//				double[] wheelRates = new double[4];
//				wheelRates[0] = wheelEncoders.getWmev().getRf();
//				wheelRates[1] = wheelEncoders.getWmev().getLf();
//				wheelRates[2] = wheelEncoders.getWmev().getLr();
//				wheelRates[3] = wheelEncoders.getWmev().getRr();
//				
//				debugRates.addElement(wheelRates);
//			}
//		};
//		TimerUtil.getTimer().schedule(recordRates, 500, 100);
	}
	
	public void disabledInit() {
//		for(int i = 0; i < debugRates.size(); i++){
//			System.out.println(i);
//			System.out.println(debugRates.elementAt(i));
//		}
//		for(int i = 0; i < debugTicks.size(); i++) {
//			System.out.println(i);
//			System.out.println(debugTicks.elementAt(i));
//		}
		
	}
	
	
	public void teleopInit() {
		System.out.println("Wheel Encoder Test Bot");
	}
	
	public void teleopPeriodic() {
		jsr.readJoysticks();
		wheelEncoders.readWheelEncoders();
		
		md.calculateWheelSpeeds();
		
		pid.calculateCurrentWheelSpeed();
		pid.sendWheelSpeeds();
		
		dash.sendData();
	}

	public void teleopDisabled() {
//		for(int i = 0; i < debugRates.size(); i++){
//			System.out.println(i);
//			System.out.println(debugRates.elementAt(i));
//		}
//		for(int i = 0; i < debugTicks.size(); i++) {
//			System.out.println(i);
//			System.out.println(debugTicks.elementAt(i));
//		}
		
	}
	public void autonomousDisabled() {
		
	}

}
