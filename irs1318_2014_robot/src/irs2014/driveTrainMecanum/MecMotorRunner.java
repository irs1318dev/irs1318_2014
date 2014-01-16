package irs2014.driveTrainMecanum;

import edu.wpi.first.wpilibj.Jaguar;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;


public class MecMotorRunner extends RobotComponentBase{
	private Jaguar jaguar1;
	private Jaguar jaguar2;
	private Jaguar jaguar3;
	private Jaguar jaguar4;
		
	
	
	private final double SPEED = .5;
	
	private int counter;
	
	public void robotInit (){
		jaguar1 = new Jaguar(PortRef.SIDECAR_SLOT, PortRef.PORT_1);
		jaguar2 = new Jaguar(PortRef.SIDECAR_SLOT, PortRef.PORT_2);
		jaguar3 = new Jaguar(PortRef.SIDECAR_SLOT, PortRef.PORT_3);
		jaguar4 = new Jaguar(PortRef.SIDECAR_SLOT, PortRef.PORT_4);
		
		System.out.println ("RobotInit");
		
		counter = 0;
	}
	
	public void teleopPeriodic (){
		if ((counter % 100) == 0){
			System.out.println ("MecMotorRunner teleop ports " + PortRef.PORT_1 + ", " + PortRef.PORT_2 + ", " + PortRef.PORT_3 + ", " + PortRef.PORT_4 + " = " + SPEED);
		}
		counter++;
		jaguar1.set(SPEED);
		jaguar2.set(SPEED);
		jaguar3.set(SPEED);
		jaguar4.set(SPEED);
	}

}
