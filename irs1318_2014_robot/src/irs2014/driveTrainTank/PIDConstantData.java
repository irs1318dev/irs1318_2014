package irs2014.driveTrainTank;

import irs2014.dipSwitch.DipSwitchRef;
import irs2014.generalData.ReferenceData;

public class PIDConstantData {
	// Velocity PID
	private final double rightKf;
	private final double rightKd;
	
	private final double leftKf;
	private final double leftKd;
	
	// Positional PID
	private final double rightPositionalKp;
	private final double rightPositionalKd;
	
	private final double leftPositionalKp;
	private final double leftPositionalKd;
	
	public PIDConstantData() {
		if(ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.COMPETITION_BOT) {
			rightKf = PIDConstantRef.COMPETITION_RIGHT_KF;
			rightKd = PIDConstantRef.COMPETITION_RIGHT_KD;
			leftKf = PIDConstantRef.COMPETITION_LEFT_KF;
			leftKd = PIDConstantRef.COMPETITION_LEFT_KD;
			rightPositionalKp = PIDConstantRef.COMPETITION_RIGHT_POSITIONAL_KP;
			rightPositionalKd = PIDConstantRef.COMPETITION_RIGHT_POSITIONAL_KD;
			leftPositionalKp = PIDConstantRef.COMPETITION_LEFT_POSITIONAL_KP;
			leftPositionalKd = PIDConstantRef.COMPETITION_LEFT_POSITIONAL_KD;
		} else {// if (ReferenceData.getInstance().getDipSwitchData().getDipSwitchState() == DipSwitchRef.PRACTICE_BOT) {
			rightKf = PIDConstantRef.PRACTICE_RIGHT_KF;
			rightKd = PIDConstantRef.PRACTICE_RIGHT_KD;
			leftKf = PIDConstantRef.PRACTICE_LEFT_KF;
			leftKd = PIDConstantRef.PRACTICE_LEFT_KD;
			rightPositionalKp = PIDConstantRef.PRACTICE_RIGHT_POSITIONAL_KP;
			rightPositionalKd = PIDConstantRef.PRACTICE_RIGHT_POSITIONAL_KD;
			leftPositionalKp = PIDConstantRef.PRACTICE_LEFT_POSITIONAL_KP;
			leftPositionalKd = PIDConstantRef.PRACTICE_LEFT_POSITIONAL_KD;
		}
	}

	public double getRightKf() {
		return rightKf;
	}

	public double getRightKd() {
		return rightKd;
	}

	public double getLeftKf() {
		return leftKf;
	}

	public double getLeftKd() {
		return leftKd;
	}

	public double getRightPositionalKp() {
		return rightPositionalKp;
	}

	public double getRightPositionalKd() {
		return rightPositionalKd;
	}

	public double getLeftPositionalKp() {
		return leftPositionalKp;
	}

	public double getLeftPositionalKd() {
		return leftPositionalKd;
	}
}
