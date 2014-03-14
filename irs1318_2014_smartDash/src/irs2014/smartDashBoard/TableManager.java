package irs2014.smartDashBoard;

import irs2014.smartDashBoard.UI.UI;
import irs2014.smartDashBoard.UI.tests.MockTable;
import irs2014.smartDashBoard.constants.ReferenceData;
import irs2014.smartDashBoard.robot.Robot;

import java.util.ArrayList;
import java.util.HashMap;

import edu.wpi.first.wpilibj.tables.IRemote;
import edu.wpi.first.wpilibj.tables.IRemoteConnectionListener;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;


/**
 * 
 * This class is simply a table listener that maintains a table of values
 * and provides access to this table to other classes 
 * 
 * @author Graham
 *
 */
public class TableManager implements ITableListener{
	
//singleton stuff
	private static TableManager instance;
	
	public static TableManager getInstance() {
		if(instance == null){
			instance = new TableManager();
		}
		return instance;
	}

//non-singleton stuff
	
	HashMap<String, Object> map;
	ArrayList<String> keys;
	ArrayList<ConnectionListener> listeners;
	boolean connected;
	ITable table;
	
	private TableManager() {
		map = new HashMap<String, Object>();
		keys = new ArrayList<String>();
		
		//this.table = new MockTable();
		
		this.listeners = new ArrayList<ConnectionListener>();
		
		Robot.setTeam(1318);
		Robot.addConnectionListener(new IRemoteConnectionListener()
		{
			@Override
			public void connected(IRemote arg0)
			{
				table_connected();
				System.out.println("Table Connected: " + arg0.toString());
			}

			@Override
			public void disconnected(IRemote arg0)
			{
				table_disconnected();
				System.out.println("Table Disconnected: " + arg0.toString());
			}

			
		}, true);
		Robot.getTable().addTableListener(this);
		
	}
	
	@Override
	/**
	 * adds values to hash map and updates old ones
	 * this will work with unexpected keys and display them, but they 
	 * will not be sorted
	 */
	public void valueChanged(ITable source, String key, Object value,
			boolean isNew) {
//		System.out.println("start of value changed "+System.currentTimeMillis());

		if(table != null) {
//			System.out.println("start of table if "+System.currentTimeMillis());
			if(table.getValue(key) == null) {
//				System.out.println("start of table value null "+System.currentTimeMillis());
				keys.add(key);
//				System.out.println("end of table value null "+System.currentTimeMillis());
			}
//			System.out.println("end of table if "+System.currentTimeMillis());
		}
			if(key != null && value != null){
//				System.out.println("start of key null "+System.currentTimeMillis());
				UI.writeLog(System.currentTimeMillis(), key + ", " + value.toString());
//				System.out.println("end of key null "+System.currentTimeMillis());
			}
				
		//required for display
		if(ReferenceData.getInstance().statusPanel != null) {
//			System.out.println("start of status panel not null "+System.currentTimeMillis());
			ReferenceData.getInstance().statusPanel.printHashMap();
//			System.out.println("end  of status panel not null "+System.currentTimeMillis());
		}
//		System.out.println("end of value changed "+System.currentTimeMillis());
	}
	
	
//called on each event (self explanitory)
	private void table_disconnected() {
		this.connected = false;
		
		//tell listeners
			for(ConnectionListener listener : listeners) {
			
				listener.onDisconnect();
			}
	}
	
	private void table_connected() {
		this.connected = true;
		
		this.table = Robot.getTable();
		
		//tell listeners
			for(ConnectionListener listener : listeners) {
				listener.onConnect();
		}
	}
	
//Getters and setters
	public ArrayList<String> getKeys() {
		return keys;
	}
	
	public ITable getTable() {
		return table;
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public HashMap<String, Object> getHashMap() {
		return map;
	}
	
//listener stuff
	public void addListener(ConnectionListener listener) {
		this.listeners.add(listener);
		
		if(this.isConnected()) {
			listener.onConnect();
		} else {
			listener.onDisconnect();
		}
	}
}
