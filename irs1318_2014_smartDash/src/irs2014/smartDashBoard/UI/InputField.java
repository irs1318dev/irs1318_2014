package irs2014.smartDashBoard.UI;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class InputField extends JPanel{
	String name;
	JTextField inputField;
	JLabel nameLabel;
	
	public InputField(String name, Component parent) {
		this.name = name;
		
		this.setForeground(parent.getForeground());
		this.setBackground(parent.getBackground());
		
		inputField = new JTextField();
		inputField.setColumns(5);
		
		nameLabel = new JLabel(name);
				
		this.add(name, nameLabel);
		this.add(inputField);
	}
	
	public String getText() {
		return inputField.getText();
	}
}
