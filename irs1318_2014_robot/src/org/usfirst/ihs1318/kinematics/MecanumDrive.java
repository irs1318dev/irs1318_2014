package org.usfirst.ihs1318.kinematics;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.data.InputVelocity;
import org.usfirst.ihs1318.shared.data.MotorSpeed;

//Checked for NPE possiblilities. There are only primitives in this class so not a problem.
public class MecanumDrive implements Runnable {

	
	public static final double ROTATION_RATE = 2 * Math.PI / 5; // (2/5) * pi is ideal
	//TODO What are the units for BASE_A and BASE_B ? .27 and .33 seem small
	public static final double BASE_A = 0.2794;
	public static final double BASE_B = 0.3302;
	public static final double SCALE_JX = 1.0;
	public static final double SCALE_JY = 1.0;
	public static final double SCALE_JRY = 1.0;
	public static final double SCALE_THETA = 1.0;
	//this is used only in teleop
	public static final double OFFSET_THETA = Math.PI;
	public static boolean enableOffsetTheta;
	
	public static final double EPLISON = 0.01;
	
	public double a = BASE_A;
	public double b = BASE_B;
	
	private boolean enabled;
	
	public void init() {
		// nothing to init.
	}
	
	public void run() {
		while(true) {
			if (isEnabled()) {
				calculateWheelSpeeds();
			}
			try {
				// TODO: fix this warning
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// notify will wake up early.
			}
		}
	}
	
	
	public void  calculateWheelSpeeds() {
		double jX,jY, jRY, omega,theta,a1, a2;
		synchronized(ReferenceData.getInstance()) {
			InputVelocity iv = ReferenceData.getInstance().getInputVelocity(); 
			jX = iv.getDesiredJx() * SCALE_JX;
			jY = iv.getDesiredJy() * SCALE_JY;
			jRY = iv.getDesiredJRY() * SCALE_JRY;
			double L = 2*BASE_A; 

			//axis is now dependent on direction joy stick points. The axis of rotation extends
			//from the front axle of the robot when jRY > 0 and extends from the back axle when jRY < 0
			
			a1 = BASE_A + jRY*L; // whatever we add here
			a2 = BASE_A - jRY*L; // we take away here (Jry can be negative)
			
			double outOfBody = 0;
			if (jRY>EPLISON) {
				outOfBody = L/2;
			} else if (jRY<-EPLISON) {
				outOfBody = -L/2;
			}
			a1 += outOfBody;
			a2 -= outOfBody;
			// a1,a2 may go outside of the chassis by BASE_A on either side.
		
			// convert joystick input to radians per second.
			omega = ReferenceData.getInstance().getInputVelocity().getDesiredW() * ROTATION_RATE;
			theta = ReferenceData.getInstance().getOrientation().getTheta() * SCALE_THETA;
			if(isOffsetThetaEnabled()){
				theta += OFFSET_THETA;
			}
		}

		// transform from field to robot coordinates
		double[] values = {0, 0, 0, 0};
		double vX = jX * Math.cos(theta) - jY * Math.sin(theta);
		double vY = jX * Math.sin(theta) + jY * Math.cos(theta);
		
		// determine wheel speeds based on the mecanum drive kinematics
		values[0] = vY - vX + omega * (a1 + BASE_B);
        values[1] = vY + vX - omega * (a1 + BASE_B);
        values[2] = vY - vX - omega * (a2 + BASE_B);
        values[3] = vY + vX + omega * (a2 + BASE_B);
        
		synchronized(KinematicData.getInstance()) {
			MotorSpeed ws = KinematicData.getInstance().getWmSpeed();
			ws.setRf(values[0]);
			ws.setLf(values[1]);
			ws.setLr(values[2]);
			ws.setRr(values[3]);
		}		
	}
	
	public boolean isOffsetThetaEnabled(){
		return enableOffsetTheta;
	}
	//set to true in normal teleop, false in autonomous
	public static void setEnableOffsetTheta(boolean enabled){
		enableOffsetTheta = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
