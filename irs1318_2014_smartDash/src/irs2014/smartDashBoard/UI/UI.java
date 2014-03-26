package irs2014.smartDashBoard.UI;

import irs2014.smartDashBoard.constants.ReferenceData;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.usfirst.ihs1318.telemetry.DataProcessing;

@SuppressWarnings("serial")
public class UI extends javax.swing.JFrame {
	BorderLayout borderLayout;
	NTPanel ntpanel;
	
	int width = 800;
	int height = 600;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReferenceData rd = ReferenceData.getInstance();
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				UI.init();
				UI ui = new UI();
			}
		});
	}

	/**
	 * creates the user interface with all panes
	 */
	public UI() {
		super("Driver Station --- IRS 2014	");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Container pane = this.getContentPane();
		
		this.setBackground(Color.DARK_GRAY);
		this.setForeground(Color.WHITE);
		this.setSize(800, 450);
		
		ntpanel = new NTPanel(this);
		this.addInputPanels();
		
		pane.add(new StatusPanel(this), BorderLayout.LINE_START);
		pane.add(ntpanel, BorderLayout.CENTER);
		pane.add(new ButtonPanel(this), BorderLayout.LINE_END);
		
		if(ReferenceData.getInstance().cameraPanelEnabled) {
			//TODO make camera panel
		}
		
		this.pack();
		this.setVisible(true);
		
		//required for display
		if(ReferenceData.getInstance().statusPanel != null) {
			ReferenceData.getInstance().statusPanel.printHashMap();
		}
	}
	
	public void addInputPanels() {
		HashMap <String, String> fieldNames = new HashMap<String, String>();
//		fieldNames.put("spid.up", "Shooter UP");
//		fieldNames.put("spid.dw", "Shooter DOWN");
		fieldNames.put("at.swti", "Shift Wait Time");
		fieldNames.put("at.di", "Drive Forward Distance");
		fieldNames.put("at.adi", "After Drive Forward");
		fieldNames.put("at.as1i", "After Shot 1");
		fieldNames.put("at.cii", "Collector In Time");
		fieldNames.put("at.acii", "After Collector In");
		fieldNames.put("at.si", "AUTO SPEED");
		fieldNames.put("at.npi", "Number of Pistons");
		fieldNames.put("cl.cwi", "Collector Wait Time");
		fieldNames.put("cl.swi", "Shoulder Wait Time");
		
		ArrayList<String> fieldOrder = new ArrayList<String>();
//		fieldOrder.add("spid.up");
//		fieldOrder.add("spid.dw");
		fieldOrder.add("at.swti");
		fieldOrder.add("at.di");
		fieldOrder.add("at.adi");
		fieldOrder.add("at.as1i");
		fieldOrder.add("at.cii");
		fieldOrder.add("at.acii");
		fieldOrder.add("at.si");
		fieldOrder.add("at.npi");
		fieldOrder.add("cl.cwi");
		fieldOrder.add("cl.swi");
		
		HashMap<String, String> overrideNames = new HashMap<String, String>();
//		overrideNames.put("spid.nto", "Pulse Override");
//		overrideNames.put("test.o", "Test Override");
		
		HashMap<String, String> fieldOverride = new HashMap<String, String>();
//		fieldOverride.put("spid.dw", "spid.nto");
//		fieldOverride.put("test.f", "test.o");
		
		this.ntpanel.add(new InputPanel(fieldNames, overrideNames, fieldOverride, fieldOrder));
	}
	
	private static FileWriter fw;
	private static BufferedWriter bw;
	private static boolean writeOutput = false;
	static final SimpleDateFormat sdfFile = new SimpleDateFormat("yyyy_MMdd_HH_mm_ss");
	static String devfrcPath = "c:/dashboard/";
	static String outputPath = "c:/dashboard_flattened/";
	
	
	public static void init() {
		boolean dontFlatten = false;
		if(fw == null){
			dontFlatten = true;
		}
		if(fw != null){
			try {
				writeOutput = false;
				bw.close();
				fw = null;
				bw = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(!dontFlatten){
			// add method to flatten data 
			File devfrcDir = new File(devfrcPath);
			File[] files = devfrcDir.listFiles();
			for (File file : files) {
				FileReader fr=null;
				FileWriter flattenFw=null;
				try {
					String outputfileName = "flattend_"+ file.getName();
					File outputfile = new File(outputPath,outputfileName);
					if (outputfile.exists()) continue; // skip files that have already been flattened.
					
					if (!outputfile.getParentFile().exists()) {
						outputfile.getParentFile().mkdirs();
					}
					
					flattenFw = new FileWriter(outputfile);
					fr = new FileReader(file);
					DataProcessing dp = new DataProcessing();
					dp.flattenRawData(fr, flattenFw);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (fr!=null) fr.close();
						if (flattenFw!=null) flattenFw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		if (fw == null) {
			try {
				File recordingFile = new File(devfrcPath,"recording_"+sdfFile.format(new Date())+".csv");
				if (!recordingFile.getParentFile().exists()) {
					recordingFile.getParentFile().mkdirs();
				}
				fw = new FileWriter(recordingFile);
				bw = new BufferedWriter(fw);
				writeOutput = true;
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
	private static int  count = 0;
	private static final Object monitor = new Object();
	private static boolean disableWrite = true;
	public static void writeLog(long timestamp, String output) {
		if (disableWrite) return;
		synchronized(monitor) {
		if (writeOutput) {
			try {
				bw.write(sdf.format(new Date(timestamp))+", "+output+"\n");
				if (count%100000==0) {// was 100
					bw.flush();
					count = 0;
				}
				count++;
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		}
	}
}
