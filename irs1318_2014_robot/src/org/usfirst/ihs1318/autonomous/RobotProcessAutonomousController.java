package org.usfirst.ihs1318.autonomous;

import java.util.TimerTask;

import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.TimerUtil;
import org.usfirst.ihs1318.shared.constants.ButtonRef;
import org.usfirst.ihs1318.shared.data.InputVelocity;
import org.usfirst.ihs1318.shared.data.LineSensorInput;
import org.usfirst.ihs1318.shared.data.Orientation;
import org.usfirst.ihs1318.shared.data.WheelMotorEncoderRates;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

//Checked for NPEs. Uses lazy getters.
public class RobotProcessAutonomousController implements Runnable {
	private static final long ARM_THRESHOLD = 2500;//milliseconds
	private static final long START_MOVEMENT = 2000;//milliseconds
	// START_MOVEMENT < ARM_THRESHHOLD (see the RAISE_ARM state
	private static final double PLACEMENT_DISTANCE = 16 * 12 +12; // inches
	private static final long GYRO_RESET_THRESHOLD = 1000;
	private StraightLineTracker straightTracker;

	private RobotProcess robotProcess;
	
	public void init() {
		robotProcess = new RobotProcess();
		
		straightTracker = new StraightLineTracker();
		straightTracker.init();
	}


	private long startTime = 0;
	boolean startTimeSet = false;

	boolean skipLineTracking = false;
	
	public void run() {
		Watchdog.getInstance().feed();
		long now = 0;
		long delayTime = 0;
		double ARM_DIRECTION = -1.0;
		
		switch (robotProcess.getState()) {
		case RobotProcess.RAISE_ARM:
			Watchdog.getInstance().feed();
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 5000;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					double armValue = 0.0;
					if(now - startTime < ARM_THRESHOLD) {
						armValue = .98;
					if(now - startTime < GYRO_RESET_THRESHOLD)	{
						synchronized (ReferenceData.getInstance()) {
							ReferenceData.getInstance().getButtonValues().
							getButtons()[ButtonRef.GYRO_RESET_JS][ButtonRef.GYRO_RESET_BUTTON] = true;
						}
					} else {
						ReferenceData.getInstance().getButtonValues().
						getButtons()[ButtonRef.GYRO_RESET_JS][ButtonRef.GYRO_RESET_BUTTON] = false;
					}

						if (now - startTime >= START_MOVEMENT) {
							if(!straightTracker.isFinished()) {
				 				synchronized(ReferenceData.getInstance()) {
				 					straightTracker.trackLine(PLACEMENT_DISTANCE).copyValues(ReferenceData.getInstance().getInputVelocity());
				 				}
				 			}
						}
					}else{
						armValue = .75;
						ReferenceData.getInstance().getButtonValues()
						   .getButtons()[ButtonRef.JOYSTICK_ARM][2] = true;
						if(!straightTracker.isFinished()) {
			 				synchronized(ReferenceData.getInstance()) {
			 					straightTracker.trackLine(PLACEMENT_DISTANCE)
			 					.copyValues(ReferenceData.getInstance().getInputVelocity());
			 				}
			 			}
					}
					ReferenceData.getInstance().getArmInput().setDesiredJy(armValue*ARM_DIRECTION);
				} else {
					ReferenceData.getInstance().getArmInput().setDesiredJy(0.0);
					startTimeSet = false;
					robotProcess.nextState();
					robotProcess.nextState();//skip extend wrist--already accounted for
				}
			}
			break;
//		case RobotProcess.EXTEND_WRIST:
//			Watchdog.getInstance().feed();
//			now = System.currentTimeMillis();
//			if (!startTimeSet) {
//				startTime= now;
//				startTimeSet = true;
//			}
//			delayTime = 500;//milli-seconds
//			synchronized(ReferenceData.getInstance()){
//				if (now - startTime < delayTime) {
//					// push wrist button
//
//				} else {
//					startTimeSet = false;
//					straightTracker.reset();
//					robotProcess.nextState();
//				}
//			}
//			break;
		case RobotProcess.FOLLOW_LINE:
			if (skipLineTracking) {
				robotProcess.nextState();//skips process
				break;
			}
			Watchdog.getInstance().feed();
/*
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 25000;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					Timer.delay(0.01);
				} else {
					startTimeSet = false;
					robotProcess.nextState();
				}
			}
*/
 			if(!straightTracker.isFinished()) {
 				synchronized(ReferenceData.getInstance()) {
 					straightTracker.trackLine(PLACEMENT_DISTANCE).copyValues(ReferenceData.getInstance().getInputVelocity());
 				}
 			}
			else {
				straightTracker.reset();
				robotProcess.nextState();
			}		
			break;
		case RobotProcess.STABILIZE_PAUSE:
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 1000;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					Timer.delay(0.01);
				} else {
					startTimeSet = false;
					robotProcess.nextState();
				}
			}
			break;
		case RobotProcess.FINAL_PLACEMENT:
			skipLineTracking = true;
			if (skipLineTracking) {
				robotProcess.nextState();//skip this stem
				robotProcess.nextState();//skip the first arm lower, jump straight to opening claw
				skipLineTracking = false; //makes this repeatedly callable, should move to next process anyway
				break;
			}
/*
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 100;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					Timer.delay(0.01);
				} else {
					startTimeSet = false;
					robotProcess.nextState();
				}
			}
*/
			
			//this code should not get called.
 			if(!straightTracker.isFinished()) {
 				synchronized(ReferenceData.getInstance()) {
 					straightTracker.trackLine(10).copyValues(ReferenceData.getInstance().getInputVelocity());
 				}
 			}
			else{
				straightTracker.reset();
				robotProcess.nextState();
				robotProcess.nextState();//skip the first arm lower, jump straight to opening claw
			}
			break;
//		case RobotProcess.RELEASE_FIRST_LOWER_ARM:
//			now = System.currentTimeMillis();
//			if (!startTimeSet) {
//				startTime= now;
//				startTimeSet = true;
//			}
//			delayTime = 100;//milli-seconds
//			synchronized(ReferenceData.getInstance()){
//				if (now - startTime < delayTime) {
//					ReferenceData.getInstance().getArmInput().setDesiredJy(-0.2*ARM_DIRECTION);
//				} else {
//					ReferenceData.getInstance().getArmInput().setDesiredJy(0.0);
//					startTimeSet = false;
//					robotProcess.nextState();
//				}
//			}
//			break;
		case RobotProcess.RELEASE_OPEN_CLAW:
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				skipLineTracking = false;
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 500;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					ReferenceData.getInstance().getButtonValues()
					   .getButtons()[ButtonRef.JOYSTICK_ARM][ButtonRef.CLAW_BUTTON] = true;
				} else {
					startTimeSet = false;
					robotProcess.nextState();
				}
			}
			break;
		case RobotProcess.RELEASE_LOWER_ARM:
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 900;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					ReferenceData.getInstance().getArmInput().setDesiredJy(-0.5*ARM_DIRECTION);
				} else {
					ReferenceData.getInstance().getArmInput().setDesiredJy(0.0);//stop moving arm
					startTimeSet = false;
					robotProcess.nextState();
				}
			}
			break;
		case RobotProcess.RELEASE_BACK_UP:
			now = System.currentTimeMillis();
			if (!startTimeSet) {
				startTime= now;
				startTimeSet = true;
			}
			delayTime = 2000;//milli-seconds
			synchronized(ReferenceData.getInstance()){
				if (now - startTime < delayTime) {
					ReferenceData.getInstance().getInputVelocity()
					.setDesiredJy(-0.6);//Jy velocity stick is inverted
				} else {
					ReferenceData.getInstance().getInputVelocity().setDesiredJy(0.0);
					startTimeSet = false;
					robotProcess.nextState();
				}
			}
			break;
		case RobotProcess.FINISHED:
			Timer.delay(0.01);
			break;
		}
	}

	public boolean isAutonomousFinished() {
		return robotProcess.isFinished();
	}

	public void reset() {
		robotProcess.reset();
		straightTracker.reset();
	}

}
