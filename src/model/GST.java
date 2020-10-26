package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import controller.SimUtils;
import controller.Simulation;

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
	private ArrayList<Job> myJobsToday;

	public GST(String gSTid, double lat, double lon) {
		this.gSTid = gSTid;
		this.lat = lat;
		this.lon = lon;
		finishTime = null;
		myJobsToday = new ArrayList<Job>();

	}

	public String[] parseMyJobsToday() {
		int totalTravelTime = 0;
		float idleTimePercentage = 0;
		int jobsCompleted = 0;

		if (myJobsToday.size() == 0) {
			String id = this.getgSTid();
			String travTimeString = "Total Travel Time Mins: 0";
			String idleTimeString = "Total Idle Time Mins: 100%";
			String jobsCompletedString = "Total Jobs Completed Today: 0";
			String[] stats = new String[] { id, travTimeString, idleTimeString, jobsCompletedString };
			return stats;
		} else {
			for (Job j : myJobsToday) {
				totalTravelTime = totalTravelTime + (j.getTravelTimeInSeconds() * 2);
				idleTimePercentage = (float) ((Simulation.getRunTime()) - (j.getJobDuration() * 60) - (j.getTravelTimeInSeconds() * 2)) / (Simulation.getRunTime()) * 100;
				jobsCompleted = myJobsToday.size();
			}
			String id = this.getgSTid();
			String travTimeString = "Total Travel Time Mins: " + SimUtils.formatSeconds(totalTravelTime);
			String idleTimeString = "Percentage of Time Idle: " + (String.format("%.0f%%", idleTimePercentage));
			String jobsCompletedString = "Total Jobs Completed Today: " + Integer.toString(jobsCompleted);
			String[] stats = new String[] { id, travTimeString, idleTimeString, jobsCompletedString };
			return stats;
		}
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