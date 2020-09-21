package model;
/**
 * @author Michael Blake
 * 
 *         A class to create CompletedJob objects which are records of jobs
 *         which have been assigned and completed
 */
public class CompletedJobRecord {
	
	private GST gst;
	private Job job;
	
	/**
	 * @param gst
	 * @param job
	 */
	public CompletedJobRecord(GST gst, Job job) {
		this.gst = gst;
		this.job = job;
	}

	@Override
	public String toString() {
		return "CompletedJobRecord [gst=" + gst + ", job=" + job + "]";
	}
	
	
	

	

}
