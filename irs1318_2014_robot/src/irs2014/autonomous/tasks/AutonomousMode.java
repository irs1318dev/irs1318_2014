package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class AutonomousMode extends AutonomousCommand
{
	public void run() 
	{
		switch(currentState)
		{
		case 0:
			isDone = true;
//          putYourRightFootIn();
			break;
//		case 1:
//			takeYourRightFootOut();
//			break;
//		case 2:
//			putYourLeftFootIn();
//			break;
//		case 3:
//			andYouShakeItAllAbout();
//			break;
//		case 4:
//			youDoTheShootyShooty();
//			break;
//		case 5:
//			andYouTurnYourselfAbout();
//			break;
//		case 6:
//			isDone = true;
//			break;
		}
	}

	public void cancel() 
	{
		stopDriveTrain();
	}

}
