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
	
	private static CalcRunnable calcRunnable;
	public static CalcRunnable getCalcRunnable() {
		if (calcRunnable==null) {
			calcRunnable = new CalcRunnable();
		}
		return calcRunnable;
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
		Thread calcThread = new Thread(getCalcRunnable());
		Thread cameraThread = new Thread(getCameraRunnable());
		getCalcRunnable().setCameraRunnable(getCameraRunnable());
		getCalcRunnable().setResolution(getCameraRunnable().getResolution());
		calcThread.start();
		cameraThread.start();
	}

}
