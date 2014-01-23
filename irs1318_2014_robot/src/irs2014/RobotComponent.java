package irs2014;


/**
 * The IterativeRobot interface. Implement as needed.
 * @author violette
 *
 */
public interface RobotComponent {
    /* ----------- Overridable initialization code -----------------*/

    /**
     * Robot-wide initialization code should go here.
     *
     * Users should override this method for default Robot-wide initialization which will
     * be called when the robot is first powered on.  It will be called exactly 1 time.
     */
    public void robotInit();

    /**
     * Initialization code for disabled mode should go here.
     *
     * Users should override this method for initialization code which will be called each time
     * the robot enters disabled mode.
     */
    public void disabledInit();

    /**
     * Initialization code for autonomous mode should go here.
     *
     * Users should override this method for initialization code which will be called each time
     * the robot enters autonomous mode.
     */
    public void autonomousInit();

    /**
     * Initialization code for teleop mode should go here.
     *
     * Users should override this method for initialization code which will be called each time
     * the robot enters teleop mode.
     */
    public void teleopInit();
    /**
     * Periodic code for disabled mode should go here.
     *
     * Users should override this method for code which will be called periodically at a regular
     * rate while the robot is in disabled mode.
     */
    public void disabledPeriodic();

    /**
     * Periodic code for autonomous mode should go here.
     *
     * Users should override this method for code which will be called periodically at a regular
     * rate while the robot is in autonomous mode.
     */
    public void autonomousPeriodic();

    /**
     * Periodic code for teleop mode should go here.
     *
     * Users should override this method for code which will be called periodically at a regular
     * rate while the robot is in teleop mode.
     */
    public void teleopPeriodic();

    /* ----------- Overridable "continuous" code -----------------*/
    /**
     * Continuous code for disabled mode should go here.
     *
     * Users should override this method for code which will be called repeatedly as frequently
     * as possible while the robot is in disabled mode.
     */
    public void disabledContinuous();

    /**
     * Continuous code for autonomous mode should go here.
     *
     * Users should override this method for code which will be called repeatedly as frequently
     * as possible while the robot is in autonomous mode.
     */
    void autonomousContinuous();
    /**
     * Continuous code for teleop mode should go here.
     *
     * Users should override this method for code which will be called repeatedly as frequently
     * as possible while the robot is in teleop mode.
     */
    void teleopContinuous();

}