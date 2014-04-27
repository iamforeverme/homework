package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import org.junit.Test;

import cn.ge.dxc.Aircon;
import cn.ge.dxc.Furnace;
import cn.ge.dxc.OccupationSensor;
import cn.ge.dxc.TemperatureSensor;
import cn.ge.dxc.Thermostat;

public class TestOccupationSensor {

	private OccupationSensor occupationSensor=new OccupationSensor();
	/*
	 * OccupationSensor status can be set as true
	 */
	@Test
	public void testOccupationSensorStatusCanBeSetTrue() {
		//Given
		//When
		occupationSensor.setOccupation(true);
		//Then
		assertTrue(occupationSensor.isOccupied());
	}
	
	/*
	 * OccupationSensor status can be set as false
	 */
	@Test
	public void testOccupationSensorStatusCanBeSetFalse() {
		//Given
		//When
		occupationSensor.setOccupation(false);
		//Then
		assertFalse(occupationSensor.isOccupied());
	}
	
	/*
	 * If the occupationSensor status has been changed , the observer will receive messages.
	 * **@
	 */
	@Test
	public void testUpdateOberserver()
	{
		//Given		
		TemperatureSensor mockTemperatureSensor = mock(TemperatureSensor.class);
		Furnace mockFurnace = mock(Furnace.class);
		Aircon mockAircon = mock(Aircon.class);
		Thermostat thermostat = new Thermostat(mockTemperatureSensor,occupationSensor, mockFurnace,mockAircon);
		// bIsUpdatForTest is a flag for testing is update function has been used
		thermostat.bIsUpdatForTest=false;
		occupationSensor.addObserver(thermostat);
		//When
		occupationSensor.setOccupation(true);
		//Then
		assertTrue(thermostat.bIsUpdatForTest);
		
	}

}
