package org.usfirst.ihs1318.telemetry;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.junit.Test;

import junit.framework.TestCase;

public class DataProcessingTest extends TestCase {
	String datapath = "c:/dashboard/";
	
	@Test
	public void testReadDatafile() throws Exception {

		for (int i = 1; i<=10; i++ ) {
			String inputfile = "recording"+i+".csv";
			FileReader fr= new FileReader(new File(datapath,inputfile));
			DataProcessing dp = new DataProcessing();
			String outputfile = "flattend_"+ inputfile;
			FileWriter fw = new FileWriter(new File(datapath,outputfile));
			dp.flattenRawData(fr, fw);
			fr.close();
			fw.close();
		}
	}

	@Test
	public void testReadDevFrcData() throws Exception {
		String devfrcPath = "c:/devfrc/2014_practice_data";
		String outputPath = "c:/devfrc/2014_practice_data_flattened";
		File devfrcDir = new File(devfrcPath);
		File[] files = devfrcDir.listFiles();
		
		for (File file : files) {
			FileReader fr= new FileReader(file);
			DataProcessing dp = new DataProcessing();
			String outputfile = "flattend_"+ file.getName();
			FileWriter fw = new FileWriter(new File(outputPath,outputfile));
			dp.flattenRawData(fr, fw);
			fr.close();
			fw.close();
		}
		
		
	}
}
