package org.usfirst.ihs1318;

import org.usfirst.ihs1318.kinematics.MecanumDrive;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;

import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.message.PrintDebugStatement;
import org.usfirst.ihs1318.pid.WheelSpeedPID;
import org.usfirst.ihs1318.reference.CameraReader;
import org.usfirst.ihs1318.reference.GyroReader;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.reference.WheelEncoderReader;
import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.TimerUtil;
import org.usfirst.ihs1318.shared.data.MotorSpeed;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class SystemTestBotCamera extends IterativeRobot {

	//camera
	CameraReader cr;
	private DashboardOutput dash;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	//TODO create and run threads
    	
    	//TODO reference data thread(s)
    	
    	System.out.println("SystemTestBotCamera.robotInit()");
    	TimerUtil.setStartTime();
    	//camera
    	cr = new CameraReader();
    	cr.init();
    	// Start reference threads
    	dash = new DashboardOutput();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	//TODO refresh rate is established here... may not be necessary with threads.
    	getWatchdog().feed();
    }
    
    public void teleopInit(){
    	System.out.println("Camera Test Bot");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	getWatchdog().feed();
    	// Reference data
    	try {
			cr.readCameraImage();
		} catch (NIVisionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AxisCameraException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dash.sendData();
    }
    
    public void teleopContinuous() {
    	
    }
    
    public void disabledInit(){

    	// Send messages.
//    	PrintDebugStatement.sendAllToStation();
    	System.out.println("SystemTestBot.disabledInit()");
    	//gr.printStoredTheta();
    	
    }
}
