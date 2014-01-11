package org.usfirst.ihs1318.shared;

import org.usfirst.ihs1318.shared.data.AirCompressorValue;
import org.usfirst.ihs1318.shared.data.ArmData;
import org.usfirst.ihs1318.shared.data.CameraFlipValue;
import org.usfirst.ihs1318.shared.data.ClawValue;
import org.usfirst.ihs1318.shared.data.ManualGyroValue;
import org.usfirst.ihs1318.shared.data.MiniBotValue;
import org.usfirst.ihs1318.shared.data.MotorSpeed;
import org.usfirst.ihs1318.shared.data.TubeRequestValue;
import org.usfirst.ihs1318.shared.data.Distance;
import org.usfirst.ihs1318.shared.data.WheelMotorEncoderTicks;
import org.usfirst.ihs1318.shared.data.WristValues;

public class KinematicData {
	private MotorSpeed wmSpeed;
	private ArmData desiredArmData;
	private MiniBotValue miniBot;
	private ClawValue claw;
	private WristValues wrist;
	private TubeRequestValue tubeRequest;
	private AirCompressorValue airCompressor;
	private CameraFlipValue cameraFlip;
	private WheelMotorEncoderTicks changeInTicks;
	private ManualGyroValue manualGyroValue;
	private Distance verticalDistance;
	
	public Distance getDistance() {
		if(verticalDistance == null)
			verticalDistance = new Distance();
		return verticalDistance;
	}
	public void setVerticalDistance(Distance verticalDistance) {
		this.verticalDistance = verticalDistance;
	}
	private static KinematicData instance;

	public static KinematicData getInstance() {
		if (instance == null) {
			instance = new KinematicData();
		}
		return instance;
	}
	public CameraFlipValue getServoDirection() {
		if(cameraFlip == null){
			cameraFlip = new CameraFlipValue();
		}
		return cameraFlip;
	}
	public ArmData getArmData() {
		if(desiredArmData == null){
			desiredArmData = new ArmData();
		}
		return desiredArmData;
	}

	public void setArmData(ArmData armData) {
		this.desiredArmData = armData;
	}

	/**
	 * Wheel motor speeds
	 * @return
	 */
	public MotorSpeed getWmSpeed() {
		if (wmSpeed == null) {
			wmSpeed = new MotorSpeed();
		}
		return wmSpeed;
	}

	public void setWmSpeed(MotorSpeed wheelSpeed) {
		this.wmSpeed = wheelSpeed;
	}
	public void setMiniBotValue(MiniBotValue miniBot){
		this.miniBot = miniBot;
	}
	public MiniBotValue getMiniBotValue(){
		if (miniBot == null) {
			miniBot = new MiniBotValue();
		}
		return miniBot;
	}
	
	public void setClawValue(ClawValue claw){
		this.claw = claw;
	}
	public ClawValue getClawValue(){
		if (claw == null) {
			claw = new ClawValue();
		}
		return claw;
	}
	
	public void setWristValue(WristValues wrist){
		this.wrist = wrist;
	}
	public WristValues getWristValue(){
		if(wrist == null){
			wrist = new WristValues();
		}
		return wrist;
	}
	
	public void setTubeRequestValue(TubeRequestValue tube){
		this.tubeRequest = tube;
	}
	public TubeRequestValue getTubeRequestValue(){
		if(tubeRequest == null){
			tubeRequest = new TubeRequestValue();
		}
		return tubeRequest;
	}
	public AirCompressorValue getAirCompressor() {
		if (airCompressor == null) {
			airCompressor = new AirCompressorValue();
		}
		return airCompressor;
	}

	public void setAirCompressor(AirCompressorValue airCompressor) {
		this.airCompressor = airCompressor;
	}

	public WheelMotorEncoderTicks getChangeInTicks() {
		if(changeInTicks == null){
			changeInTicks = new WheelMotorEncoderTicks();
		}
		return changeInTicks;
	}

	public void setManualGyroValue(ManualGyroValue manualGyroValue) {
		this.manualGyroValue = manualGyroValue;
	}
	public ManualGyroValue getManualGyroValue() {
		if(manualGyroValue == null){
			return manualGyroValue = new ManualGyroValue();
		}
		return manualGyroValue;
	}
}
