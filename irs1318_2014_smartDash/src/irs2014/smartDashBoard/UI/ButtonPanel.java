package irs2014.smartDashBoard.UI;

import irs2014.smartDashBoard.ButtonKeyHandler;
import irs2014.smartDashBoard.TableManager;
import irs2014.smartDashBoard.constants.ReferenceData;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import edu.wpi.first.wpilibj.tables.ITable;

public class ButtonPanel extends JPanel{
	
	ITable table;
	ButtonKeyHandler keyHandler;
	
	public ButtonPanel(Frame frame){
		super();
		this.setPreferredSize(new Dimension(frame.getWidth()*2/7, frame.getHeight()));
		this.setBackground(ReferenceData.getInstance().disabledColor);
		this.setForeground(Color.black);
		keyHandler = new ButtonKeyHandler();
		this.setLayout(new FlowLayout());
		this.table = TableManager.getInstance().getTable();
		
		final ButtonPanel myButtonPanel = this;
		ActionListener listener = new ActionListener(){
			ButtonPanel anonButtonPanel = myButtonPanel;
			
			@Override
			public void actionPerformed(ActionEvent e){
				if(e.getSource().getClass() == Button.class){
					anonButtonPanel.buttonPressed((Button)(e.getSource()));
				}
			}
		};
		
		
		for(int i = 0; i < keyHandler.getButtonOrder().size(); i++){
			Button temp = new Button(keyHandler.getButtonOrder().get(i));
			temp.addActionListener(listener);
			this.add(temp);
			//temp.setDisplayedMnemonic() => try to set hot-key
		}
		
	}
	
	private void buttonPressed(Button b){
		table.putBoolean(keyHandler.getButtonMap().get(b.getName()), true);
	}

}
