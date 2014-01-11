package org.usfirst.ihs1318.discrete;

import org.usfirst.ihs1318.kinematics.ClawKinematics;
import org.usfirst.ihs1318.kinematics.WristKinematics;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.data.ButtonValues;

//Checked for NPEs. Uses lazy getters for objects.
public class ArmController implements Runnable{
	boolean enabled;
	WristController wrist;
	ClawController claw;
	WristKinematics wristKin;
	ClawKinematics clawKin;

	ButtonValues buttons;
	
	public ArmController(){
	}
	public void init(){
		wrist = new WristController();
		wrist.init();
		claw = new ClawController();
		claw.init();
		wristKin = new WristKinematics();
		wristKin.init();
		clawKin = new ClawKinematics();
	}
	
	/**
	 * Use this only for Multi-threaded. If you run this on the single-threaded bot, you will run into some problems.
	 */
	public void run() {
			if (isEnabled()) {
//				getTargetHeight();
				
				getClawKin().calculateClawKinematics();
				getWristKin().calculateWristValues();

				getClaw().setAsCurrent();
				getWrist().setAsCurrent();
				//wrist.setAsCurrent(buttonPanel);
				//implement motor send/read speeds here
			}		
	}
	
	/**
	 * retrieves third 
	 * retrieves array of booleans from the button panel
	 */
	public void getTargetHeight(){
		synchronized(ReferenceData.getInstance()){
			ReferenceData.getInstance().getButtonValues().copyValues(getButtons());
			//this line will be implemented when button panel is installed
			//buttonPanel = getButtons().getButtonValue(ButtonRef., button);
		}
	}
	
	private ButtonValues getButtons() {
		if(buttons == null) {
			buttons = new ButtonValues();
		}
		return buttons;
	}
	/**
	 * sets the solenoid configuration for the given height
	 */
	// TODO: what is this supposed to do?
	public void setConfig(){
		
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public ClawKinematics getClawKin() {
		if(clawKin == null){
			clawKin = new ClawKinematics();
			//no init method.
		}
		return clawKin;
	}
	public WristKinematics getWristKin() {
		if(wristKin == null){
			wristKin = new WristKinematics();
			wristKin.init();
		}
		return wristKin;
	}
	public ClawController getClaw() {
		if(claw == null){
			claw = new ClawController();
			claw.init();
		}
		return claw;
	}
	public WristController getWrist() {
		if(wrist == null) {
			wrist = new WristController();
			wrist.init();
		}
		return wrist;
	}
	public void setWrist(WristController wrist) {
		this.wrist = wrist;
	}
	public void setWristKin(WristKinematics wristKin) {
		this.wristKin = wristKin;
	}
	public void setClawKin(ClawKinematics clawKin) {
		this.clawKin = clawKin;
	}
	public void setClaw(ClawController claw) {
		this.claw = claw;
	}
}
