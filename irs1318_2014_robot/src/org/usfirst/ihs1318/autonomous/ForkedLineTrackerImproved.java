package org.usfirst.ihs1318.autonomous;

import org.usfirst.ihs1318.pid.PIDGain;
import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.TimerUtil;
import org.usfirst.ihs1318.shared.data.Distance;
import org.usfirst.ihs1318.shared.data.InputVelocity;
import org.usfirst.ihs1318.shared.data.LineSensorInput;
import org.usfirst.ihs1318.shared.data.Orientation;


public class ForkedLineTrackerImproved {
	private static final int Y_DISTANCE_TO_FORK = 162;//inches
	private static final int Y_DISTANCE_TO_CHANGE_BACK = 240;//inches
	private static final int Y_DISTANCE_TO_HANG = 240 - 12;//inches. need to be 2 feet back to hang tube.
	private Distance distance;
	
	private InputVelocity desiredMotion;
	private LineSensorInput currentSensors;
	private Orientation angle;
	private double translationAngle;
	//private TimerIHS timer;
	private boolean haveReachedFork = false;
	private double timeReachedFork = -1;
	private boolean hasPassedFork = false;
	private int desiredLine;
	private boolean finished;
	private boolean forkedTrackingFinished;
	private double startTime;



	public void init() {
		desiredMotion = new InputVelocity();
		currentSensors = new LineSensorInput();
		angle = new Orientation();
		startTime = TimerUtil.getSeconds();
		setTranslationAngle(0.0);// always aim straight ahead
		distance = new Distance();
	}
	
	public InputVelocity trackLine(int desiredLine) {
		checkTicks();
		getDesiredMotion().setAll(setHorizontalComponent(), setVerticalComponent(), 0, setRotation());
		
		return getDesiredMotion();
	}

	private InputVelocity getDesiredMotion() {
		if(desiredMotion == null) {
			desiredMotion = new InputVelocity();
		}
		return desiredMotion;
	}

	private double setVerticalComponent() {
		PIDGain gains = new PIDGain();
		gains.setAll(.01, 0, 0);	
		
		synchronized(KinematicData.getInstance()){
			KinematicData.getInstance().getDistance().copyValuesTo(distance);
		}
		
		if(!haveReachedFork)
			return gains.getKp() * (Y_DISTANCE_TO_FORK - distance.getY());
		else if(!forkedTrackingFinished)
			return gains.getKp() * (Y_DISTANCE_TO_CHANGE_BACK - distance.getY());
		else
			return gains.getKp() * (Y_DISTANCE_TO_HANG - distance.getY());
	}

	private double setHorizontalComponent() {
		double big, medium, small;
		big = .5;
		medium = .3;
		small = .15;
		
		if(haveReachedFork && !hasPassedFork){
			return medium * getDirection();
		}
		
		if(desiredLine == AutonomousController.LINE_CENTER_RIGHT)
			return setHorizontalCompTrackingRight(small, medium, big);
		else if(desiredLine == AutonomousController.LINE_CENTER_LEFT)
			return setHorizontalCompTrackingLeft(small, medium, big);
		return 0.0;
	}

	private double setHorizontalCompTrackingLeft(double small, double medium, double big) {
		boolean forkedLine = haveReachedFork && !forkedTrackingFinished;
		if(forkedLine){
			switch(getCurrentSensors().getLineSensorState()) {
			case LineSensorInput.LEFT_AND_CENTER_ON:
				return Math.sin(Math.PI/6);
			case LineSensorInput.CENTER_ONLY_ON:
				return small * Math.sin(Math.PI/6);
			case LineSensorInput.RIGHT_ONLY_ON:
				return big * Math.sin(Math.PI /6);
			case LineSensorInput.RIGHT_AND_CENTER_ON:
				return medium * Math.sin(Math.PI / 6);
			case LineSensorInput.NONE_ON:
				return -medium * Math.sin(Math.PI);
			case LineSensorInput.LEFT_ONLY_ON:
				checkTicks();				
				default:
					return 0;				
			}
		}
		else{	
			switch(getCurrentSensors().getLineSensorState()) {
			case LineSensorInput.LEFT_AND_CENTER_ON:
				checkTicks();
			case LineSensorInput.LEFT_ONLY_ON:
				default:
					return 0;
			}
		}
	}

	private double setHorizontalCompTrackingRight(double small, double medium, double big) {
		boolean forkedLine = haveReachedFork && !forkedTrackingFinished;
		if(forkedLine){
			switch(getCurrentSensors().getLineSensorState()) {
			case LineSensorInput.RIGHT_AND_CENTER_ON:
				return Math.sin(Math.PI/6);
			case LineSensorInput.CENTER_ONLY_ON:
				return -small * Math.sin(Math.PI/6);
			case LineSensorInput.LEFT_ONLY_ON:
				return -big * Math.sin(Math.PI/6);
			case LineSensorInput.LEFT_AND_CENTER_ON:
				return -medium * Math.sin(Math.PI/6);
			case LineSensorInput.RIGHT_ONLY_ON:
				checkTicks();
				return 0;
				
				default:
					return 0;				
			}
		}
		else{	
			switch(getCurrentSensors().getLineSensorState()) {
			case LineSensorInput.RIGHT_AND_CENTER_ON:
				checkTicks();
			case LineSensorInput.RIGHT_ONLY_ON:
				return 0;
			}
		}
		return big;
	}
	
	private void checkTicks() {
		if(distance.getY() > Y_DISTANCE_TO_FORK)
			haveReachedFork = true;
		if(distance.getY() > Y_DISTANCE_TO_CHANGE_BACK)
			forkedTrackingFinished = true;
		if(distance.getY() < Y_DISTANCE_TO_HANG)
			finished = true;
			
	}

	/**
	 * The line values are from the driver's perspective. This assumes right is positive 
	 * (to go left from driver's perspective, robot
	 * needs to go right).
	 * @return +/- 1 depending on the direction the robot should move.
	 */

	private int getDirection() {
		int right = 1;
		int left = -1;
		if(desiredLine == AutonomousController.LINE_CENTER_LEFT)
			return right;
		return left;
	}

	private double setRotation() {
		double angleEpsilon = Math.PI/18; // 10 degrees
		if(Math.abs(getAngle().getTheta()) < angleEpsilon )
			return 0.0;
		return -getAngle().getTheta() /Math.PI/6;//theta should always be zero
	}

	public double getTranslationAngle() {
		return translationAngle;
	}

	public void setTranslationAngle(double translationAngle) {
		this.translationAngle = translationAngle;
	}


	public double getSensorState() {
		return getCurrentSensors().getLineSensorState();
	}	

	public LineSensorInput getCurrentSensors() {
		if(currentSensors == null) {
			currentSensors = new LineSensorInput();
		}
		return currentSensors;
	}

	public void setCurrentSensors(LineSensorInput currentSensors) {
		this.currentSensors = currentSensors;
	}
	
	public Orientation getAngle() {
		if(angle == null) {
			angle = new Orientation();
		}
		return angle;
	}

	public void setAngle(Orientation angle) {
		this.angle = angle;
	}

	public boolean hasReachedFork() {
		return haveReachedFork;
	}

	public boolean hasPassedFork() {
		return hasPassedFork;
	}

	public double getTimeReachedFork() {
		return timeReachedFork;
	}

	public void setTimeReachedFork(double timeReachedFork) {
		this.timeReachedFork = timeReachedFork;
	}

	public void setHaveReachedFork(boolean haveReachedFork) {
		this.haveReachedFork = haveReachedFork;
	}

	public void setHavePassedFork(boolean havePassedFork) {
		this.hasPassedFork = havePassedFork;
	}

	private void setDesiredLine(int desiredLine) {
		if(desiredLine == AutonomousController.LINE_CENTER_LEFT)
			this.desiredLine = desiredLine;
		else if(desiredLine == AutonomousController.LINE_CENTER_RIGHT) 
			this.desiredLine = desiredLine;
		else
			throw new RuntimeException(desiredLine + " is not a valid forked tracking line.");
	}

	public boolean isFinished() {
		return finished;
	}

}
