package model;

public class GST {
	
	//private double lat, lng;
	
	private int Id; //The GST's ID
	private int fitterDistrict;
	private int jobStatus; 
	private String jobStatu;
	
	public GST(int Id, int fitterDistrict, int jobStatus) {	
//		this.lat = lat;
//		this.lng = lng;
		
		this.Id = Id;
		this.fitterDistrict = fitterDistrict;
		this.jobStatus = jobStatus;
	
	}
	
	

	public int getId(){
        	return Id;
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

}
