package irs2014.networkTable;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;

public class IRSTable
{
	public static void putBoolean(String key, boolean value)
	{
		putBoolean(key, new Boolean(value));
	}
	public static void putBoolean(String key, Boolean value)
	{
		if (value==null) return;
		try
		{
			if(SmartDashboard.getBoolean(key) != value.booleanValue())
				SmartDashboard.putBoolean(key, value.booleanValue());
		}
		catch (NetworkTableKeyNotDefined ex)
		{
			SmartDashboard.putBoolean(key, value.booleanValue());
		}
	}
	
	public static void putNumber(String key, double value)
	{
		putNumber(key, new Double(value));
	}
	public static void putNumber(String key, Double value)
	{
		if (value==null) return;
		try
		{
			if(SmartDashboard.getNumber(key) != value.doubleValue())
				SmartDashboard.putNumber(key, value.doubleValue());
		}
		catch (NetworkTableKeyNotDefined ex)
		{
			SmartDashboard.putNumber(key, value.doubleValue());
		}
	}

	public static void putString(String key, String value)
	{
		try
		{
			if(SmartDashboard.getString(key) != value)
				SmartDashboard.putString(key, value);
		}
		catch (NetworkTableKeyNotDefined ex)
		{
			SmartDashboard.putString(key, value);
		}
	}

	public static boolean getBoolean(String key)
	{
		return SmartDashboard.getBoolean(key);
	}
	
	public static double getNumber(String key)
	{
		return SmartDashboard.getNumber(key);
	}
	
	public static String getString(String key)
	{
		return SmartDashboard.getString(key);
	}
}