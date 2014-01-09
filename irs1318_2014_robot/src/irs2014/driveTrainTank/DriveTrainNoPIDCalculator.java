package irs2014.driveTrainTank;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;

public class DriveTrainNoPIDCalculator extends RobotComponentBase
{
	public void teleopPeriodic() {
			

		ReferenceData.getInstance().getDriveTrainData().setRightPIDSpeed(ReferenceData
				.getInstance()
				.getDriveTrainData()
				.getRightSpeedSetPoint());
		ReferenceData.getInstance().getDriveTrainData().setLeftPIDSpeed(ReferenceData
				.getInstance()
				.getDriveTrainData()
				.getLeftSpeedSetPoint());	

	}
}
