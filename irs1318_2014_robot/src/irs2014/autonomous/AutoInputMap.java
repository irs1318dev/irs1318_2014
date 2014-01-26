package irs2014.autonomous;

import irs2014.autonomous.tasks.*;
import irs2014.userInputDevices.*;
import irs2014.generalData.*;

public class AutoInputMap 
{
	//Variables
	private AutoTaskRunner autoTaskRunner;
	
	public AutoInputMap(AutoTaskRunner autoTaskRunner)
	{
		this.autoTaskRunner = autoTaskRunner; 
	}
	
	public void update()
	{
		if(ReferenceData.getInstance().getUserInputData().getIsActive())
		{
			if(autoTaskRunner.hasCurrentTask())
			{//Gives the user back control
				autoTaskRunner.cancelCurrentTask();
			}
			
			
			if(!autoTaskRunner.hasCurrentTask())
			{//sets the current macro, if applicable
				System.out.println("About to add the current task");
				if(ReferenceData.getInstance().getUserInputData().getGoForward())
					autoTaskRunner.setAutoTask(new GoForward());
				//autoTaskRunner.setAutoTask();
			}
		}
	}
}
