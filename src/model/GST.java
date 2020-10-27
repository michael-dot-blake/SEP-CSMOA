package model;

import java.time.LocalDate;
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
	private LocalDate shift;
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

	public String[] parseMyJobsToday() {
		int totalTravelTime = 0;
		float idleTimePercentage = 0;
		int jobsCompleted = 0;
		long shiftLength = Simulation.getRunTime();
		int totalJobDuration = 0;
		int activeTime = 0;

		if (myJobsToday.size() == 0) {
			String id = this.getgSTid();
			String travTimeString = "Total Travel Time Mins: 0";
			String idleTimeString = "Total Idle Time Mins: 100%";
			String jobsCompletedString = "Total Jobs Completed Today: 0";
			String[] stats = new String[] { id, travTimeString, idleTimeString, jobsCompletedString };
			return stats;
		} else {
			for (Job myJobs : myJobsToday) {
				totalTravelTime += myJobs.getTravelTimeInSeconds() * 2;
				totalJobDuration += (myJobs.getJobDuration() * 60);
				activeTime = totalTravelTime + totalJobDuration; 
				idleTimePercentage = (float) (activeTime * 100) / shiftLength;
				jobsCompleted = myJobsToday.size();
			}
			String id = this.getgSTid();
			String travTimeString = "Total Travel Time Mins: "
					+ Integer.toString(totalTravelTime/60);
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