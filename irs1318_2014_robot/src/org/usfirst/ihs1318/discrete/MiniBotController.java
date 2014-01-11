package org.usfirst.ihs1318.discrete;

import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.constants.ConnectionRef;

import edu.wpi.first.wpilibj.Solenoid;

//Checked for NPEs. Uses lazy getters.
public class MiniBotController {
	
	private Solenoid miniBotExtended; 
	private Solenoid miniBotRetracted;
	private boolean deployValue;

	public MiniBotController(){

	}
	public void init(){
		miniBotExtended = new Solenoid(ConnectionRef.SOLENOID_SLOT, ConnectionRef.SOLENOID_MINI_EXTENDED);
		miniBotRetracted = new Solenoid(ConnectionRef.SOLENOID_SLOT, ConnectionRef.SOLENOID_MINI_RETRACTED);
		setMiniBotDeploySolenoids(false);
	}
	public void getDeployValues(){
		synchronized(KinematicData.getInstance()){
			deployValue = KinematicData.getInstance().getMiniBotValue().getState();
		}
		setMiniBotDeploySolenoids(deployValue);
	}
	public void setMiniBotDeploySolenoids(boolean state){
		getMiniBotExtended().set(state);
		getMiniBotRetracted().set(!state);
	}
	
	
	
	public Solenoid getMiniBotExtended() {
		if(miniBotExtended == null){
			miniBotExtended = new Solenoid(ConnectionRef.SOLENOID_SLOT, ConnectionRef.SOLENOID_MINI_EXTENDED);
		}
		return miniBotExtended;
	}
	public void setMiniBotExtended(Solenoid miniBotExtended) {
		this.miniBotExtended = miniBotExtended;
	}
	public Solenoid getMiniBotRetracted() {
		if(miniBotRetracted == null) {
			miniBotRetracted = new Solenoid(ConnectionRef.SOLENOID_SLOT, ConnectionRef.SOLENOID_MINI_RETRACTED);
		}
		return miniBotRetracted;
	}
	public void setMiniBotRetracted(Solenoid miniBotRetracted) {
		this.miniBotRetracted = miniBotRetracted;
	}
	public boolean isDeployValue() {
		return deployValue;
	}
	public void setDeployValue(boolean deployValue) {
		this.deployValue = deployValue;
	}
}
