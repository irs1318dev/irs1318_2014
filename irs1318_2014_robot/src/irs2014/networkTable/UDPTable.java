package irs2014.networkTable;

import java.io.IOException;

import javax.microedition.io.Datagram;
import javax.microedition.io.UDPDatagramConnection;
import javax.microedition.io.Connector;

public class UDPTable {
	
	private static UDPTable instance;
	
	public static UDPTable getInstance(){
		if(instance == null){
			instance = new UDPTable();
		}
		return instance;
	}
	
	private UDPTable(){}
	
	UDPDatagramConnection datagramSocket;
	int messageCount;
	
	public void init() throws IOException {
		datagramSocket = (UDPDatagramConnection)Connector.open("datagram://:1140");
	}
	
	public void sendData(String key, String value) {
		String data = messageCount +","+key+","+value+"\n";
		byte[] databytes = data.getBytes();
		try {
			Datagram dg = datagramSocket.newDatagram(databytes, databytes.length);
			datagramSocket.send(dg);
		} catch (IOException e) {
		}
	}

}
