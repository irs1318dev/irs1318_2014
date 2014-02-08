package irs2014.timmer;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Utility;
import irs2014.components.RobotComponentBase;

public class TimeMeasureRunner extends RobotComponentBase{

	private long intervalStart = 0;
	private long currentTime;
	private long counter = 0;
	private long totalTime = 0;
	private long interval;
	
	public void teleopPeriodi(){
		if(intervalStart == 0){
			intervalStart = Utility.getFPGATime();
			return;
		}
		currentTime = Utility.getFPGATime();
		interval = currentTime - intervalStart;
		totalTime += interval;
		counter++;
	}
	
	public void disabledInit(){
		System.out.println("Average Cycle Time: " + (totalTime/counter));
	}
	
}
