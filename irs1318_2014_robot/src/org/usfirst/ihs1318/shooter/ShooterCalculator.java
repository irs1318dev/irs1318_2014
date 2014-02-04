package org.usfirst.ihs1318.shooter;

import irs2014.generalData.ReferenceData;

public class ShooterCalculator {
	
	public void robotInit() {
		
	}
	
	public void teleopPeriodic() {
		if(ReferenceData.getInstance().getPressureSensorData().getIsPressurized() && ReferenceData.getInstance().getUserInputData().getExtendAllShooterSolenoids()) {
			ReferenceData.getInstance().getShooterData().setMiddleSolenoidState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setInnerSolenoidsState(ShooterRef.EXTEND);
			ReferenceData.getInstance().getShooterData().setOuterSolenoidsState(ShooterRef.EXTEND);
		}
		
	}
	//for the one piston extension macro
		public void extendMiddlePiston() {
			
			if(ReferenceData.getInstance().getShooterData().getMiddleSolenoidState() == false)
			{
				ReferenceData.getInstance().getShooterData().setMiddleSolenoidState(true);
			}
			
		}
		
		//for the one piston retraction macro
		public void retractMiddlePiston() {

				
			if(ReferenceData.getInstance().getShooterData().getMiddleSolenoidState() == true)
			{
					ReferenceData.getInstance().getShooterData().setMiddleSolenoidState(false);
			}
				
		}
		
		
		//possible use for the two piston extension macro
		//TODO decide to use inner or outer pistons for the two piston extension macro
		public void extendInnerPistons() {
			
			if(ReferenceData.getInstance().getShooterData().getInnerSolenoidsState() == false)
			{
				ReferenceData.getInstance().getShooterData().setInnerSolenoidsState(true);
			}
		}
		
		//possible use for the two piston retraction macro
		public void retractInnerPistons() {
				
			if(ReferenceData.getInstance().getShooterData().getInnerSolenoidsState() == true)
			{
					ReferenceData.getInstance().getShooterData().setInnerSolenoidsState(false);
			}
		}
			
		
		//possible use for the two piston extension macro
		//TODO decide to use inner or outer pistons for the two piston extension macro
		public void extendOuterPistons() {
			
			if(ReferenceData.getInstance().getShooterData().getOuterSolenoidsState() == false)
			{
				ReferenceData.getInstance().getShooterData().setOuterSolenoidsState(true);
			}
		}
		
		//possible use for the two piston retraction macro
		public void retractOuterPistons() {
			
			if(ReferenceData.getInstance().getShooterData().getOuterSolenoidsState() == true)
			{
				ReferenceData.getInstance().getShooterData().setOuterSolenoidsState(false);
			}
		}
		
		
		//for the three piston extension macro
		public void extendOuterAndMiddlePistons() {
			
			if(ReferenceData.getInstance().getShooterData().getOuterSolenoidsState() == false)
			{
				ReferenceData.getInstance().getShooterData().setOuterSolenoidsState(true);
			}
			
			if(ReferenceData.getInstance().getShooterData().getMiddleSolenoidState() == false)
			{
				ReferenceData.getInstance().getShooterData().setMiddleSolenoidState(true);
			}
		}
		
		//for the three piston retraction macro
		public void retractOuterAndMiddlePistons() {
				
			if(ReferenceData.getInstance().getShooterData().getOuterSolenoidsState() == true)
			{
					ReferenceData.getInstance().getShooterData().setOuterSolenoidsState(false);
			}
				
			if(ReferenceData.getInstance().getShooterData().getMiddleSolenoidState() == true)
			{
					ReferenceData.getInstance().getShooterData().setMiddleSolenoidState(false);
			}
		}

		
		//for the piston extension macro
		public void extendAllPistons() {
			
			if(ReferenceData.getInstance().getShooterData().getOuterSolenoidsState() == false)
			{
				ReferenceData.getInstance().getShooterData().setOuterSolenoidsState(true);
			}
			
			if(ReferenceData.getInstance().getShooterData().getInnerSolenoidsState() == false)
			{
				ReferenceData.getInstance().getShooterData().setInnerSolenoidsState(true);
			}
			
			if(ReferenceData.getInstance().getShooterData().getMiddleSolenoidState() == false)
			{
				ReferenceData.getInstance().getShooterData().setMiddleSolenoidState(true);
			}
		}
		
		//for the five piston retraction macro
		public void retractAllPistons() {
				
			if(ReferenceData.getInstance().getShooterData().getOuterSolenoidsState() == true)
			{
					ReferenceData.getInstance().getShooterData().setOuterSolenoidsState(false);
			}
				
			if(ReferenceData.getInstance().getShooterData().getInnerSolenoidsState() == true)
			{
					ReferenceData.getInstance().getShooterData().setInnerSolenoidsState(false);
			}
				
			if(ReferenceData.getInstance().getShooterData().getMiddleSolenoidState() == true)
			{
					ReferenceData.getInstance().getShooterData().setMiddleSolenoidState(false);
			}
		}
		
		
}
