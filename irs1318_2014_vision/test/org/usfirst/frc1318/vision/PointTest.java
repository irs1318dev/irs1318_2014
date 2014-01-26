package org.usfirst.frc1318.vision;

import org.junit.*;
import org.usfirst.frc1318.data.DataStructure;
import org.usfirst.frc1318.data.DataStructureContractTest;
import org.usfirst.frc1318.data.Point;

import static org.junit.Assert.*;

public class PointTest extends DataStructureContractTest {
	private static double EPS = .01;
	Point p1;
	Point p2;
	
	@Before
	public void setUp() {
		p1 = new Point(0,0);
		p2 = new Point(0,0);
	}

	@Test
	public void scales() {
		p1 = new Point(2, 2);
		p1.scale(4.0);
		assertPoint(8, 8, p1);
		
		p1 = new Point(10, 10);
		p1.scale(1.0/10.0);
		assertPoint(1, 1, p1);
		
		p1 = new Point(5, 5);
		p1.scale(-.2);
		assertPoint(-1.0, -1.0, p1);
	}
	
	@Test
	public void rotatesAboutOrigin() {
		p2 = new Point(0.0, 0.0);

		
		p1 = new Point(1.0, 0.0);
		assertEquals(1.0, p1.distance(p2), EPS);

		p1 = new Point(1.0, 0.0);
		p1.rotate(p2, 90.0);
		assertPoint(0.0, 1.0, p1);

		p1 = new Point(1.0, 0.0);
		assertEquals(1.0, p1.distance(p2), EPS);
		
		p1 = new Point(0.0, 1.0);
		p1.rotate(p2, -90.0);
		assertPoint(1.0, 0.0, p1);
		
		p1 = new Point(1.0, 0.0);
		p1.rotate(p2, 30.0);
		assertPoint(Math.cos(Math.PI/6), Math.sin(Math.PI/6), p1);
	}
	
	@Test
	public void rotatesAboutAnyPoint() {
		p1 = new Point(2, 2);
		p2 = new Point(4, 6);
		p1.rotate(p2, 45.0);
		assertPoint(5.41, 1.76, p1);
	}
	
	@Test
	public void adds() {
		p1 = new Point(-1, -2);
		p2 = new Point(3, 4);
		p1.add(p2);
		assertPoint(2, 2, p1);
		
		p1 = new Point(-1, -2);
		p1.add(3.0, 4.0);
		assertPoint(2, 2, p1);
	}
	
	@Test
	public void subtracts() {
		p1 = new Point(-3, -5);
		p2 = new Point(5, 10);
		p1.subt(p2);
		assertPoint(-8, -15, p1);
		
		p1 = new Point(-3, -5);
		p1.subt(5, 10);
		assertPoint(-8, -15, p1);
	}


	@Test
	public void multiplies() {
		p1 = new Point(-4, 5);
		p2 = new Point(2, -3);
		p1.mult(p2);
		assertPoint(-8, -15, p1);
		
		p1 = new Point(-4, 5);
		p1.mult(2, -3);
		assertPoint(-8, -15, p1);
	}

	@Test
	public void divides() {
		p1 = new Point(-4, 5);
		p2 = new Point(2, -3);
		p1.div(p2);
		assertPoint(-2, -5.0/3, p1);
		
		p1 = new Point(-4, 5);
		p1.div(2, -3);
		assertPoint(-2, -5.0/3, p1);
	}
	
	@Test
	public void handlesDivideByZero() {	
		p1 = new Point(1, 2);
		p2 = new Point(0, 0);
		p1.div(p2);
		assertPoint(1, 2, p1);
		
		p1 = new Point(1, 2);
		p1.div(0,2);
		assertPoint(1, 1, p1);
	}
	
	private void assertPoint(double x, double y, Point p) {
		assertEquals(x, p.getX(), EPS);
		assertEquals(y, p.getY(), EPS);
	}

//	@Override
	protected DataStructure createData1() {
		return new Point(1, 2);
	}

//	@Override
	protected DataStructure createData2() {
		return new Point(3, 4);
	}
}
