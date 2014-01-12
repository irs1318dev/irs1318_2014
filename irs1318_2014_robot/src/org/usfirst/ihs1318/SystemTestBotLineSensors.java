package org.usfirst.ihs1318;

import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.reference.LineSensorReader;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.TimerUtil;
import org.usfirst.ihs1318.shared.data.LineSensorInput;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * Reads the line sensors and displays the results.
 * 
 * @author Patrick
 *
 */
public class SystemTestBotLineSensors extends IterativeRobot {
	private LineSensorReader reader;
	private DashboardOutput dash;
	
	public void robotInit() {
		System.out.println("RobotInit()");
		TimerUtil.setStartTime();
		reader = new LineSensorReader();
		dash = new DashboardOutput();
	}

	
	public void disabledInit() {
		// TODO Auto-generated method stub
		super.disabledInit();
	}

	
	public void autonomousInit() {
		// TODO Auto-generated method stub
		super.autonomousInit();
	}

	
	public void teleopInit() {
		System.out.println("Line Sensor Test Bot");
	}

	private String previousSensorState = "";
	public void teleopPeriodic() {
		reader.readSensors();
		int sensorState;
		synchronized(ReferenceData.getInstance()){
			sensorState = ReferenceData.getInstance().getLineSensorValues().getLineSensorState();
		}
		
		String currentSensorState;
		switch(sensorState) {
		case LineSensorInput.ALL_ON:
			currentSensorState = "All on";
			break;
		case LineSensorInput.CENTER_ONLY_ON:
			currentSensorState = "Center only on";
			break;
		case LineSensorInput.LEFT_AND_CENTER_ON:
			currentSensorState = "Left and Center on";
			break;
		case LineSensorInput.LEFT_ONLY_ON:
			currentSensorState = "Left only on";
			break;
		case LineSensorInput.NONE_ON:
			currentSensorState = "None on";
			break;
		case LineSensorInput.RIGHT_AND_CENTER_ON:
			currentSensorState = "Right and center on";
			break;
		case LineSensorInput.RIGHT_AND_LEFT_ON:
			currentSensorState = "Right and left only on";
			break;
		case LineSensorInput.RIGHT_ONLY_ON:
			currentSensorState = "Right only on";
			break;
			default:
				currentSensorState = "ERROR-NO READING";
		}
		
		if(!currentSensorState.equals(previousSensorState)) {
			System.out.println(currentSensorState);
			currentSensorState = previousSensorState;
		}
		
		dash.sendData();
				
	}

	
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub
		super.autonomousPeriodic();
	}

	
	public void teleopPeriodic1() {
		// TODO Auto-generated method stub
		super.teleopPeriodic();
	}

	
	public void disabledContinuous() {
		// TODO Auto-generated method stub
		super.disabledPeriodic();
	}

	
	public void autonomousContinuous() {
		// TODO Auto-generated method stub
		super.autonomousPeriodic();
	}

	
	public void teleopContinuous() {
		// TODO Auto-generated method stub
		super.teleopPeriodic();
	}

}
