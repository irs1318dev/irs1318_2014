package org.usfirst.ihs1318.pid;

import org.usfirst.ihs1318.shared.constants.ConnectionRef;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANJaguar.ControlMode;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

public class CANJaguarIHS1318 {
	private CANJaguar jaguar;
	
	public void init() {
//		try{
//		jaguar = new CANJaguar(ConnectionRef.ARM_MOTOR_CAN);
//		}
//		catch (CANTimeoutException e){		
//		}
			for (int i=0; i<64; i++) {
				try {
					jaguar = new CANJaguar(i);
				} catch (Exception e) {
					System.out.println("i="+i+", "+e.getMessage());
					e.printStackTrace();
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
			
	}

	public void changeMode(ControlMode controlMode) {
		try {
			getJaguar().changeControlMode(controlMode);
		} catch (CANTimeoutException e) {
		}
	}
	
	public void setX(double ouputValue) {
		try {
			getJaguar().setX(ouputValue);
		} catch (CANTimeoutException e) {
		}
	}
	
	public CANJaguar getJaguar() {
		if(jaguar == null) {
			try {
				jaguar = new CANJaguar(ConnectionRef.ARM_MOTOR_CAN);
			} catch (CANTimeoutException e) {
			}
		}
		return jaguar;
	}

	public void setJaguar(CANJaguar jaguar) {
		this.jaguar = jaguar;
	}
	
	

}
