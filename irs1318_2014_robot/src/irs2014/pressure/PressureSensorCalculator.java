package irs2014.pressure;

import irs2014.generalData.ReferenceData;
import irs2014.components.RobotComponentBase;

public class PressureSensorCalculator extends RobotComponentBase {
	
	public void teleopInit(){
		ReferenceData.getInstance().getPressureSensorTimerData().setShouldStartTimer(true);
		ReferenceData.getInstance().getPressureSensorTimerData().setShouldStopTimer(false);
	}
	
	public void teleopPeriodic() {
		
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized()){
			if(!ReferenceData.getInstance().getPressureSensorData().getWasPressurized()){
				ReferenceData.getInstance().getPressureSensorTimerData().setShouldRestartTimer(true);
			}else{
				ReferenceData.getInstance().getPressureSensorTimerData().setShouldRestartTimer(false);
				ReferenceData.getInstance().getPressureSensorTimerData().setShouldStopTimer(false);
				ReferenceData.getInstance().getPressureSensorTimerData().setShouldStartTimer(false);
			}
		}else if(!ReferenceData.getInstance().getPressureSensorData().getIsPressurized()){
			if(ReferenceData.getInstance().getPressureSensorData().getWasPressurized()){
				ReferenceData.getInstance().getPressureSensorTimerData().setShouldRestartTimer(true);
			}else{
				ReferenceData.getInstance().getPressureSensorTimerData().setShouldRestartTimer(false);
				ReferenceData.getInstance().getPressureSensorTimerData().setShouldStopTimer(false);
				ReferenceData.getInstance().getPressureSensorTimerData().setShouldStartTimer(false);
			}
		}
		
//		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized()) {
//			ReferenceData.getInstance().getPressureSensorTimerData().setShouldStopTimer(false);
//			if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() != ReferenceData.getInstance().getPressureSensorData().getWasPressurized()) {
//				ReferenceData.getInstance().getPressureSensorTimerData().setShouldStartTimer(true);
//			} else if (ReferenceData.getInstance().getPressureSensorData().getIsPressurized() == ReferenceData.getInstance().getPressureSensorData().getWasPressurized()) {
//				ReferenceData.getInstance().getPressureSensorTimerData().setShouldStartTimer(false);
//			}
//		}
//		
//		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() == false) {
//			ReferenceData.getInstance().getPressureSensorTimerData().setShouldStopTimer(false);
//		}
	}
}
