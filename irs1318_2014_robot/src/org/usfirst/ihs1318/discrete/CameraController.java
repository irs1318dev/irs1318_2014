package org.usfirst.ihs1318.discrete;
import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ConnectionRef;


import edu.wpi.first.wpilibj.Servo;

//Checked for NPEs. Uses lazy getters.
public class CameraController {
	Servo servo = null; //TODO check with electronics on the number
	boolean direction; //true = forward, false = backward

	public CameraController(){
	}
	public void init(){
		direction = false;
		servo = new Servo(ConnectionRef.SERVO_SLOT, ConnectionRef.SERVO_CHANNEL);
		servo.setBounds(254, 0, 0,0 , 2);
		servo.setAngle(0);
	}
	public void getDirection(){
		synchronized(ReferenceData.getInstance()){
			synchronized(KinematicData.getInstance()){
				direction = KinematicData.getInstance().getServoDirection().getState();
			}
		}
		setDirection(direction);
	}
	public void setDirection(boolean state){
		if (state) {
			getServo().setAngle(170);
		}
		else
			getServo().setAngle(0);
	}
	
	public Servo getServo() {
		if (servo == null) {
			servo = new Servo(ConnectionRef.SERVO_SLOT, ConnectionRef.SERVO_CHANNEL);
			servo.setBounds(254, 0, 0,0 , 2);
			servo.setAngle(0);
		}
		return servo;
	}
}

