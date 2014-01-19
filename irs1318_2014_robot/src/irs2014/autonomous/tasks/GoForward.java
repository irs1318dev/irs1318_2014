package irs2014.autonomous.tasks;

import irs2014.autonomous.*;

public class GoForward extends AutonomousCommand
{
	public void run() 
	{
		switch(currentState)
		{
			case 0:
				driveForward(3);
				break;
			case 1:
				isDone = true;
				break;
		}
	}

	public void cancel() //find a way to make this nicer?
	{
		irs2014.generalData.ReferenceData.getInstance().getUserInputData().setJoystickY(0);
	}
}