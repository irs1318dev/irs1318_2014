package irs2014;

public interface IIterativeRobot {
	/**
	 * Initializes any robot-related resources this runner may have.
	 * This may include things like Jaguar controllers, Joystick objects,
	 * accelerometers, etc.
	 * 
	 */
	public void robotInit();
	public void disabledInit();
	
	public void teleopInit();
	public void teleopPeriodic();
	public void teleopDisabled();
	
	public void autonomousInit();
	public void autonomousPeriodic();
	public void autonomousDisabled();
		
}
