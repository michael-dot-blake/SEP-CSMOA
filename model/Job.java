package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

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
	    private String jobPriority;
	    private String suburb;
	    private String street;
	    private int houseNum1;
	    private String houseNum2;
	    private int postcode;
	    private int fitterDistrict;
	    private int jobDuration;
	    
	    
	    //An integer to represent an hour of time in the simulation
	    private double startTime;
	    private double endTime;
	    private double travelTime;
	    private static final AtomicInteger count = new AtomicInteger(0); 
	    
	    
		/**
		 * Constructor for a final Job object with all values initialised
		 * 
		 * @param orderNum
		 * @param orderType
		 * @param orderDescription
		 * @param issueCode
		 * @param issueDescription
		 * @param mainActType
		 * @param mainActDescip
		 * @param orderCreateDate
		 * @param orderCreateTime
		 * @param jobPriority
		 * @param suburb
		 * @param street
		 * @param houseNum1
		 * @param houseNum2
		 * @param postcode
		 * @param fitterDistrict
		 * @param jobDuration
		 */
		public Job(int orderNum, String orderType, String orderDescription, String issueCode, String issueDescription,
				String mainActType, String mainActDescription,  LocalDate orderCreateDate, LocalTime orderCreateTime, String jobPriority,
				String suburb, String street, int houseNum1, String houseNum2, int postcode, int fitterDistrict,
				int jobDuration) {
			this.orderNum = orderNum;
			this.orderType = orderType;
			this.orderDescription = orderDescription;
			this.issueCode = issueCode;
			this.issueDescription = issueDescription;
			this.mainActType = mainActType;
			this.mainActDescription = mainActDescription;
			this.orderCreateDate = orderCreateDate;
			this.orderCreateTime = orderCreateTime;
			this.jobPriority = jobPriority;
			this.suburb = suburb;
			this.street = street;
			this.houseNum1 = houseNum1;
			this.houseNum2 = houseNum2;
			this.postcode = postcode;
			this.fitterDistrict = fitterDistrict;
			this.jobDuration = jobDuration;
		}
	    
		
	    /**
	     * Constructor for basic functionality where jobs are allocated by fitter district and have some
	     * basic hard coded variables
	     * 
	     * @param fitterDistrict
	     * @param startTime
	     */
		
	    public Job(int fitterDistrict, double startTime) {
			orderNum = count.incrementAndGet();	
			this.fitterDistrict = fitterDistrict;
			this.startTime = startTime;
			
		}
	    
	    
	    public double getEndTime(double travelTime, double jobDuration) {
			endTime = travelTime + jobDuration;
			return endTime;
		}
	    
	    public double getTravelTime() {
	    	return travelTime;
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

		public void setOrderCreateDate(LocalDate orderCreateDate) {
			this.orderCreateDate = orderCreateDate;
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

		
	    public double getStartTime() {
				return startTime;
			}

			public void setStartTime(int startTime) {
				this.startTime = startTime;
			}
		
		 @Override
		    public String toString() {
		        return "Job [Number: " + orderNum + ", FitterDistrict: " +fitterDistrict + " Job Priority: " + jobPriority + ", Activity Type: " + mainActType +", Starting Date: " + orderCreateDate + ", Starting Time: " + orderCreateTime +", Estimated Duration: " + jobDuration + "]";
		    }


		@Override
		public int compareTo(Job o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
}
