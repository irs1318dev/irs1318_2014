package irs2014.autonomous;

import java.util.Vector;

import irs2014.autonomous.tasks.AutonomousMode;
import irs2014.components.RobotComponentBase;

public class AutoTaskRunner extends RobotComponentBase
{
	//Variables
	private static AutoTask currentTask;//will either be null, or a reference to a current task.
	private Vector completedTasks;//contains completed tasks.
	private AutoInputMap autoInputMap;
	
	public void robotInit()
	{//Construction, runs once.
		System.out.println("Automomous task started. ((Friendly Warning: Too many umlauts!?))");
		completedTasks = new Vector();
		autoInputMap = new AutoInputMap(this);
	}
	
	public void autonomousPeriodic()
	{
		if(currentTask == null)
			setAutoTask(new AutonomousMode());
	}
	
	public void teleopPeriodic() 
	{
		autoInputMap.update();
		System.out.println("Ran the autoInputMap update");
		if(currentTask != null)
		{
			currentTask.run();
			if(currentTask.isDone())
			{//If the task's isDone method returned true, meaning it's done...
				completedTasks.addElement(currentTask);
				currentTask = null;
				System.err.println("You shouldn't see this after only one pass.");
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
	
	public static void setAutoTask(AutoTask task)
	{//Changed so that it will initialize the task if available, but you can still set the task to null.
		if(task != null)
		{
			task.init();	
		}
		currentTask = task;
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
