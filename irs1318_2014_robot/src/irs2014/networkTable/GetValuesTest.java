package irs2014.networkTable;

import org.usfirst.frc1318.FRC2013.shared.ReferenceData;
import org.usfirst.frc1318.generic.networktable.IRSTable;

import irs2014.components.RobotComponentBase;

public class GetValuesTest extends RobotComponentBase{
	
	private String shooterField = "spid.vsp";
	private String shooterOverride = "spid.nto";
	
	private String testField = "test.f";
	private String testOverride = "test.o";
	
	private int counter = 0;
	
	public void robotInit(){
		IRSTable.putBoolean(shooterOverride, false);
		IRSTable.putNumber(shooterField, new Double(0));
		IRSTable.putBoolean(testOverride, false);
		IRSTable.putNumber(testField, new Double(0));
	}
	
	public void teleopPeriodic(){
		if((counter % 100) == 0){
			if(IRSTable.getBoolean(shooterOverride)){
				System.out.println(IRSTable.getNumber(shooterField));
			}
			if(IRSTable.getBoolean(testOverride)){
				System.out.println(IRSTable.getNumber(testField));
			}
		}
		counter++;
	}

}
