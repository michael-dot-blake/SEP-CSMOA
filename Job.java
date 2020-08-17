import java.time.LocalDateTime; 

public class Job {

    private int fitterDistrict;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int jobDuration;
    private int jobComplexity;
    private int travelTime;

    public Job(int fitterDistrict, LocalDateTime startTime, int jobComplexity, int travelTime) {   
        this.fitterDistrict = fitterDistrict;
        this.startTime = startTime;
        this.jobDuration = calculateJobDuration(jobComplexity, travelTime);
        

    }

    /*
    * Method to calculate how long a job will take a GST to complete based on the complexity
    * of the job and the travel time to the job in minutes.
    */
    public int calculateJobDuration(int complexity, int travelTime) {
        
        if (complexity == 1) {
            jobDuration = 45 + travelTime;
        } 
        else if (complexity == 2) {
            jobDuration = 60 + travelTime;
        }
        else if (complexity == 3) {
            jobDuration = 75 + travelTime;
        }
        return jobDuration;
    }
    
    public String toString() {
        return "Job Info - " + "\nFitter District: " +fitterDistrict + " \nStarting Time: " + startTime + 
        "\nEstimated Duration: " + jobDuration +"\n";
    }
}