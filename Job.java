import java.time.LocalDateTime; 

public class Job {

    private int fitterDistrict;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Job(int fitterDistrict, LocalDateTime startTime, LocalDateTime endTime) {   
        this.fitterDistrict = fitterDistrict;
        this.startTime = startTime;
        this.endTime = endTime;

    }
    
    public String toString() {
        return "Job Info: " +fitterDistrict + " - " + startTime + " - " + endTime;
    }
}