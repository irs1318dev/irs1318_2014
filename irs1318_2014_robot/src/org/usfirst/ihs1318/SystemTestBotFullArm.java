package org.usfirst.ihs1318;


import java.util.TimerTask;

import org.usfirst.ihs1318.discrete.AirCompressorController;
import org.usfirst.ihs1318.discrete.ArmController;

import org.usfirst.ihs1318.discrete.MiniBotController;
import org.usfirst.ihs1318.kinematics.AirCompressorKinematics;
import org.usfirst.ihs1318.kinematics.ArmCalculator;
import org.usfirst.ihs1318.kinematics.MiniBotKinematics;
import org.usfirst.ihs1318.message.DashboardOutput;

import org.usfirst.ihs1318.pid.ArmPID;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.shared.TimerUtil;

import java.util.Vector;



import edu.wpi.first.wpilibj.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class SystemTestBotFullArm extends IterativeRobot{
	private JoystickReader jsr;
	private MiniBotKinematics miniKin;
	private MiniBotController mini;
	private ArmController arm;
	private AirCompressorKinematics airKin;
	private AirCompressorController airComp;	
	private TimerTask printAndStoreValues;
	private Vector debug;

	private ArmCalculator armCalc;
	private ArmPID armPID;
	
	private DashboardOutput dash;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		System.out.println("SystemTestBot.robotInit()");
		TimerUtil.setStartTime();
		//joystick
		jsr = new JoystickReader();
		jsr.init();

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
		
		debug = new Vector();
		
		dash = new DashboardOutput();
		

		//		printAndStoreValues = new TimerTask() {
//			public void run() {
//				System.out.println("Wrist distal closed:" + arm.getWrist().getDistalWristOpen().get());	
//				System.out.println("Wrist proximal open:" + arm.getWrist().getProximalWristOpen().get());
//				System.out.println("Claw open:" + arm.getClaw().getClawOpen());	
//				System.out.println("Mini-bot open:" + mini.getMiniBotExtended());
//				System.out.println("");
//				
//				boolean[] values = new boolean[8];
//				values[0] = arm.getWrist().getDistalWristOpen().get();
//				values[1] = arm.getWrist().getDistalWristClosed().get();
//				values[2] = arm.getWrist().getProximalWristOpen().get();
//				values[3] = arm.getWrist().getProximalWristClosed().get();
//				values[4] = arm.getClaw().getClawOpen().get();
//				values[5] = arm.getClaw().getClawClosed().get();
//				values[6] = mini.getMiniBotExtended().get();
//				values[7] = mini.getMiniBotRetracted().get();
//							
//				debug.addElement(values);
//			}
//		};
		
//		TimerUtil.getTimer().schedule(printAndStoreValues, 1000, 1000);
	
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		// TODO refresh rate is established here... may not be necessary with
		getWatchdog().feed();
		
	}

	public void teleopInit() {
		System.out.println("Solenoid Test Bot");
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		getWatchdog().feed();
		jsr.readJoysticks();
		
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

	public void teleopContinuous() {

	}

	public void disabledInit() {
//		System.out.println("SystemTestBot.disabledInit()");
//		
//		for(int x = 0; x < 10; x++)
//			System.out.println("");
////						    false, false, false, false, false, false, false, false, 
//		System.out.println("WD-O   WD-C   WP-O   WP-C   C-O    C-C    M-Ext  M-Ret  ");
//		for(int i = 0; i < debug.size(); i++) {
//			System.out.println(debug.elementAt(i));
//		}

	}

	public void teleopDisabled() {
		
	}

	public void autonomousDisabled() {
		// TODO Auto-generated method stub
		
	}
}
