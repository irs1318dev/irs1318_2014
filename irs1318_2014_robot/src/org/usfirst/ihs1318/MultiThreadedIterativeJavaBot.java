package org.usfirst.ihs1318;


import org.usfirst.ihs1318.kinematics.MecanumDrive;
import org.usfirst.ihs1318.message.PrintDebugStatement;
import org.usfirst.ihs1318.pid.WheelSpeedPID;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.reference.WheelEncoderReader;
import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.TimerUtil;
import org.usfirst.ihs1318.shared.data.MotorSpeed;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class MultiThreadedIterativeJavaBot extends IterativeRobot {

	// Reference
	private WheelEncoderReader wer = null;
	private Thread werThread = null;
	private JoystickReader jsr = null;
	private Thread jsrThread = null;

	// Kinematics
	private MecanumDrive md = null;
	private Thread mdThread = null;
	
	// PID
	private WheelSpeedPID wsp = null;
	private Thread wspThread = null;

	// Message
	private DriverStation station;
	private DriverStationLCD lcd = DriverStationLCD.getInstance();
	static Line LINE_USER6;
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	//TODO create and run threads
    	
    	//TODO reference data thread(s)
    	TimerUtil.setStartTime();
    	// Wheel encoder
    	wer = new WheelEncoderReader();
    	wer.init();
    	werThread = new Thread(wer);
    	
    	// Joystick
    	jsr = new JoystickReader();
    	jsr.init();
    	jsrThread = new Thread(jsr);
    	
    	
    	//TODO kinematic data thread(s)
    	md = new MecanumDrive();
    	md.init();
    	mdThread = new Thread(md);
    	
    	
    	// hard code Motor kinematic output for now...-1.0 to 1.0
//    	MotorSpeed testMs = new MotorSpeed();
//    	testMs.setLf(0.1);
//    	testMs.setRf(0.1);
//    	testMs.setLr(0.1);
//    	testMs.setRr(0.1);
//    	synchronized (KinematicData.getInstance()) {
//    		testMs.copyValues(KinematicData.getInstance().getWmSpeed());
//    	}
    	
    	//TODO PID controller thread(s)
    	
    	// wheel speeds
    	wsp = new WheelSpeedPID();
    	wsp.init();
    	wsp.setDebugEnabled(true);
    	wspThread = new Thread(wsp);

    	//TODO Display publisher thread(s)
    	//TODO Discrete controller thread(s)
    

    	//TODO start threads here -- is order important ?
    	
    	// Start reference threads
    	werThread.start();
    	wsp.setEnabled(true);
    	jsrThread.start();
    	jsr.setEnabled(true);

    	// Start kinematic calculation thread
    	mdThread.start();
    	md.setEnabled(true);
    	
    	// Start PID threads
    	wspThread.start();
    	wsp.setEnabled(true);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	//TODO refresh rate is established here... may not be necessary with threads.
    	getWatchdog().feed();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        //TODO refresh rate is established here... may not be necessary with threads.
    	getWatchdog().feed();
    }
    
    public void disabledInit(){

    	// Send messages.
    	PrintDebugStatement.sendAllToStation();
    	
    	// Turn off reference data readers
    	wer.setEnabled(false);
    	jsr.setEnabled(false);

    	// Turn off kinematics
    	md.setEnabled(false);

    	// Turn off PID
    	wsp.setEnabled(false);
    }
}
