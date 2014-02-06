package irs2014.generalData;


import irs2014.collector.CollectorData;

import irs2014.driveTrainTank.DriveTrainData;
import irs2014.shooter.PressureSensorData;
import irs2014.shooter.ShooterData;
import irs2014.userInputDevices.UserInputData;

public class ReferenceData {
	
	private static ReferenceData data;
	
	private UserInputData userInputData;
	private DriveTrainData driveTrainData;
	
	private CollectorData collectorData;
	
	private PressureSensorData pressureSensorData;
	private ShooterData shooterData;	
	private EncoderHistory encoderHistory;
	
	private ReferenceData(){
	}
	
	public static ReferenceData getInstance(){
		if(data == null){
			data = new ReferenceData();
		}
		return data;
	}
	
	public UserInputData getUserInputData(){
		if(userInputData == null){
			userInputData = new UserInputData();
		}
		return userInputData;
	}
	
	public DriveTrainData getDriveTrainData(){
		if(driveTrainData == null){
			driveTrainData = new DriveTrainData();
		}
		return driveTrainData;
	}
	
	public CollectorData getCollectorData(){
		if(collectorData == null){
				collectorData = new CollectorData();
		}
		return collectorData;
	}

	public PressureSensorData getPressureSensorData() {
		if(pressureSensorData == null) {
			pressureSensorData = new PressureSensorData();
		}
		return pressureSensorData;
	}
	
	public ShooterData getShooterData() {
		if(shooterData == null) {
			shooterData = new ShooterData();
		}
		return shooterData; 
	}
	
	public EncoderHistory getEncoderHistory()
	{
		if(encoderHistory == null)
			encoderHistory = new EncoderHistory();
		return encoderHistory;
	}
}
