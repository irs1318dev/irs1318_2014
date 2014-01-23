package irs2014;

import java.util.Vector;

public class BotVector {
	private Vector v;
	
	public BotVector() {
		v = new Vector();
	}
	
	public void add(RobotComponent elem) {
		v.addElement(elem);
	}
	
	public RobotComponent get(int index) {
		return (RobotComponent) v.elementAt(index);
	}
	
	public int size() {
		return v.size();
	}
}