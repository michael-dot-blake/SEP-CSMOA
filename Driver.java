import java.util.ArrayList;
import java.time.LocalDateTime;

public class Driver {

    public static void main(String[] args) {
        
        ArrayList<Job> job_queue = new ArrayList<Job>();
        
        job_queue.add(new Job(420, LocalDateTime.of(2020, 5, 20, 9, 0, 0), 1, 30));
        job_queue.add(new Job(500, LocalDateTime.of(2020, 5, 20, 12, 0, 0), 2, 30));
        job_queue.add(new Job(580, LocalDateTime.of(2020, 5, 20, 15, 0, 0), 3, 30));
        
        for (Job j: job_queue) {
            System.out.println(j);
        }

        ArrayList<Technician> gst_queue = new ArrayList<>();

        gst_queue.add(new Technician(1, 420));
        gst_queue.add(new Technician(2, 500));
        gst_queue.add(new Technician(3, 330));

        for (Technician t: gst_queue) {
            System.out.println(t);
        }

    }

}
    
