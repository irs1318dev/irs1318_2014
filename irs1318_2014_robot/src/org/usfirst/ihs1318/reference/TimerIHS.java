package org.usfirst.ihs1318.reference;

import edu.wpi.first.wpilibj.Timer;

public class TimerIHS {
	private Timer timer;
	
	private boolean testModeEnabled;
	private double testTime;
	
	
	/**
	 * 
	 * @return The current time in seconds. If in test mode, returns the test time.
	 */
	public double getTime() {
		if(testModeEnabled) {
			return testTime;
		}
		return getTimer().get()/1000000;
	}

	public Timer getTimer() {
		if(timer == null){
			timer = new Timer();
		}
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public boolean isTestModeEnabled() {
		return testModeEnabled;
	}

	public void setTestModeEnabled(boolean testModeEnabled) {
		this.testModeEnabled = testModeEnabled;
	}

	public double getTestTime() {
		return testTime;
	}

	public void setTestTime(double testTime) {
		this.testTime = testTime;
	}

}
