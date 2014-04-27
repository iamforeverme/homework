package cn.ge.dxc;

import java.util.Observable;
import java.util.Observer;


public class Thermostat implements Observer
{
	private TemperatureSensor temperatureSensor;
	private OccupationSensor occupationSensor;
	private Furnace furnace;
	private Aircon aircon;
	private double desiredMaxTemperature;
	private double desiredMinTemperature;
	public boolean bIsUpdatForTest;
	public boolean bIsDebug;


	public Thermostat(TemperatureSensor temperatureSensor,
			OccupationSensor occupationSensor,
			Furnace furnace,
			Aircon aircon) {
		// TODO Auto-generated constructor stub
		this.furnace=furnace;
		this.aircon=aircon;
		this.temperatureSensor=temperatureSensor;
		this.occupationSensor=occupationSensor;
		this.temperatureSensor.addObserver(this);
		this.occupationSensor.addObserver(this);
		bIsDebug=false;
		desiredMaxTemperature=100;
		desiredMinTemperature=0;
	}
	
	private void debugPrint(String str)
	{
		if(bIsDebug)
			System.out.print(str+'\n');
	}
	
	public void setDebug()
	{
		bIsDebug=!bIsDebug;
	}

	public void setDesiredMinTemperature(double d) {
		// TODO Auto-generated method stub
		if(d>desiredMaxTemperature)
			desiredMinTemperature=desiredMaxTemperature;
		else
			this.desiredMinTemperature=d;
		update(null,null);
		
	}
	public void setDesiredMaxTemperature(double d) {
		if(d<desiredMinTemperature)
			desiredMaxTemperature=desiredMinTemperature;
		else
			desiredMaxTemperature=d;
		update(null,null);
	}

	public boolean isFurnaceRunning() {
		// TODO Auto-generated method stub
		return furnace.isRunning();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		bIsUpdatForTest=true;
		updateFurnace();
		updateAircon();
	}
	public void updateFurnace()
	{
		debugPrint("start update Furnace");
		if(!this.occupationSensor.isOccupied())
		{
			debugPrint("is not Occupied");
			if(!furnace.isRunning())
			{
				debugPrint("is not running");
				return;
			}
			debugPrint("should stop");
			furnace.stop();
			return;
		}
		if(this.desiredMinTemperature>temperatureSensor.getTemperature())
		{
			debugPrint("should be running");
			if(furnace.isRunning())
			{
				debugPrint("is running");
				return;
			}
			debugPrint("should start");
			furnace.start();
		}
		else
		{
			debugPrint("should not be running");
			if(furnace.isRunning())
			{
				debugPrint("should stop");
				furnace.stop();
			}
			else
				debugPrint("is not running");
		}
		debugPrint("End update Furnace.");
	}
	public void updateAircon()
	{
		debugPrint("start update Aircon");
		if(!this.occupationSensor.isOccupied())
		{
			debugPrint("is not Occupied");
			if(!aircon.isRunning())
			{
				debugPrint("is not running");
				return;
			}
			debugPrint("should stop");
			aircon.stop();
			return;
		}
		if(this.desiredMaxTemperature<temperatureSensor.getTemperature())
		{
			debugPrint("should be running");
			if(aircon.isRunning())
			{
				debugPrint("is running");
				return;
			}
			debugPrint("should start");
			aircon.start();
		}
		else
		{
			debugPrint("should not be running");
			if(aircon.isRunning())
			{
				debugPrint("should stop");
				aircon.stop();
			}
			else
				debugPrint("is not running");
		}
		debugPrint("End update Aircon.");
	}

	public boolean isOccupied() {
		// TODO Auto-generated method stub
		return this.occupationSensor.isOccupied();
	}

	public double getMinTemperature() {
		// TODO Auto-generated method stub
		return desiredMinTemperature;
	}

	public double getMaxTemperature() {
		// TODO Auto-generated method stub
		return desiredMaxTemperature;
	}
	
	public double getSenserTemperature() {
		// TODO Auto-generated method stub
		return temperatureSensor.getTemperature();
	}

	public boolean isAirconRunning() {
		// TODO Auto-generated method stub
		return aircon.isRunning();
	}

	public void setOccupation(boolean b) {
		// TODO Auto-generated method stub
		occupationSensor.setOccupation(b);
	}
	public void setSensorTemperature(double d) {
		// TODO Auto-generated method stub
		temperatureSensor.setTemperature(d);
	}
}
