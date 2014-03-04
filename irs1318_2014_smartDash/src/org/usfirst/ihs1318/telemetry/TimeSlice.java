package org.usfirst.ihs1318.telemetry;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TimeSlice {
	
	private long startTime =0;
	private Map<String,DataValue> data= new TreeMap<String,DataValue>();

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public void addRawValue(RawData rd) {
		if (!data.containsKey(rd.getName())) {
			DataValue dv = new DataValue();
			dv.setName(rd.getName());
			data.put(dv.getName(), dv);
		}
		data.get(rd.getName()).addRawData(rd);
	}
	
	public String printCsv(long runStartTime, Set<String> dataFields) {
		long time = getStartTime() - runStartTime;
		String ret = ""+time ;
		for (String key: dataFields) {
			if (data.containsKey(key)) {
				ret += ", "+ data.get(key).getPublishedValue();
			} else {
				ret += ", ";
			}
		}
		return ret;
		
	}

}
