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
	private double totalTime;

	/**
	 * Constructor for a completedJob object with some basic information
	 * 
	 * @param fitterDistrict
	 * @param startTime
	 */
	public CompletedJob(int fitterDistrict, int startTime) {
		super(fitterDistrict, startTime);
		this.jobDuration = 60.0;
		this.travelTime = 30.0;
		totalTime = travelTime+jobDuration;
		
	}
	
	public String toString() {
		return "\nJob Details " + "\nFitterDistrict: " + getFitterDistrict() + "\nStart Time:  " +  +getStartTime() + 
		"\nJob Duration: " +jobDuration + "\nTravel Time: " +travelTime + "\nTotal Time: " +totalTime;
		
	}

	
	
}
