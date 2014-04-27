package cn.ge.dxc;

public class Furnace {
	boolean bIsRunning;
	public int timersForTest;

	public boolean isRunning() {
		// TODO Auto-generated method stub
		return bIsRunning;
	}

	public void start() {
		// TODO Auto-generated method stub
		bIsRunning=true;
		timersForTest++;
	}

	public void stop() {
		// TODO Auto-generated method stub
		bIsRunning=false;
		timersForTest++;
	}

}
