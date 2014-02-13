package irs2014.generalData;


import irs2014.collector.CollectorData;
import irs2014.dipSwitch.DipSwitchData;
import irs2014.driveTrainTank.DriveTrainData;
import irs2014.lineSensor.LineSensorData;
import irs2014.shooter.PressureSensorData;
import irs2014.shooter.PressureSensorTimerData;
import irs2014.shooter.ShooterData;
import irs2014.userInputDevices.UserInputData;

public class ReferenceData {
	
	private static ReferenceData data;
	
	private UserInputData userInputData;
	private DriveTrainData driveTrainData;
	
	private CollectorData collectorData;
	
	private PressureSensorData pressureSensorData;
	private ShooterData shooterData;	
	private DipSwitchData dipSwitchData;
	private EncoderState encoderState; 
	private PressureSensorTimerData pressureSensorTimerData;
	private LineSensorData lineSensorData; 
	
	private ReferenceData(){
	}
	
	public static ReferenceData getInstance(){
		if(data == null){
			data = new ReferenceData();
		}
		return data;
	}
	
	public DipSwitchData getDipSwitchData() {
		if (dipSwitchData == null){
			dipSwitchData = new DipSwitchData();
		}
		return dipSwitchData;
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
	
	public PressureSensorTimerData getPressureSensorTimerData() {
		if(pressureSensorTimerData == null) {
			pressureSensorTimerData = new PressureSensorTimerData(); 
		}
		return pressureSensorTimerData; 
	}
	
	public ShooterData getShooterData() {
		if(shooterData == null) {
			shooterData = new ShooterData();
		}
		return shooterData; 
	}
	
	public LineSensorData getLineSensorData() {
		if (lineSensorData == null) {
			lineSensorData = new LineSensorData();
		}
		return lineSensorData; 
	}
	
	public EncoderState getEncoderState()
	{
		if(encoderState == null)
			encoderState = new EncoderState();
		return encoderState;
	}
}
