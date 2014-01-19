package irs2014.autonomous;

public interface AutoTask 
{
	void init();//this is the initalization. It will only run once.
	void run();//This is the run method- it will go until it returns false.
	
	boolean isDone();
	void cancel();//This resets some of the data in the case of an emergency stop.
	
}
