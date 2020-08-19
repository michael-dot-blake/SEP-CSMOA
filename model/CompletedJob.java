package model;

public class CompletedJob extends Job {
	
	private double jobDuration;
	private double travelTime;

	public CompletedJob(int fitterDistrict, int startTime) {
		super(fitterDistrict, startTime);
		this.jobDuration = 60.0;
		this.travelTime = 30.0;
	}
	
	public String toString() {
		return "Job Details: " + getFitterDistrict() + " - " +getStartTime() + 
		" - " +jobDuration + " - " +travelTime;
		
	}


	

	

}
