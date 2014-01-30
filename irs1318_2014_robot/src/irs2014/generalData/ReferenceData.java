package irs2014.generalData;

import irs2014.collector.CollectorData;
import irs2014.driveTrainTank.DriveTrainData;
import irs2014.userInputDevices.UserInputData;

public class ReferenceData {
	
	private static ReferenceData data;
	
	private UserInputData userInputData;
	private DriveTrainData driveTrainData;
	
	private CollectorData collectorData;
	
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

}
