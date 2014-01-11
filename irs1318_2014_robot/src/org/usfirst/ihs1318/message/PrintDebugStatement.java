package org.usfirst.ihs1318.message;

import java.util.Enumeration;
import java.util.Hashtable;

public class PrintDebugStatement {
	
	public static final int MAX_LINES = 100;
	public static final int DISCARD_FIRST_LINES = 100;
	private int counter;
    public String[] printOut = new String[MAX_LINES];
    
    private static final Hashtable debuggers = new Hashtable();
    
    /**
     * Singleton instance of the PrintDebugClass for each distinct title 
     * @return
     */
    public static PrintDebugStatement getDebug(String title) {
    	if (!debuggers.contains(title)) {
    		PrintDebugStatement debug = new PrintDebugStatement();
    		debug.setTitle(title);
    		debuggers.put(title, debug);
    	}
    	return (PrintDebugStatement)debuggers.get(title);
    }
    
    public static Enumeration getDebugTitles() {
    	return debuggers.elements();
    }

    /**
     * iterates through all print debug statements, and sends each to the station.
     */
    public static void sendAllToStation() {
    	Enumeration debugTitles = PrintDebugStatement.getDebugTitles();
    	while (debugTitles.hasMoreElements()) {
    		PrintDebugStatement pds = (PrintDebugStatement)debugTitles.nextElement();
    		pds.sendToStation();
    	}
    }

	private String title;

    
    public void reset() {
    	counter = 0;
    	printOut = new String[MAX_LINES];
    }
	
    /**
     * Adds a line after the first DISCARD_FIRST_LINES have been submitted.
     * Stops adding lines after MAX_LINES have been added.
     * @param line
     */
    public void addLine(String line) {
    	if (counter < DISCARD_FIRST_LINES) return;
    	if (counter > DISCARD_FIRST_LINES + MAX_LINES) return;
    	printOut[counter - DISCARD_FIRST_LINES - 1] = line;
    	counter++;
    }
    
    /**
     * Send accumulated information to the driver station System.out and
     * reset when done. 
     * 
     * Each line is pre-pended with a counter value.
     */
    public void sendToStation() {
    	
    	System.out.println(getTitle());
    	for (int i = 0; i < MAX_LINES; i++) {
    	    System.out.println(""+i+DISCARD_FIRST_LINES+","+printOut[i]);
    	}
    	reset();
    }

	public String getTitle() {
		return title;
	}

	private void setTitle(String title) {
		this.title = title;
	}
	
	

}
