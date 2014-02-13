package irs2014.shooter2;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;

public class OneSolenoidRunner2 extends RobotComponentBase{
	
	private DoubleSolenoid[] solenoid;
	private int counter;
	
	public void robotInit(){
		solenoid = new DoubleSolenoid[8];
		solenoid[0] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, 1, 2);
		solenoid[1] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, 3, 4);
		solenoid[2] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, 5, 6);
		solenoid[3] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, 7, 8);
		solenoid[4] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, 1, 2);
		solenoid[5] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, 3, 4);
		solenoid[6] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, 5, 6);
		solenoid[7] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, 7, 8);
//		solenoid = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, 1, 2);
	}
	
	public void teleopPeriodic(){
		for(int i = 0; i < solenoid.length; i++){
			solenoid[i].set(Value.kForward);
			Timer.delay(.5);
			solenoid[i].set(Value.kReverse);
			Timer.delay(.5);
			solenoid[i].set(Value.kOff);
			Timer.delay(1);
		}
	}
	
	

}
