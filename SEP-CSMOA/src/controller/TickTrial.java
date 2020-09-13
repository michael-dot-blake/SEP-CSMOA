package controller;
/**
 * @author Rakibul Hasan Arif
 * 
 *
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
//import java.util.Date;
//import java.util.Timer;
import java.util.TimerTask;

public class TickTrial extends TimerTask {
	int count=0;

    @Override
    
    public void run() {
    	  ClockTicker ct=new ClockTicker();
		  LocalDate ld = LocalDate.of( 2020 , 11 , 23 );
		  LocalTime lt = LocalTime.MIN;
		  LocalDateTime myDateTime = LocalDateTime.of(ld, lt);
		  
		  do {
		        System.out.println("Timer task started at:"+myDateTime);
		        completeTask();
		        myDateTime = myDateTime.plusMinutes(20);
		        System.out.println("Timer task finished at:"+myDateTime);
		        sendNotification();
		        
		        count++;
		        
		  } while (count <= 7); 
		  	
    }
    
    

    private void completeTask() {
        try {
            //assuming it takes 2 secs to complete the task
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void sendNotification()
    {
    	System.out.println("Here is Done");
    }
    


}
