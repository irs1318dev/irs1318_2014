package org.usfirst.ihs1318;

import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.pid.ArmPID;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.shared.TimerUtil;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

public class SystemTestBotSimpleArm extends IterativeRobot{
	Jaguar arm = new Jaguar(6,3);
	JoystickReader j = new JoystickReader();//y is reversed
	ArmPID armPID;
	DashboardOutput dash;
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	TimerUtil.setStartTime();
    	
    	armPID = new ArmPID();
    	armPID.init();
    	
    	dash = new DashboardOutput();
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
     //   arm.set(-j.getY());
        armPID.readEncoder();
        dash.sendData();
    }
    
    public void disabledPeriodic() {
        
    }
}
