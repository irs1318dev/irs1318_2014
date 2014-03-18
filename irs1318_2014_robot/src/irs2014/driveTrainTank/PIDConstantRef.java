package irs2014.driveTrainTank;

public interface PIDConstantRef {
	// Competition bot
	double COMPETITION_RIGHT_KF = 0.5;
	double COMPETITION_RIGHT_KD = 0.0005;
	
	double COMPETITION_LEFT_KF = 0.5;
	double COMPETITION_LEFT_KD = 0.0005;
	
	double COMPETITION_RIGHT_POSITIONAL_KP = .0001; //TODO
	double COMPETITION_RIGHT_POSITIONAL_KD = 0; //TODO
	
	double COMPETITION_LEFT_POSITIONAL_KP = .0001; //TODO
	double COMPETITION_LEFT_POSITIONAL_KD = 0; //TODO
	
	double COMPETITION_KB = .3;
	
	// Practice bot
	double PRACTICE_RIGHT_KF = 0.5;
	double PRACTICE_RIGHT_KD = 0.0008;//0.0005;
	
	double PRACTICE_LEFT_KF = 0.5;
	double PRACTICE_LEFT_KD = 0.0005;
	
	double PRACTICE_RIGHT_POSITIONAL_KP = .0001; //TODO
	double PRACTICE_RIGHT_POSITIONAL_KD = 0; //TODO
	
	double PRACTICE_LEFT_POSITIONAL_KP = .0001; //TODO
	double PRACTICE_LEFT_POSITIONAL_KD = 0; //TODO
	
	double PRACTICE_KB = .3;
	
}
