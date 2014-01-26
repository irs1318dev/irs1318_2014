package org.usfirst.frc1318.vision;

public class VisionSystem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VisionSystem vs = new VisionSystem();
		try {
			vs.see();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static CameraRunnable cr;
	public static CameraRunnable getCameraRunnable() {
		if (cr==null) {
			cr = new CameraRunnable();
			cr.setResolution(320);
			cr.setFramesPerSecond(10);
		}
		return cr;
	}
	
	public void see() throws Exception {
		Thread cameraThread = new Thread(getCameraRunnable());
		cameraThread.start();
	}

}
