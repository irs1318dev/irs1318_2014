package irs2014.smartDashBoard.UI;

import irs2014.smartDashBoard.ConnectionListener;
import irs2014.smartDashBoard.DataPacket;
import irs2014.smartDashBoard.KeyHandeler;
import irs2014.smartDashBoard.TableManager;
import irs2014.smartDashBoard.UDPTableManager;
import irs2014.smartDashBoard.UdpKeyListener;
import irs2014.smartDashBoard.constants.ReferenceData;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

@SuppressWarnings("serial")
public class StatusPanel extends JTextArea {
	StringBuilder builder;
	TableManager tableManager;
	KeyHandeler keyHandeler;

	
	
	public StatusPanel(JFrame frame){
		super();
		
		ReferenceData.getInstance().statusPanel = this;
		
		this.setEditable(false);
		
		this.setAutoscrolls(false);
		
		this.setPreferredSize(new Dimension(frame.getWidth() * 2 / 7, 
				frame.getHeight()));
		
		this.setForeground(frame.getForeground());
		
		keyHandeler = KeyHandeler.getInstance();
		builder = new StringBuilder();
		tableManager = TableManager.getInstance();
		
		final StatusPanel myStatusPanel = this;
		ConnectionListener connectListener = new ConnectionListener() {
            StatusPanel anonStatusPanel = myStatusPanel;
			@Override
			public void onConnect() {
				anonStatusPanel.onConnect();
				
			}

			@Override
			public void onDisconnect() {
				anonStatusPanel.onDisconnect();
				
			}
			
		};
		
		tableManager.addListener(connectListener);
		
		this.printHashMap();
		
		UDPTableManager.getInstance().addUdpKeyListener(new UdpKeyListener() {
			@Override
			public void udpArrived(String key) {
				DataPacket p = UDPTableManager.getInstance().get(key);
				System.out.println(String.format("key=%s value=%s",key,p.getValue()));
			}
		});
	}
	
	public void printHashMap(){
//		System.out.println("start of print hash map "+ System.currentTimeMillis());
		this.builder.setLength(0);
		this.builder.append("VALUES FROM BOT \n \n");
		
		ArrayList<String> keys = keyHandeler.getKeyOrder();

//		System.out.println("start of for keys loop "+ System.currentTimeMillis());
		for(String key : keys) {

			String message = KeyHandeler.getInstance().getReadabilityMap().get(key);
			Object stored;
			String value;
			
			if(message == null) {
				continue;
			}else {
				stored = null;
				
				if(tableManager.getTable() != null){
					try {
						stored = tableManager.getTable().getValue(key);
					} catch (TableKeyNotDefinedException  ex) {
//						ex.printStackTrace();
					}
				}
				if (null != stored) {
					value = stored.toString();
				}else{
					value = "NF";
				}
				
				builder.append(message + "\t" + value + "\n");
			}
			
		}//for
//		System.out.println("end of for keys loop "+ System.currentTimeMillis());
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				StatusPanel.this.setText(StatusPanel.this.builder.toString());
//				System.out.println("end of set text "+ System.currentTimeMillis());
				StatusPanel.this.invalidate();
//				System.out.println("end of print hash map "+ System.currentTimeMillis());
			}
			
		});
		
		
	}//printhashmap

	
	public void onConnect() {
		this.setBackground(ReferenceData.getInstance().activeColor);
	}

	
	public void onDisconnect() {
		this.setBackground(ReferenceData.getInstance().inactiveColor);
	}
	
	

}//class
