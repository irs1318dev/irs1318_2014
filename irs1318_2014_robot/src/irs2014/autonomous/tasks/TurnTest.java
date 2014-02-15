package irs2014.autonomous.tasks;

import irs2014.autonomous.AutonomousCommand;

public class TurnTest extends AutonomousCommand
{
	public void run() 
	{
		switch(currentState)
		{
		case 0:
			goForwardRel(100);
			break;
		case 1:
			rotate(-45);
			break;
		case 2:
			goForwardRel(100);
			break;
		case 3:
			rotate(45);
			break;
		case 4:
			goForwardRel(100);
			break;
		case 5:
			isDone = true;
			break;
		}
	}
	public void cancel() 
	{
		stopDriveTrain();
	}
}
