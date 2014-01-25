package org.usfirst.frc1318.shared.data;

import org.usfirst.frc1318.data.*;

public class KinematicData {
	private static KinematicData data;

	public static KinematicData getInstance() {
		if(data == null)
			data = new KinematicData();
		return data;
	}
	
	public static void clear() {
		data = null;
	}
	
	private boolean tipperReady;
	
	private KinematicData() {
		
	}



	public void setTipperReady(boolean tipperReady) {
		this.tipperReady = tipperReady;
	}
	
	public boolean isTipperReady() {
		return tipperReady;
	}
}
