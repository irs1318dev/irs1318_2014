package org.usfirst.frc1318.data;

import org.junit.*;
import org.usfirst.frc1318.*;

import static org.junit.Assert.*;

public abstract class DataStructureContractTest extends RobotTest {
	protected DataStructure data1;
	protected DataStructure data2;
	
	@Before
	public final void setUpContractTest() {
		data1 = createData1();
		data2 = createData2();
	}
	/**
	 * Creates a new instance of the DataStructure class under test.
	 * Initializes it with values.
	 * 
	 * @return An instance of the DataStructure under test.
	 */
	protected abstract DataStructure createData1();
	
	/**
	 * Creates a new instance of the DataStructure class under test.
	 * Initializes the instance with different values from those initialized to
	 * dataStructure1.
	 * 
	 * @return A different instance of the DataStructure under test. Should have
	 * different values from data1.
	 */
	protected abstract DataStructure createData2();

	
	private DataStructure createOtherData() {
		return new DataStructure() {
			@Override
			public boolean copyTo(DataStructure otherData) {
				return false;
			}			
		};
	}

	@Test
	public void equals() {
		assertFalse("Should not be equal to null", data1.equals(null));
		assertFalse(data1.equals("this is not a data structure"));
		assertFalse("Should only be equal to own class", data1.equals(createOtherData()));
		assertFalse(data1.equals(data2));
		assertTrue(data2.equals(data2));

		assertTrue(data1.equals(createData1()));
		assertTrue(data2.equals(createData2()));
	}

	@Test
	public void copies() {
		assertFalse(data1.copyTo(null));
		
		assertFalse(data1.copyTo(createOtherData()));
		
		assertFalse(data1.equals(data2));
		assertTrue(data1.copyTo(data2));
		assertEquals(data1, data2);
		
		DataStructure data2 = createData2();
		assertFalse(data2.equals(data1));
		assertTrue(data2.copyTo(data1));
		assertEquals(data2, data1);
	}

}
