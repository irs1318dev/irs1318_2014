package org.usfirst.ihs1318.telemetry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class DataProcessing {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
	
	private int timeSlice = 100; // 1/10 sec
	private Map<Long,TimeSlice> dataBuckets = new TreeMap<Long,TimeSlice>();
	private Set<String> dataFields = new TreeSet<String>();
	
	private long runStartTime =0;
	public void flattenRawData(Reader input, Writer output) throws IOException, ParseException {
		
		// read lines
		// double: "10:45:37.305, wLR, -0.11842361585790721"
		// boolean: "10:45:37.306, bot, false"
		// string: "10:45:37.306, cFlip, Backward"
		// enum: "10:45:37.306, wrist, 1"
		BufferedReader br = new BufferedReader(input);
		BufferedWriter bw = new BufferedWriter(output);
		String s;
		while ((s=br.readLine())!=null) {
//			System.out.println(s);
			String[] fields = s.split(",");
			Date d = sdf.parse(fields[0]);
			if (runStartTime==0) {
				runStartTime = d.getTime();
			}
			String name = fields[1].trim();
			String rawData = fields[2].trim();
			RawData rd = new RawData();
			rd.setTimestamp(d);
			rd.setName(name);
			rd.setRawData(rawData);
			addRawData(rd);
//			System.out.println(d.getTime()/timeSlice); // every 1/10 sec
		}
		// write output
		String heading = "time";
		for (String field : dataFields) {
			heading += ", "+ field;
		}
		System.out.println(heading);
		bw.write(heading+"\n");
		bw.flush();

		int count = 0;
		for (Long time: dataBuckets.keySet()) {
			String str = dataBuckets.get(time).printCsv(runStartTime, dataFields);
			if (count<10) System.out.println(str);
			bw.write(str+"\n");
		}
		bw.flush();
		
	}
	
	private void addRawData(RawData rd) {
		Long timeBucket = new Long(rd.getTimestamp().getTime()/timeSlice);
		if (!dataBuckets.containsKey(timeBucket)) {
			TimeSlice ts = new TimeSlice();
			ts.setStartTime(timeBucket*timeSlice);
			dataBuckets.put(timeBucket, ts);
		}
		dataBuckets.get(timeBucket).addRawValue(rd);
		dataFields.add(rd.getName());
	}

}
