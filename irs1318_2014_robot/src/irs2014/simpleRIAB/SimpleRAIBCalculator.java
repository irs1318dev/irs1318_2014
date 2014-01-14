package irs2014.simpleRIAB;

import irs2014.components.RobotComponentBase;

public class SimpleRAIBCalculator extends RobotComponentBase{
	
	public void teleopPeriodic(){
		GamePadData.getInstance().setYValue(.5);
	}

}
