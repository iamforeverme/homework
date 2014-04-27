package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import cn.ge.dxc.Aircon;
import cn.ge.dxc.Furnace;
import cn.ge.dxc.OccupationSensor;
import cn.ge.dxc.TemperatureSensor;
import cn.ge.dxc.Thermostat;

public class TestTemperatureSensor {

	private TemperatureSensor temperatureSensor=new TemperatureSensor();
	
	/*
	 * TemperatureSensor can get the interior temperature
	 * 
	 */
	@Test
	public void testTemperatureSensorSetAndGetTemperature() {
		//Given
		//When
		temperatureSensor.setTemperature(20.0);
		//Then
		assertTrue(temperatureSensor.getTemperature()==20.0);
	}
	
	/*
	 * If the interior temperature changes , the observer will receive messages.
	 * **@
	 */
	@Test
	public void testUpdateOberserver()
	{
		//Given		
		Furnace mockFurnace = mock(Furnace.class);
		Aircon mockAircon = mock(Aircon.class);
		OccupationSensor mockOccupationSensor = mock(OccupationSensor.class);
		Thermostat thermostat = new Thermostat(temperatureSensor,mockOccupationSensor, mockFurnace,mockAircon);
		// bIsUpdatForTest is a flag for testing is update function has been used
		thermostat.bIsUpdatForTest=false;
		temperatureSensor.addObserver(thermostat);
		//When
		temperatureSensor.setTemperature(0.0);
		//Then
		assertTrue(thermostat.bIsUpdatForTest);
	}
}
