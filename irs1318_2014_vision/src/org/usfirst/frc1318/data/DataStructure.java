package org.usfirst.frc1318.data;

public interface DataStructure {
	/**
	 * Copies data into a corresponding data structure.
	 * @param otherData A data structure of the same type as this one.
	 * @return True if copying worked; false if not.
	 */
	public boolean copyTo(DataStructure otherData);
}
