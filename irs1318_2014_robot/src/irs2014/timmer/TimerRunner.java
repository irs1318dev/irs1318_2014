package irs2014.timmer;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Utility;
import irs2014.components.RobotComponentBase;
import irs2014.networkTable.IRSTable;
import irs2014.networkTable.NTRef;

public class TimerRunner extends RobotComponentBase{
	
	private long timeInterval = 10000; //micro seconds
	private long intervalStart = 0;
	private long currentTime;
	
	public void teleopPeriodic(){
		if(intervalStart == 0){
			intervalStart = Utility.getFPGATime();
			return;
		}
		currentTime = Utility.getFPGATime();
		if((currentTime - intervalStart) < timeInterval){
			double remainingTime = (timeInterval - (currentTime - intervalStart))/ 1000000.0;	//microseconds to seconds
			Timer.delay(remainingTime);
		}else{
			IRSTable.putString(NTRef.Timer_Message, ("interval too short by: " + (currentTime - timeInterval)));
		}
		intervalStart = currentTime;
	}

}
