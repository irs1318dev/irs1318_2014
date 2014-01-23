package org.usfirst.ihs1318.shared;

import java.util.Timer;

public class TimerUtil {
	private static long startTime = 0;
	private static Timer timer;
	
	/**
	 * Use this to mark the start of competition.
	 */
	public static void setStartTime() {
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * To use this method, you must have intialized the start point.
	 * 
	 * @return The time the robot has been in competition (in seconds)
	 */
	public static double getSeconds() {
		long currentTime = System.currentTimeMillis();
		if(startTime == 0) {
			throw new RuntimeException("startTime never intialized");
		}
		return (double) (currentTime - startTime) / 1000.0;
	}
	
	/**
	 * To use this method, you must have initialized the start point.
	 * 
	 * @return The time that the robot has been in competition (in milliseconds)
	 */
	public static long getMilliseconds() {
		return System.currentTimeMillis() - startTime;
	}
	
	/**
	 * Use this to schedule TimerTasks, which might look something like..
	 * 	TimerUtil.getTimer().schedule(TimerTask taskToDo, long taskDelayInMillis)
	 * 
	 * @return The timer object inside of TimerUtil.
	 */
	
	public static Timer getTimer() {
		if(timer == null){
			timer = new Timer();
		}
		return timer;
	}

}
