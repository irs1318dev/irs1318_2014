package irs2014.timer;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Utility;
import irs2014.components.RobotComponentBase;

public class TimeMeasureRunner extends RobotComponentBase{

	private long intervalStart = 0;
	private long currentTime;
	private long counter = 0;
	private long totalTime = 0;
	private long interval;
	private long maxTime;
	private long minTime;
	
	private long externalLoop = 0;
	private long externalTotal = 0;
	private long externalMax;
	private long externalMin;
	private long externalInterval = 0;
	
	public void teleopInit(){
		intervalStart = Utility.getFPGATime();
		externalLoop = intervalStart;
		maxTime = 0;
		minTime = Long.MAX_VALUE;
		externalMax = 0;
		externalMin = Long.MAX_VALUE;
	}
	
	public void teleopPeriodic(){
		currentTime = Utility.getFPGATime();
		externalInterval = currentTime - externalLoop;
		interval = currentTime - intervalStart;
		totalTime += interval;
		externalTotal += externalInterval;
		counter++;
		if(interval > maxTime){
			maxTime = interval;
		}
		if(interval < minTime){
			minTime = interval;
		}
		if(externalInterval > externalMax){
			externalMax = externalInterval;
		}
		if(externalInterval < externalMin){
			externalMin = externalInterval;
		}
		intervalStart = currentTime;
		externalLoop = Utility.getFPGATime();
	}
	
	public void disabledInit(){
		if(counter != 0){
			System.out.println("Average Cycle Time: " + (totalTime/counter));
		}
		System.out.println("Maximum Cycle Time: " + maxTime);
		System.out.println("Minimum Cycle Time: " + minTime);
		if(counter != 0){
			System.out.println("Average External Time: " + (externalTotal/counter));
		}
		System.out.println("Maximum External Time: " + externalMax);
		System.out.println("Minimum External Time: " + externalMin);
	}
	
}
