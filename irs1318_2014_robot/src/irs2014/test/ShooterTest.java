package irs2014.test;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import irs2014.components.RobotComponentBase;

public class ShooterTest extends RobotComponentBase {
	
	private Joystick joystick;
	private DoubleSolenoid inner1;
	private DoubleSolenoid inner2;
	private DoubleSolenoid outer1;
	private DoubleSolenoid outer2;
	private DoubleSolenoid middle;
	
	public void robotInit(){
		joystick = new Joystick(1);
		middle = new DoubleSolenoid(1, 5, 6);
		inner1 = new DoubleSolenoid(1, 3, 4);
		inner2 = new DoubleSolenoid(1, 7, 8);
		outer1 = new DoubleSolenoid(1, 1, 2);
		outer2 = new DoubleSolenoid(2, 1, 2);
	}
	
	public void teleopPeriodic(){
//		System.out.println(joystick.getRawButton(1));
		//INDIVIDUAL
//		if(joystick.getRawButton(1)){
//			middle.set(Value.kForward);
//		}else{
//			middle.set(Value.kReverse);
//		}
//		if(joystick.getRawButton(2)){
//			inner1.set(Value.kForward);
//		}else{
//			inner1.set(Value.kReverse);
//		}
//		if(joystick.getRawButton(3)){
//			inner2.set(Value.kForward);
//		}else{
//			inner2.set(Value.kReverse);
//		}
//		if(joystick.getRawButton(4)){
//			outer1.set(Value.kForward);
//		}else{
//			outer1.set(Value.kReverse);
//		}
//		if(joystick.getRawButton(5)){
//			outer2.set(Value.kForward);
//		}else{
//			outer2.set(Value.kReverse);
//		}
		
		//ALL
		if(joystick.getRawButton(1)){
			setAllSolenoids(Value.kForward);
			middle.set(Value.kForward);
		}else if(joystick.getRawButton(2)){
			middle.set(Value.kReverse);
		}else{
			setAllSolenoids(Value.kReverse);
		}
	}
	
	private void setAllSolenoids(Value value){
//		middle.set(value);
		inner1.set(value);
		inner2.set(value);
		outer1.set(value);
		outer2.set(value);
	}

}
