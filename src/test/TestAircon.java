package test;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.ge.dxc.Aircon;

public class TestAircon {

	private Aircon aircon=new Aircon();
	
	/*
	 * Aircon can start
	 * 
	 */
	@Test
	public void testAirconCanStart() {
		//Given
		//When
		aircon.start();
		//Then
		assertTrue(aircon.isRunning());
	}
	
	/*
	 * Aircon can stop
	 * 
	 */
	@Test
	public void testAirconCanStop() {
		//Given
		//When
		aircon.stop();
		//Then
		assertFalse(aircon.isRunning());
	}

}
