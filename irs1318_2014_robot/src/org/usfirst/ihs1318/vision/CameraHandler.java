package org.usfirst.ihs1318.vision;

import org.usfirst.ihs1318.shared.ReferenceData;
import org.usfirst.ihs1318.shared.data.ImageData;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 * Given an image from the CameraController, with illuminated pegs, find
 * 
 * 1. Where the grid pattern overlays on top of the main grid. 2. Find the angle
 * that transforms the image into the same plane as the main grid 3. Find the
 * scale factor that transforms the image into the same size as the main grid
 * 
 * This class also allows the operator to specify the row (1,2,3) and peg
 * (1,2,3,4,5,6).
 * 
 * The angle and distance can be used to navigate to the desired peg.
 */
public class CameraHandler {

	// constants
	public final static int PEGS = 9; // number of pegs
	public final static int PEGS_IN_LINE = 3;
	public final static double PARTICLE_LIMIT = .0002; // percentile area limit
														// that renders the
	// point as ambiguous to camera error.
	
	public final static int ERROR_THRESHOLD = 9;
	public final static double RADIAL_CORRECTION = 1.024; // correction of error
															// on camera
	private final static int MIN_DISTANCE = 17; // min distance of pegs to be
												// considered aligned

	private final static double kPY = .75;
	private final static double kPX = .001; // ?

	private final static int X_CENTER = 0; // center where the robot can place a
											// tube
	private final static int Y_MEASURED_LOW = 20;

	private static AxisCamera cam = AxisCamera.getInstance();
	private static ColorImage image;

	private final static double measuredDistance = 3.0; // measured distance
														// corresponding with a
														// y virtual distance
	private final static double measuredYVirtual = 152.6; // double to convert
															// for division
	private final static double measuredXVirtual = 50.0; // double to convert
															// for division
	private final static double measuredXDistance = 3.0; // measured distance
															// corresponding to
															// a measured x
															// distance
	private Timer timer = new Timer();
	private ImageData imageData;

	
	public void init() {
	
	}
	
	
	public CameraHandler() {
		cam.writeCompression(0);
		cam.writeBrightness(10);
		cam.writeResolution(AxisCamera.ResolutionT.k320x240);
		// tracker.setFirstColorThresholds(8,12,50,100,30,60); //hsl, for
		// different shades of orange.
		// tracker.setSecondColorThresholds(8,12,50,100,30,60); //hsl, for
		// different shades of orange.
		// tracker.setSecondColorThresholds();
		Timer.delay(5.0);
	}

	// see if camera even works
	/*
	 * public void runOnce() throws AxisCameraException, NIVisionException {
	 * 
	 * String fileName = "anonworkingname.jpeg"; //change filename if
	 * (cam.freshImage()) { ColorImage image = cam.getImage();
	 * image.write(fileName); image.free(); } }
	 */
	
	public ImageData getImageData() {
		if (imageData==null) {
			imageData = new ImageData();
		}
		return imageData;
	}

	public void setImageData(ImageData imageData) {
		this.imageData = imageData;
	}
	//
	public  void testParticleReport() throws AxisCameraException, NIVisionException {
		
		int imageRVN;
		
		synchronized (ReferenceData.getInstance()) {

			imageRVN =  ReferenceData.getInstance().getImageData().getRVN();
		}
		
		if (getImageData().getRVN() < imageRVN)
		{
			
			getImageData().setRVN(imageRVN);
			ParticleAnalysisReport[] reports = getImageData().getParticleReport();
			int j = 0;
	
			for (int i = 0; i < reports.length; i++) {
	
				if (reports[i].particleToImagePercent >= PARTICLE_LIMIT)
				{
					reports[j] = reports[i];
					j++;
				}
			}
				
				System.out.println(j+" particles!");
		}
	}

	//
	public  double calculateDistance()
			throws AxisCameraException, NIVisionException {
		int imageRVN;
		synchronized (ReferenceData.getInstance()) {

			imageRVN =  ReferenceData.getInstance().getImageData().getRVN();
		}
		
		if (getImageData().getRVN() < imageRVN)
		{
			getImageData().setRVN(imageRVN);
			// assume that you shouldn't call this code if two grids are in
			// view...
			// gets a report on 9 pegs even if they don't exist yet....

			image = cam.getImage();

			BinaryImage maskedImage = image.thresholdRGB(245, 255, 245, 255, 245, 255);
			image.free();
			ParticleAnalysisReport[] reports = maskedImage
					.getOrderedParticleAnalysisReports(PEGS); // get
			// particle analysis

			maskedImage.free();

			// remove particles that are percentally to small if not all
			// particles are found

			int j = 0;

			for (int i = 0; i < reports.length; i++) {

				if (reports[i].particleToImagePercent >= PARTICLE_LIMIT)
				{
					reports[j] = reports[i];
					j++;
				}
			}
			// move these particles to my list, as they cannot be modified in

			// particleAnalysisReport

			Particle[] particleList = new Particle[j];

			//Particle[] particleList = particles;

			 for (int i = 0; i < particleList.length; i++)
			 {

			 particleList[i].x = reports[i].center_mass_x;

			 particleList[i].y = reports[i].center_mass_y;

			 }
			j = particleList.length;
			System.out.println("There are " + j + " real particles found");
			if (particleList.length == 0) {
				System.out.println("No particles detected");
				return -1;
			} else if (particleList.length == 1) {

				System.out.println("Only one paricle");

				return -1;
			}
			// >= 2 pegs
			else {
				int yVirtual = 0; // 0 means nothing
				particleList = correctRadialError(particleList);
				particleList = mergeSort(particleList); // sort by x_center_mass
														// (x)

				// if particles >= 2. THERE MIGHT BE SOME RARE CASES THAT THIS
				// ISN'T TRUE
				// if one peg is covered or two adjacent pegs are shown ...gotta
				// fix that
				for (int i = 0; i < particleList.length; i++) {
					System.out.println("particle[" + i + "].x = "
							+ particleList[i].x);
					System.out.println("particle[" + i + "].y = "
							+ particleList[i].y);
				}
				System.out.println("******");
				int[] divisionPoints = findPegGroups(particleList);
				// an array that contains the index of division points in the
				// particleList
				boolean foundPair = false;

				// find a pair of points...
				int priorPoint = 0; // 0th index is considered a division point
				for (int i = 0; i < divisionPoints.length; i++) {
					// 2 pegs in alignment
					if (Math.abs(priorPoint - divisionPoints[i]) == 2) { // [a,b,b,c]
						yVirtual = particleList[priorPoint].y
								- particleList[priorPoint + 1].y; // atleast 2
																	// particles
						foundPair = true;

					}
					// if 3 pegs are in alignment, then only the distance
					// between two pegs nearest to each other is needed..
					else if (Math.abs(priorPoint - divisionPoints[i]) == PEGS_IN_LINE) {
						Particle[] ySortedList = new Particle[PEGS_IN_LINE];
						ySortedList = copyOfRange(particleList, priorPoint,
								divisionPoints[i]);
						selectionSort(ySortedList); // sort y to find adjacent
													// particles
						yVirtual = ySortedList[0].y - ySortedList[1].y; // adjacent
																		// particles
																		// that
																		// are
																		// the
																		// lowest
						foundPair = true;
						break;
					}

					priorPoint = divisionPoints[i];

				}
				if (foundPair) {
					yVirtual = Math.abs(yVirtual);
					System.out
							.println("The virtual distance between any 2 particles is about:"
									+ yVirtual);
					double trueDistanceAway = CalculateDistanceFromPeg(yVirtual);
					System.out.println("Y distance away at " + yVirtual
							+ " pixels is " + trueDistanceAway + ".");
					return trueDistanceAway;
				} else {
					System.out.println("Failed to find aligned pegs");
				}
				return -1;
			}
			// else if (reports.length % 2 == 1) uhh.... this could be a problem
			// later when grouping particles
		} else {
			return -1;
		}
	}
	
	
	//search for pegs: MUST BE LESS THAN A 2 meter distance
	//ASSUMPTIONS ABOUT PEGS ARE BASED ON THE ABOVE DISTANCE that if a peg division is centered there shouldnt be any other pegs in picture
	/*
	if pegs == 1 {
	 CENTER();
	}
	if there are pegs > 2 (with only 2 distinct pegs lines (as with high/low, its not possible to determine left vs.right) 
	 and the peg must be in the field of view) {
	 identify the middle peg, as it is higher up than the other two pegs
	 if a low button is hit, move left or right
	 else if its the middle peg move left or right
	 this should occur until only one peg is left on the screen (or two aligned pegs, one division point)
	 when there is one division point call CENTER();

	 //a second METHOD using a 3 x 3 button box is given as a simpler alternative to high/low:
	 if pegs > 2 and doesn't matter how many (but the desired peg must be in the field of view):
	 
	 if the desired peg is left, move left
	 if the desired peg is right, move right
	 if the desired peg is middle, move left or right
	 continue until there is one peg left or two aligned (one division point) then call CENTER();
	}
	 
	CENTER() {
	if there is one division point of pegs and it is unaligned, center it
	else if it is centered, drive forward 'z' meters depending on high or low peg / height
	}
	*/
	public  double searchForPegs(Particle[] particles) {

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
					System.out.println("To close!");
				}
				else if (high)
				{
					System.out.println("I'ma drive forward!");
					//do set stuff
					//drive x encoder ticks
				}
				return -1;
			}
			else if (particleList.length == 1) {
				// close to the peg board, use method 2 to find the distance.
				// this consists of finding how far the peg is from the bottom
				// of the picture
				particleList = correctRadialError(particleList);
				center(particleList);
				//correct x error first
				
				
				
				
				return -1;
			}
			else if (particleList.length > 1)
			{
				int[] divisionPoints = findPegGroups(particleList);

				//center based on the division point
			}
				return -1;
		}
		return -1;
	}

	// calculates the Y distance from the pegs using the Y virtual difference
	// from a saved position
	public  double CalculateDistanceFromPeg(int yVirtual) { // assumes
																	// proportionality
																	// theta
																	// should be
																	// small for
																	// camera

		return measuredDistance
				* calculateScaleFactor(yVirtual, measuredYVirtual);

	}

	public  void center(Particle[] particleList) {
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
		if (errorX >= ERROR_THRESHOLD) {	
			jX = errorX * kPX * -1.0;  //if error is positive we want to move left, not right!
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
				int errorY = 120 - particleList[0].y; //120 is top of screen in 360x240 mode
				jY = errorY * kPY;
			}
			synchronized(ReferenceData.getInstance()) {
				//write jX = 0 and jY
			}
			
		}
	}
	public  double calculateScaleFactor(int virtual, double measuredValue) {
		return measuredValue / virtual; // inversely proportional
	}

	public  double calculateXDistance(int xVirtual) { // might not be
															// logical as I
															// don't know
															// whether there's a
															// proportion
		// or inverse proportion
		return measuredXDistance
				* calculateScaleFactor(xVirtual, measuredXVirtual);
	}

	public  Particle[] correctRadialError(Particle[] particles) {
		for (int i = 0; i < particles.length; i++) {
			particles[i].x = (int) Math.floor(particles[i].x
					* RADIAL_CORRECTION);
			// ugh... it's a constant... I will need another object..
		}
		return particles;
	}

	// sorting routine if needed, change to how the reports[] are
	// sort particles using mergesort by x_center_mass
	public  Particle[] mergeSort(Particle[] arr) {
		if (arr.length <= 1)
			return arr;
		int mid = arr.length / 2;
		Particle[] temp = copyOfRange(arr, 0, mid);
		Particle[] temp2 = copyOfRange(arr, mid, arr.length);
		temp = mergeSort(temp);
		temp2 = mergeSort(temp2);
		return merge(temp, temp2);
	}

	public  Particle[] merge(Particle[] arr1, Particle[] arr2) {
		Particle[] result = new Particle[arr1.length + arr2.length];
		int i = 0, j = 0, k = 0;
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i].x < arr2[j].x) {
				result[k] = arr1[i];
				i++;
			} else {
				result[k] = arr2[j];
				j++;
			}
			k++;
		}
		// only one will run
		while (i < arr1.length) {
			result[k] = arr1[i];
			i++;
			k++;
		}
		while (j < arr2.length) {
			result[k] = arr2[j];
			j++;
			k++;
		}
		return result;
	}

	// for y, it is simpler
	public  void selectionSort(Particle[] array) {
		int location;
		int least;
		Particle temp;
		for (int i = 0; i < array.length; i++) {
			location = i;
			least = array[i].y;
			for (int j = i + 1; j < array.length; j++) {
				if (least > array[j].y) {
					least = array[j].y;
					location = j;
				}
			}
			temp = array[i];
			array[i] = array[location];
			array[location] = temp;
		}
	}

	public  Particle[] copyOfRange(Particle[] arr, int start, int end) {
		if (end - start == 0) // null list
			return null;
		Particle[] temp = new Particle[end - start];
		for (int i = start; i < end; i++) {
			temp[i - start] = new Particle();
			temp[i - start].x = arr[i].x;
			temp[i - start].y = arr[i].y;
		}
		return temp;
		// does what Arrays.copyOfRange does
		// makes a new array from elements start to begin
	}

	// finds peg pairs
	// returns an array of the points of which the pegs differ
	// starting with the differing peg
	// [30, 50] =x , will give 1 and 2 as the division point, where 0 is implied
	// number of divisions cannot exceed 3 by definition (3 groups of pegs)
	// if looking at one board....
	// assumes number of pegs >= 2
	public  int[] findPegGroups(Particle[] pegCollection) {
		int x_center = pegCollection[0].x;
		int[] divisionPoints = new int[6]; // OVER ESTIMATE
		int j = 0;
		for (int i = 1; i < pegCollection.length; i++) {

			if (Math.abs(x_center - pegCollection[i].x) >= MIN_DISTANCE) {
				x_center = pegCollection[i].x;
				divisionPoints[j] = i;
				j++;
			}
		}
		int[] newDivPoint = new int[j + 1];
		for (int i = 0; i < j; i++)
			newDivPoint[i] = divisionPoints[i];
		newDivPoint[j] = pegCollection.length;

		return newDivPoint;
	}
}