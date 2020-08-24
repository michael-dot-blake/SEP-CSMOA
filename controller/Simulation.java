package controller;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

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
	private ArrayList<Job> jobPool = new ArrayList<Job>();

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
	private void report() {
		for (CompletedJob cj : completedJobs) {
			System.out.println(cj.toString());
		}
	}

	private void createGSTs(int numGSTs) {

		int count = 0;
		do {
			gstPool.add(new GST(fitterDistricts[rand.nextInt(10)]));
			count++;
		} while (count < numGSTs);

			for (GST g: gstPool) {
			System.out.println(g.toString());
			}

	}

	private void createJobs(int numJobs) {

		int count = 0;
		do {
			jobPool.add(new Job(fitterDistricts[rand.nextInt(10)], rand.nextInt(10)));
			count++;
		} while (count < numJobs);

			for (Job j: jobPool) {
			System.out.println(j.toString());
			}

	}

	/**
	 * Main simulation method
	 */
	private void runSim(int numJobs, int numGSTs) {

		
		createJobs(numJobs);
		createGSTs(numGSTs);
		// set running time
		runtime = 10;

		do {
			// Loop through jobPool until timer matches job start time
			for (Job j : jobPool) {
				if (timer == j.getStartTime()) {

					// when the timer reaches the scheduled job time then the job
					// is added to the job queue
					jobQueue.add(j);

					// Now loop through both the jobQueue and the gstPool to find matching fitter
					// districts and whether GST is currently available
					// Note: this will be changed to lat,lng later in project
					for (Job jq : jobQueue) {
						for (GST g : gstPool) {

							// if a match is found, the job is completed and a completedJob object is
							// created with some information about the job
							if ((g.getFitterDistrict() == jq.getFitterDistrict())) {

								// flag the GST as unavailable
								g.setAvailable(false);

								// when the timer progresses to the time that the job is completed
								// a completedjob object is created
								if (timer == jq.getEndTime(jq.getStartTime(), jq.getJobDuration())) {
									completedJobs.add(new CompletedJob(jq.getFitterDistrict(), jq.getStartTime(), g));

									// The gst flag is reset to available and they can now be assigned new jobs
									g.setAvailable(true);

								}

							} 

						} 

					} 

				} 

			} 

			timer++;

		} while (timer <= runtime);

		report();

	}

	public static void main(String[] args) {

		Simulation sim = new Simulation();
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter number of Jobs: ");
		int numJobs = sc.nextInt();
		System.out.println("Enter number of GSTs: ");
		int numGSTs = sc.nextInt();

		//sim.runSim(numJobs, numGSTs);
		sim.createJobs(numJobs);
		sim.createGSTs(numGSTs);
		
		sc.close();

	}// end main

}// end class
