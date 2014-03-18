package irs2014.autonomous;

import irs2014.autonomous.tasks.*;
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
//		if(ReferenceData.getInstance().getUserInputData().getIsActive())
//		{
//			System.out.println("is not active");
//			if(autoTaskRunner.hasCurrentTask())
//			{//Gives the user back control
//				autoTaskRunner.cancelCurrentTask();
//			}
			
			
			if(!autoTaskRunner.hasCurrentTask())
			{//sets the current macro, if applicable
//				System.out.println("About to add the current task");
				if(ReferenceData.getInstance().getUserInputData().getGoForward())
					autoTaskRunner.setAutoTask(new GoForward());
				else if(ReferenceData.getInstance().getUserInputData().getCollectBall())
					autoTaskRunner.setAutoTask(new CollectBall());
				else if(ReferenceData.getInstance().getUserInputData().getEjectBall())
					autoTaskRunner.setAutoTask(new EjectBall());
				//autoTaskRunner.setAutoTask();
				else if(ReferenceData.getInstance().getUserInputData().getClam()){
					System.out.println("before autoTaskRunner set to clam");
					autoTaskRunner.setAutoTask(new Clam());
					System.out.println("after autoTaskRunner set to clam");
				}
				else if(ReferenceData.getInstance().getUserInputData().getUnClam())
					autoTaskRunner.setAutoTask(new UnClam());
			}
//		}
	}
}
