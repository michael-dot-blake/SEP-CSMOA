package model;

import java.time.LocalDateTime;

/**
 * @author Sawmya Patidar
 * 
 *         A class to create Job objects when they are added to the simulation
 *
 */
public class Job implements Comparable<Job> {

	// instance variables to be initialised when a job is created

	private String orderNum;
	private LocalDateTime orderCreateDateAndTime;
	private String jobPriority;
	private String suburb;
	private String street;
	private String houseNum1;
	private String postcode;
	private int jobDurationInMinutes;
	private GST assignedGST;

	// instance variables to be set prior to the creation of a completedJobRecord
	// object
	private LocalDateTime endDateAndTime;
	private long idleTime;
	private int travelTimeInSeconds;

	/**
	 * @param orderNum
	 * @param orderCreateDateAndTime
	 * @param jobPriority
	 * @param suburb
	 * @param street
	 * @param houseNum1
	 * @param postcode
	 * @param jobDurationInMinutes
	 * @param assignedGST
	 * @param endDateAndTime
	 * @param idleTime
	 * @param travelTimeInSeconds
	 */

	public Job(String orderNum, String jobPriority, String houseNum1, String street, String suburb, String postcode,
			LocalDateTime orderCreateDateAndTime, int jobDurationInMinutes, GST assignedGST,
			LocalDateTime endDateAndTime, long idleTime, int travelTimeInSeconds) {
		this.orderNum = orderNum;
		this.jobPriority = jobPriority;
		this.houseNum1 = houseNum1;
		this.street = street;
		this.suburb = suburb;
		this.postcode = postcode;
		this.orderCreateDateAndTime = orderCreateDateAndTime;
		this.jobDurationInMinutes = jobDurationInMinutes;
		this.assignedGST = assignedGST;
		this.endDateAndTime = endDateAndTime;
		this.idleTime = idleTime;
		this.travelTimeInSeconds = travelTimeInSeconds;

	}

	public void setJobPriority(String jobPriority) {
		this.jobPriority = jobPriority;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getJobPriority() {
		return jobPriority;
	}

	public LocalDateTime getOrderCreateDateAndTime() {

		if (this.orderCreateDateAndTime.isBefore(LocalDateTime.now().plusDays(1))) {
			long yearsBehind = orderCreateDateAndTime.getYear();
			long yearsToAdd = LocalDateTime.now().getYear() - yearsBehind;
			yearsToAdd += 1;
			return orderCreateDateAndTime.plusYears(yearsToAdd);
		} else
			return orderCreateDateAndTime;
	}

	public String getSuburb() {
		return suburb;
	}

	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNum1() {
		return houseNum1;
	}

	public void setHouseNum1(String houseNum1) {
		this.houseNum1 = houseNum1;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public int getJobDuration() {
		return jobDurationInMinutes;
	}

	public void setJobDuration(int jobDuration) {
		this.jobDurationInMinutes = jobDuration;
	}

	public LocalDateTime getEndDateAndTime() {
		return endDateAndTime;
	}

	public void setEndDateAndTime(LocalDateTime endDateAndTime) {
		this.endDateAndTime = endDateAndTime;
	}

	public int getTravelTimeInSeconds() {
		return travelTimeInSeconds;
	}

	public void setTravelTimeInSeconds(int travelTimeInSeconds) {
		this.travelTimeInSeconds = travelTimeInSeconds;
	}

	public GST getAssignedGST() {
		return assignedGST;
	}

	public void setAssignedGST(GST g) {
		this.assignedGST = g;
	}

	public void setOrderCreateDateAndTime(LocalDateTime orderCreateDateAndTime) {
		this.orderCreateDateAndTime = orderCreateDateAndTime;
	}

	public void setIdleTime(long jobIdleTime) {
		this.idleTime = jobIdleTime;
	}

	public long getIdleTime() {
		return idleTime;
	}


	@Override
	public int compareTo(Job o) {
		return 0;
	}

	@Override
	public String toString() {
		return " Job [Num: " + orderNum + " Job Priority: " + jobPriority + ", Start Date and Time: "
				+ getOrderCreateDateAndTime() + ", Duration: " + jobDurationInMinutes + ", Travel_Time: "
				+ (travelTimeInSeconds / 60) + ", Idle_Time: " + idleTime + ", End Date And Time: " + endDateAndTime
				+ "]";
	}

}
