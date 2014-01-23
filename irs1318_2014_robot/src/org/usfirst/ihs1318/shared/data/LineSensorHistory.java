package org.usfirst.ihs1318.shared.data;

import org.usfirst.ihs1318.reference.TimerIHS;




public class LineSensorHistory {
	private final int LENGTH = 20;
	private LineSensorInput[] sensorHistory = new LineSensorInput[LENGTH];
	private double[] timerHistory = new double[LENGTH];
	private int writes = 0; //the number of times that the history has been written to
	private int pointer = 0;	
	private TimerIHS timer = new TimerIHS();
	private double lastTime;
	private double currentTime;
	
	private boolean testModeEnabled = true;

	
	public LineSensorHistory() {
		initTimer();
	}
	
	public void write(LineSensorInput input) {
		if(input == null)
			throw new RuntimeException("input should never be null."); 
		
		if(!input.equals(sensorHistory[pointer])){
			int desiredWriteLocation;
			
			if (writes == 0) {
				desiredWriteLocation = 0;
				initTimer();
			}
			else {
			desiredWriteLocation = writes % LENGTH;	
			
			if (sensorHistory[desiredWriteLocation] == null)
				sensorHistory[desiredWriteLocation] = new LineSensorInput();

			input.copyValuesTo(sensorHistory[desiredWriteLocation]);
			writeTimer(desiredWriteLocation);
			pointer = desiredWriteLocation;
			incrementWrites();
			}
		}
	}
	
	private void writeTimer(int desiredWriteLocation) {
		timer.getTime();
		currentTime = timer.getTime();
		
		double changeInTime = currentTime - lastTime;
		timerHistory[desiredWriteLocation - 1] = changeInTime;
		
		currentTime = lastTime;
	}
	
	private void initTimer() {
		if(testModeEnabled){
			timer.setTestModeEnabled(true);
			timer.setTestTime(0.0);
			lastTime = 0;
		}
		else {
		timer.getTimer().reset();
		timer.getTimer().start();
		lastTime = 0;
		}
	}

	private void incrementWrites() {
		writes++;
	}
	
	public LineSensorInput getMostRecent() {
		return sensorHistory[pointer];
	}
	
	public LineSensorInput getLast() {
		return sensorHistory[pointer + 1];
	}
	
	/**
	 * @param n th recent, 1 should return most recent
	 * @return the LineSensorInput stored in the nth most recent slot.
	 */
	
	public LineSensorInput getNthRecent(int n) {
		if(n > LENGTH)
			throw new RuntimeException(n + " is greater than " + LENGTH + ". Only slot numbers less than or equal to " + LENGTH + " exist.");
		if(n == 0)
			throw new RuntimeException(n + " was zero. Pass nonzero numbers"); //If you want the most recent value, enter a 1		
		if(n < 0)
			throw new RuntimeException(n + " was invalid. No negative numbers");
		
		int desiredPositionBack = pointer - n + 1; //plus one b/c 1 = most recent
		int nthRecentPointer = pointer + desiredPositionBack;
		
		while(nthRecentPointer < 0)
			nthRecentPointer += LENGTH;
		return sensorHistory[nthRecentPointer % LENGTH];
	}
	/**
	 * 
	 * @param nth most recent, 1 should return the very last value
	 * @return the LineSensorInput stored in the nth last slot
	 */
	
	public LineSensorInput getNthLast(int n) {
		if(n > LENGTH)
			throw new RuntimeException(n + " is greater than " + LENGTH + ". Only slot numbers less than or equal to " + LENGTH + " exist.");
		if(n == 0)
			throw new RuntimeException(n + " was zero. Pass nonzero numbers"); //If you want the most recent value, enter a 1		
		if(n < 0)
			throw new RuntimeException(n + " was invalid. No negative numbers");
		
		int nthLastPointer = (pointer + n) % LENGTH;	
		return sensorHistory[nthLastPointer];
	}
		
	public int getLENGTH() {
		return LENGTH;
	}

	public int getWrites() {
		return writes;
	}

	public void setWrites(int writes) {
		this.writes = writes;
	}

	public int getPointer() {
		return pointer;
	}

	public void copyTo(LineSensorHistory dest) {
		for(int i = 0; i < LENGTH; i++){
			if(this.sensorHistory[i] != null)
				this.sensorHistory[i].copyValuesTo(dest.sensorHistory[i]);
		}
		dest.writes = this.writes;
		dest.pointer = this.pointer;
	}


	public double getPreviousTime() {
		return timerHistory[pointer - 1];
	}

	public double getCurrentTime() {
		return timer.getTime() - lastTime;
	}

	public double getNthRecentTime(int n) {
		if(n > LENGTH)
			throw new RuntimeException(n + " is greater than " + LENGTH + ". Only slot numbers less than or equal to " + LENGTH + " exist.");
		if(n == 0)
			throw new RuntimeException(n + " was zero. Pass nonzero numbers"); //If you want the most recent value, enter a 1		
		if(n < 0)
			throw new RuntimeException(n + " was invalid. No negative numbers");
		
		int desiredPositionBack = pointer - n + 1; //plus one b/c 1 = most recent
		int nthRecentPointer = pointer + desiredPositionBack;
		
		while(nthRecentPointer < 0)
			nthRecentPointer += LENGTH;
		return timerHistory[nthRecentPointer];
	}

	public TimerIHS getTimer() {
		return timer;
	}

	public void setTimer(TimerIHS timer) {
		this.timer = timer;
	}

	
	
	
	
	

}
