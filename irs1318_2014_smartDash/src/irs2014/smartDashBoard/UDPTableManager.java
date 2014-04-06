package irs2014.smartDashBoard;

import java.net.SocketException;
import java.util.ArrayList;

public class UDPTableManager {
	
	private static UDPTableManager instance;
	
	public static UDPTableManager getInstance() {
		if(instance == null){
			try {
				instance = new UDPTableManager();
				instance.init();
			} catch (SocketException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	private ArrayList<String> keys;
	private UDPListener udpListener;
	
	private UDPTableManager() throws SocketException{
		keys = new ArrayList<String>();
	}
	
	private void init(){
		udpListener = new UDPListener();
		Thread listenerThread = new Thread(udpListener);
		listenerThread.start();
	}
	
	public void addUdpKeyListener(UdpKeyListener listener) {
		udpListener.addUdpKeyListener(listener);
	}
	
	public void removeUdpKeyListener(UdpKeyListener listener) {
		udpListener.removeUdpKeyListener(listener);
	}


	public DataPacket get(String key) {
		return udpListener.get(key);
	}

}
