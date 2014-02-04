package irs2014.smartDashBoard.logging;

import irs2014.smartDashBoard.constants.ReferenceData;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Calendar;

public class SessionLogger {
	File file;
	FileOutputStream fileOutput;
	boolean inSession = false;
	
	
	public SessionLogger() {
		
	}
	
	public void newSession() {
		file = new File(ReferenceData.getInstance().log_file_location + "log.sdblog");
		
		//open file for writing
		try {
			fileOutput = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		inSession = true;
	}
	
	public void endSession() {
		if(inSession) {
			inSession = false;
			
			if(null != fileOutput) {
				
				
				
				//close file
				try {
					fileOutput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}//t/c
				
			}//if
		}//if
		
	}
	
	public void log(String tag, String value) {
		
		try {
			fileOutput.write(("[" + tag + "] " + value + "\n").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}//t/c
		
	}//m
	
}//c
