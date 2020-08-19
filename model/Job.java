package model;

public class Job {

	//private double lat, lng;
	private int fitterDistrict;
	private char priority;
	private int timeJobReceived, startTime;

	public Job(int fitterDistrict, int startTime) {
//		this.lat = lat;
//		this.lng = lng;
		this.fitterDistrict = fitterDistrict;
		this.startTime = startTime;
		priority = 'A';
		
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public int getFitterDistrict() {
		return fitterDistrict;
	}
	
	
}
