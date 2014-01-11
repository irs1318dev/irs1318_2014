package org.usfirst.ihs1318.discrete;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.constants.ConnectionRef;
import org.usfirst.ihs1318.shared.data.ClawValue;

import edu.wpi.first.wpilibj.Solenoid;

//Checked for NPEs. Uses lazy getters.
public class ClawController {

	private Solenoid clawClosed;
	private Solenoid clawOpen;
	private ClawValue value = new ClawValue();

	public ClawController(){
	}
	public void init(){
		clawClosed = new Solenoid(ConnectionRef.SOLENOID_SLOT,
				ConnectionRef.SOLENOID_CLAW_CLOSED_CHANNEL);
		clawOpen = new Solenoid(ConnectionRef.SOLENOID_SLOT, 
				ConnectionRef.SOLENOID_CONTROLLER_CLAW_OPEN_CHANNEL);
		getClawClosed().set(true);
		getClawOpen().set(false);
	}
	
	public void setAsCurrent() {
		synchronized (KinematicData.getInstance()) {
			KinematicData.getInstance().getClawValue().copyValues(getValue());
		}
		setState(getValue().getState());
	}
	
	public void setState(boolean state){
		//true closes claw
		getClawClosed().set(state);
		getClawOpen().set(!state);
	}
	
	public Solenoid getClawOpen() {
		if(clawOpen == null) {
			clawOpen = new Solenoid(ConnectionRef.SOLENOID_SLOT, 
					ConnectionRef.SOLENOID_CONTROLLER_CLAW_OPEN_CHANNEL);
		}
		return clawOpen;
	}
	public void setClawOpen(Solenoid claw) {
		this.clawOpen = claw;
	}
	
	public Solenoid getClawClosed(){
		if(clawClosed == null) {
			clawClosed = new Solenoid(ConnectionRef.SOLENOID_SLOT,
					ConnectionRef.SOLENOID_CLAW_CLOSED_CHANNEL);
		}
		return clawClosed;
	}
	public void setClawClosed(Solenoid claw){
		this.clawClosed = claw;
	}
	public ClawValue getValue() {
		if(value == null){
			value = new ClawValue();
		}
		return value;
	}
	public void setValue(ClawValue value) {
		this.value = value;
	}
}
