package irs2014.generalOpperations;

public class JoystickFilter {
	
	public static double applyLinearDeadBand(double x, double band) {
		double output = 0;
		if (Math.abs(x) < band){
			output = 0;
//			System.out.println("Active deadband... "+x);
		} else if (x <= -band) {
			output = (x + band)/(1-band);
//			System.out.println("Deadband negative... x="+x+", output"+output);
			
		} else if (x >= band) {
			output = (x - band)/(1-band);
//			System.out.println("Deadband positive... x="+x+",output="+output);
		}
		return output;
	}

	public static Speed applyClamp(Speed speed, double clamp) {
		Speed newSpeed = new Speed();
		if(Math.abs(speed.speedL)>1){
			newSpeed.speedR = speed.speedR/Math.abs(speed.speedL);
			newSpeed.speedL = speed.speedL/Math.abs(speed.speedL);
		}
		if (Math.abs(speed.speedR)>1){
			newSpeed.speedL = speed.speedL/Math.abs(speed.speedR);
			newSpeed.speedR = speed.speedR/Math.abs(speed.speedR);
		}
		return newSpeed;

	}
	
	public static class Speed {
		public double speedL;
		public double speedR;
		public Speed(){
			
		}
	}
}
