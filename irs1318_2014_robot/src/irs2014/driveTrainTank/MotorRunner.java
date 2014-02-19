package irs2014.driveTrainTank;

import edu.wpi.first.wpilibj.Talon;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;

public class MotorRunner extends RobotComponentBase {
	
	private Talon talon1;
//	private Talon talon2;
	private final int PORT_1 = 1;
//	private final int PORT_2 = 2;
//	private final int SIDECAR_SLOT = 2;
	private final double SPEED = .7;
	private int counter;
	
	public void robotInit (){
		talon1 = new Talon(PortRef.SIDECAR_SLOT, PortRef.PRACTICE_TALON_L);
//		talon2 = new Talon(PortRef.SIDECAR_SLOT, PORT_2);
		System.out.println("robotInit");
		counter = 0;
	}
	
	public void teleopPeriodic (){
		counter++;
		talon1.set(SPEED);
//		talon2.set(SPEED);
		if((counter % 100) == 0){
//			System.out.println("MotorRunner teleopPeriodic ports: " + PORT_1 + ", " + PORT_2 + " = " + SPEED);
		}
	}

}
