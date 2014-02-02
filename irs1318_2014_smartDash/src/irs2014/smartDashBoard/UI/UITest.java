package irs2014.smartDashBoard.UI;

import irs2014.smartDashBoard.constants.ReferenceData;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class UITest extends JFrame {

	public UITest() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		this.setForeground(Color.WHITE);
		this.setBackground(ReferenceData.getInstance().disabledColor);
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		UITest ui = new UITest();

	}

}
