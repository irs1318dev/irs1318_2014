package irs2014.components;

import irs2014.RobotComponent;


/**
 * Class that provides stub implementations for the RobotComponent interface. This is meant to 
 * help serve as a buffer between the interface and any changes that may occur in the WPILib. Extend
 * this class to build a robot component.
 * @author violette
 *
 */
public class RobotComponentBase implements RobotComponent {

	public void robotInit() {

	}

	public void disabledInit() {

	}

	public void autonomousInit() {

	}

	public void teleopInit() {

	}

	public void disabledPeriodic() {

	}

	public void autonomousPeriodic() {
		teleopPeriodic();
	}

	/**
	 * The main method that should be overridden. NOTE: <code>autonomousPeriodic</code> defaults to 
	 * calling this method. If your class should not be active in autonomous mode, include the following
	 * line in your class:<br/><br/>
	 * <code>public void autonomousPeridodic(){} </code>
	 */
	public void teleopPeriodic() {
		
	}

	public void disabledContinuous() {

	}

	public void autonomousContinuous() {

	}

	public void teleopContinuous() {

	}

}