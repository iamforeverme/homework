package test;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.ge.dxc.Furnace;

public class TestFurnace {

	private Furnace furnace=new Furnace();
	/*
	 * Furnace can start
	 */
	@Test
	public void testFurnaceCanStart() {
		//Given
		//When
		furnace.start();
		//Then
		assertTrue(furnace.isRunning());
	}
	
	/*
	 * Furnace can stop
	 */
	@Test
	public void testFurnaceCanStop() {
		//Given
		//When
		furnace.stop();
		//Then
		assertFalse(furnace.isRunning());
	}

}
