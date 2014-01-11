package irs2014.helloWorld;

import irs2014.components.RobotComponentBase;

public class HelloWorldRunner extends RobotComponentBase{

	int counter;
	
	public void robotInit(){
		System.out.println("Hello World init");
		counter = 0;
	}
	
	public void teleopPeriodic(){
		counter++;
		if((counter % 100) == 0){
			System.out.println("Hello World");
		}
	}
	
}
