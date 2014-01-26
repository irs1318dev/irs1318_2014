package org.usfirst.frc1318.data;

public class PositionTest extends DataStructureContractTest {

	@Override
	protected DataStructure createData1() {
		Position p = new Position();
		p.setAll(30, Math.PI/3);
		return p;
	}

	@Override
	protected DataStructure createData2() {
		Position p = new Position();
		p.setAll(15, Math.PI);
		return p;
	}

}
