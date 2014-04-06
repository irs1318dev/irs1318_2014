package irs2014.smartDashBoard;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UDPListener implements Runnable{
	
	private DatagramSocket socket;
	private DatagramPacket recievePacket;
	private byte[] recieveData;
	private Object monitor = new Object();
	
	private Map<String, DataPacket> map;
	
	public void init() throws SocketException{
		recieveData = new byte[128];
		socket = new DatagramSocket(1140);
		keyListeners = new HashSet<UdpKeyListener>();
//		recievePacket = new DatagramPacket(recieveData, recieveData.length);
		map = new HashMap<String, DataPacket>();
	}
	
	@Override
	public void run() {
		while(true){
			recievePacket = new DatagramPacket(recieveData, recieveData.length);
			try {
				socket.receive(recievePacket);
				String data = new String(recievePacket.getData());
			} catch (IOException e) {}
		}
	}
	
	private Set<UdpKeyListener> keyListeners;
	public void addUdpKeyListener(UdpKeyListener listener) {
		keyListeners.add(listener);
	}
	
	public void removeUdpKeyListener(UdpKeyListener listener) {
		keyListeners.remove(listener);
	}
	private void put(String key, DataPacket data){
		synchronized(monitor){
			if(map.containsKey(key)){
				map.remove(key);
			}
			map.put(key, data);
		}
		for (UdpKeyListener listener : keyListeners) {
			listener.udpArrived(key);
		}
	}
	
	
	public DataPacket get(String key){
		synchronized(monitor){
			return map.get(key);
		}
	}
	
	private DataPacket decode(String data){	//TODO: unit test 
		int endOfData = data.indexOf("\n");
		if(endOfData < 0){
			return null;
		}
		String actualData = data.substring(0, endOfData);
		String[] dataArr = actualData.split(",");
		String key = dataArr[0];
		int messageNum = Integer.parseInt(dataArr[1]);
		String value = dataArr[2];
		return new DataPacket(key, messageNum, value);
	}

}
