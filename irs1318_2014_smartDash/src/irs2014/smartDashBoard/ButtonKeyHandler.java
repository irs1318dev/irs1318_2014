package irs2014.smartDashBoard;

import java.util.ArrayList;
import java.util.HashMap;

public class ButtonKeyHandler {
	
	HashMap<String, String> buttonMap;
	ArrayList<String> buttonOrder;
	
	public ButtonKeyHandler(){
		buttonMap = new HashMap<String, String>();
		buttonOrder = new ArrayList<String>();
		
		buttonMap.put(ButtonRef.SET_ONE_SHOT, "tr.so");
		buttonMap.put(ButtonRef.SET_THREE_SHOT, "tr.st");
		buttonMap.put(ButtonRef.SET_FIVE_SHOT, "tr.sf");
		
		buttonMap.put(ButtonRef.FIVE_PISTON_SHOT, "s.fs");
		buttonMap.put(ButtonRef.THREE_PISTON_SHOT, "s.ts");
		buttonMap.put(ButtonRef.ONE_PISTON_SHOT, "s.os");
		
		buttonMap.put(ButtonRef.EXTEND_SHOOTER, "s.es");
		buttonMap.put(ButtonRef.RETRACT_SHOOTER, "s.rs");
		
		buttonOrder.add(ButtonRef.SET_ONE_SHOT);
		buttonOrder.add(ButtonRef.SET_THREE_SHOT);
		buttonOrder.add(ButtonRef.SET_FIVE_SHOT);
		
		buttonOrder.add(ButtonRef.EXTEND_SHOOTER);
		buttonOrder.add(ButtonRef.RETRACT_SHOOTER);
		
		buttonOrder.add(ButtonRef.ONE_PISTON_SHOT);
		buttonOrder.add(ButtonRef.THREE_PISTON_SHOT);
		buttonOrder.add(ButtonRef.FIVE_PISTON_SHOT);
	}
	
	public ArrayList<String> getButtonOrder(){
		return buttonOrder;
	}
	
	public HashMap<String, String> getButtonMap(){
		return buttonMap;
	}

}
