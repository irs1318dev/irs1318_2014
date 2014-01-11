package org.usfirst.ihs1318;

import java.util.TimerTask;

import org.usfirst.ihs1318.autonomous.AutonomousController;
import org.usfirst.ihs1318.discrete.AirCompressorController;
import org.usfirst.ihs1318.discrete.ArmController;
import org.usfirst.ihs1318.discrete.CameraController;
import org.usfirst.ihs1318.discrete.MiniBotController;
import org.usfirst.ihs1318.kinematics.AirCompressorKinematics;
import org.usfirst.ihs1318.kinematics.ArmCalculator;
import org.usfirst.ihs1318.kinematics.CameraFlipKinematics;
import org.usfirst.ihs1318.kinematics.MecanumDrive;
import org.usfirst.ihs1318.kinematics.MiniBotKinematics;
import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.pid.ArmPID;
import org.usfirst.ihs1318.pid.WheelSpeedPID;
import org.usfirst.ihs1318.reference.GyroReader;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.reference.LineSensorReader;
import org.usfirst.ihs1318.reference.WheelEncoderReader;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.TimerUtil;
import org.usfirst.ihs1318.shared.constants.ButtonRef;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class SystemTetsBotGarrettAuto extends IterativeRobot {
	private JoystickReader jsr;
	private WheelEncoderReader wheelEncoders;
	private TimerTask recordTicks;
	private TimerTask recordRates;
	
	private MiniBotKinematics miniKin;
	private MiniBotController mini;
	private AirCompressorKinematics airKin;
	private AirCompressorController airComp;	
	private ArmCalculator armCalc;
	private GyroReader gyro;
	private LineSensorReader lineSensorReader;
	private CameraFlipKinematics cfk;
	private CameraController cc;
	private MecanumDrive md;
	private AutonomousController auto;
	private LineSensorReader lr;
	private GyroReader gr;
	private WheelSpeedPID wheelPID;
	private ArmPID armPID;
	private AirCompressorController ac;
	private ArmController armSolenoids;
	long startTime;
	long endTime;
	Timer t = new Timer();
	
	public void robotInit() {
		System.out.println("Initializing Line Tracking Test");

		getWatchdog().feed();
		TimerUtil.setStartTime();
		System.out.println("RobotInit()");
		wheelEncoders = new WheelEncoderReader();
		wheelEncoders.init();
		jsr = new JoystickReader();
		jsr.init();
		
		md = new MecanumDrive();
		md.init();
		wheelPID = new WheelSpeedPID();
		wheelPID.init();
		
		gyro = new GyroReader();
		gyro.init();

		cfk = new CameraFlipKinematics();
		cc = new CameraController();
		cc.init();
		
		airKin = new AirCompressorKinematics();

		airComp = new AirCompressorController();
		airComp.init();

		// turn on air by default
		synchronized(ReferenceData.getInstance()) {
		ReferenceData.getInstance()
	    .getButtonValues().getButtons()[ButtonRef.AIR_JS][ButtonRef.AIR_ENABLE] = true;
		}
		airKin.calculateAirCompressorKinematics();
		airComp.determineStatus();

		
		
		miniKin = new MiniBotKinematics();
		
		mini = new MiniBotController();
		mini.init();
		armSolenoids = new ArmController();
		armSolenoids.init();
		armSolenoids.setEnabled(true);
		
		armCalc = new ArmCalculator();
		armPID = new ArmPID();
		armPID.init();

		cfk.calculateServo();
		cc.getDirection();
		

//		lineSensorReader = new LineSensorReader();
//		lineSensorReader.init();

		
		TimerUtil.setStartTime();
		
//		gr = new GyroReader();
//		gr.init();
		
//		lr = new LineSensorReader();
//		lr.init();
//		
		auto = new AutonomousController();
		auto.init();
		
		md = new MecanumDrive();
		md.init();
		
		wheelPID = new WheelSpeedPID();
		wheelPID.init();
		
		armPID = new ArmPID();
		armPID.init();
		
//		ac = new AirCompressorController();
//		ac.init();
//		ac.getCompressor().start();
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



	
	public void teleopPeriodic() {
		getWatchdog().feed();
		jsr.readJoysticks();
		wheelEncoders.readWheelEncoders();

		gyro.readAngle();
		
		md.calculateWheelSpeeds();
		
		wheelPID.calculateCurrentWheelSpeed();
		wheelPID.sendWheelSpeeds();

		airKin.calculateAirCompressorKinematics();
		airComp.determineStatus();

		armSolenoids.getClawKin().calculateClawKinematics();
		armSolenoids.getClaw().setAsCurrent();

		armSolenoids.getWristKin().calculateWristValues();
		armSolenoids.getWrist().setAsCurrent();
		
		armSolenoids.getTargetHeight(); // no-op

		armPID.readEncoder();
		
		armPID.calculateArmSpeed();
		armPID.sendSpeed();

		miniKin.calculateMiniBot();
		mini.getDeployValues();

	}

	
	public void disabledContinuous() {
		// TODO Auto-generated method stub
		super.disabledPeriodic();
	}

	
	public void autonomousPeriodic() {
			}

	
	public void teleopContinuous() {
		// TODO Auto-generated method stub
		t.start();
		if(t.get() < 10){
			synchronized(ReferenceData.getInstance()){
				ReferenceData.getInstance().getInputVelocity().setAll(0, 0, 0, 0);
				ReferenceData.getInstance().getArmInput().setDesiredJy(-.6);
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.JOYSTICK_ARM][ButtonRef.WRIST_BUTTON_MED] = true;
			}
		}else if(t.get() > 10){
			synchronized(ReferenceData.getInstance()){
				ReferenceData.getInstance().getButtonValues().getButtons()[ButtonRef.JOYSTICK_ARM][ButtonRef.CLAW_BUTTON] = true;
			}
		}else{
			synchronized(ReferenceData.getInstance()){
				ReferenceData.getInstance().getArmInput().setDesiredJy(.4);
			}
		}
		//lr.readSensors();
		//auto.trackLine();
		md.calculateWheelSpeeds();
		wheelPID.calculateCurrentWheelSpeed();
		wheelPID.sendWheelSpeeds();
		
		armSolenoids.run();
		armPID.calculateArmSpeed();
		armPID.sendSpeed();

	}

}
