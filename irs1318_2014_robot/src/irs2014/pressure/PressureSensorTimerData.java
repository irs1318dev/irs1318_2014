package irs2014.pressure;

public class PressureSensorTimerData {
	private boolean shouldStartTimer;
	private boolean shouldStopTimer;
	private boolean shouldRestartTimer;
	private long timerStartTime; 
	private long timerTime; 
	
	public boolean getShouldStartTimer() {
		return shouldStartTimer; 
	}
	
	public boolean getShouldStopTimer() {
		return shouldStopTimer;
	}
	
	public long getTimerStartTime() {
		return timerStartTime; 
	}
	
	public long getTimerTime() {
		return timerTime;
	}
	
	public void setShouldStartTimer(boolean shouldStartTimer) {
		this.shouldStartTimer = shouldStartTimer;
	}
	
	public void setShouldStopTimer(boolean shouldStopTimer) {
		this.shouldStopTimer = shouldStopTimer;
	}
	
	public void setTimerStartTime(long timerStartTime) {
		this.timerStartTime = timerStartTime;
	}
	
	public void setTimerTime(long timerTime) {
		this.timerTime = timerTime; 
	}

	public boolean getShouldRestartTimer() {
		return shouldRestartTimer;
	}

	public void setShouldRestartTimer(boolean shouldRestartTimer) {
		this.shouldRestartTimer = shouldRestartTimer;
	}
}
