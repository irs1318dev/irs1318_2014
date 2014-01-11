package org.usfirst.ihs1318;

import org.usfirst.ihs1318.message.DashboardOutput;
import org.usfirst.ihs1318.reference.JoystickReader;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.TimerUtil;
import org.usfirst.ihs1318.shared.constants.ButtonRef;

import edu.wpi.first.wpilibj.IterativeRobot;

public class SystemTestBotButtons extends IterativeRobot {
	private JoystickReader joysticks = new JoystickReader();
	private DashboardOutput dash;

	public void robotInit() {
		System.out.println("RobotInit()");
		TimerUtil.setStartTime();
		joysticks = new JoystickReader();
		joysticks.init();
		dash = new DashboardOutput();
	}


	public void disabledInit() {

	}


	public void autonomousInit() {

	}


	public void teleopInit() {
		System.out.println("Button Test Bot");
	}


	public void disabledPeriodic() {

	}


	public void autonomousPeriodic() {

	}


	public void teleopPeriodic() {
		joysticks.readJoysticks();
		synchronized (ReferenceData.getInstance()){
			boolean[][] buttons  = ReferenceData.getInstance().getButtonValues().getButtons();
			for(int i = 0; i < ButtonRef.NUM_JOYSTICKS; i++){
				for(int j = 0; j < ButtonRef.NUM_BUTTONS; j++) {
					if(buttons[i][j])
						System.out.println("Joystick " + i + ", Button " + j + "was read.");
				}
			}
		}
		
		dash.sendData();
	}


	public void disabledContinuous() {

	}


	public void autonomousContinuous() {

	}


	public void teleopContinuous() {

	}
	
}
