package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Sawmya Patidar
 * 
 *         A class to create Job objects when they are added to the simulation
 *
 */
public class Job implements Comparable<Job> {

	// instance variables to be initialised when a job is created
	private String orderNum;
	private String orderType;
	private String orderDescription;
	private String issueCode;
	private String issueDescription;
	private String mainActType;
	private String mainActDescription;
	private LocalDate orderCreateDate;
	private LocalTime orderCreateTime;
	private LocalDateTime orderCreateDateAndTime;
	private String jobPriority;
	private String suburb;
	private String street;
	private String houseNum1;
	private String houseNum2;
	private String postcode;
	private String fitterDistrict;
	private int jobDurationInMinutes;

	// instance variables to be set prior to the creation of a completedJobRecord
	// object
	private LocalDateTime endDateAndTime;
	private int travelTimeInSeconds;

	/**
	 * Constructor for a final Job object with all values initialised
	 * 
	 * @param jobId
	 * @param orderType
	 * @param orderDescription
	 * @param issueCode
	 * @param issueDescription
	 * @param mainActType
	 * @param mainActDescip
	 * @param jobDate
	 * @param jobTime
	 * @param jobPriority
	 * @param suburb
	 * @param street
	 * @param houseNum1
	 * @param houseNum2
	 * @param postcode
	 * @param fitterDistrict
	 * @param jobDuration
	 * @param endDateAndTime
	 */
	public Job(String jobId, String orderType, String orderDescription, String issueCode, String issueDescription,
			String mainActType, String mainActDescription, LocalDate jobDate, LocalTime jobTime,
			LocalDateTime startDateAndTime, String jobPriority, String suburb, String street, String houseNum1,
			String houseNum2, String postcode, String fitterDistrict, int jobDurationInMinutes, int travelTimeInSeconds,
			LocalDateTime endDateAndTime) {
		this.orderNum = jobId;
		this.orderType = orderType;
		this.orderDescription = orderDescription;
		this.issueCode = issueCode;
		this.issueDescription = issueDescription;
		this.mainActType = mainActType;
		this.mainActDescription = mainActDescription;
		this.orderCreateDate = jobDate;
		this.orderCreateTime = jobTime;
		this.orderCreateDateAndTime = startDateAndTime;
		this.jobPriority = jobPriority;
		this.suburb = suburb;
		this.street = street;
		this.houseNum1 = houseNum1;
		this.houseNum2 = houseNum2;
		this.postcode = postcode;
		this.fitterDistrict = fitterDistrict;
		this.jobDurationInMinutes = jobDurationInMinutes;
		this.setTravelTimeInSeconds(travelTimeInSeconds);
		this.endDateAndTime = endDateAndTime;

	}
	// Getters and setters

	public String getMainActDescription() {
		return mainActDescription;
	}

	public void setMainActDescription(String mainActDescription) {
		this.mainActDescription = mainActDescription;
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

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public String getIssueCode() {
		return issueCode;
	}

	public void setIssueCode(String issueCode) {
		this.issueCode = issueCode;
	}

	public String getIssuedescription() {
		return issueDescription;
	}

	public void setIssuedescription(String issuedescription) {
		this.issueDescription = issuedescription;
	}

	public String getMainActType() {
		return mainActType;
	}

	public void setMainActType(String mainActType) {
		this.mainActType = mainActType;
	}

	public LocalDate getOrderCreateDate() {
		return orderCreateDate;
	}

	public void setOrderCreateDate(LocalDate jobString) {
		this.orderCreateDate = jobString;
	}

	public LocalTime getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(LocalTime orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getJobPriority() {
		return jobPriority;
	}

	public LocalDateTime getOrderCreateDateAndTime() {
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

	public String getHouseNum2() {
		return houseNum2;
	}

	public void setHouseNum2(String houseNum2) {
		this.houseNum2 = houseNum2;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getFitterDistrict() {
		return fitterDistrict;
	}

	public void setFitterDistrict(String fitterDistrict) {
		this.fitterDistrict = fitterDistrict;
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

	@Override
	public int compareTo(Job o) {
		return 0;
	}

	@Override
	public String toString() {
		return "Job [Number: " + orderNum + ", FitterDistrict: " + fitterDistrict + " Job Priority: " + jobPriority
				+ ", Activity Type: " + mainActType + ", Starting Date: " + orderCreateDate + ", Starting Time: "
				+ orderCreateTime + ", Duration: " + jobDurationInMinutes + ", Travel Time: " + travelTimeInSeconds
				+ " ,End Date And Time: " + endDateAndTime + "]";
	}

	public int getTravelTimeInSeconds() {
		return travelTimeInSeconds;
	}

	public void setTravelTimeInSeconds(int travelTimeInSeconds) {
		this.travelTimeInSeconds = travelTimeInSeconds;
	}

}
