package org.usfirst.ihs1318.shared;

import org.usfirst.ihs1318.shared.data.ArmEncoderValues;
import org.usfirst.ihs1318.shared.data.ArmInput;
import org.usfirst.ihs1318.shared.data.AutoPlacementValue;
import org.usfirst.ihs1318.shared.data.ButtonValues;
import org.usfirst.ihs1318.shared.data.ImageData;
import org.usfirst.ihs1318.shared.data.InputVelocity;
import org.usfirst.ihs1318.shared.data.LimitSwitchValue;
import org.usfirst.ihs1318.shared.data.LineSensorHistory;
import org.usfirst.ihs1318.shared.data.LineSensorInput;
import org.usfirst.ihs1318.shared.data.Orientation;
import org.usfirst.ihs1318.shared.data.WheelMotorEncoderRates;
import org.usfirst.ihs1318.shared.data.WheelMotorEncoderTicks;

/**
 * This class contains static references for synchronized shared data structures.
 * @author first
 *
 */
public class ReferenceData {
	
	public static boolean DEBUG = true;
	
	private static ReferenceData instance;
	
	private InputVelocity inputVelocity;
	private Orientation orientation;
	private WheelMotorEncoderRates wheelEncoderValues;
	private ArmEncoderValues armEncoderValues; //TODO Figure out the ArmEncoderVAlues class. See the class
	private LineSensorInput lineSensorValues;
	private ButtonValues buttons;
	private ImageData cameraImage;
	private AutoPlacementValue autoPlace;
	

	private LineSensorHistory lineSensorHistory;

	private ArmInput armInput;

	private WheelMotorEncoderTicks wheelEncoderTicks;

	private LimitSwitchValue upperLimitSwitchValue;

	private LimitSwitchValue lowerLimitSwitchValue;
	
	public static ReferenceData getInstance() {
		if (instance==null) {
			instance = new ReferenceData();
		}
		return instance;
	}
	
	
	public ImageData getImageData() {
		if (cameraImage == null)
			cameraImage = new ImageData();
		return cameraImage;
	}
	public void setImageData(ImageData data) {
		this.cameraImage = data;
	}
	
	//TODO This will set values to be the same reference as the ReferenceData object
	public ArmEncoderValues getArmEncoderValues() {
		if(armEncoderValues == null)
			armEncoderValues = new ArmEncoderValues();
		return armEncoderValues;
	}

	public void setArmEncoderValues(ArmEncoderValues armEncoderValues) {
		this.armEncoderValues = armEncoderValues;
	}

	
	public InputVelocity getInputVelocity() {
		if (inputVelocity==null) {
			inputVelocity = new InputVelocity();
		}
		return inputVelocity;
	}

	public void setInputVelocity(InputVelocity inputVelocity) {
		this.inputVelocity = inputVelocity;
	}

	public Orientation getOrientation() {
		if (orientation==null) {
			orientation = new Orientation();
		}
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public WheelMotorEncoderRates getWheelEncoderRates() {
		if (wheelEncoderValues==null) {
			wheelEncoderValues = new WheelMotorEncoderRates();
		}
		return wheelEncoderValues;
	}

	public void setWheelEncoderValues(WheelMotorEncoderRates wheelEncoderValues) {
		this.wheelEncoderValues = wheelEncoderValues;
	}
	public void setLineSensorValues(LineSensorInput lineSensorValues) {
		this.lineSensorValues = lineSensorValues;
	}
	public LineSensorInput getLineSensorValues() {
		if(lineSensorValues == null) 
			lineSensorValues = new LineSensorInput();
		return lineSensorValues;
	}
	public LineSensorHistory getLineSensorHistory() {
		if(this.lineSensorHistory == null)
			lineSensorHistory = new LineSensorHistory();
		return lineSensorHistory;
	}
	
	public ButtonValues getButtonValues(){
		if(buttons == null){
			buttons = new ButtonValues();
		}
		return buttons;
	}
	public void setButtonValues(ButtonValues buttons) {
		this.buttons = buttons;
	}
	public ArmInput getArmInput() {
		if(armInput == null){
			armInput = new ArmInput();
		}
		return armInput;
	}

	public WheelMotorEncoderTicks getWheelEncoderTicks() {
		if(wheelEncoderTicks == null){
			wheelEncoderTicks = new WheelMotorEncoderTicks();
		}
		return wheelEncoderTicks;
	}

	public LimitSwitchValue getUpperLimitSwitchValue() {
		if(upperLimitSwitchValue == null) {
			upperLimitSwitchValue = new LimitSwitchValue();
		}
		return upperLimitSwitchValue;
	}


	public LimitSwitchValue getLowerLimitSwitchValue() {
		if(lowerLimitSwitchValue == null)
			lowerLimitSwitchValue = new LimitSwitchValue();
		return lowerLimitSwitchValue;
	}
	
	public AutoPlacementValue getAutoPlacementValue(){
		if(autoPlace == null){
			autoPlace = new AutoPlacementValue();
		}
		return autoPlace;
	}
	public void setAutoPlacementValue(AutoPlacementValue autoPlace){
		this.autoPlace = autoPlace;
	}

}
