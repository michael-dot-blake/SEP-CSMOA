package model;

import java.time.LocalDateTime;

/**
 * @author Yun Wang
 * 
 *         A class used to create GST objects which will be assigned to jobs in
 *         the simulation
 */

public class GST implements Comparable<GST> {

	private String gSTid;
	private double lat, lon;
	private LocalDateTime finishTime;
	private int travelTime;

	public GST(String gSTid, double lat, double lon) {
		this.gSTid = gSTid;
		this.lat = lat;
		this.lon = lon;
		finishTime = null;
		travelTime = 0;
	}

	public String getgSTid() {
		return gSTid;
	}

	public void setgSTid(String gSTid) {
		this.gSTid = gSTid;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public void setFinishTime(LocalDateTime finishTime) {
		this.finishTime = finishTime;

	}

	public LocalDateTime getFinishTime() {
		return finishTime;
	}

	public void setTravelTime(int travelTime) {
		this.travelTime = travelTime;

	}

	public int getTravelTime() {
		return travelTime;
	}
	
	@Override
	public int compareTo(GST g1) {
		if (this.travelTime > g1.travelTime) {
			return 1;
		}
		if (this.travelTime < g1.travelTime) {
			return -1;
			
		} else {
			return 0;
		}

	}

	

	@Override
	public String toString() {
		return "GST id= " + gSTid;
	}

}