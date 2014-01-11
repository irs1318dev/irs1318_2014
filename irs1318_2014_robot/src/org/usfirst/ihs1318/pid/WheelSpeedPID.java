package org.usfirst.ihs1318.pid;

import org.usfirst.ihs1318.message.PrintDebugStatement;
import org.usfirst.ihs1318.shared.KinematicData;
import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.constants.ButtonRef;
import org.usfirst.ihs1318.shared.constants.ConnectionRef;
import org.usfirst.ihs1318.shared.data.InputVelocity;
import org.usfirst.ihs1318.shared.data.MotorSpeed;
import org.usfirst.ihs1318.shared.data.WheelMotorEncoderRates;

//Checked for NPE. Uses lazy getters.
public class WheelSpeedPID implements Runnable {

	
	public static final String DEBUG_TITLE_WHEEL_SPEED_PID = "Wheel speed PID";

	private PIDGain msGain; // motor speed PID gain values (tune these)
	private MotorSpeed pidMS; // motor speed from pid calculation
	private MotorSpeed kinMS; // motor speed from kinematics
	private WheelMotorEncoderRates refMEnc; // wheel encoders
	private InputVelocity refIV; // joystick values
	
	private MecanumWheel rfWheel;
	private MecanumWheel rrWheel;
	private MecanumWheel lfWheel;
	private MecanumWheel lrWheel;

	//sprint
	boolean sprintEnabled;
	
	// velocity clamping speeds
	double minV = -.7;
	double maxV = .7;
	
	boolean enabled;
	boolean debugEnabled=true;
	int printCount;

	private int debug = 0;
	
	/**
	 * calculates the wheel motor speeds and then 
	 * sets the wheel motor speeds 
	 */
	public void run() {	
		while(true) {
			if (isEnabled()) {
				calculateCurrentWheelSpeed();
				sendWheelSpeeds();
			}
			try {
				// TODO: fix warning
				Thread.currentThread().sleep(50);
			} catch (InterruptedException e) {
				// notify will wake up early.
			}
		}
	}

	public void init() {		
		this.rfWheel = new MecanumWheel(ConnectionRef.MOTOR_SLOT_RF, ConnectionRef.MOTOR_CHANNEL_RF, true);
		this.lfWheel = new MecanumWheel(ConnectionRef.MOTOR_SLOT_LF, ConnectionRef.MOTOR_CHANNEL_LF, true);
		this.lrWheel = new MecanumWheel(ConnectionRef.MOTOR_SLOT_LR, ConnectionRef.MOTOR_CHANNEL_LR, true);
		this.rrWheel = new MecanumWheel(ConnectionRef.MOTOR_SLOT_RR, ConnectionRef.MOTOR_CHANNEL_RR, true);
		
		initPIDGain();
	}
	
	public void initPIDGain() {
		getMsGain().setKp(0.0);
		getMsGain().setKd(0.0005);
		getMsGain().setKi(0.0);
	}

	/**
	 * Updates the currentWheelSpeed values based on the desired WheelSpeed from
	 * the KinematicData and the WheelEncoderValues from the ReferenceData.
	 */
	public void calculateCurrentWheelSpeed() {
		synchronized (KinematicData.getInstance()) {
			KinematicData.getInstance().getWmSpeed()
					.copyValues(getKinMS());
		}
		synchronized (ReferenceData.getInstance()) {
			ReferenceData.getInstance().getWheelEncoderRates()
					.copyValues(getRefMEnc());
			sprintEnabled = ReferenceData.getInstance().getButtonValues().getButtonValue(
					ButtonRef.SPRINT_JS, ButtonRef.SPRINT_BUTTON);
		}

		//TODO normalize so 1.0 is maximum here or after the calculation ?		
		// PID Sc = Sd + Kd * ( omega_d - omega_actual)
		
		setClampValues();
		double currentRightFront = clamp(getKinMS().getRf())
		+ getMsGain().getKd()
		* (   getKinMS().calculateWheelOmega(getKinMS().getRf()) 
				- getRefMEnc().getRf()   );
        getPidMS().setRf(currentRightFront);
		
		if(debug % 30 == 0) {
		debug = 0;
		System.out.println(
				  "crf " + currentRightFront
				+",krf " + getKinMS().getRf()
				+",omg " + getKinMS().calculateWheelOmega(getKinMS().getRf())
				+",enc " + getRefMEnc().getRf()
				);
		}
		debug++;

        // the left wheels read negative from the right wheels, including the anglular velocity
		double currentLeftFront = clamp(-getKinMS().getLf())
				+ getMsGain().getKd()
				* (-getKinMS().calculateWheelOmega(getKinMS().getLf()) 
						- getRefMEnc().getLf());
		getPidMS().setLf(currentLeftFront);

		double currentLeftRear = clamp(-getKinMS().getLr())
				+ getMsGain().getKd()
				* (-getKinMS().calculateWheelOmega(getKinMS().getLr()) 
						- getRefMEnc().getLr());
		getPidMS().setLr(currentLeftRear);

		double currentRightRear = clamp(getKinMS().getRr())
				+ getMsGain().getKd()
				* (getKinMS().calculateWheelOmega(getKinMS().getRr()) 
						- getRefMEnc().getRr());
		getPidMS().setRr(currentRightRear);
		
		/*if (isDebugEnabled()) {
			if (printCount%20==0) {
			System.out.println("lf="+getRefMEnc().getLf()
					           +",rf="+getRefMEnc().getRf()
					           +",lr="+getRefMEnc().getLr()
					           +",rr="+getRefMEnc().getRr()
					           );
			}
			printCount++;
		}*/

	}
	
	private void setClampValues() {
		double baseValue;
		if(sprintEnabled)
			baseValue = .95;		
		else
			baseValue = .7;
		
		minV = -baseValue;
		maxV = baseValue;
		
	}

	public void sendWheelSpeeds() {
		getRfWheel().set(getPidMS().getRf());
		getLfWheel().set(getPidMS().getLf());
		getLrWheel().set(getPidMS().getLr());
		getRrWheel().set(getPidMS().getRr());
//		if(debug % 30 == 0) {
//			debug = 0;
//			System.out.println(
//					  "lf " + getPidMS().getLf()
//					+",lr " + getPidMS().getLr()
//					+",rf " + getPidMS().getRf()
//					+",rr " + getPidMS().getRr()
//					);
//		}
//		debug++;
		
	}
	
	private void addDebugData() {
		PrintDebugStatement debug = PrintDebugStatement.getDebug(DEBUG_TITLE_WHEEL_SPEED_PID);
		StringBuffer line =  new StringBuffer();
		line.append(Math.floor(getRefMEnc().getRf() * 10) / 10 + ",");
		line.append(Math.floor(getRefMEnc().getLf() * 10) / 10 + ",");
		line.append(Math.floor(getRefMEnc().getLr() * 10) / 10 + ",");
		line.append(Math.floor(getRefMEnc().getRr() * 10) / 10 + ",");

		line.append(Math.floor(getRfWheel().getWheelMotor().get() * 100) / 100 + ",");
		line.append(Math.floor(getLfWheel().getWheelMotor().get() * 100) / 100 + ",");
		line.append(Math.floor(getLrWheel().getWheelMotor().get() * 100) / 100 + ",");
		line.append(Math.floor(getRrWheel().getWheelMotor().get() * 100) / 100 + ",");
		
//		line.append(Math.floor(rrWheel.get() * 100) / 100 + ",");
//			printOut[counter - 101] += "," + Math.floor(jX * 100) / 100;
//			printOut[counter - 101] += "," + Math.floor(jY * 100) / 100;
//			printOut[counter - 101] += "," + Math.floor(rJoyStick * 100) / 100;


		debug.addLine(line.toString());
		
	}
	
	
	public MotorSpeed getPidMS() {
		if (pidMS == null) {
			pidMS = new MotorSpeed();
		}
		return pidMS;
	}

	public void setPidMS(MotorSpeed currentWheelSpeed) {
		this.pidMS = currentWheelSpeed;
	}

	public MotorSpeed getKinMS() {
		if (kinMS == null) {
			kinMS = new MotorSpeed();
		}
		return kinMS;
	}

	public void setKinMS(MotorSpeed desiredWheelSpeed) {
		this.kinMS = desiredWheelSpeed;
	}

	public WheelMotorEncoderRates getRefMEnc() {
		if (refMEnc == null) {
			refMEnc = new WheelMotorEncoderRates();
		}
		return refMEnc;
	}

	public void setRefMEnc(WheelMotorEncoderRates actualEncoderValues) {
		this.refMEnc = actualEncoderValues;
	}

	/**
	 * @return the PID gains associated with the wheel motors.
	 */
	public PIDGain getMsGain() {
		if (msGain == null) {
			msGain = new PIDGain();
		}
		return msGain;
	}

	/**
	 * re-initialize the wheel speed gains.
	 * @param wheelSpeedGain
	 */
	public void setMsGain(PIDGain wheelSpeedGain) {
		this.msGain = wheelSpeedGain;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
    public double clamp(double val)
    {
	if (val > maxV) {
	    val = maxV;
	}
	if (val < minV) {
	    val = minV;
	}
	return val;
    }

	public boolean isDebugEnabled() {
		return debugEnabled;
	}

	public void setDebugEnabled(boolean debugEnabled) {
		this.debugEnabled = debugEnabled;
	}

	public InputVelocity getRefIV() {
		if (refIV == null ) {
			refIV = new InputVelocity();
		}
		return refIV;
	}

	public void setRefIV(InputVelocity refIV) {
		this.refIV = refIV;
	}
	

	public boolean isSprintEnabled() {
		return sprintEnabled;
	}

	public void setSprintEnabled(boolean sprintEnabled) {
		this.sprintEnabled = sprintEnabled;
	}

	public MecanumWheel getRfWheel() {
		return rfWheel;
	}

	public void setRfWheel(MecanumWheel rfWheel) {
		this.rfWheel = rfWheel;
	}

	public MecanumWheel getRrWheel() {
		return rrWheel;
	}

	public void setRrWheel(MecanumWheel rrWheel) {
		this.rrWheel = rrWheel;
	}

	public MecanumWheel getLfWheel() {
		return lfWheel;
	}

	public void setLfWheel(MecanumWheel lfWheel) {
		this.lfWheel = lfWheel;
	}

	public MecanumWheel getLrWheel() {
		return lrWheel;
	}

	public void setLrWheel(MecanumWheel lrWheel) {
		this.lrWheel = lrWheel;
	}


}
