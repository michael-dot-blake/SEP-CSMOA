package controller;

import java.util.ArrayList;
import java.util.PriorityQueue;

import model.CompletedJob;
import model.GST;
import model.Job;

/**
 * @author Michael Blake
 * 
 * Simulation class to handle assigning jobs to GSTs
 * in early stages of development
 *
 */
public class Simulation {
	
	// variable used to store time
	private static int timer = 0;
	private int runtime;

	// Queue storage for jobs
	private PriorityQueue<Job> jobQueue = new PriorityQueue<Job>();

	private ArrayList<CompletedJob> completedJobs = new ArrayList<CompletedJob>();

	// A pool of GSTs to which jobs can be assigned
	private ArrayList<GST> gstPool = new ArrayList<GST>();

	// A pool of jobs representing historical data
	private ArrayList<Job> jobPool = new ArrayList<Job>();

	/**
	 * Loop through completedJobs array and call toString
	 */
	private void report() {
		for (CompletedJob cj : completedJobs) {
			System.out.println(cj.toString());
		}
	}
	
	/**
	 * Main simulation method
	 */
	private void runSim() {
		
				//Add some workers to an array
				gstPool.add(new GST(240));
				gstPool.add(new GST(320));
				gstPool.add(new GST(800));
				gstPool.add(new GST(503));
				gstPool.add(new GST(220));
				gstPool.add(new GST(442));
				gstPool.add(new GST(250));
				gstPool.add(new GST(301));
				gstPool.add(new GST(350));
				gstPool.add(new GST(420));

				//Add some jobs to an array
				jobPool.add(new Job(240, 1));
				jobPool.add(new Job(320, 2));
				jobPool.add(new Job(800, 3));
				jobPool.add(new Job(503, 4));
				jobPool.add(new Job(220, 5));
				jobPool.add(new Job(442, 6));
				jobPool.add(new Job(250, 7));
				jobPool.add(new Job(301, 8));
				jobPool.add(new Job(350, 9));
				jobPool.add(new Job(420, 10));

				// set running time
				runtime = 10;

				do {
					//Loop through jobPool until timer matches job start time
					for (Job j : jobPool) {// start for1
						if (timer == j.getStartTime()) {// start if1

							
							// when the timer reaches the scheduled job time then the job
							// is added to the job queue
							jobQueue.add(j);

							//Now loop through both the jobQueue and the gstPool to find matching fitter districts
							//Note: this will be changed to lat,lng later in project
							for (Job jq : jobQueue) {// start for2
								for (GST g : gstPool) {// start for3
									if (g.getFitterDistrict() == jq.getFitterDistrict()) {// start if2
										
										//if a match is found, the job is completed and a completedJob object is created with
										//some information about the job
										completedJobs.add(new CompletedJob(jq.getFitterDistrict(), jq.getStartTime()));
										
										//Now the job is removed the job queue
										jobQueue.remove(jq);

									} // end if2

								} // end for3

							} // end for2

						} // end if1

					} // end for 1

					timer++;

				} while (timer <= runtime);

				report();
		
	}

	public static void main(String[] args) {
		Simulation sim = new Simulation();
		sim.runSim();
		

	}// end main

}// end class
