package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;

import model.CompletedJob;
import model.GST;
import model.Job;

/**
 * @author Michael Blake
 * 
 *         Simulation class to handle assigning jobs to GSTs in early stages of
 *         development
 *
 */
public class Simulation {

	// variable used to store time
	private static int timer = 0;
	private static int runtime;

	Random rand = new Random();

	// Queue storage for jobs
	private PriorityQueue<Job> jobQueue = new PriorityQueue<Job>();

	// Store completed jobs
	private ArrayList<CompletedJob> completedJobs = new ArrayList<CompletedJob>();

	// A pool of GSTs to which jobs can be assigned
	private ArrayList<GST> gstPool = new ArrayList<GST>();

	// A pool of jobs representing historical data
	//private ArrayList<Job> jobPool = new ArrayList<Job>();

	// A pool of fitter districts which can be randomly assigned to jobs and GSTS
	private int[] fitterDistricts;

	public Simulation() {

		// Store fitter districts for random distribution
		fitterDistricts = new int[10];
		fitterDistricts[0] = 240;
		fitterDistricts[1] = 320;
		fitterDistricts[2] = 800;
		fitterDistricts[3] = 503;
		fitterDistricts[4] = 220;
		fitterDistricts[5] = 442;
		fitterDistricts[6] = 250;
		fitterDistricts[7] = 301;
		fitterDistricts[8] = 350;
		fitterDistricts[9] = 420;

	}

	/**
	 * Loop through completedJobs array and call toString
	 */

	private void initLogger() throws SecurityException, IOException {

		try {
			Log myLog = new Log("log.txt");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			for (Job j : completedJobs) {
				myLog.logger.log(Level.INFO, " " + j.toString());
			}

		} catch (Exception e) {

		}

	}

	/**
	 * Main simulation method
	 */
	private void runSim(int runtimeInHours) {

		// A testing function which progresses time by one minute each time a a while
		// loop executes
		
		JobFactory jf = new JobFactory();
		jf.initJobs();
		ArrayList<Job> jobPool = jf.getJobPool();
		
		long runtimeInSeconds;
		runtimeInSeconds = runtimeInHours * 3600;
		int count = 0;
		LocalDate ld = LocalDate.of(2019, 8, 8);
		LocalTime lt = LocalTime.MIN;
		LocalDateTime myDateTime = LocalDateTime.of(ld, lt);
		do {
			for (Job j : jobPool ) {
				//System.out.println("Job Creation Time: "+j.getOrderCreateDateAndTime());
				//System.out.println("Value of LocalDateTime: " + myDateTime);
				if (myDateTime.isEqual(j.getOrderCreateDateAndTime())) {
					
					System.out.println("Times match");
					// when the timer reaches the scheduled job time then the job
					// is added to the job queue
					jobQueue.add(j);
					System.out.println(jobQueue);

					// Now loop through both the jobQueue and the gstPool to find matching fitter
					// districts and whether GST is currently available
					// Note: this will be changed to lat,lng later in project
//					for (Job jq : jobQueue) {
//						for (GST g : gstPool) {
//
//							// if a match is found, the job is completed and a completedJob object is
//							// created with some information about the job
//							if ((g.getFitterDistrict() == jq.getFitterDistrict()) && g.getIsAvailable() == true) {
//
//								// flag the GST as unavailable
//								g.setAvailable(false);
//
//								// when the timer progresses to the time that the job is completed
//								// a completedjob object is created
//
////								if (timer == jq.getEndTime(jq.getStartTime(), jq.getJobDuration())) {
////									completedJobs.add(new CompletedJob(jq.getFitterDistrict(), jq.getStartTime(), g));
////
////									jobQueue.remove(j);
////									
////									// The gst flag is reset to available and they can now be assigned new jobs
////									g.setAvailable(true);
////
////								}
//
//							}
//
//						}
//
//					}

				}

			}
			//System.out.println(myDateTime);
			myDateTime = myDateTime.plusSeconds(1);
			count++;
		} while (count <= runtimeInSeconds);

	}

	public void runTimer(int runtimeInHours) {

		// A testing function which progresses time by one minute each time a a while
		// loop executes
		long runtimeInMinutes;
		runtimeInMinutes = runtimeInHours * 60;
		ArrayList<LocalDateTime> storedTime = new ArrayList<LocalDateTime>();
		int thisCount = 0;
		LocalDate ld = LocalDate.of(2020, 11, 23);
		LocalTime lt = LocalTime.MIN;
		LocalDateTime myDateTime = LocalDateTime.of(ld, lt);
		do {
			storedTime.add(thisCount, myDateTime);
			myDateTime = myDateTime.plusMinutes(1);
			thisCount++;
		} while (thisCount <= runtimeInMinutes);

	}

	public static void main(String[] args) throws SecurityException, IOException {

		Simulation s = new Simulation();
		s.runSim(24);

	}// end main

}// end class
