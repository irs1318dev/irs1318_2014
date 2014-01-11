package org.usfirst.ihs1318.autonomous;

public class RobotProcess {
	public static final int RAISE_ARM = 0;
	public static final int EXTEND_WRIST = 1;
	public static final int FOLLOW_LINE = 2;
	public static final int STABILIZE_PAUSE = 3;
	public static final int FINAL_PLACEMENT = 4;
	public static final int RELEASE_FIRST_LOWER_ARM = 5;
	public static final int RELEASE_OPEN_CLAW = 6;
	public static final int RELEASE_LOWER_ARM = 7;
	public static final int RELEASE_BACK_UP = 8;
	public static final int FINISHED = 9;

	private int state = RAISE_ARM;
	private boolean working;
	
	public void nextState() {
		if (FINISHED==state) return;
		state ++;
	}
	
	public int getState() {
		return state;
	}
	
	public boolean isFinished() {
		return FINISHED==state;
	}
	
	
	public boolean isWorking() {
		return working;
	}
	
	public void setWorking(boolean working) {
		this.working = working;
	}

	public void reset() {
		state = 0;
		
	}
	
	
}
