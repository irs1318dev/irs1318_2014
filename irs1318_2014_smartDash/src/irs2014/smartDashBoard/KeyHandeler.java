package irs2014.smartDashBoard;

import java.util.ArrayList;
import java.util.HashMap;

public class KeyHandeler {

	HashMap<String, String> keyMappings;
	ArrayList<String> keyOrder;
	
	static KeyHandeler instance;
	
	public static KeyHandeler getInstance() {
		if(instance == null) {
			instance = new KeyHandeler();
		}
		
		return instance;
	}
	
	private KeyHandeler() {
		keyMappings = new HashMap<String, String>();
		Integer value = new Integer(0);
		
//Add all values into table so they are more readable
//not required for display

		this.keyMappings.put("dt.re", "ENCODER R ");
		this.keyMappings.put("dt.le", "ENCODER L ");
		this.keyMappings.put("dt.rsp", "SETPOINT R ");
		this.keyMappings.put("dt.lsp", "SETPOINT L ");
		this.keyMappings.put("dt.rps", "VELOCITY R ");
		this.keyMappings.put("dt.lps", "VELOCITY L ");
		this.keyMappings.put("cl.bp", "BALL PREST ");
		this.keyMappings.put("cl.ms", "COL SPOINT ");
		this.keyMappings.put("cl.css", "COL SOL ST ");
		this.keyMappings.put("s.pss", "PRESS ST ");
		this.keyMappings.put("s.cmss", "MID SOL ST ");
		this.keyMappings.put("s.ciss", "INN SOL ST ");
		this.keyMappings.put("s.coss", "OUT SOL ST ");
		this.keyMappings.put("s.cass", "SHT ANG ST ");
		this.keyMappings.put("i.ss", "TRIG SET ");
		this.keyMappings.put("t.m", "Timer: ");
		
		keyOrder = new ArrayList<String>();
		
//this keeps the output in the right order
		this.keyOrder.add("i.ss");
		this.keyOrder.add("cl.bp");
		this.keyOrder.add("s.pss");
		this.keyOrder.add("s.cass");
		this.keyOrder.add("dt.rsp");
		this.keyOrder.add("dt.lsp");
		this.keyOrder.add("dt.rps");
		this.keyOrder.add("dt.lps");
		this.keyOrder.add("dt.re");
		this.keyOrder.add("dt.le");
		this.keyOrder.add("cl.ms");
		this.keyOrder.add("cl.css");
		this.keyOrder.add("s.cmss");
		this.keyOrder.add("s.ciss");
		this.keyOrder.add("s.coss");
		this.keyOrder.add("t.m");
	}
	
	public String makeReadable(String key){
		return keyMappings.get(key).toString();
	}
	
	public HashMap<String, String> getReadabilityMap() {
		return keyMappings;
	}
	
	public ArrayList<String> getKeyOrder() {
		return keyOrder;
	}
}
