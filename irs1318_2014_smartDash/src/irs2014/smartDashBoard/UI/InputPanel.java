package irs2014.smartDashBoard.UI;

import irs2014.smartDashBoard.ConnectionListener;
import irs2014.smartDashBoard.TableManager;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import edu.wpi.first.wpilibj.tables.ITable;

public class InputPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3456407891290547726L;
	
	HashMap<String, Checkbox> override;
	ITable table;
	HashMap<String, String> fieldNames;
	HashMap<String, InputField> fields;
	Button submitButton;
	Button unOverrideButton;
	HashMap<String, String> overrideNames;
	
	/**
	 * This is a semi-generic class that updates values in an ITable based on 
	 * user input into text fields
	 * 
	 * 
	 * @param fieldNames
	 * 		a hashmap with keys being keys from the ITable, and values being 
	 * 		the name of the field to show up in the gui
	 */
	public InputPanel(HashMap<String, String> fieldNames, HashMap<String, String> overrideNames, HashMap<String, String> fieldOverride) {
		this.table = TableManager.getInstance().getTable();
//		System.out.println("table from InputPanel = " + table);
		this.override = new HashMap<String, Checkbox>();
		this.fieldNames = fieldNames;
		this.fields = new HashMap<String, InputField>();
		this.overrideNames = overrideNames;
		
		
		//make panels show up in correct layout
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		this.setLayout(new FlowLayout());
		
		for(String s : overrideNames.keySet()){
			Checkbox newBox = new Checkbox(overrideNames.get(s));
			newBox.setState(false);
			override.put(s, newBox);
		}
//		override = new Checkbox("Override");
//		override.setState(false);
//		this.add(override);
		
		//create input fields for all values
		for(String s : fieldNames.keySet()) {
			InputField newField = new InputField(fieldNames.get(s), this);
//			this.add(newField);
			fields.put(s, newField);
		}
		
		for(String s : fieldNames.keySet()){
			this.add(fields.get(s));
			if(override.containsKey(fieldOverride.get(s))){
				this.add(override.get(fieldOverride.get(s)));
			}
		}
		
		final InputPanel myInputPanel = this;
		
		unOverrideButton = new Button("Uncheck Overrides");
		
		ActionListener unOverrideListener = new ActionListener(){

			InputPanel anonInputPanel = myInputPanel;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				anonInputPanel.actionPerformedUnOverride(e);
			}
			
		};
		
		unOverrideButton.addActionListener(unOverrideListener);
		
		this.add(unOverrideButton);
		
		submitButton = new Button("Send to Robot");
		
		ActionListener submitListener = new ActionListener() {
			InputPanel anonInputPanel = myInputPanel;

			@Override
			public void actionPerformed(ActionEvent e) {
				anonInputPanel.actionPerformedSubmit(e);
				
			}};
		submitButton.addActionListener( submitListener );
		
		this.add(submitButton);
		
		ConnectionListener connectListener = new ConnectionListener() {
            InputPanel anonInputPanel = myInputPanel;
			@Override
			public void onConnect() {
				anonInputPanel.onConnect();
			}

			@Override
			public void onDisconnect() {
				anonInputPanel.onDisconnect();
			}
			
		};
		
		TableManager.getInstance().addListener(connectListener);
	}
	
	protected void actionPerformedUnOverride(ActionEvent e) {
		for(String s : override.keySet()){
			override.get(s).setState(false);
		}
	}
	/**
	 * called on click of submitButton
	 * 
	 * this puts values from fields into itable as the (hopefully) correct types
	 *
	 * @Override
	 */
	public void actionPerformedSubmit(ActionEvent e) {
		
//		table.putBoolean(overrideKey, override.getState());
		for(String s : override.keySet()){
			table.putBoolean(s, override.get(s).getState());
		}
		
		//loop through all fields
		for( String s : fields.keySet()) {
			
			//Make sure type is correct and put into itable
			
			//Number
			if (table == null) {
				
				System.out.println("Table has not been initialized");
				
			} else if (table.getValue(s) == (null)) {
				
				System.out.println("value at " + s + " does not exist!");
				
			}else if(table.getValue(s).getClass().equals( Double.class)) {
				
				table.putNumber(s, Double.parseDouble(fields.get(s).getText()));
				
			//Boolean
			}else if ( table.getValue(s).getClass() == Boolean.class ) {
				
				table.putBoolean(s, Boolean.parseBoolean(fields.get(s).getText()));
			
			//String
			}else if (table.getValue(s).getClass() == String.class) {
				
				if(!fields.get(s).getText().equals("")){
					table.putString(s, fields.get(s).getText());
				}
				
			//Other
			}else{
				
				System.out.println("error: Type control in class InputPanel or nonexistant key");
				
			}//i/e
				
		}//f
		
		System.out.println("Table updated from InputPanel");
		
	}//m

	public void onConnect() {
		this.submitButton.setEnabled(true);
		this.table = TableManager.getInstance().getTable();
		
	}
	
	public void onDisconnect() {
		this.submitButton.setEnabled(false);
		
	}

}//c
