package org.usfirst.frc1318.data;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;
import org.usfirst.frc1318.data.Trapezoid;


public class TrapezoidTest extends DataStructureContractTest{
	
	Trapezoid square;
	Trapezoid rect;
	Trapezoid rectForTheta;
	Trapezoid trap;
	double tolerance = 0.1;
	
	@Before
	public void construct() {
		square = new Trapezoid(0,0, 0,2, 2,2, 0,0, 2,0);
		rect = new Trapezoid(0,0, 0,4, 6,4, 0,0, 6,0);
		rectForTheta = new Trapezoid(0,0, 0,3, 4,3, 0,0, 4,0);
		trap = new Trapezoid(0,0, 0,4, 2,2, 0,0, 2,0);
	}
	
	@Test
	public void squareMidpointYTest() {
		assertEquals(1, square.findMidpoint().getY(), 0.001);
	}

	@Test
	public void squareMidpointXTest() {
		assertEquals(1, square.findMidpoint().getX(), 0.001);
	}
	
	@Test
	public void rectangleMidpointYTest() {
		assertEquals(2, rect.findMidpoint().getY(), 0.001);
	}
	
	@Test
	public void rectangleMidpointXTest() {
		assertEquals(3, rect.findMidpoint().getX(), 0.001);
	}
	
	@Test
	public void squareWidthTest() {
		assertEquals(2, square.findWidth(), 0.001);
	}
	
	@Test
	public void rectangleWidthTest() {
		assertEquals(6, rect.findWidth(), 0.001);
	}
	
	@Test
	public void squareHeightTest() {
		assertEquals(2, square.findHeight(), 0.001);
	}
	
	@Test
	public void rectangleHeightTest() {
		assertEquals(4, rect.findHeight(), 0.001);
	}
	
	@Test
	public void differentHeightsTest() {
		assertEquals(3 , trap.findHeight(), 0.001);
	}
	
	@Test
	public void calculateThetaTest() {
		assertEquals(0, rectForTheta.findTheta(), 0.1);
	}
	
	@Test
	public void calculateThetaFieldTest() {
		assertEquals(-60.0, trap.findThetaField(), 0.1);
	}

	@Override
	protected DataStructure createData1() {
		square = new Trapezoid(0,0, 0,2, 2,2, 0,0, 2,0);
		DataStructure data = square;
		return data;
	}

	@Override
	protected DataStructure createData2() {
		rect = new Trapezoid(0,0, 0,4, 6,4, 0,0, 6,0);
		DataStructure data = rect;
		return data;
	}
	
}
