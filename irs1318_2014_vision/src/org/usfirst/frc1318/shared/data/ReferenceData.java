package org.usfirst.frc1318.shared.data;

import org.usfirst.frc1318.data.*;

/**
 * A shared data class that can be used for
 * synchronization purposes. Operates with a 
 * singleton object so that it can be locked
 * in a synchronized block.
 * @author violette
 *
 */
public class ReferenceData {
	private static ReferenceData data;
	
	public static ReferenceData getInstance() {
		if(data == null)
			data = new ReferenceData();
		return data;
	}
	
	public static void clear() {
		data = null;
	}

	
	private boolean canFail;
	
	private ReferenceData() {
	}
	
	public boolean isCANFail() {
		return canFail;
	}
	
	public void setCANFail(boolean canFail) {
		this.canFail = canFail;
	}

	
}
