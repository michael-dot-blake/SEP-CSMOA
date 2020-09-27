package model;

public class SimpleCoordinates {
	
	private double lat;
	private double lon;
	
	public SimpleCoordinates(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	@Override
	public String toString() {
		return lat + "," + lon;
	}
	
}
