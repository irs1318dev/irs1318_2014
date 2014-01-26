package irs2014.autonomous;

import java.util.Vector;
import irs2014.components.RobotComponentBase;

public class AutoTaskRunner extends RobotComponentBase
{
	//Variables
	private AutoTask currentTask;//will either be null, or a reference to a current task.
	private Vector completedTasks;//contains completed tasks.
	private AutoInputMap autoInputMap;
	
	public void robotInit()
	{//Construction, runs once.
		System.out.println("Automomous tasks started. Friendly Warning: Too many umlauts!?");
		completedTasks = new Vector();
		autoInputMap = new AutoInputMap(this);
	}

	public void teleopPeriodic() 
	{
		autoInputMap.update();
		if(currentTask != null)
		{
			currentTask.run();
			if(currentTask.isDone())
			{//If the task's isDone method returned true, meaning it's done...
				completedTasks.addElement(currentTask);
				currentTask = null;
			}
		}
	}
	
	public void cancelCurrentTask()
	{//Aborts everything going on. Call this when driver input is detected during a task.
		if(currentTask != null)
		{
			currentTask.cancel();
		}
		currentTask = null;
	}
	
	
	
	
	/////////////////////////////////////////////
	//Gets, sets, and the like.
    /////////////////////////////////////////////
	
	public void setAutoTask(AutoTask task)
	{//Changed so that it will initialize the task if available, but you can still set the task to null.
		if(task != null)
		{
			task.init();	
		}
		this.currentTask = task;
	}
	
	public boolean hasCurrentTask()
	{//Whether or not we actually have a current task
		return currentTask != null;
	}
	
	public AutoTask getCurrentTask()
	{//Gives us the current task.
		return currentTask;
	}
	
	public Vector getTaskHistory()
	{//Gives us the list of completed tasks.
		return completedTasks;
	}
}
