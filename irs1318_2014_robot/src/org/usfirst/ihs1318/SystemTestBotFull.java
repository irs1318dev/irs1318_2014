package org.usfirst.ihs1318;

import java.util.TimerTask;
import java.util.Vector;

import org.usfirst.ihs1318.discrete.AirCompressorController;
import org.usfirst.ihs1318.discrete.ArmController;
import org.usfirst.ihs1318.discrete.MiniBotController;
import org.usfirst.ihs1318.kinematics.AirCompressorKinematics;
import org.usfirst.ihs1318.kinematics.ArmCalculator;
import org.usfirst.ihs1318.kinematics.MecanumDrive;
import org.usfirst.ihs1318.kinematics.MiniBotKinematics;
import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.pid.ArmPID;
import org.usfirst.ihs1318.pid.WheelSpeedPID;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.reference.WheelEncoderReader;
import org.usfirst.ihs1318.shared.TimerUtil;

import edu.wpi.first.wpilibj.IterativeRobot;


public class SystemTestBotFull extends IterativeRobot {
	private JoystickReader jsr;
	private WheelEncoderReader wheelEncoders;
	private MecanumDrive md;
	private WheelSpeedPID pid;
	private TimerTask recordTicks;
	private TimerTask recordRates;
	
	private MiniBotKinematics miniKin;
	private MiniBotController mini;
	private ArmController arm;
	private AirCompressorKinematics airKin;
	private AirCompressorController airComp;	
	private ArmCalculator armCalc;
	private ArmPID armPID;

	private Vector debugTicks;
	private Vector debugRates;
	
	private DashboardOutput dash;
	
	public void robotInit() {
		System.out.println("SystemTestBotFull.robotInit()");
		getWatchdog().feed();
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
		

		airKin = new AirCompressorKinematics();

		airComp = new AirCompressorController();
		airComp.init();
		miniKin = new MiniBotKinematics();
		
		mini = new MiniBotController();
		mini.init();
		arm = new ArmController();
		arm.init();
		
		armCalc = new ArmCalculator();
		armPID = new ArmPID();
		armPID.init();
		
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
		System.out.println("Full Bot");
	}
	
	public void teleopPeriodic() {
		getWatchdog().feed();
		jsr.readJoysticks();
		wheelEncoders.readWheelEncoders();
		
		md.calculateWheelSpeeds();
		
		pid.calculateCurrentWheelSpeed();
		pid.sendWheelSpeeds();

		airKin.calculateAirCompressorKinematics();
		airComp.determineStatus();

		arm.getClawKin().calculateClawKinematics();
		arm.getClaw().setAsCurrent();

		arm.getWristKin().calculateWristValues();
		arm.getWrist().setAsCurrent();
		
		arm.getTargetHeight(); // no-op

		armPID.readEncoder();
		
		armPID.calculateArmSpeed();
		armPID.sendSpeed();

		miniKin.calculateMiniBot();
		mini.getDeployValues();
		
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
