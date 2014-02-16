package irs2014.smartDashBoard;

import java.util.ArrayList;
import java.util.HashMap;

public class ButtonKeyHandler {
	
	HashMap<String, String> buttonMap;
	ArrayList<String> buttonOrder;
	
	public ButtonKeyHandler(){
		buttonMap = new HashMap<String, String>();
		buttonOrder = new ArrayList<String>();
		
		buttonMap.put(ButtonRef.EXTEND_SHOOTER, "s.es");
		buttonMap.put(ButtonRef.RETRACT_SHOOTER, "s.rs");
		buttonMap.put(ButtonRef.EXTEND_COLLECTOR, "c.ec");
		buttonMap.put(ButtonRef.RETRACT_COLLECTOR, "c.rc");
		
		buttonOrder.add(ButtonRef.EXTEND_COLLECTOR);
		buttonOrder.add(ButtonRef.RETRACT_COLLECTOR);
		buttonOrder.add(ButtonRef.EXTEND_SHOOTER);
		buttonOrder.add(ButtonRef.RETRACT_SHOOTER);
	}
	
	public ArrayList<String> getButtonOrder(){
		return buttonOrder;
	}
	
	public HashMap<String, String> getButtonMap(){
		return buttonMap;
	}

}
