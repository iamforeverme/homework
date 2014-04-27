package cn.ge.dxc;

import java.util.Observable;

public class OccupationSensor extends Observable{
	boolean bIsOccupied;

	public boolean isOccupied() {
		// TODO Auto-generated method stub
		return bIsOccupied;
	}
	public OccupationSensor()
	{
		bIsOccupied=false;
	}
	public void setOccupation(boolean occStatus) {
		// TODO Auto-generated method stub
		bIsOccupied=occStatus;
		setChanged();
	    notifyObservers();
		
	}

}
