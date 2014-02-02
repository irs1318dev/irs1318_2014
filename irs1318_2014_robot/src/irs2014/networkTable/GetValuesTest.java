package irs2014.networkTable;

import irs2014.components.RobotComponentBase;

public class GetValuesTest extends RobotComponentBase{
	
	private String shooterField = "spid.vsp";
	private String shooterOverride = "spid.nto";
	
	private String testField = "tests.f";
	private String testOverride = "test.o";
	
	private int counter = 0;
	
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
