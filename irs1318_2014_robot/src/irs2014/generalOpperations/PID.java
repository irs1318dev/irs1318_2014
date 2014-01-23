package irs2014.generalOpperations;

import edu.wpi.first.wpilibj.Timer;


/**
 * This class is a PID controller with a feedFoward controller that uses the
 * setpoint as input.  
 * 
 * TODO tags mark code that must be commented for testing and uncommented for 
 * deploy
 * 
 * To use pid control:
 * 		set the setpoint
 * 		feed input values regularly
 * 		use output values 
 * 
 * for reference:
 *	 	http://en.wikipedia.org/wiki/PID_controller
 * 		http://en.wikipedia.org/wiki/Feed_forward_(control)
 * 
 * @author Graham
 */

public class PID
{
	//instance constants
	double ki = 0;	//for integral
	double kd = 0;	//for d/dx
	double kp = 0;	//for p
	double kf = 0;	//for something else
	double kFade = 0;
	int clampMode = -1;
	
	//feedback data
	double position = 0;
	double lastPositionError = 0;
	double positionSetpoint = 0;
	
	double velocity= 0;
	double lastVelocityError = 0;
	double velocitySetpoint = 0;
	
	double integral;		//integral of error data in memory
	double slope = 0;			//approximate slope of input.. units in / seconds
	double dt = .001;
	double prevTime;
	double error = 0;
	double curTime = 0;
	double output = 0;
	
	//outputData
	double maxOutput =  10000000;
	double minOutput = -10000000;
	double clampRatio = 1;
	double clampMagnitude = 10000000;
	
	//other vars
	Timer timer;
	double timeStep = .001;
	private double kScale;
	
	//static constants
	public static final int CLAMP_NONE = -1;
	public static final int CLAMP_RATIO = 0;
	public static final int CLAMP_RANGE = 1;
	public static final int CLAMP_MAGNITUDE = 2;
	
	
	public void setKScale(double scale){
		this.kScale = scale;
	}

////////////////////////////////////////////////////////////////////////////////
	/**
	 * This constructor initializes the object and sets constants to affect gain
	 * 
	 * Get rid of memmorySize
	 * 
	 * @param kp scalar for proportional component
	 * @param ki scalar for integral component
	 * @param kd scalar for derivative component
	 * @param kf scalar for feed-foward control
	 */
	public PID(double kp, double ki, double kd, double kf)
	{
		this.ki = ki;
		this.kd = kd;
		this.kp = kp;
		this.kf = kf;
		
		this.clampMode = clampMode;
		
		
		timer = new Timer();
		timer.start();
		prevTime = timer.get();
//		*/
		
		//TODO uncomment^^^^
	}
	
	//second for no feed-forward
	
	/**
	 * This constructor initializes the object and sets constants to affect gain
	 * 
	 * Get rid of memmorySize
	 * 
	 * @param kp scalar for proportional component
	 * @param ki scalar for integral component
	 * @param kd scalar for derivative component
	 */
	public PID(double kp, double ki, double kd)
	{
		this.ki = ki;
		this.kd = kd;
		this.kp = kp;
		
		timer = new Timer();
		timer.start();
		prevTime = timer.get();
//		*/
		
		//TODO uncomment^^^^
	}

////////////////////////////////////////////////////////////////////////////////
	/**
	 * input should be in the same unit as the setpoint.  this method should be
	 * called in a loop and fed feedback data so that the controller works
	 * 
	 * @param input feedback data
	 */
	public void input(double input)
	{
		this.position = input;
		
		//update dt
		curTime = timer.get(); 	
		dt = curTime - this.prevTime;
		
		//To prevent division by zero, output updates at a max of 1kHz
		if(dt >= timeStep)
		{
			this.prevTime = curTime;
			
			this.update();
			this.calculateOutput();
		}
	}
	
	public void inputWithVelocity(double position, double velocity) {
		this.position = position;
		this.velocity = velocity;
	}
	
////////////////////////////////////////////////////////////////////////////////
	
	private void calculateOutput()
	{
		output = kp * error + ki * integral + kd * slope + kf * positionSetpoint;
		output = clamp(output);
	}
	
////////////////////////////////////////////////////////////////////////////////
	/**
	 * this returns the output of the PID controller.  Ideally this should 
	 * eventually be the setpoint
	 * 
	 * @return value to be used
	 */
	public double getOutput()
	{
		return output;
	}
	
//Private methods
////////////////////////////////////////////////////////////////////////////////
	
	//this updates essential values
	private void update()
	{	
		//update calculated values
		updateError();
		updateIntegral();
		updateSlope();
		
	}
	
////////////////////////////////////////////////////////////////////////////////
	
	private double clamp(double value)
	{
		switch(clampMode){
		
		case CLAMP_RANGE:
			
			if(value > maxOutput)
				return maxOutput;
			else if(value < minOutput)
				return minOutput;
			else return value;
			
		case CLAMP_RATIO:
			
			double ratmax = positionSetpoint + positionSetpoint*Math.abs(clampRatio);
			double ratmin = positionSetpoint - positionSetpoint*Math.abs(clampRatio);
			
			if(value > ratmax)
				return ratmax;
			else if(value < ratmin)
				return ratmin;
			else return value;
			
		case CLAMP_MAGNITUDE:
			
			double magmax = positionSetpoint + Math.abs(clampMagnitude);
			double magmin = positionSetpoint - Math.abs(clampMagnitude);
			
			if(value > magmax)
				return magmax;
			else if(value < magmin)
				return magmin;
			else 
				return value;
			
		default:
			return value;
		}
	}

////////////////////////////////////////////////////////////////////////////////

	private void updateError()
	{
		lastPositionError = error;
//		error = position - kScale * positionSetpoint;
		error = position - kScale * positionSetpoint;
		
	}
	
////////////////////////////////////////////////////////////////////////////////
	//This method updates the integral value
	private void updateIntegral()
	{
		integral *= kFade;
		integral += error * dt;
	}
	
////////////////////////////////////////////////////////////////////////////////
	
	//this method uses input and dt to find the rate of change of 
	private void updateSlope()
	{
		slope = (error);
	}
	
////////////////////////////////////////////////////////////////////////////////
//getters and setters///////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////	

////////////////////////////////////////////////////////////////////////////////
	//for not feed forward
	/**
	 * This can be used to adjust "constants" on the fly, possibly for an 
	 * adaptive controller
	 * 
	 * @param kp same as constructor
	 * @param ki same as constructor
	 * @param kd same as constructor
	 */
	public void setConstants(double kp, double ki, double kd)
	{
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
	}
	
////////////////////////////////////////////////////////////////////////////////
	//for feed forward
	/**
	 * This can be used to adjust "constants" on the fly, possibly for an 
	 * adaptive controller
	 * 
	 * @param kp same as constructor
	 * @param ki same as constructor
	 * @param kd same as constructor
	 * @param kf same as constructor
	 */
	public void setConstants(double kp, double ki, double kd, double kf)
	{
		this.kp = kp;
		this.ki = ki;
		this.kd = kd;
		this.kf = kf;
	}

////////////////////////////////////////////////////////////////////////////////
	public void setKp(double kp){this.kp = kp;}
	public void setKi(double ki){this.ki = ki;}
	public void setKd(double kd){this.kd = kd;}
	public void setKf(double kf){this.kf = kf;}
	public void setKFade(double kFade){this.kFade = kFade;}
	public void setMaxOutput(double value){this.maxOutput = value;}
	public void setMinOutput(double value){this.minOutput = value;}
	
////////////////////////////////////////////////////////////////////////////////

	
	public void setClampRange(double min, double max)
	{ 
		this.clampMode = CLAMP_RANGE;
		this.minOutput = min; 
		this.maxOutput = max;
	}
	
	public void setClampRatio(double ratio)
	{
		this.clampMode = CLAMP_RATIO;
		this.clampRatio = ratio;
	}
	
	public void setClampMagnitude(double magnitude)
	{
		this.clampMode = CLAMP_MAGNITUDE;
		this.clampMagnitude = magnitude;
	}
	
	public void setClampMode(int clampMode)
	{
		this.clampMode = clampMode;
	}
	
////////////////////////////////////////////////////////////////////////////////
	public void setTimer(Timer timer)
	{
		this.timer = timer;
	}
	
////////////////////////////////////////////////////////////////////////////////
	
	public void setSetpoint(double setpoint)
	{
		this.positionSetpoint = setpoint;
	}
	
////////////////////////////////////////////////////////////////////////////////
	
	public double getIntegral()
	{
		return integral;
	}
	
////////////////////////////////////////////////////////////////////////////////
	
	public double getSlope()
	{
		return slope;
	}

	
	public String toString() {
		return"kp="+kp+", ki="+ki+", kd="+kd+", kf="+kf;
	}
}