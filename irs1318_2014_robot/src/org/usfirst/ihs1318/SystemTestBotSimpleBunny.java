package org.usfirst.ihs1318;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;

public class SystemTestBotSimpleBunny extends IterativeRobot {
	Jaguar frontTurn = new Jaguar(2); //
	Jaguar backMotor = new Jaguar(4);
	Joystick motorcycle = new Joystick(1);
	Jaguar backTurn = new Jaguar(3); //
	Jaguar frontMotor = new Jaguar(1);
	Joystick motorcycle2 = new Joystick(2);
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
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
    	frontTurn.set(motorcycle.getX());
    	backMotor.set(-motorcycle.getY());
    	
    	backTurn.set(motorcycle2.getX());
    	frontMotor.set(-motorcycle2.getY());
    }
}
