package org.usfirst.frc1318.vision;

import org.usfirst.frc1318.data.Point;
import org.usfirst.frc1318.data.Trapezoid;

import edu.wpi.first.wpilibj.networking.NetworkAdditionListener;
import edu.wpi.first.wpilibj.networking.NetworkConnectionListener;
import edu.wpi.first.wpilibj.networking.NetworkListener;
import edu.wpi.first.wpilibj.networking.NetworkTable;

public class PublishValues {

	private static boolean initialized = false;
	private static boolean printOutput = true;

	private static int lastGoodFrame;
	private static Object initMonitor = new Object();
	private static boolean error=true;
	
	public static String TABLE_NAME = "SmartDashboard";

	public static void publishValues(int resolution, int frameCount,
			Trapezoid trapezoid) {

		initialize();

		if (!trapezoid.hasError()) {
			lastGoodFrame = frameCount;
		}

		synchronized (initMonitor) {
			NetworkTable t = NetworkTable.getTable(TABLE_NAME);
			
			t.beginTransaction();
			t.putInt("r", resolution);
			t.putInt("lfc", lastGoodFrame);

			String errorMessage = "";
			error = trapezoid.hasError();
			errorMessage = trapezoid.getErrorMessage();

//			if (frameCount%100==0); {
//				error = !error;
//				if (error) {
//					errorMessage = ""+System.currentTimeMillis();
//				}
//				else {
//					errorMessage = "";
//				}
//			}
			// clear error message
			t.putInt("e", (error?1:0));
			
			t.putString("emsg", (errorMessage!=null?errorMessage:""));
			
			if (!error) {
				// update actual values
				Point mp = trapezoid.findMidpoint();
				t.putDouble("mx", mp.getX());
				t.putDouble("my", mp.getY());
				t.putDouble("h", trapezoid.findHeight());
				t.putDouble("w", trapezoid.findWidth());
				t.putDouble("ar", trapezoid.findAspectRatio());
				t.putDouble("fov", trapezoid.getFieldOfViewOffset());
				t.putInt("s", trapezoid.findFieldSide());
			}
			
			t.putInt("cfc", frameCount); // write this last to key off on the cRio
			t.endTransaction();
		}
		

	}
	

	private static void printValue(String source, String key, Object value) {
		if (printOutput) {
			String output = String.format("%s, %s, %s, %s",System.currentTimeMillis(),source, key, value);
			System.out.println(output);
		}
		
	}
	
	private static int connectionCount;
	private static int cfcCount;
	
	private static void initialize() {

		synchronized (initMonitor) {
			if (!initialized) {

				NetworkTable.setTeam(1318);

				NetworkListener networkListener = new NetworkListener() {

					@Override
					public void valueConfirmed(String key, Object value) {
//						if ("fov".equals(key) || "h".equals(key)) {
//							printValue("confirmed",key, value);
//						} else if ("cfc".equals(key) && (cfcCount++)%50==0) {
//							printValue("confirmed",key,value);
//						}
						
						if ("mm".equals(key)) {
							boolean booleanValue = ((Boolean)value).booleanValue();
							VisionSystem.getCameraRunnable().setMovieMode(booleanValue);
						}
//						printValue("confirmed",key, value);
					}

					@Override
					public void valueChanged(String key, Object value) {
//						if ("fov".equals(key) || "h".equals(key)) {
//							printValue("changed",key, value);
//						} else if ("cfc".equals(key) && (cfcCount++)%50==0) {
//							printValue("changed",key,value);
//						}
						if ("mm".equals(key)) {
							boolean booleanValue = ((Boolean)value).booleanValue();
							VisionSystem.getCameraRunnable().setMovieMode(booleanValue);
						}
					}
				};
				NetworkTable.getTable("t").addListenerToAll(networkListener);
				NetworkAdditionListener networkAdditionListener = new NetworkAdditionListener() {

					@Override
					public void fieldAdded(String key, Object value) {
//						printValue("added",key, value);

					}

				};
				NetworkTable.getTable("t").addAdditionListener(
						networkAdditionListener);

				boolean immediateNotify = true;
				NetworkConnectionListener networkConnectionListener = new NetworkConnectionListener() {

					@Override
					public void disconnected() {
						printValue("disconnected","c1", connectionCount);

					}

					@Override
					public void connected() {
						printValue("disconnected","c1", connectionCount++);

					}
				};
				NetworkTable.getTable("t").addConnectionListener(
						networkConnectionListener, immediateNotify);
				initialized = true;
			}
		}
	}

}
