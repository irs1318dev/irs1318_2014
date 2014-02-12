package irs2014.userInputDevices;

public interface ButtonRef {
	int EXTEND_COLLECTOR = 10; //TODO
	int RETRACT_COLLECTOR = 12; //TODO
	int COLLECTOR_MOTOR_IN = 6; //TODO
	int COLLECTOR_MOTOR_OUT = 4; //TODO
	int STOP_COLLECTOR_MOTOR = 2; //TODO
	
//	int COLLECT_BALL = 8; //TODO
//	int EJECT_BALL = 8; //TODO
	
//	int EXTEND_ALL_SHOOTER_SOLENOIDS = 8; //TODO
//	int EXTEND_INNER_SHOOTER_SOLENOIDS = 8; //TODO
//	int EXTEND_INNER_THREE_SHOOTER_SOLENOIDS = 8; //TODO
//	int EXTEND_MIDDLE_SHOOTER_SOLENOIDS = 8; //TODO
	int RETRACT_SHOOTER = 7; //TODO 
//	int EXTEND_SHOOTER_ANGLE = 11; //TODO 
//	int RETRACT_SHOOTER_ANGLE = 9; //TODO
	int EXTEND_BOTH = 11;	//Collector and Shooter Angle
	int RETRACT_BOTH = 9;	//Collector and Shooter Angle 
	int STEP_SHOOTER = 5;
	int GO_FORWARD = 3;
	int TRIGGER = 1;
	
	//5 macros to detect sensors 
}
