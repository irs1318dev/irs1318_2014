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
	
	public void see() throws Exception {
		Thread cameraThread = new Thread(new CameraRunnable());
		cameraThread.start();
	}

}
