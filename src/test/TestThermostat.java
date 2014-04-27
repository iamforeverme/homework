/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.ge.dxc.Aircon;
import cn.ge.dxc.Furnace;
import cn.ge.dxc.OccupationSensor;
import cn.ge.dxc.TemperatureSensor;
import cn.ge.dxc.Thermostat;
import static org.mockito.Mockito.*;


/**
 * @author 212328060
 *
 */
public class TestThermostat {
	private TemperatureSensor mockTemperatureSensor = mock(TemperatureSensor.class);
	private Furnace mockFurnace = mock(Furnace.class);
	private OccupationSensor mockOccupationSensor = mock(OccupationSensor.class);
	private Aircon mockAircon = mock(Aircon.class);
	private Thermostat thermostat = new Thermostat(mockTemperatureSensor,mockOccupationSensor, mockFurnace,mockAircon); 

	
	/*
	 * When the home is occupied and the home interior temperature 
	 * falls below the desired minimum temperature then the Furnace should start
	 */
	@Test
	public void testOccpiedHomeTemperatureFalls() {
		// Given
			//set the desired minimum termperature
		thermostat.setDesiredMinTemperature(20.0);

		// When
			//the home is occupied and the home interior temperature 
			//falls below the desired minimum termperature.
		when(mockOccupationSensor.isOccupied()).thenReturn(true);
		when(mockTemperatureSensor.getTemperature()).thenReturn(15.0);
		
		// Then
			//the Furnace should start
		thermostat.update(null,null);
		verify(mockFurnace).start();
	}
	
	/*
	 * When the home is occupied and the home interior temperature 
	 * rises to greater than or equal to the desired minimum 
	 * temperature then the Furnace should stop.
	 */
	@Test
	public void testOccpiedHomeTemperatureRaises()
	{
		// Given
					//set the desired minimum
		thermostat.setDesiredMinTemperature(19.0);
		when(mockFurnace.isRunning()).thenReturn(true);
		// When  
					//the home is occupied and the home interior temperature rises to greater than or equal to the desired minimum
		when(mockTemperatureSensor.getTemperature()).thenReturn(19.0);
		when(mockOccupationSensor.isOccupied()).thenReturn(true);
		
		// Then
					//the Furnace should stop
		thermostat.update(null,null);
		verify(mockFurnace).stop();
	}
	
	/*
	 * When the home is occupied and the desired minimum temperature 
	 * is set to a value greater than the home interior temperature 
	 * then the Furnace should start.
	 */
	@Test
	public void testOccpiedHomeTemperatureIsSetToAGreaterValue()
	{
		// Given
			//the home interior temperature is stable and the desired temperature is lower than the interior temperature
		when(mockTemperatureSensor.getTemperature()).thenReturn(19.0);
		thermostat.setDesiredMinTemperature(15.0);
		when(mockAircon.isRunning()).thenReturn(false);
		
		// When  
				//the home is occupied and set the desired minimum greater
		when(mockOccupationSensor.isOccupied()).thenReturn(true);
		thermostat.setDesiredMinTemperature(20.0);
		
		// Then
				//the Furnace should start
		verify(mockFurnace).start();
	}
	
	/*
	 * When the home is occupied and the desired minimum temperature 
	 * is set to a value less than or equal to the home interior
	 *  temperature then the Furnace should stop.
	 */
	@Test
	public void  testOccpiedHomeTemperatureIsSetToALessValue()
	{
		// Given
			//the home interior temperature is stable and the desired temperature is higher than the interior temperature
		when(mockTemperatureSensor.getTemperature()).thenReturn(19.0);
		thermostat.setDesiredMinTemperature(20.0);
		when(mockFurnace.isRunning()).thenReturn(true);
		
		// When  
				//the home is occupied and set the desired minimum less

		when(mockOccupationSensor.isOccupied()).thenReturn(true);
		thermostat.setDesiredMinTemperature(15.0);
		
		// Then
				//the Furnace should stop
		verify(mockFurnace).stop();
	}
	
	/*
	 * When furnace is running ,the home is unoccupied,the Furnace should stop.
	 * 
	 * **@
	 */
	@Test
	public void testHomeIsNotOccpiedWhenFurnaceRun()
	{
		//Given
		when(mockFurnace.isRunning()).thenReturn(true);
		//When 
			// The home is not occupied
		when(mockOccupationSensor.isOccupied()).thenReturn(false);
		//Then
			// The Furnace should stop
		thermostat.update(null,null);
		verify(mockFurnace).stop();
	}
	
	/*
	 * When furnace is not running ,the home is unoccupied,the Furnace should keep
	 * status,not use stop function.
	 * **@
	 */
	@Test
	public void testHomeIsNotOccpiedWhenFurnaceNotRun()
	{
		//Given
		when(mockFurnace.isRunning()).thenReturn(false);
		//When 
			// The home is not occupied
		when(mockOccupationSensor.isOccupied()).thenReturn(false);
		//Then
			// The Furnace should stop
		thermostat.update(null,null);
		verify(mockFurnace, times(0)).stop();
	}
	
	
	/*
	    * Test that when the home is occupied and sensor temperature raises above the desired maximum temperature  
	    * then the AirCon starts.
	*/
	@Test
	public void testWhenOccupiedSenTempHigherThenAirconStart() {
		//Given 
			//the designed maximum is 26
		thermostat.setDesiredMaxTemperature(26.0);
		
		//When 
			//home is occupied and the sensor temperature equals to 27
		when(mockOccupationSensor.isOccupied()).thenReturn(true);
		when(mockTemperatureSensor.getTemperature()).thenReturn(27.0);
	
		//Then 
			//AirCon starts
		thermostat.update(null,null);
		verify(mockAircon).start();		
	}
	/**
	   * Test that when the home is occupied and sensor temperature falls below or equal to the desired maximum temperature  
	   * then the AirCon stops.
	*/
	@Test
	public void testWhenOccupiedSenTempLowerThenAirconStop(){
		//Given
			//the designed maximum is 26
		thermostat.setDesiredMaxTemperature(26.0);
		when(mockAircon.isRunning()).thenReturn(true);
	
		//When
			//home is occupied and the sensor temperature equals to 25
		when(mockOccupationSensor.isOccupied()).thenReturn(true);
		when(mockTemperatureSensor.getTemperature()).thenReturn(25.0);
	
		//Then 
			//AirCon stops
		thermostat.update(null,null);
		verify(mockAircon).stop();
	}
	
	/**
	   * Test that when the home is occupied and desired maximum temperature is set to a value less than 
	   * the home interior temperature then the AirCon starts.
	*/
	@Test
	public void testWhenOccupiedDeisredTempLowerThenAirconStart(){
		//Given
			//the home interior temperature 26
		when(mockTemperatureSensor.getTemperature()).thenReturn(26.0);
		when(mockAircon.isRunning()).thenReturn(false);
		
		//When
			//home is occupied and the sensor temperature 25
		when(mockOccupationSensor.isOccupied()).thenReturn(true);
		thermostat.setDesiredMaxTemperature(25.0);
	
		//Then 
			//AirCon starts
		verify(mockAircon).start();
	}
	
	/**
	   * Test that when the home is occupied and desired maximum temperature is set to a value more than 
	   * the home interior temperature then the AirCon stops.
	*/
	@Test
	public void testWhenOccupiedDeisredTempHigherThenAirconStart(){
		//Given
			//the home interior temperature 26
		when(mockTemperatureSensor.getTemperature()).thenReturn(26.0);
		when(mockAircon.isRunning()).thenReturn(true);
		
		//When
			//home is occupied and the sensor temperature 27
		when(mockOccupationSensor.isOccupied()).thenReturn(true);
		thermostat.setDesiredMaxTemperature(27.0);
	
		//Then 
			//AirCon starts
		verify(mockAircon).stop();
	}
	
	@Test
	public void testWhenNotOccupiedThenAirconStop(){
		//Given
			//any condition
		
		//When
			//home is not occupied 
		when(mockAircon.isRunning()).thenReturn(true);
		when(mockOccupationSensor.isOccupied()).thenReturn(false);
	
		//Then 
			//AirCon starts
		thermostat.update(null,null);
		verify(mockAircon).stop();
	}

	/*
	 * When the home is occupied the interior temperature is lower than the desired Min
	 * temperature, the furnace shall start for once, even the temperature in next 5 mins is
	 * always lower than expected temperature.
	 * **@
	 */
	@Test
	public void testFurnaceStartTimes()
	{
		
		//Given
			//The home is occupied the interior temperature is lower than the desired Min temperature
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		Furnace furnace = new Furnace();
		OccupationSensor occupationSensor = new OccupationSensor();
		Aircon aircon = new Aircon();
		furnace.timersForTest=0;
		occupationSensor.setOccupation(true);
		temperatureSensor.setTemperature(19.0);
		Thermostat thermostat = new Thermostat(temperatureSensor,occupationSensor, furnace,aircon); 
		thermostat.setDesiredMinTemperature(25.0);
		//When 
			// The thermostat updates for three time.
		thermostat.update(null,null);
		thermostat.update(null,null);
		thermostat.update(null,null);
		//Then
			// The Furnace should start once
		assertEquals(furnace.timersForTest, 1);
	}
	
	/*
	 * When the home is occupied the interior temperature is higher than the desired Min
	 * temperature, the furnace shall stop for once, even the temperature in next 5 mins is
	 * always higher than expected temperature.
	 * **@
	 */
	@Test
	public void testFurnaceStopTimes()
	{
		
		//Given
			//The home is occupied the interior temperature is higer than the desired Min temperature
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		Furnace furnace = new Furnace();
		OccupationSensor occupationSensor = new OccupationSensor();
		Aircon aircon = new Aircon();
		furnace.timersForTest=0;
		occupationSensor.setOccupation(true);
		temperatureSensor.setTemperature(19.0);
		Thermostat thermostat = new Thermostat(temperatureSensor,occupationSensor, furnace,aircon); 
		thermostat.setDesiredMinTemperature(25.0);
		//When 
			// The thermostat updates for three time.
		thermostat.update(null,null);
		thermostat.update(null,null);
		thermostat.update(null,null);
		//Then
			// The Furnace should start once
		assertEquals(furnace.timersForTest, 1);
	}
	
	
	
	
	/*
	 * When the home is occupied the interior temperature is higher than the desired Max
	 * temperature, the furnace shall start for once, even the temperature in next 5 mins is
	 * always higher than expected temperature.
	 * **@
	 */
	@Test
	public void testAirconStartTimes()
	{
		
		//Given
			//The home is occupied the interior temperature is higher than the desired Max temperature
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		Furnace furnace = new Furnace();
		OccupationSensor occupationSensor = new OccupationSensor();
		Aircon aircon = new Aircon();
		aircon.timersForTest=0;
		occupationSensor.setOccupation(true);
		temperatureSensor.setTemperature(25.0);
		Thermostat thermostat = new Thermostat(temperatureSensor,occupationSensor, furnace,aircon); 
		thermostat.setDesiredMaxTemperature(19.0);
		//thermostat.setDebug();
		//When 
			// The thermostat updates for three time.
		thermostat.update(null,null);
		thermostat.update(null,null);
		thermostat.update(null,null);
		//Then
			// The Furnace should start once
		assertEquals(aircon.timersForTest, 1);
	}
	
	/*
	 * When the home is occupied the interior temperature is lower than the desired Max
	 * temperature, the furnace shall stop for once, even the temperature in next 5 mins is
	 * always higger than expected temperature.
	 * **@
	 */
	@Test
	public void testAirconStopTimes()
	{
		
		//Given
			//The home is occupied the interior temperature is higher than the desired Max temperature
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		Furnace furnace = new Furnace();
		OccupationSensor occupationSensor = new OccupationSensor();
		Aircon aircon = new Aircon();
		occupationSensor.setOccupation(true);
		temperatureSensor.setTemperature(19.0);
		Thermostat thermostat = new Thermostat(temperatureSensor,occupationSensor, furnace,aircon); 
		thermostat.setDesiredMaxTemperature(15.0);
		thermostat.setDebug();
		//When 
			// The thermostat updates for three time.
		temperatureSensor.setTemperature(12.0);
		temperatureSensor.setTemperature(12.0);
		temperatureSensor.setTemperature(12.0);

		//Then
			// The Furnace should start once
		assertEquals(aircon.timersForTestStop, 1);
	}
	
	/*thermostat 's the max desired temperature should not be lower than 
	 * the min desired temperature. if it has been set so, the max desired temperature
	 * will be set the value as the min temperature. 
	 * 
	 * **@
	 */
	@Test
	public void testMaxTemperatureCanNotBeSetLowerThanMin()
	{
		
		//Given
		//When 
		thermostat.setDesiredMinTemperature(20.0);
		thermostat.setDesiredMaxTemperature(10.0);
		//Then
		assertTrue(thermostat.getMaxTemperature()==thermostat.getMinTemperature());
	}
	
	/*thermostat 's the min desired temperature should not be higer than 
	 * the max desired temperature. if it has been set so, the min desired temperature
	 * will be set the value as the max temperature. 
	 * 
	 * **@
	 */
	@Test
	public void testMinTemperatureCanNotBeSetLowerThanMax()
	{
		
		//Given
		//When 
		thermostat.setDesiredMaxTemperature(20.0);
		thermostat.setDesiredMinTemperature(30.0);
		//Then
		assertTrue(thermostat.getMaxTemperature()==thermostat.getMinTemperature());
	}
	/*
	 * When the user overrides the actual temperature 
	 * then the Furnace and AirCon status updates accordingly.
	*/
	@Test
	public void testUserOverideTemperatureThenFurnaceUpdate()
	{
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		Furnace furnace = new Furnace();
		OccupationSensor occupationSensor = new OccupationSensor();
		Aircon aircon = new Aircon();
		occupationSensor.setOccupation(true);
		temperatureSensor.setTemperature(12.0);
		
		Thermostat thermostat = new Thermostat(temperatureSensor,occupationSensor, furnace,aircon); 
		thermostat.setDesiredMaxTemperature(19.0);
		aircon.stop();
		aircon.timersForTestStart=0;
		thermostat.setSensorTemperature(25.0);
		assertTrue(thermostat.isAirconRunning());
	}
	
	/*
	 * When the user toggles the occupied/unoccupied status 
	 * then the Furnace and AirCon status updates accordingly.
	 */
	@Test
	public void testUserTogglesOcStatusThenFurnaceUpdate()
	{
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		Furnace furnace = new Furnace();
		OccupationSensor occupationSensor = new OccupationSensor();
		Aircon aircon = new Aircon();
		occupationSensor.setOccupation(true);
		temperatureSensor.setTemperature(25.0);
		Thermostat thermostat = new Thermostat(temperatureSensor,occupationSensor, furnace,aircon); 
		thermostat.setDesiredMaxTemperature(19.0);
		thermostat.setOccupation(false);
		assertTrue(thermostat.isFurnaceRunning()==false);
	}
	/*
	 * When the user specifies a desired minimum or maximum 
	 * temperature then the Furnace and AirCon status updates accordingly.
	 */
	@Test
	public void testSpecifyDesiredTemperature()
	{
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		Furnace furnace = new Furnace();
		OccupationSensor occupationSensor = new OccupationSensor();
		Aircon aircon = new Aircon();
		occupationSensor.setOccupation(true);
		temperatureSensor.setTemperature(25.0);
		Thermostat thermostat = new Thermostat(temperatureSensor,occupationSensor, furnace,aircon); 
		thermostat.setDesiredMaxTemperature(19.0);
		assertTrue(thermostat.isAirconRunning());
	}
	/*
	 * Thermostat can get and set temperature
	 */
	@Test
	public void testThermostatSetAndGetTemperature()
	{
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		Furnace furnace = new Furnace();
		OccupationSensor occupationSensor = new OccupationSensor();
		Aircon aircon = new Aircon();
		Thermostat thermostat = new Thermostat(temperatureSensor,occupationSensor, furnace,aircon); 
		thermostat.setSensorTemperature(20.0);
		assertTrue(thermostat.getSenserTemperature()==20.0);
	}
	/*
	 * Thermostat can get and set occupation status
	 */
	@Test
	public void testThermostatSetAndGetOccupation()
	{
		TemperatureSensor temperatureSensor = new TemperatureSensor();
		Furnace furnace = new Furnace();
		OccupationSensor occupationSensor = new OccupationSensor();
		Aircon aircon = new Aircon();
		Thermostat thermostat = new Thermostat(temperatureSensor,occupationSensor, furnace,aircon); 
		thermostat.setOccupation(true);;
		assertTrue(thermostat.isOccupied()==true);
	}
}


