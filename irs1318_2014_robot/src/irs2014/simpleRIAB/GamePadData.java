package irs2014.simpleRIAB;

public class GamePadData {
	
	private static GamePadData instance;
	
	private double yValue;
	
	private GamePadData(){
	}
	
	public static GamePadData getInstance(){
		if(instance == null){
			instance = new GamePadData();
		}
		return instance;
	}
	
	public double getYValue(){
		return yValue;
	}
	
	public void setYValue(double d){
		yValue = d;
	}
	
}
