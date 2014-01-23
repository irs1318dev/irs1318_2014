package org.usfirst.ihs1318;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class SystemTestBotF3 extends IterativeRobot {
	Jaguar leftMotor = new Jaguar(1);
	Jaguar rightMotor = new Jaguar(2);
	Jaguar top = new Jaguar(3);
	Jaguar bot = new Jaguar(4);
	Joystick leftWheel = new Joystick(1);
	Joystick rightWheel = new Joystick(2);
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
	System.out.println("hello");
	System.out.println("world");
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
	leftMotor.set(-leftWheel.getY());
	rightMotor.set(rightWheel.getY());
    if(leftWheel.getRawButton(3)){
    	top.set(1.0);
    }else if(leftWheel.getRawButton(2)){
    	bot.set(0);
    }else{
    	top.set(0);
    }
    if(rightWheel.getRawButton(3)){
    	bot.set(1.0);
    }else if(rightWheel.getRawButton(2)){
    	bot.set(-1.0);
    }else{
    	bot.set(0);
    }
    
    
    }

    }


