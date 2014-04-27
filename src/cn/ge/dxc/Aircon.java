package cn.ge.dxc;

public class Aircon{
	boolean bIsRunning;
	public int timersForTest;
	public int timersForTestStart;
	public int timersForTestStop;

	public void start() {
	
		bIsRunning=true;
		timersForTest++;
		timersForTestStart++;
	}
	public boolean isRunning(){
		return bIsRunning;
	}
	public void stop() {
		// TODO Auto-generated method stub
		bIsRunning=false;
		timersForTest++;
		timersForTestStop++;
	}
	public Aircon()
	{
		timersForTestStart=0;
		timersForTest=0;
		timersForTestStop=0;
	}

}
