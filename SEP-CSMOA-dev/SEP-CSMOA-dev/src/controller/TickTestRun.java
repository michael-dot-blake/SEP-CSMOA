package controller;

/**
 * @author Rakibul Hasan Arif
 * 
 *
 */

import java.util.Timer;
import java.util.TimerTask;

public class TickTestRun 
{ 
    public static void main(String args[]){
    	long startTime = System.nanoTime();
        TimerTask timerTask = new TickTrial();
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.schedule(timerTask, 0, 1*1000);
        System.out.println("TimerTask started");
        //cancel after sometime
        try {
            Thread.sleep(30000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
//        System.exit(0);
        System.out.println("TimerTask Finihsed");
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Total Duration of the Program is : " + totalTime + " NanoSecond");
    }
} 