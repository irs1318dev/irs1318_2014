package irs2014.autonomous;

import java.util.Vector;

import irs2014.autonomous.tasks.*;
import irs2014.components.RobotComponentBase;

public class AutoTaskRunner extends RobotComponentBase
{
	//Variables
	private static AutoTask currentTask;//will either be null, or a reference to a current task.
	private Vector completedTasks;//contains completed tasks.
	private AutoInputMap autoInputMap;
	
	public void robotInit()
	{//Construction, runs once.
//		System.out.println("Automomous task started. ((Friendly Warning: Too many umlauts!?))");
		completedTasks = new Vector();
		autoInputMap = new AutoInputMap(this);
	}
	
	boolean hasRun = false;
	public void autonomousInit()
	{
		robotInit();
//		currentTask = new AutoOpenDriveShoot();
		currentTask = new AutoOpenDriveShootTwo();
	}
	public void autonomousPeriodic()
	{
		if(currentTask == null && hasRun == false){
			currentTask = new AutoOpenDriveShootTwo();
		}
		if(currentTask != null)
		{
			currentTask.run();
			if(currentTask.isDone())
			{
				completedTasks.addElement(currentTask);
				currentTask = null;
				hasRun = true;
//				System.out.println("Autonomous code is done! Wohoo!");
			}
		}
	}
	
	public void teleopPeriodic() 
	{
		autoInputMap.update();
		if(currentTask != null)
		{
//			System.out.println("AutoTaskRunner before run");
			currentTask.run();
//			System.out.println("AutoTaskRunner after run");
			if(currentTask.isDone())
			{//If the task's isDone method returned true, meaning it's done...
				completedTasks.addElement(currentTask);
				currentTask = null;
//				System.out.println("Task complete.");
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
//			System.out.println("setAutoTask after task.init");
		}
		currentTask = task;
	}
	
	public boolean hasCurrentTask()
	{//Whether or not we actually have a current task
		return currentTask != null && !currentTask.isDone();
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
