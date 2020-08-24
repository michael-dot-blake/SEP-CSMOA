package model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yun Wang
 * 
 * A class used to create GST objects which will be
 * assigned to jobs in the simulation
 */

public class GST {
	
	//private double lat, lng;
	
	//The GST's ID
	
	private static final AtomicInteger count = new AtomicInteger(0); 
	private int id; 
	private int fitterDistrict;
	private int jobStatus; 
	private String jobStatu;
	private boolean isAvailable;
	
//	public GST(int Id, int fitterDistrict, int jobStatus) {	
//		this.lat = lat;
//		this.lng = lng;
//		this.Id = Id;
//		this.fitterDistrict = fitterDistrict;
//		this.jobStatus = jobStatus;
//	
//	}
	
	

	/**
	 * A constructor used to create GSTs with basic functionality.
	 * Early development only requires them to be initialised with
	 * their fitter district in order to be assigned jobs.
	 * 
	 * @param fitterDistrict
	 */
	public GST(int fitterDistrict) {
		this.fitterDistrict = fitterDistrict;
		id = count.incrementAndGet();	
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	
	public int getId(){
        	return id;
    	}
	
	public int getJobStatus(){
        	return jobStatus;
    	}
	
	public int getFitterDistrict() {
		return fitterDistrict;
	}
	
	public void setJobStatu(String jobStatu) {
		this.jobStatu = jobStatu;
	}
	
	public String getJobStatu() {
        if (this.jobStatus == 0) {
            return "Available";
        } else if (this.jobStatus == 1) {
            return "Working";
        } else if (this.jobStatus == 2) {
            return "In Emergency task";
        }
		return jobStatu;
	}
	
	
	@Override
	public String toString() {
		return "GST [id=" + id + ", fitterDistrict=" + fitterDistrict + ", jobStatus=" + jobStatus + ", jobStatu="
				+ jobStatu + ", isAvailable=" + isAvailable + "]";
	}


}