package org.usfirst.ihs1318.shooter;

import irs2014.generalData.PortRef;
import irs2014.generalData.ReferenceData;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class ShooterRunner {
	
	private DoubleSolenoid middleSolenoid;
	private DoubleSolenoid innerSolenoids;
	private DoubleSolenoid outerSolenoids;
	private DoubleSolenoid shooterAngleSolenoid; 
	
	public void robotInit() {
		middleSolenoid = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.SHOOTER_MIDDLE_SOLENOID_EXTENDER_PORT, PortRef.SHOOTER_MIDDLE_SOLENOID_RETRACTOR_PORT);
		innerSolenoids = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.SHOOTER_INNER_SOLENOIDS_EXTENDER_PORT, PortRef.SHOOTER_INNER_SOLENOIDS_RETRACTOR_PORT);
		outerSolenoids = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.SHOOTER_OUTER_SOLENOIDS_EXTENDER_PORT, PortRef.SHOOTER_OUTER_SOLENOIDS_RETRACTOR_PORT);
		shooterAngleSolenoid = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT, PortRef.SHOOTER_ANGLE_SOLENOID_EXTENDER_PORT, PortRef.SHOOTER_ANGLE_SOLENOID_RETRACTOR_PORT);
		
		System.out.println("DoubleSolenoids robotInit()");
		
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
	
	
	public void teleopPeriodic() {
		if(ReferenceData.getInstance().getShooterData().getMiddleSolenoidState() == ShooterRef.EXTEND) {
			middleSolenoid.set(Value.kForward);
		} else {
			middleSolenoid.set(Value.kReverse);
		}
		if(ReferenceData.getInstance().getShooterData().getInnerSolenoidsState() == ShooterRef.EXTEND){
			innerSolenoids.set(Value.kForward);
		} else {
			innerSolenoids.set(Value.kReverse);
		}
		if(ReferenceData.getInstance().getShooterData().getOuterSolenoidsState() == ShooterRef.EXTEND){
			outerSolenoids.set(Value.kForward);
		} else {
			outerSolenoids.set(Value.kReverse);
		}
		if(ReferenceData.getInstance().getShooterData().getShooterAngleSolenoidState() == ShooterRef.EXTEND) {
			shooterAngleSolenoid.set(Value.kForward);
		} else {
			shooterAngleSolenoid.set(Value.kReverse);
		}
	}
}
