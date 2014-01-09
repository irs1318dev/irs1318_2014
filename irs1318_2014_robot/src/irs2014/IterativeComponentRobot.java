package irs2014;

import edu.wpi.first.wpilibj.IterativeRobot;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public abstract class IterativeComponentRobot extends IterativeRobot {
	private BotVector components;
	private int iterator = 0;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	components = currentRobotComponents();
    	for(iterator = 0; iterator < components.size(); iterator++) {
    		components.get(iterator).robotInit();
    	}
    }
    
    public abstract BotVector currentRobotComponents();

	
	public void disabledInit() {
    	for(iterator = 0; iterator < components.size(); iterator++) {
    		components.get(iterator).disabledInit();
    	}
	}

	
	public void autonomousInit() {
    	for(iterator = 0; iterator < components.size(); iterator++) {
    		components.get(iterator).autonomousInit();
    	}
	}

	
	public void teleopInit() {
    	for(iterator = 0; iterator < components.size(); iterator++) {
    		components.get(iterator).teleopInit();
    	}
	}

	
	public void disabledPeriodic() {
    	for(iterator = 0; iterator < components.size(); iterator++) {
    		components.get(iterator).disabledPeriodic();
    	}
	}

	
	public void autonomousPeriodic() {
    	for(iterator = 0; iterator < components.size(); iterator++) {
    		components.get(iterator).autonomousPeriodic();
    	}
	}

	
	public void teleopPeriodic() {
    	for(iterator = 0; iterator < components.size(); iterator++) {
    		components.get(iterator).teleopPeriodic();
    	}
	}

	
	public void disabledContinuous() {
    	for(iterator = 0; iterator < components.size(); iterator++) {
    		components.get(iterator).disabledContinuous();
    	}
	}

	
	public void autonomousContinuous() {
    	for(iterator = 0; iterator < components.size(); iterator++) {
    		components.get(iterator).autonomousContinuous();
    	}
	}

	
	public void teleopContinuous() {
    	for(iterator = 0; iterator < components.size(); iterator++) {
    		components.get(iterator).teleopContinuous();
    	}
	}
}