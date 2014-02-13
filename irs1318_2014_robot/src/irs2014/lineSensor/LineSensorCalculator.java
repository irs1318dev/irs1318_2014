package irs2014.lineSensor;

import irs2014.generalData.ReferenceData;

public class LineSensorCalculator {
	
	public void teleopPeriodic() {
		if(ReferenceData.getInstance().getLineSensorData().getIsOnLine() && ReferenceData.getInstance().getLineSensorData().getIsRequested()) {
			ReferenceData.getInstance().getLineSensorData().setOnLineLeftTick(ReferenceData.getInstance().getDriveTrainData().getLeftEncoderData().getTicks());
			ReferenceData.getInstance().getLineSensorData().setOnLineRightTick(ReferenceData.getInstance().getDriveTrainData().getRightEncoderData().getTicks());
		}
	}

}
