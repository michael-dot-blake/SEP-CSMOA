package model;

/**
 * @author Yun Wang
 * 
 * A class used to create GST objects which will be
 * assigned to jobs in the simulation
 */

public class GST {
	
	private String gSTid;
	private double lat, lon;
	private boolean isAvailable;
	private int fitterDistrict;
	
	public GST(String gSTid, double lat, double lon) {
		this.gSTid = gSTid;
		this.lat = lat;
		this.lon = lon;
		isAvailable = true;		
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

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getFitterDistrict() {
		return fitterDistrict;
	}
	
	public boolean getIsAvailable() {
		return isAvailable;
	}
	
	
	@Override
	public String toString() {
		return "GST [id=" + gSTid + ", fitterDistrict=" + fitterDistrict + ", isAvailable=" + isAvailable + "]";
	}


}