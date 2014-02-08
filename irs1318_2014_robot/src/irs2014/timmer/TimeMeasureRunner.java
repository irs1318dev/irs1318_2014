package irs2014.timmer;

import edu.wpi.first.wpilibj.Timer;
import irs2014.components.RobotComponentBase;

public class TimeMeasureRunner extends RobotComponentBase{

	private double intervalStart = 0;
	private double currentTime;
	private double counter = 0;
	private double totalTime = 0;
	private double interval;
	
	public void teleopPeriodi(){
		if(intervalStart == 0){
			intervalStart = Timer.getFPGATimestamp();
			return;
		}
		currentTime = Timer.getFPGATimestamp();
		interval = currentTime - intervalStart;
		totalTime += interval;
		counter++;
	}
	
	public void disabledInit(){
		System.out.println("Average Cycle Time: " + (totalTime/counter));
	}
	
}
