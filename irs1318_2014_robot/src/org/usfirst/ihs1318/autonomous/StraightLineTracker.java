package org.usfirst.ihs1318.autonomous;

import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.EncoderRef;
import org.usfirst.ihs1318.shared.data.InputVelocity;
import org.usfirst.ihs1318.shared.data.LineSensorInput;
import org.usfirst.ihs1318.shared.data.Orientation;
import org.usfirst.ihs1318.shared.data.WheelMotorEncoderTicks;

public class StraightLineTracker {
	public static double driveTicks = EncoderRef.convertToWheelTicks(15 * 12);
	private static final double EPSILON = EncoderRef.convertToWheelTicks(3);
	private InputVelocity desiredMotion;
	private LineSensorInput currentSensors;
	private Orientation angle;
	
	private boolean setStartTicks = true;
	private WheelMotorEncoderTicks currentTicks = null;
	private WheelMotorEncoderTicks startTicks;
	private double translationAngle;
	private boolean finished;

	public void init() {
		desiredMotion = new InputVelocity();
		currentSensors = new LineSensorInput();
		angle = new Orientation();
		setTranslationAngle(0.0);// straight line always
		reset();
		
	}
	public InputVelocity trackLine(double inches) {

		// do nothing if finished... reset finished separately.
		if (finished) {
			getDesiredMotion().setAll(0, 0, 0, 0);
			return getDesiredMotion();
		}
		// first time through since reset, re-zero counters
		if(setStartTicks) {
			synchronized(ReferenceData.getInstance()) {
				ReferenceData.getInstance().getWheelEncoderTicks().copyValues(getStartTicks());
			}
			setStartTicks = false;
		}
		driveTicks = EncoderRef.convertToWheelTicks(inches);
		if (!finished) {
			getDesiredMotion().setAll(0, setVerticalComponent(), 0, 0);			
		}
		else {
			getDesiredMotion().setAll(0, 0, 0, 0);
		}
		
		return getDesiredMotion();
	}

	public void reset() {
		finished = false;
		setStartTicks = true;
		setStartTicks(null);
		
	}
	
	public InputVelocity getDesiredMotion() {
		if(desiredMotion == null) {
			desiredMotion = new InputVelocity();
		}
		return desiredMotion;
	}
	private double setVerticalComponent() {
		
		double maxPower = 1.0;
		double ticksRemaining = 0.0;
		synchronized(ReferenceData.getInstance()) {
			// perform component-wise updates on wheel encoder ticks
			ReferenceData.getInstance().getWheelEncoderTicks().copyValues(getCurrentTicks());
			// determine how much robot has moved, based on component-wise movements...
			// side-to-side and rotations should average out, leaving forward motion only
			//TODO check sign
			WheelMotorEncoderTicks ticksDriven = getCurrentTicks().subtract(getStartTicks());
			ticksRemaining = driveTicks - ticksDriven.getAverage();
		}
		
		if(ticksRemaining <= EPSILON) {
			finished = true;
			return 0;
		}
		if(ticksRemaining > driveTicks/2)
			return maxPower;
		if(ticksRemaining > driveTicks/4)
			return maxPower/2;
		if(ticksRemaining > driveTicks/8){
			return maxPower/5;
		}
		return maxPower/10;
	}

	private double setHorizontalComponent() {
		double big, medium, small;
		big = .5;
		medium = .3;
		small = .15;
				
		if(getCurrentSensors().getLineSensorState() == LineSensorInput.NONE_ON) {			
			if (getCurrentSensors().lastKnownLineOnLeft()) {
				return -big;
			}
			if (getCurrentSensors().lastKnownLineOnRight()) {
				return big;		
			}
		}

		if (getCurrentSensors().getLineSensorState() == LineSensorInput.RIGHT_ONLY_ON) {
			return medium;
		}

		if (getCurrentSensors().getLineSensorState() == LineSensorInput.LEFT_ONLY_ON) {
			return -medium;
		}
		
		if (getCurrentSensors().getLineSensorState() == LineSensorInput.RIGHT_AND_CENTER_ON) {
			return small;
		}

		if (getCurrentSensors().getLineSensorState() == LineSensorInput.LEFT_AND_CENTER_ON) {
			return -small;
		}

		return 0;
	}

	private double setRotation() {
		double angleEpsilon = Math.PI/36; // 5 degrees
		if(Math.abs(getAngle().getTheta()) < angleEpsilon )
			return 0.0;
		return getAngle().getTheta()/ (angleEpsilon * 3.0);//joystick rotation is inverted
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
		if (currentSensors == null) {
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

	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
		
	}
	public WheelMotorEncoderTicks getStartTicks() {
		if (startTicks == null) {
			startTicks = new WheelMotorEncoderTicks();
		}
		return startTicks;
	}
	
	public WheelMotorEncoderTicks getCurrentTicks() {
		if(currentTicks == null) {
			currentTicks = new WheelMotorEncoderTicks();
		}
		return currentTicks;
	}
	public void setStartTicks(WheelMotorEncoderTicks wmet) {
		startTicks = wmet;
	}

}
