import java.util.ArrayList;
import java.time.LocalDateTime;

public class Driver {

    public static void main(String[] args) {
        
        ArrayList<Job> job_queue = new ArrayList<Job>();
        
        job_queue.add(new Job(420, LocalDateTime.of(2020, 5, 20, 9, 0, 0), LocalDateTime.of(2020, 5, 20, 10, 0, 0)));
        job_queue.add(new Job(500, LocalDateTime.of(2020, 8, 20, 14, 0, 0), LocalDateTime.of(2020, 8, 20, 15, 0, 0)));
        job_queue.add(new Job(330, LocalDateTime.of(2020, 11, 20, 17, 0, 0), LocalDateTime.of(2020, 11, 20, 18, 0, 0)));

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
    
