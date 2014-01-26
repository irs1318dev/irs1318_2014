package org.usfirst.frc1318.vision;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc1318.data.Point;
import org.usfirst.frc1318.data.Trapezoid;

public class PointsToTrapezoidTest {

	PointsToTrapezoid test;
	
	Point point1;
	Point point2;
	Point point3;
	Point point4;
	Point point5;
	Point point6;
	Point point7;
	Point point8;
	
	List<Point> pointList;
	
	@Before
	public void inIt() {
		test = new PointsToTrapezoid();
		pointList = new ArrayList<Point>();
		point1 = new Point(227, 300);
		point2 = new Point(224, 398);
		point3 = new Point(358, 301);
		point4 = new Point(357, 400);
		point5 = new Point(233, 389);
		point6 = new Point(235, 309);
		point7 = new Point(348, 391);
		point8 = new Point(349, 308);
	}
	
	@Test
	public void shouldGetLowest() {
		pointList.add(point1);
		pointList.add(point2);
		pointList.add(point3);
		pointList.add(point4);
		pointList.add(point5);
		pointList.add(point6);
		pointList.add(point7);
		pointList.add(point8);
		assertEquals(point1, test.startGetLowest(pointList, point7));
	}
	
	@Test
	public void shouldGetHighest() {
		pointList.add(point1);
		pointList.add(point2);
		pointList.add(point3);
		pointList.add(point4);
		pointList.add(point5);
		pointList.add(point6);
		pointList.add(point7);
		pointList.add(point8);
		assertEquals(point4, test.startGetHighest(pointList, point1));
	}
	
	@Test
	public void shouldNotFilterFourPoints() {
		pointList.add(point1);
		pointList.add(point2);
		pointList.add(point3);
		pointList.add(point4);
		List<Point> result = test.getOuterPoints(pointList);
		assertEquals(4, result.size());
		assertTrue(result.contains(point1));
		assertTrue(result.contains(point2));
		assertTrue(result.contains(point3));
		assertTrue(result.contains(point4));
	}
	
	@Test
	public void filtersOutInnerPoints() {
		pointList.add(point1);
		pointList.add(point2);
		pointList.add(point3);
		pointList.add(point4);
		pointList.add(point5);
		pointList.add(point6);
		pointList.add(point7);
		pointList.add(point8);
		List<Point> result = test.getOuterPoints(pointList);
		assertEquals(4, result.size());
		assertTrue(result.contains(point1));
		assertTrue(result.contains(point2));
		assertTrue(result.contains(point3));
		assertTrue(result.contains(point4));
	}
	/*
	@Test
	public void filtersOutIdenticalPoints() {
		pointList.add(point1);
		pointList.add(point2);
		pointList.add(point3);
		pointList.add(point4);
		pointList.add(point1);
		pointList.add(point4);
		List<Point> result = test.filterOutInnerPoints(pointList);
		assertEquals(4, result.size());
		assertTrue(result.contains(point1));
		assertTrue(result.contains(point2));
		assertTrue(result.contains(point3));
		assertTrue(result.contains(point4));
	}
	*/
	@Test
	public void convertsToTrapezoid() {
		pointList.add(point1);
		pointList.add(point2);
		pointList.add(point3);
		pointList.add(point4);
		Trapezoid result = test.getTrapezoid(pointList);
		Trapezoid expected = new Trapezoid(point1, point2, point3, point4);
		assertEquals(expected, result);
	}
	
	@Test
	public void convertsToTrapezoidInOrder() {
		pointList.add(point4);
		pointList.add(point2);
		pointList.add(point1);
		pointList.add(point3);
		Trapezoid result = test.getTrapezoid(pointList);
		Trapezoid expected = new Trapezoid(point4, point2, point1, point3);
		assertTrue(expected.equals(result));
	}
	
	@Test
	public void doesEverything() {
		pointList.add(point1);
		pointList.add(point2);
		pointList.add(point3);
		pointList.add(point4);
		pointList.add(point5);
		pointList.add(point6);
		pointList.add(point7);
		pointList.add(point8);
		Trapezoid result = test.getOuterTrapezoid(pointList);
		Trapezoid expected = new Trapezoid(point4, point2, point1, point3);
		assertTrue(expected.equals(result));
	}

}
