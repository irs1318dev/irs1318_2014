package irs2014Test.collectorTest;

import irs2014.collector.*;
import irs2014.generalData.ReferenceData;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CollectorCalculatorTest {
	CollectorCalculator calculator;
	
	
	@Before
	public void setUpCollectorCalculatorTest (){
		calculator = new CollectorCalculator();
	}
	
	@Test
	public void extendTest(){
		ReferenceData.getInstance().getUserInputData().setExtendCollector(true);
		ReferenceData.getInstance().getUserInputData().setRetractCollector(false);
		calculator.teleopPeriodic();
		assertEquals(CollectorRef.EXTEND, ReferenceData.getInstance().getCollectorData().getSolenoidData().getSolenoidState());
	}
	
	@Test
	public void retractTest(){
		ReferenceData.getInstance().getUserInputData().setExtendCollector(false);
		ReferenceData.getInstance().getUserInputData().setRetractCollector(true);
		calculator.teleopPeriodic();
		assertEquals(CollectorRef.RETRACT, ReferenceData.getInstance().getCollectorData().getSolenoidData().getSolenoidState());
	}
	
	@Test
	public void preferExtendTest(){
		ReferenceData.getInstance().getUserInputData().setExtendCollector(true);
		ReferenceData.getInstance().getUserInputData().setRetractCollector(true);
		calculator.teleopPeriodic();
		assertEquals(CollectorRef.EXTEND, ReferenceData.getInstance().getCollectorData().getSolenoidData().getSolenoidState());
	}
	@Test
	public void motorInTest(){
		ReferenceData.getInstance().getUserInputData().setStopCollectorMotor(true);
		ReferenceData.getInstance().getUserInputData().setCollectorMotorOut(false);
		ReferenceData.getInstance().getUserInputData().setCollectorMotorIn(false);
		calculator.teleopPeriodic();
		assertEquals(0.0, ReferenceData.getInstance().getCollectorData().getMotorData().getCollectorMotorSpeed(), 0.0);
	}
}
