package org.usfirst.ihs1318.reference;

import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ButtonRef;
import org.usfirst.ihs1318.shared.data.ArmInput;
import org.usfirst.ihs1318.shared.data.InputVelocity;
import org.usfirst.ihs1318.shared.data.Orientation;

import edu.wpi.first.wpilibj.Joystick;

public class GamePadReader {
	public static final double DEAD_ZONE = .17;
	public static final int JOYSTICK_VELOCITY = ButtonRef.JOYSTICK_VELOCITY;
	public static final int JOYSTICK_ROTATION = ButtonRef.JOYSTICK_ROTATION;
	public static final int JOYSTICK_ARM = ButtonRef.JOYSTICK_ARM;
	
	
	private Joystick velocityStick;
	private Joystick rotationStick;

	private Joystick armStick;

    private boolean prevPressRotation2 = false;
    private boolean prevPressRotation3 = false;
    private boolean prevPressRotation4 = false;
    private boolean prevPressRotation5 = false;
    private ButtonReader buttonReader;
	
	InputVelocity iv;
	ArmInput armInput;
	Orientation or;
	
	private boolean enabled;
	
	public void init() {
		//The Joystick() constructor is not 0-indexed so you have to add one.
		velocityStick = new Joystick(JOYSTICK_VELOCITY + 1);
		rotationStick = new Joystick(JOYSTICK_ROTATION + 1);	
		armStick = new Joystick(JOYSTICK_ARM + 1);

	}
	
	public void run() {
		while(true) {
			if (isEnabled()) {
				readJoysticks();
			}
			try {
				// TODO: fix warning
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// notify will wake up early.
			}
		}
	}
    
	/**
	 * This function is a double whammy. Not only does it read the joys tick motion, but it also reads
	 * the buttons!! In other words, you don't need to make a separate ButtonReader object to get button 
	 * values.
	 */
    public void readJoysticks() {
    	getIv().setDesiredJx(accountForDeadZone(velocityStick.getX()));
    	getIv().setDesiredJy(accountForDeadZone(-velocityStick.getY())); // this was flipped.
    	getIv().setDesiredW(accountForDeadZone(-rotationStick.getX())); // this was flipped
    	getIv().setDesiredJRY(accountForDeadZone(rotationStick.getY()));
    	getArmInput().setDesiredJy(accountForDeadZone(armStick.getY()));
    	changeAngle();
//		getButtonReader().setButtonValues(this);
		
    	synchronized(ReferenceData.getInstance()) {
    		//commented code is for teleop auto placement. taken out
    		//boolean autoPlace = ReferenceData.getInstance().getAutoPlacementValue().getAutoPlace();
    		//if(!autoPlace && velocityStick.getMagnitude() == 0){
    			getIv().copyValues(ReferenceData.getInstance().getInputVelocity());
    			getOr().copyValues(ReferenceData.getInstance().getOrientation());
    			getArmInput().copyValuesTo(ReferenceData.getInstance().getArmInput());
    		//}
    		//else if(accountForDeadZone(velocityStick.getMagnitude()) > 0 || accountForDeadZone(rotationStick.getMagnitude()) > 0 || accountForDeadZone(armStick.getMagnitude()) > 0){
    		//	ReferenceData.getInstance().getAutoPlacementValue().setAutoPlace(false);
    		//}
    	}
    }

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public InputVelocity getIv() {
		if (iv == null) {
			iv = new InputVelocity();
		}
		return iv;
	}
	public Orientation getOr() {
		if (or == null) {
			or = new Orientation();
		}
		return or;
	}

	public void setIv(InputVelocity iv) {
		this.iv = iv;
	}
	
	
	//TODO Add documentation.
	//Won't this be overriden anyway by the compass/gyro dream team?
	  public void changeAngle() {
			if (rotationStick.getRawButton(3) && !prevPressRotation3)
			{
				getOr().setTheta(0);
			}
			else if(rotationStick.getRawButton(2) && !prevPressRotation2)
			{

				getOr().setTheta(Math.PI);
			
			}
			else if(rotationStick.getRawButton(4) && !prevPressRotation4)
			{

				getOr().setTheta(Math.PI/2);
			}
			else if (rotationStick.getRawButton(5) && !prevPressRotation5)
			{
				getOr().setTheta((3*Math.PI)/2);
			}

			prevPressRotation2 = rotationStick.getRawButton(2);
			prevPressRotation3 = rotationStick.getRawButton(3);
			prevPressRotation4 = rotationStick.getRawButton(4);
			prevPressRotation5 = rotationStick.getRawButton(5);
	  }

		public double getXWithDeadZone() {
			return accountForDeadZone(velocityStick.getX());
		}

		public double getYWithDeadZone() {
			return accountForDeadZone(velocityStick.getY());
		}
		
		public static final int USE_LINEAR_DEADZONE = 1;
		public static final int USE_QUADRATIC_DEADZONE = 2;
		public static final int USE_CUBIC_DEADZONE = 3;

		private int activeDeadzone = USE_QUADRATIC_DEADZONE;
		
		public double accountForDeadZone(double val) {
			switch (activeDeadzone) {
			case USE_LINEAR_DEADZONE:
				return linearDeadzone(val);
			case USE_QUADRATIC_DEADZONE:
				return quadradicDeadzone(val);
			case USE_CUBIC_DEADZONE:
				return cubicDeadzone(val);
			default:
				return quadradicDeadzone(val);
					
			}
		}

		private double linearDeadzone(double val) {
			if (isInDeadZone(val)) {
				return 0;
			}
			return deadZoneCompensation(val);
			
		}
		
		private double quadradicDeadzone(double val) {
			if (val>-0.05 && val<0.05) return 0;
			if (val<0) {
				return -val*val;
			}
			else {
				return val*val;
			}
		}

		private double cubicDeadzone(double val) {
			if (val>-0.05 && val<0.05) return 0;
			return val*val*val;
		}
		
		public boolean isInDeadZone(double val) {
			if (Math.abs(val) <= DEAD_ZONE)
				return true;
			return false;
		}

		//TODO this needs to be changed into something besides a line (cubic)
		public double deadZoneCompensation(double val) {
			if (val < 0)
				return (1 / (1 - DEAD_ZONE) * (val + DEAD_ZONE)); // negative val
			else
				return (1 / (1 - DEAD_ZONE) * (val - DEAD_ZONE)); // positive val
		}
		
		public Joystick getVelocityStick(){
			return velocityStick;
		}
		public Joystick getRotationStick(){
			return rotationStick;
		}
		public Joystick getArmStick() {
			return armStick;
		}

		public ButtonReader getButtonReader() {
			if (buttonReader==null) {
				buttonReader = new ButtonReader();
			}
			return buttonReader;
		}

		public void setButtonReader(ButtonReader buttonReader) {
			this.buttonReader = buttonReader;
		}
		

		public ArmInput getArmInput() {
			  if(armInput == null){
				  armInput = new ArmInput();
			  }
		  		return armInput;
		  }
		 
		 public void setArmInput(ArmInput armInput) {
		  		this.armInput = armInput;
		  }
}
