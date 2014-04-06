package irs2014.smartDashBoard;

public class DataPacket {

	private long timeStamp;
	private int messageNumber;
	private String key;
	private String value;
	
	public DataPacket(String key, int messageNumber, String value){
		this.timeStamp = System.currentTimeMillis();
		this.key = key;
		this.messageNumber = messageNumber;
		this.value = value;
	}
	
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getMessageNumber() {
		return messageNumber;
	}
	public void setMessageNumber(int messageNumber) {
		this.messageNumber = messageNumber;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
