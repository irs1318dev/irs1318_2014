package irs2014.generalData;


import irs2014.collector.CollectorData;
import irs2014.dipSwitch.DipSwitchData;
import irs2014.driveTrainTank.DriveTrainData;
import irs2014.lineSensor.LineSensorData;
import irs2014.shooter.AngleData;
import irs2014.shooter.PressureSensorData;
import irs2014.shooter.PressureSensorTimerData;
import irs2014.shooter.ShooterData;
import irs2014.shooter2.ShooterData2;
import irs2014.userInputDevices.UserInputData;
import irs2014.userInputDevices2.UserInputData2;

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
	private AngleData angleData;
	
	private UserInputData2 userInputData2;
	private ShooterData2 shooterData2;
	
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
	
	public AngleData getAngleData()
	{
		if(angleData == null)
			angleData = new AngleData();
		return angleData;
	}
	
	public UserInputData2 getUserInputData2(){
		if(userInputData2 == null){
			userInputData2 = new UserInputData2();
		}
		return userInputData2;
	}
	
	public ShooterData2 getShooterData2(){
		if(shooterData2 == null){
			shooterData2 = new ShooterData2();
		}
		return shooterData2;
	}
}
