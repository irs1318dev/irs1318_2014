package org.usfirst.ihs1318.vision;

import org.usfirst.ihs1318.shared.ReferenceData;

/**
 * Given an image from the CameraController, with illuminated pegs, find
 * 
 * 1. Where the grid pattern overlays on top of the main grid.
 * 2. Find the angle that transforms the image into the same plane as the main grid
 * 3. Find the scale factor that transforms the image into the same size as the main grid
 * 
 * This class also allows the operator to specify the row (1,2,3) and peg (1,2,3,4,5,6).
 * 
 * The angle and distance can be used to navigate to the desired peg.
 */
public class GridDetector {
	
	//constants
	public final static int PEGS = 9; //number of pegs
	public final static int PEGS_IN_LINE = 3;
	public final static double PARTICLE_LIMIT = .0002;  //percentile area limit that renders the
	//point as ambiguous to camera error.
	public final static double RADIAL_CORRECTION = 1.024; // correction of error on camera
	private final static int MIN_DISTANCE = 17; //min distance of pegs to be considered aligned
	
	private final static double kPY = .75;
	private final static double kPX = .001; //?
	
	private final static int X_CENTER  = 0; // center where the robot can place a tube
	private final static int Y_MEASURED_LOW = 20;
	
	
	
	
	
	//private static AxisCamera cam = AxisCamera.getInstance();
	//private static ColorImage image;

	private final static double measuredDistance = 3.0;  // measured distance corresponding with a y virtual distance
	private final static double measuredYVirtual = 152.6; 	//double to convert for division
	private final static double measuredXVirtual = 50.0; 	//double to convert for division
	private final static double measuredXDistance = 3.0; 	 //measured distance corresponding to a measured x distance
	//private Timer timer = new Timer();
	    
	    
		public GridDetector() {
		    //cam.writeCompression(0);
		    //cam.writeBrightness(10);
		    //cam.writeResolution(AxisCamera.ResolutionT.k320x240);
		    //tracker.setFirstColorThresholds(8,12,50,100,30,60); //hsl, for different shades of orange.
		    //tracker.setSecondColorThresholds(8,12,50,100,30,60); //hsl, for different shades of orange.	    
		    //tracker.setSecondColorThresholds();
	        //Timer.delay(5.0);
		}
		
		
		//see if camera even works
		/*
		public void runOnce() throws AxisCameraException, NIVisionException
		{
			
			String fileName = "anonworkingname.jpeg";  //change filename
            if (cam.freshImage()) {
            ColorImage image = cam.getImage();
            image.write(fileName);
            image.free();
            }
		}
		*/

		public static double searchForPegs(Particle[] particles) {

			if (true) // (cam.freshImage())

			{
				// assume that you shouldn't call this code if two grids are in
				// view...
				// gets a report on 9 pegs even if they don't exist yet....

				// ParticleAnalysisReport[] reports =
				// maskedImage.getOrderedParticleAnalysisReports(PEGS); // get
				// particle analysis

				// maskedImage.free();

				// remove particles that are percentally to small if not all
				// particles are found

				// int j = 0;

				// for (int i = 0; i < reports.length; i++)

				// {

				// if (reports[i].particleToImagePercent >= PARTICLE_LIMIT)

				// {

				// reports[j] = reports[i];

				// / j++;

				// }

				// }

				// move these particles to my list, as they cannot be modified in

				// particleAnalysisReport

				// Particle[] particleList = new Particle[j];

				Particle[] particleList = particles;

				// for (int i = 0; i < particleList.length; i++)

				// {

				// particleList[i].x = reports[i].center_mass_x;

				// particleList[i].y = reports[i].center_mass_y;

				// }
				int j = particleList.length;
				System.out.println("There are " + j + " real particles found");
				if (particleList.length == 0)
				{
					boolean high;
					boolean low;
					synchronized(ReferenceData.getInstance()) {
						//get buttons
						high = false;
						low = false;
					}
					if (low) {
						//TOOO CLOSEEE
					}
					else if (high)
					{
						//do set stuff
					}
					return -1;
				}
				else if (particleList.length == 1) {
					// close to the peg board, use method 2 to find the distance.
					// this consists of finding how far the peg is from the bottom
					// of the picture
					particleList = correctRadialError(particleList);
					//correct x error first
					boolean low;
					boolean high;
					synchronized(ReferenceData.getInstance()) {
						//get buttons
						 low = false;
						 high = false;
					}
					
					double jY;
					double jX;
					int errorX = particleList[0].x - X_CENTER;
					if (errorX >= 5) {	
						jX = errorX * kPX;
						synchronized(ReferenceData.getInstance()) {
							//write jX and jY = 0
						}
					}
					else {
						if (low) {
							int errorY = Y_MEASURED_LOW - particleList[0].y;
							jY = errorY * kPY;
						}
						else if (high) {
							int errorY = 120 - particleList[0].y;
							jY = errorY * kPY;
						}
						synchronized(ReferenceData.getInstance()) {
							//write jX = 0 and jY
						}
						
					}
					
					
					
					return -1;
				}
				else if (particleList.length > 1)
				{
					int[] divisionPoints = findPegGroups(particleList);
					if (divisionPoints.length == particleList.length)
					{
						//no aligned particles, so we need to find the right particle
						//to track.
						
					}
				}
					return -1;
			}
			return -1;
		}
		public static Particle[] correctRadialError(Particle[] particles) {
			for (int i = 0; i < particles.length; i++)
			{
				particles[i].x = (int) Math.floor(particles[i].x
						* RADIAL_CORRECTION);
				// ugh... it's a constant... I will need another object..
			}
			return particles;
		}
		public static int[] findPegGroups(Particle[] pegCollection) {
			int x_center = pegCollection[0].x;
			int[] divisionPoints = new int[6]; //OVER ESTIMATE	
			int j = 0;
			for (int i = 1; i < pegCollection.length; i++)
			{

				if (Math.abs(x_center - pegCollection[i].x) >= MIN_DISTANCE)
				{
					x_center = pegCollection[i].x;
					divisionPoints[j] = i;
					j++;
				}
			}
			int[] newDivPoint = new int[j+1];
			for (int i = 0; i < j; i++)
				newDivPoint[i] = divisionPoints[i];
			newDivPoint[j] = pegCollection.length ;
			
			return newDivPoint;
		}
	}

