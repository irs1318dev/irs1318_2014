package irs2014.driveTrainMecanum;

import irs2014.components.RobotComponentBase;

public class MecMotorRunner extends RobotComponentBase{
	private Jaguar jaguar1;
	private Jaguar jaguar2;
	private Jaguar jaguar3;
	private Jaguar jaguar4;
		
	private final int PORT_1 = 1;
	private final int PORT_2 = 2;
	private final int PORT_3 = 3;
	private final int PORT_4 = 4;
	
	private final int SIDECAR_SLOT = 2;
	
	private final double SPEED = .5;
	
	private final int counter;
	
	public void robotInit (){
		jaguar1 = new Jaguar(this.SIDECAR_SLOT, PORT_1);
		jaguar2 = new Jaguar(this.SIDECAR_SLOT, PORT_2);
		jaguar3 = new Jaguar(this.SIDECAR_SLOT, PORT_3);
		jaguar4 = new Jaguar(this.SIDECAR_SLOT, PORT_4);
		
		System.out.println ("RobotInit");
		
		counter = 0;
	}
	
	public void teleopPeriodic (){
		if ((counter % 100) == 0){
			System.out.println ("MecMotorRunner teleop ports " + PORT_1 + ", " + PORT_2 + ", " + PORT_3 + ", " + PORT_4 + " = " + SPEED);
		}
		counter++;
		jaguar1.set(SPEED);
		jaguar2.set(SPEED);
		jaguar3.set(SPEED);
		jaguar4.set(SPEED);
	}

}
