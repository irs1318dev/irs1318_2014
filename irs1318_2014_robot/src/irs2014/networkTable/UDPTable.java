package irs2014.networkTable;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPTable {
	
	private static UDPTable instance;
	
	public static UDPTable getInstance(){
		if(instance == null){
			instance = new UDPTable();
		}
		return instance;
	}
	
	private UDPTable(){}
	
	DatagramSocket datagramSocket;
	int messageCount;
	
	public void init() throws SocketException{
		datagramSocket = new DatagramSocket(1140);
		datagramSocket.setBroadcast(true);
	}
	
	public void sendData(String key, String value) {
		String data = messageCount +","+key+","+value+"\n";
		byte[] databytes = data.getBytes();
		DatagramPacket p = new DatagramPacket(databytes,databytes.length);
		try {
			datagramSocket.send(p);
		} catch (IOException e) {
		}
	}

}
