package irs2014.generalData;

import edu.wpi.first.wpilibj.SensorBase;


public interface PortRef {
	
	int JOYSTICK_1 = 1;
	int JOYSTICK_2 = 2;
	
	int SIDECAR_SLOT = SensorBase.getDefaultDigitalModule();
	int DIGITAL_IO = SensorBase.getDefaultSolenoidModule();
	int SOLENOID_MODULE_PORT = SensorBase.getDefaultSolenoidModule();
	
	int TALON_R = 2;
	int TALON_L = 1;
	
	int PORT_1 = 1;
	int PORT_2 = 2;
	int PORT_3 = 3;
	int PORT_4 = 4;

	int ENCODER_R_A = 1;
	int ENCODER_R_B = 2;

	int ENCODER_L_A = 3;
	int ENCODER_L_B = 4;
	
	int JAGUAR_1 = 1;
	
	int FIRE = 1;
	int EXTEND_COLLECTOR = 2;
	
	int GO_FORWARD = 3;
	
	int COLLECTOR_MOTOR = 0; //TODO
	
	int COLLECTOR_EXTENDER_SOLENOID_PORT = 0; //TODO
	int COLLECTOR_RETRACTOR_SOLENOID_PORT = 0; //TODO
	
	int COLLECTOR_LIMIT_SWITCH_PORT = 0; //TODO
}
