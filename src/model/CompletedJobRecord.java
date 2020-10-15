package model;

import com.opencsv.bean.CsvBindByName;

/**
 * @author Michael Blake
 * 
 *         A class to create CompletedJob objects which are records of jobs
 *         which have been assigned and completed
 */
public class CompletedJobRecord {

	private GST gst;
	private Job job;
	
	@CsvBindByName(column = "A-Job ID")
	private String createNum;
	@CsvBindByName(column = "B-Address")
	private String address;
	@CsvBindByName(column = "C-Suburb")
	private String suburb;
	@CsvBindByName(column = "D-Postcode")
	private String postcode;
	@CsvBindByName(column = "E-Start Date Time")
	private String startDateAndTime;
	@CsvBindByName(column = "F-Idle Time mins")
	private String idleTime;
	@CsvBindByName(column = "G-Travel Time mins")
	private Integer travelTime;
	@CsvBindByName(column = "H-Job Duration mins")
	private Integer jobDuration;
	@CsvBindByName(column = "I-End Date Time")
	private String endDateAndTime;

	/**
	 * @param gst
	 * @param job
	 */
	public CompletedJobRecord(GST gst, Job job) {
		this.gst = gst;
		this.job = job;
		this.createNum = Integer.toString(job.getOrderNum());
		this.address = job.getHouseNum1() + " " + job.getStreet();
		this.suburb = job.getSuburb();
		this.postcode = job.getPostcode();
		this.startDateAndTime = job.getOrderCreateDateAndTime().toString();
		this.idleTime = Long.toString(job.getIdleTime());
		this.travelTime = (job.getTravelTimeInSeconds() / 60);
		this.jobDuration = job.getJobDuration();
		this.endDateAndTime = job.getEndDateAndTime().toString();

	}

	@Override
	public String toString() {
		return "CompletedJobRecord: [Assigned " + gst + "," + job + "]";
	}

}
