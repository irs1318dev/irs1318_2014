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
		if(false)//ReferenceData.getInstance().getUserInputData().getAllActive,,,,?
		{
			if(autoTaskRunner.hasCurrentTask())
			{
				autoTaskRunner.cancelCurrentTask();
			}
			if(!autoTaskRunner.hasCurrentTask())
			{
				//If(someButton.isDown)
				autoTaskRunner.setAutoTask();
			}
		}
	}
}
