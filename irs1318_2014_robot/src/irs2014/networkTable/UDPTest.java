package irs2014.networkTable;

import java.net.SocketException;

import irs2014.components.RobotComponentBase;

public class UDPTest extends RobotComponentBase{
	
	private double test;
	
	public void robotInit(){
		test = 0;
		try {
			UDPTable.getInstance().init();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void teleopPeriodic(){
		double value = test++/7.0;
		UDPTable.getInstance().sendData("nt.ts", ("" + value));
	}

}
