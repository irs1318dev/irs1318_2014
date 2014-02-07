package irs2014.driveTrainTank;

import irs2014.components.RobotComponentBase;
import irs2014.generalData.ReferenceData;

public class DriveTrainNoPIDCalculator extends RobotComponentBase
{
	public void teleopPeriodic() {
			

		ReferenceData.getInstance().getDriveTrainData().getRightPIDData().setPIDVelocity(ReferenceData
				.getInstance()
				.getDriveTrainData()
				.getRightPIDData().getVelocitySetpoint());
		ReferenceData.getInstance().getDriveTrainData().getLeftPIDData().setPIDVelocity(ReferenceData
				.getInstance()
				.getDriveTrainData()
				.getLeftPIDData().getVelocitySetpoint());	

	}
}
