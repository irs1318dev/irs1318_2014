package irs2014.simpleRIAB;

import irs2014.components.RobotComponentBase;
import irs2014.generalOperations.GamePad;

public class GamePadReaderRIAB extends RobotComponentBase{
	
	GamePad gamePad;
	
	public void robotInit(){
		gamePad = GamePad.create(PortRef.GAME_PAD);
	}
	
	int debugCount=0;
	
	public void teleopPeriodic(){
		double yleft = gamePad.getYLeft();
		if (debugCount%100==0) {
	    	//System.out.println("GamePadReaderRIAB.teleopPeriodic() yleft = " + yleft);
		}
		GamePadData.getInstance().setYValue(yleft);
	}

}
