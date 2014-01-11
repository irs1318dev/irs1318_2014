package org.usfirst.ihs1318.autonomous;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.data.InputVelocity;
import org.usfirst.ihs1318.shared.data.WheelMotorEncoderTicks;

//Checked for NPEs. Uses lazy getters.
public class WheelDistanceCalculator {
	private WheelMotorEncoderTicks ticksInitial;
	private WheelMotorEncoderTicks ticksCurrent;
	private WheelMotorEncoderTicks changeInTicks;
	private WheelMotorEncoderTicks finishedTicks;
	
	private boolean started;
	private boolean finished;

	public void init() {
		ticksInitial = new WheelMotorEncoderTicks();
		ticksCurrent = new WheelMotorEncoderTicks();
		changeInTicks = new WheelMotorEncoderTicks();
		finishedTicks = new WheelMotorEncoderTicks();
	}
	
	/**
	 * Reads the current ticks from the ReferenceData encoder values. Sets the start flag to true and the finished flag to false.
	 */
	public void markInitialTicks(){
		started = true;
		finished = false;
		synchronized(ReferenceData.getInstance()){
			ReferenceData.getInstance().getWheelEncoderTicks().copyValues(getTicksInitial());
		}
		
		synchronized (KinematicData.getInstance()) {
			KinematicData.getInstance().getChangeInTicks().copyValues(changeInTicks);
		}
		
		finishedTicks = new WheelMotorEncoderTicks();
		ticksInitial.copyValues(finishedTicks);
		finishedTicks.add(changeInTicks);
	}
	
	/**
	 * Calculates the number of ticks necessary to move back the distance that
	 * the robot needs to move to deploy its arm. Should be called in a loop as it
	 * compares the current ticks to the initial ticks.
	 */
	public void calculateTicks(){
		updateTicksCurrent();
		finished = checkTicks();
		sendVelocities();
	}
	
	/**
	 * Writes a joystick backward to the ReferenceData.
	 * 
	 * @param stop Whether or not the desiredTicks has been reached
	 */
	private void sendVelocities() {
		InputVelocity joystickValue = new InputVelocity();
		if(finished){
			joystickValue.setAll(0, 0, 0, 0);
		}
		else{
			joystickValue.setAll(0, -.2, 0, 0);
		}
		
		synchronized(KinematicData.getInstance()){
			joystickValue.copyValues(ReferenceData.getInstance().getInputVelocity());
		}
	}
	private boolean checkTicks() {		
		//a bigger negative number is smaller than a smaller one.
		//if the current ticks pass the finished ones, they will less than
		//the finished ones.
		return finishedTicks.isGreaterThan(ticksCurrent);		
	}
	
	private void updateTicksCurrent() {
		synchronized(ReferenceData.getInstance()){
			ReferenceData.getInstance().getWheelEncoderTicks().copyValues(getTicksCurrent());
		}		
	}
	
	public boolean isFinished() {
		return finished;
	}
	public boolean isStarted() {
		return started;
	}
	public void setStarted(boolean started) {
		this.started = started;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public WheelMotorEncoderTicks getTicksInitial() {
		if(ticksInitial == null) {
			ticksInitial = new WheelMotorEncoderTicks();
		}
		return ticksInitial;
	}

	public void setTicksInitial(WheelMotorEncoderTicks ticksInitial) {
		this.ticksInitial = ticksInitial;
	}

	public WheelMotorEncoderTicks getTicksCurrent() {
		if(ticksCurrent == null){
			ticksCurrent = new WheelMotorEncoderTicks();
		}
		return ticksCurrent;
	}

	public void setTicksCurrent(WheelMotorEncoderTicks ticksCurrent) {
		this.ticksCurrent = ticksCurrent;
	}

	
}
