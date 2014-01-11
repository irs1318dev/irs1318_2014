package org.usfirst.ihs1318;


import org.usfirst.ihs1318.autonomous.AutonomousController;
import org.usfirst.ihs1318.kinematics.MecanumDrive;
import org.usfirst.ihs1318.message.PrintDebugStatement;
import org.usfirst.ihs1318.pid.WheelSpeedPID;
import org.usfirst.ihs1318.reference.ButtonReader;
import org.usfirst.ihs1318.reference.GyroReader;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.reference.LineSensorReader;
import org.usfirst.ihs1318.reference.WheelEncoderReader;
import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.TimerUtil;
import org.usfirst.ihs1318.shared.data.MotorSpeed;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANJaguar.PositionReference;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class SingleThreadJavaBot extends IterativeRobot {

	// Reference
	private WheelEncoderReader wer = null;
	private JoystickReader jsr = null;
	private GyroReader gr = null;
	private AutonomousController lt = null;
	private LineSensorReader sensors = null;
	
	// Kinematics
	private MecanumDrive md = null;
	
	// PID
	private WheelSpeedPID wsp = null;

	// Message
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	System.out.println("RobotInit");
    	TimerUtil.setStartTime();
    	//TODO create and run threads
    	
    	//TODO reference data thread(s)
    	// Wheel encoder
    	wer = new WheelEncoderReader();
    	wer.init();
    	
    	//Line Tracker
    	lt = new AutonomousController();
    	lt.init();
    	
    	//Line Sensors
    	sensors = new LineSensorReader();
    	
    	// Joystick
    	jsr = new JoystickReader();
    	jsr.init();
    	
    	gr = new GyroReader();
    	gr.init();
    	
    	//TODO kinematic data thread(s)
    	md = new MecanumDrive();
    	md.init();
    	
    	// wheel speeds
    	wsp = new WheelSpeedPID();
    	wsp.init();
    	wsp.setDebugEnabled(true);

    	//TODO Display publisher thread(s)
    	//TODO Discrete controller thread(s)
    

    	//TODO start threads here -- is order important ?
    	
    	// Start reference threads
    	wsp.setEnabled(true);
    	jsr.setEnabled(true);
    	gr.setGyroEnabled(true);

    	// Start kinematic calculation thread
    	md.setEnabled(true);
    	
    	// Start PID threads
    	wsp.setEnabled(true);
    	System.out.println("end of RobotInit");
    }
    
    public void autonomousInit(){
    	md.setEnableOffsetTheta(false);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	//TODO refresh rate is established here... may not be necessary with threads.
    	getWatchdog().feed();
    	sensors.readSensors();
    	
     	wer.readWheelEncoders();
    	   	
    	// Kinematics calculation
    	md.calculateWheelSpeeds();
    	
    	// Wheel speed PID commands
    	wsp.calculateCurrentWheelSpeed();
    	wsp.sendWheelSpeeds();
    	
    	gr.setTheta();
    	gr.readAngle();
    	
    }
    
    public void teleopInit(){
    	md.setEnableOffsetTheta(true);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	getWatchdog().feed();

    	// Reference data
    	jsr.readJoysticks();
    	wer.readWheelEncoders();
    	
    	
    	// Kinematics calculation
    	md.calculateWheelSpeeds();
    	
    	// Wheel speed PID commands
    	wsp.calculateCurrentWheelSpeed();
    	wsp.sendWheelSpeeds();
    	
    	gr.setTheta();
    	gr.readAngle();
    	
    	
    }
    
    public void disabledInit(){
    	gr.resetGyro();
    	// Send messages.
    	PrintDebugStatement.sendAllToStation();
    	
    }
    
}
