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

	public static Velocity applyClamp(Velocity velocity, double clamp) {
		Velocity newVelocity = new Velocity();
		if(Math.abs(velocity.leftVelocity)>1){
			newVelocity.rightVelocity = velocity.rightVelocity/Math.abs(velocity.leftVelocity);
			newVelocity.leftVelocity = velocity.leftVelocity/Math.abs(velocity.leftVelocity);
		}
		if (Math.abs(velocity.rightVelocity)>1){
			newVelocity.leftVelocity = velocity.leftVelocity/Math.abs(velocity.rightVelocity);
			newVelocity.rightVelocity = velocity.rightVelocity/Math.abs(velocity.rightVelocity);
		}
		return newVelocity;

	}
	
	public static class Velocity {
		public double leftVelocity;
		public double rightVelocity;
		public Velocity(){
			
		}
	}
}
