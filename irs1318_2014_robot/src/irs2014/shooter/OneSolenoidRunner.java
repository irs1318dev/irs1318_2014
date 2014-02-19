package irs2014.shooter;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import irs2014.components.RobotComponentBase;
import irs2014.generalData.PortRef;

public class OneSolenoidRunner extends RobotComponentBase{
	
	private DoubleSolenoid[] solenoid;
	private int counter;
	private boolean currentState = true;
	
	public void robotInit(){
		solenoid = new DoubleSolenoid[5];
		solenoid[0] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, 7, 8);
		solenoid[1] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, 5, 6);
		solenoid[2] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_1, 3, 4);
		solenoid[3] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, 7, 8);
		solenoid[4] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, 1, 2);
//		solenoid[5] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, 3, 4);
//		solenoid[6] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, 5, 6);
//		solenoid[7] = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, 7, 8);
//		solenoid = new DoubleSolenoid(PortRef.SOLENOID_MODULE_PORT_2, 3, 4);
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
		
//		if((counter%10) == 0){
//			currentState = !currentState;
//			if(currentState){
//				solenoid.set(Value.kForward);
//			}else{
//				solenoid.set(Value.kReverse);
//			}
//		}
//		counter++;
	}
	
	

}
