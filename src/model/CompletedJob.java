package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Michael Blake
 * 
 *         A class to create CompletedJob objects which are records of jobs
 *         which have been assigned and completed
 */

public class CompletedJob extends Job {

	/**
	 * @param orderNum
	 * @param orderType
	 * @param orderDescription
	 * @param issueCode
	 * @param issueDescription
	 * @param mainActType
	 * @param mainActDescription
	 * @param orderCreateDate
	 * @param orderCreateTime
	 * @param orderCreateDateAndTime
	 * @param endDateAndTime
	 * @param jobPriority
	 * @param suburb
	 * @param street
	 * @param houseNum1
	 * @param houseNum2
	 * @param postcode
	 * @param fitterDistrict
	 * @param jobDuration
	 */

	public CompletedJob(int orderNum, String orderType, String orderDescription, String issueCode,
			String issueDescription, String mainActType, String mainActDescription, LocalDate orderCreateDate,
			LocalTime orderCreateTime, LocalDateTime orderCreateDateAndTime, 
			String jobPriority, String suburb, String street, int houseNum1, String houseNum2, int postcode,
			int jobDuration, int fitterDistrict, LocalDateTime endTimeAndDate, GST myGST) {
		super(orderNum, orderType, orderDescription, issueCode, issueDescription, mainActType, mainActDescription,
				orderCreateDate, orderCreateTime, orderCreateDateAndTime, jobPriority, suburb, street,
				houseNum1, houseNum2, postcode, fitterDistrict, jobDuration);
	}

	@Override
	public String toString() {
		return "Job [Number: " + getOrderNum() + ", FitterDistrict: " + getFitterDistrict() + " Job Priority: "
				+ getJobPriority() + ", Activity Type: " + getMainActType() + ", Starting Date: " + getOrderCreateDate()
				+ ", Starting Time: " + getOrderCreateTime() + ", End Time: " + getEndDateAndTime() + "]";
	}

}
