package org.usfirst.ihs1318.discrete;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.constants.ConnectionRef;
import org.usfirst.ihs1318.shared.data.WristValues;

import edu.wpi.first.wpilibj.Solenoid;

//Checked for NPEs. Uses lazy getters.
public class WristController {
	private Solenoid distalWristOpen;
	private Solenoid distalWristClosed;
	private Solenoid proximalWristOpen;
	private Solenoid proximalWristClosed;
	private WristValues value;
	
	public WristController() {
	}
	public void init(){
		distalWristOpen = new Solenoid(ConnectionRef.SOLENOID_SLOT, 
				ConnectionRef.SOLENOID_DISTAL_WRIST_OPEN_CHANNEL);
		distalWristClosed = new Solenoid(ConnectionRef.SOLENOID_SLOT,
				ConnectionRef.SOLENOID_DISTAL_WRIST_CLOSED_CHANNEL);
		proximalWristOpen = new Solenoid(ConnectionRef.SOLENOID_SLOT, 
				ConnectionRef.SOLENOID_PROXIMAL_WRIST_OPEN_CHANNEL);
		proximalWristClosed = new Solenoid(ConnectionRef.SOLENOID_SLOT,
				ConnectionRef.SOLENOID_PROXIMAL_WRIST_CLOSED_CHANNEL);
		value = new WristValues();
	}

	public void setdistalWristOpen(boolean state) {
		getDistalWristOpen().set(state);
	}

	public void setdistalWristClosed(boolean state) {
		getDistalWristClosed().set(state);
	}


	public void setproximalWristOpen(boolean state) {
		getProximalWristOpen().set(state);
	}

	public void setProximalWristClosed(boolean state) {
		getProximalWristClosed().set(state);
	}

	public void setAsCurrent	() {
		synchronized (KinematicData.getInstance()) {
			KinematicData.getInstance().getWristValue().copyValues(getValue());
		}
		setState(getValue().isProximalWristBool(), getValue().isDistalWristBool());
	}
	
	/**
	 * Open and close the distal and proximal wrists. True should open, false should close.
	 * 
	 * @param proximalWristBool boolean open.
	 * @param distalWristBool boolean open.
	 */
	public void setState( boolean proximalWristBool, boolean distalWristBool) {
		//what state this is..we need to find out..
		setdistalWristOpen(distalWristBool);
		setdistalWristClosed(!distalWristBool);
		setproximalWristOpen(proximalWristBool);
		setProximalWristClosed(!proximalWristBool);
	}


	public Solenoid getDistalWristOpen() {
		if(distalWristOpen == null) {
			distalWristOpen = new Solenoid(ConnectionRef.SOLENOID_SLOT, 
					ConnectionRef.SOLENOID_DISTAL_WRIST_OPEN_CHANNEL);
		}
		return distalWristOpen;
	}

	public void setDistalWristOpen(Solenoid distalWristOpen) {
		this.distalWristOpen = distalWristOpen;
	}

	public Solenoid getDistalWristClosed() {
		if(distalWristClosed == null) {
			distalWristClosed = new Solenoid(ConnectionRef.SOLENOID_SLOT,
					ConnectionRef.SOLENOID_DISTAL_WRIST_CLOSED_CHANNEL);
		}
		return distalWristClosed;
	}

	public void setDistalWristClosed(Solenoid distalWristClosed) {
		this.distalWristClosed = distalWristClosed;
	}

	public Solenoid getProximalWristOpen() {
		if(proximalWristOpen == null){
			proximalWristOpen = new Solenoid(ConnectionRef.SOLENOID_SLOT, 
					ConnectionRef.SOLENOID_PROXIMAL_WRIST_OPEN_CHANNEL);
		}
		return proximalWristOpen;
	}

	public void setProximalWristOpen(Solenoid proximalWristOpen) {
		this.proximalWristOpen = proximalWristOpen;
	}

	public Solenoid getProximalWristClosed() {
		return proximalWristClosed;
	}

	public void setProximalWristClosed(Solenoid proximalWristClosed) {
		if(proximalWristClosed == null) {
			proximalWristClosed = new Solenoid(ConnectionRef.SOLENOID_SLOT,
					ConnectionRef.SOLENOID_PROXIMAL_WRIST_CLOSED_CHANNEL);
		}
		this.proximalWristClosed = proximalWristClosed;
	}
	public WristValues getValue() {
		if(value == null) {
			value = new WristValues();
		}
		return value;
	}
	public void setValue(WristValues value) {
		this.value = value;
	}
	

}
