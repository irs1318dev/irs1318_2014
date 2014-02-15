package irs2014.timmer;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Utility;
import irs2014.components.RobotComponentBase;
import irs2014.networkTable.IRSTable;
import irs2014.networkTable.NTRef;

public class TimerRunner extends RobotComponentBase{
	
	private long timeInterval = 30000; //micro seconds
	private long intervalStart = 0;
	private long currentTime;
	private long difference;
//	private int numDelays = 0;
//	private double totalDelay = 0;
//	private long timeBefore = 0;
//	private long timeAfter = 0;
	
	public void teleopInit(){
		intervalStart = Utility.getFPGATime();
	}
	
	public void teleopPeriodic(){
//		if(intervalStart == 0){
//			intervalStart = Utility.getFPGATime();
//			return;
//		}
		currentTime = Utility.getFPGATime();
		difference = currentTime - intervalStart;
		if(difference < timeInterval){
			double remainingTime = (timeInterval - difference)/ 1000000.0;	//microseconds to seconds
//			timeBefore = Utility.getFPGATime();
			Timer.delay(remainingTime);
//			timeAfter = Utility.getFPGATime();
//			System.out.println("timeBefore = " + timeBefore + ",  timeAfter = " + timeAfter + ",  waitTime = " + (timeAfter - timeBefore));
//			numDelays++;
//			totalDelay += remainingTime;
//			System.out.println("remainingTime = " + remainingTime + ",  timeInterval = " + timeInterval + ",  difference = " + difference);
		}else{
			IRSTable.putString(NTRef.Timer_Message, ("interval too short by: " + difference));
		}
		intervalStart = currentTime;
	}
	
	public void disabledInit(){
//		System.out.println("Number of delays: " + numDelays);
//		System.out.println("Total time delayed: " + totalDelay);
	}

}
