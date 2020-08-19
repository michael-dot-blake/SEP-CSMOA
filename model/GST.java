package model;

public class GST {
	
	//private double lat, lng;
	
	private int Id; //The GST's ID
	private int fitterDistrict;
	private int job_status; 
	private String job_statu;
	
	public GST(int Id, int fitterDistrict, int job_status) {	
//		this.lat = lat;
//		this.lng = lng;
		
		this.Id = Id;
		this.fitterDistrict = fitterDistrict;
		this.job_status = job_status;
	
	}
	
	

	public int getId(){
        	return Id;
    	}
	
	public int getJob_Status(){
        	return job_status;
    	}
	
	public int getFitterDistrict() {
		return fitterDistrict;
	}
	
	public void setJobStatu(String jobStatu) {
		this.jobStatu = jobStatu;
	}
	
	public String getJob_Statu() {
        if (this.job_status == 0) {
            return "Available";
        } else if (this.job_status == 1) {
            return "Working";
        } else if (this.job_status == 2) {
            return "In Emergency task";
        }
		return job_statu;
	}

}
