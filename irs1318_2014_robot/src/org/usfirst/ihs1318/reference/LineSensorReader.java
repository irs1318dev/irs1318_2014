package org.usfirst.ihs1318.reference;

import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ConnectionRef;
import org.usfirst.ihs1318.shared.data.LineSensorInput;

import edu.wpi.first.wpilibj.DigitalInput;

public class LineSensorReader {
    DigitalInput left; // digital inputs for line tracking sensors
    DigitalInput center;
    DigitalInput right;
    LineSensorInput values = null;


    public LineSensorReader() {
    }
    
    public void init() {
        center = new DigitalInput(ConnectionRef.LINE_SENSOR_SLOT, ConnectionRef.LINE_SENSOR_CENTER_CHANNEL);
        right = new DigitalInput(ConnectionRef.LINE_SENSOR_SLOT, ConnectionRef.LINE_SENSOR_RIGHT_CHANNEL);
        left = new DigitalInput(ConnectionRef.LINE_SENSOR_SLOT, ConnectionRef.LINE_SENSOR_LEFT_CHANNEL);
    }
    
    public void readSensors() {
        getValues().setAll(getLeft().get(), getCenter().get(), getRight().get());
        
    	synchronized(ReferenceData.getInstance()){
    		getValues().copyValuesTo(ReferenceData.getInstance().getLineSensorValues());
    	//	ReferenceData.getInstance().getLineSensorHistory().write(getValues());
    	}
//    	SmartDashboard.log(getValues().getLineSensorState(), "LSS");
    }

	public DigitalInput getLeft() {
		if(left == null)
	        left = new DigitalInput(ConnectionRef.LINE_SENSOR_SLOT, ConnectionRef.LINE_SENSOR_LEFT_CHANNEL);
		return left;
	}

	public void setLeft(DigitalInput left) {
		this.left = left;
	}

	public DigitalInput getCenter() {
		if(center == null)
	        center = new DigitalInput(ConnectionRef.LINE_SENSOR_SLOT, ConnectionRef.LINE_SENSOR_CENTER_CHANNEL);
		return center;
	}

	public void setCenter(DigitalInput center) {
		this.center = center;
	}

	public DigitalInput getRight() {
		if(right == null)
	        right = new DigitalInput(ConnectionRef.LINE_SENSOR_SLOT, ConnectionRef.LINE_SENSOR_LEFT_CHANNEL);
		return right;
	}

	public void setRight(DigitalInput right) {
		this.right = right;
	}

	public LineSensorInput getValues() {
		if (values == null) {
			values = new LineSensorInput();
		}
		return values;
	}

	public void setValues(LineSensorInput values) {
		this.values = values;
	}

}

