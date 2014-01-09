package irs2014.generalData;

import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.SensorBase;

public interface PortRef {
	
	int JOYSTICK = 1;
	
	int SIDECAR_SLOT = SensorBase.getDefaultDigitalModule();
	int DIGITAL_IO = SensorBase.getDefaultSolenoidModule();

	int TALON_R = 2;
	int TALON_L = 1;

	int ENCODER_R_A = 1;
	int ENCODER_R_B = 2;

	int ENCODER_L_A = 3;
	int ENCODER_L_B = 4;
	
}
