package irs2014.smartDashBoard.UI;

import irs2014.smartDashBoard.constants.ReferenceData;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class NTPanel  extends JPanel  {

	private ArrayList<InputPanel> enabledPanels;
	public NTPanel(JFrame frame) {
		this.setPreferredSize(new Dimension(frame.getWidth() *2 / 7, frame.getHeight()));
		this.setBackground(ReferenceData.getInstance().disabledColor);
		this.setForeground(Color.white);
		
		this.enabledPanels = new ArrayList<InputPanel>();
		
		if(false != ReferenceData.getInstance().NTPanelEnabled) {
			this.initialize();
		}
	}
	
	private void initialize() {
		this.removeAll();
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		for(InputPanel c : enabledPanels) {
			this.add(c);
		}
		
		this.invalidate();
	}
	

	public void addPanel(InputPanel panel) {
		this.enabledPanels.add(panel);
		this.initialize();
	}

}
