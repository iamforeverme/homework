package cn.ge.dxc;

import java.util.Observable;

public class TemperatureSensor extends Observable {
	private double temperature;

	public double getTemperature() {
		// TODO Auto-generated method stub
		return temperature;
	}

	public void setTemperature(double t) {
		// TODO Auto-generated method stub
		temperature=t;
		setChanged();
	    notifyObservers();
	}
	public TemperatureSensor()
	{
		temperature=0.0;
	}

}
