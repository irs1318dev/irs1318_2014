/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.ihs1318.pid;

import edu.wpi.first.wpilibj.*;

/**
 *
 * @author violette
 */

//Checked for NPE. Uses lazy getters.
public class MecanumWheel
{
	private Jaguar wheelMotor;
	private int motorSlot;
	private int motorChannel;
	
    private boolean invertMotor;
    private double motorSpeed; // in a different unit

    public MecanumWheel(int motorSlot, int motorChannel, boolean invertMotor)
    {
    	setMotorSlot(motorSlot);
    	setMotorChannel(motorChannel);
    	this.invertMotor = invertMotor;
    }
    
    public void init() {
    	wheelMotor = new Jaguar(motorSlot, motorChannel);
    }

    private void setMotorChannel(int motorChannel) {
		this.motorChannel = motorChannel;
	}

	private void setMotorSlot(int motorSlot) {
		this.motorSlot = motorSlot;		
	}

	public void set(double speed)
    {
	// temp change to test 1/25/2011
	if (invertMotor)
	    getWheelMotor().set(-speed);
	else
	    getWheelMotor().set(speed);

    }

    public Jaguar getWheelMotor() {
    	if(wheelMotor == null)
    		wheelMotor = new Jaguar(motorSlot, motorChannel);
		return wheelMotor;
	}

	////////////////////////////////////////GETTERS AND SETTERS/////////////////////////////////////////////////
    public boolean isInvertMotor()
    {
	return invertMotor;
    }

    public void setInvertMotor(boolean invertMotor)
    {
	this.invertMotor = invertMotor;
    }

    public double getMotorSpeed()
    {
	return motorSpeed;
    }

    public void setMotorSpeed(double motorSpeed)
    {
	this.motorSpeed = motorSpeed;
	//do conversions here
	set(motorSpeed);
    }
}
