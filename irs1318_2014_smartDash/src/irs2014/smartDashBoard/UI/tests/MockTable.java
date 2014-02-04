package irs2014.smartDashBoard.UI.tests;

import java.util.HashMap;

import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

public class MockTable implements ITable {
	HashMap<String, Object> map;
	
	public MockTable() {
		map = new HashMap<String, Object>();
		map.put("s.sp", new Double(2.8123));
	}
	
	
	@Override
	public boolean containsKey(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsSubTable(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ITable getSubTable(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(String key) throws TableKeyNotDefinedException {
		return map.get(key);
	}

	@Override
	public void putValue(String key, Object value)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retrieveValue(String key, Object externalValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putNumber(String key, double value) {
		System.out.println("value input as number: " + new Double(value).toString());
		
	}

	@Override
	public double getNumber(String key) throws TableKeyNotDefinedException {
		return (Double) map.get(key);
	}

	@Override
	public double getNumber(String key, double defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void putString(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getString(String key) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString(String key, String defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putBoolean(String key, boolean value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getBoolean(String key) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getBoolean(String key, boolean defaultValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addTableListener(ITableListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTableListener(ITableListener listener,
			boolean immediateNotify) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTableListener(String key, ITableListener listener,
			boolean immediateNotify) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSubTableListener(ITableListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTableListener(ITableListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putInt(String key, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getInt(String key) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getInt(String key, int defaultValue)
			throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void putDouble(String key, double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getDouble(String key) throws TableKeyNotDefinedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDouble(String key, double defaultValue) {
		// TODO Auto-generated method stub
		return 0;
	}


}
