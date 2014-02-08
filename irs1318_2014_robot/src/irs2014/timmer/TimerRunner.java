package irs2014.timmer;

import edu.wpi.first.wpilibj.Timer;
import irs2014.components.RobotComponentBase;
import irs2014.networkTable.IRSTable;
import irs2014.networkTable.NTRef;

public class TimerRunner extends RobotComponentBase{
	
	private double timeInterval = .003; //in seconds with millisecond revolution
	private double intervalStart = 0;
	private double currentTime;
	
	public void teleopPeriodic(){
		if(intervalStart == 0){
			intervalStart = Timer.getFPGATimestamp();
			return;
		}
		currentTime = Timer.getFPGATimestamp();
		if((currentTime - intervalStart) < timeInterval){
			//try{
				Timer.delay(currentTime - timeInterval);
			//}catch(){}	TODO: figure out exception type 
		}else{
			IRSTable.putString(NTRef.Timer_Message, ("interval too short by: " + (currentTime - timeInterval)));
		}
		intervalStart = currentTime;
	}

}
