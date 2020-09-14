package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Sawmya Patidar
 *  
 * A class to create Job objects when they are added 
 * to the simulation
 *
 */
public class Job implements Comparable<Job> {
		 
	    private int orderNum;
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
	    private int houseNum1;
	    private String houseNum2;
	    private int postcode;
	    private int fitterDistrict;
	    private int jobDuration; 
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
		 */
		public Job(int jobId, String orderType, String orderDescription, String issueCode, String issueDescription,
				String mainActType, String mainActDescription,  LocalDate jobDate, LocalTime jobTime, LocalDateTime startDateAndTime, String jobPriority,
				String suburb, String street, int houseNum1, String houseNum2, int postcode, int fitterDistrict,
				int jobDuration) {
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
			this.jobDuration = jobDuration;
		}
	    	public Job(){
	    	
	    	}
	    //Getters and setters
	  
	    public String getMainActDescription() {
			return mainActDescription;
		}

		public void setMainActDescription(String mainActDescription) {
			this.mainActDescription = mainActDescription;
		}

		public void setJobPriority(String jobPriority) {
			this.jobPriority = jobPriority;
		}

		public int getOrderNum() {
			return orderNum;
		}

		public void setOrderNum(int orderNum) {
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

		public String getIssuceCode() {
			return issueCode;
		}

		public void setIssuceCode(String issuceCode) {
			this.issueCode = issuceCode;
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

		public int getHouseNum1() {
			return houseNum1;
		}

		public void setHouseNum1(int houseNum1) {
			this.houseNum1 = houseNum1;
		}

		public String getHouseNum2() {
			return houseNum2;
		}

		public void setHouseNum2(String houseNum2) {
			this.houseNum2 = houseNum2;
		}

		public int getPostcode() {
			return postcode;
		}

		public void setPostcode(int postcode) {
			this.postcode = postcode;
		}

		public int getFitterDistrict() {
			return fitterDistrict;
		}

		public void setFitterDistrict(int fitterDistrict) {
			this.fitterDistrict = fitterDistrict;
		}

		public int getJobDuration() {
			return jobDuration;
		}

		public void setWorkTime(int jobDuration) {
			this.jobDuration = jobDuration;
		}
		
		
		 @Override
		    public String toString() {
		        return "Job [Number: " + orderNum + ", FitterDistrict: " +fitterDistrict + " Job Priority: " + jobPriority + ", Activity Type: " + mainActType +", Starting Date: " + orderCreateDate + ", Starting Time: " + orderCreateTime +", Estimated Duration: " + jobDuration + "]";
		    }


		@Override
		public int compareTo(Job o) {
			return 0;
		}
		
}
