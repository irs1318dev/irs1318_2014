package org.usfirst.ihs1318.telemetry;

import java.util.Date;

public class RawData {
	private Date timestamp;
	private String name;
	private String rawData;
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRawData() {
		return rawData;
	}
	public void setRawData(String rawData) {
		this.rawData = rawData;
	}
	
	public String toString() {
		return getTimestamp()+","+getName()+","+getRawData();
	}

}
