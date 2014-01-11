package org.usfirst.ihs1318;

import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.reference.CameraReader;
import org.usfirst.ihs1318.shared.TimerUtil;
import org.usfirst.ihs1318.vision.CameraHandler;
import org.usfirst.ihs1318.vision.GridDetector;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class SystemTestBotVision extends IterativeRobot {

	CameraReader reader = new CameraReader();
	CameraHandler g = new CameraHandler();
	DashboardOutput dash;
	
    public void robotInit() {
    	TimerUtil.setStartTime();
    	System.out.println("Initiating");
    	dash = new DashboardOutput();
    }
    
    public void autonomousInit(){
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    
    }
    
   

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	try {
    		reader.readCameraImage();
			//g.testParticleReport();
		} catch (AxisCameraException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NIVisionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dash.sendData();
    }
    
    public void periodicInit() {
    	System.out.println("init");
    	reader.init();
    }
    
    
    public void disabledInit(){
    }
    
}
