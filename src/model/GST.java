package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.opencsv.bean.CsvBindByName;

/**
 * @author Yun Wang
 * 
 *         A class used to create GST objects when they are added to the
 *         simulation via the GSTFactory. It includes bindings for each instance
 *         variables which are used by the opencsv library to write objects to a
 *         .csv file in a formatted way. It also defines an additional
 *         constructor which is used to create records of GST activity on any
 *         given day
 */

public class GST implements Comparable<GST> {

	@CsvBindByName(column = "A-GST ID")
	private String gSTid;
	@CsvBindByName(column = "B-Lat")
	private double lat;
	@CsvBindByName(column = "C-Lon")
	private double lon;
	@CsvBindByName(column = "D-Date")
	private LocalDate shift;
	@CsvBindByName(column = "E-Travel Mins")
	private int totalTravelTime;
	@CsvBindByName(column = "F-Work Mins")
	private int totalTimeWorked;
	@CsvBindByName(column = "G-Jobs Completed")
	private int numJobsCompleted;

	private LocalDateTime finishTime;
	private ArrayList<Job> myJobsToday;

	public GST(String gSTid, double lat, double lon, LocalDate shift) {
		this.gSTid = gSTid;
		this.lat = lat;
		this.lon = lon;
		this.shift = shift;
		finishTime = null;
		myJobsToday = new ArrayList<Job>();
	}

	public GST(String gSTid, double lat, double lon, LocalDate shift, int totalTravelTime, int totalTimeWorked,
			int numJobsCompleted) {
		this.gSTid = gSTid;
		this.lat = lat;
		this.lon = lon;
		this.shift = shift;
		this.totalTravelTime = totalTravelTime / 60;
		this.totalTimeWorked = totalTimeWorked / 60;
		this.numJobsCompleted = numJobsCompleted;
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

	public LocalDate getShiftDate() {
		return shift;
	}

	public void setFinishTime(LocalDateTime finishTime) {
		this.finishTime = finishTime;

	}

	public LocalDateTime getFinishTime() {
		return finishTime;
	}

	public ArrayList<Job> getMyJobsToday() {
		return myJobsToday;
	}

	@Override
	public int compareTo(GST g1) {
		return 0;
	}

	// }

	@Override
	public String toString() {
		return "GST id= " + gSTid;
	}

}