package irs2014;

public interface IterativeRobotRunnable {
	/**
	 * Initializes any robot-related resources this runner may have.
	 * This may include things like Jaguar controllers, Joystick objects,
	 * accelerometers, etc.
	 * 
	 */
	public void initResources();
	
	/**
	 * Runs a single iteration of loop. Can be called in IterativeRobot class.
	 */
	public void teleopOnce();
	
	public void autonomousOnce();
	
	public void disabled();
	
}
