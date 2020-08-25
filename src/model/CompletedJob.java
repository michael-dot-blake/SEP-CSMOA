package model;

/**
 * @author Michael Blake
 * 
 * A class to create CompletedJob objects which are
 * records of jobs which have been assigned and completed
 */

public class CompletedJob extends Job {
	
	//The following variables will be hard coded initially
	private double jobDuration;
	private double travelTime;
	private double endTime;
	private GST gst;

	/**
	 * Constructor for a completedJob object with some basic information
	 * 
	 * @param fitterDistrict
	 * @param startTime
	 */
	public CompletedJob(int fitterDistrict, double startTime, GST gst) {
		super(fitterDistrict, startTime);
		this.jobDuration = 1;
		this.travelTime = 1;
		this.gst = gst;
		endTime = startTime + travelTime + jobDuration;
		
	}
	
	public double getEndTime() {
		return endTime;
	}
	
	
//	public String toString() {
//		return "\nJob Details" +"\nJob ID: " + this.getOrderNum() + "\nGST ID: " + gst.getId() + "\nFitterDistrict: " + getFitterDistrict() + "\nStart Time:  " +  +getStartTime() + 
//		"\nJob Duration: " +jobDuration + "\nTravel Time: " +travelTime + "\nJob Finish Time: " +endTime;
//		
//	}
	
	public String toString() {
		return "\nJob Details" +"\nJob ID: " + this.getOrderNum() +  "\nFitterDistrict: " + getFitterDistrict();
	}

	
	
}
