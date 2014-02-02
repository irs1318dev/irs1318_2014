package irs2014.smartDashBoard.constants;

import irs2014.smartDashBoard.UI.StatusPanel;

import java.awt.Color;
import java.awt.Dimension;

import edu.wpi.first.wpilibj.tables.ITable;

public class ReferenceData {
	static ReferenceData instance;
	static Object lock = new Object();
	
	
	public final Dimension window_size = new Dimension(800, 600);
	
	public final Color activeColor = new Color(128, 128, 0);
	public final Color inactiveColor = new Color(128, 0, 128);
	public final Color disabledColor = Color.DARK_GRAY;
	
	public boolean NTPanelEnabled = true;
	public boolean StatusPanelEnabled = true;
	public boolean cameraPanelEnabled = false;
	public boolean shooterSetpointPanelEnabled = false;
	
	public StatusPanel statusPanel;
	
	public String log_file_location = "";
	
	private ReferenceData() {
		//SINGELTON
	}
	
	public static ReferenceData getInstance() {
		if( null == instance) {
			
			//prevent double initialization
			synchronized(lock) {
				instance = new ReferenceData();
			}
		}
		return instance;
	}
	
}
