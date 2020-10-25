package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.PriorityQueue;

import org.locationtech.jts.geom.Coordinate;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import model.CompletedJobRecord;
import model.GST;
import model.Job;

/**
 * @author Michael Blake
 * @author Clark Skinner
 * 
 *         Simulation class to handle assigning jobs to GSTs in early stages of
 *         development
 */
public class Simulation {

	// Queue storage for jobs
	private PriorityQueue<Job> jobQueue = new PriorityQueue<Job>();

	private Deque<Job> idleJobQueue = new ArrayDeque<Job>();

	// Store completed jobs
	private ArrayList<CompletedJobRecord> completedJobs = new ArrayList<CompletedJobRecord>();

	private ArrayList<GST> availableGSTPool = new ArrayList<GST>();

	private ArrayList<GST> busyGSTs = new ArrayList<GST>();

	private String JOB_FILE_PATH;

	private String GST_FILE_PATH;

	private String LOG_FILE_NAME;
	
	//An Integer representing the size of the isochrone in seconds
	private final int ISOCHRONE_BOUNDARY = 1800;
	
	private static final int SECONDS_IN_A_DAY = 86400;
	
	private static long runTimeInSeconds = 0;
	
	
	


	/**
	 * Main simulation method
	 * 
	 * @throws IOException
	 * @throws SecurityException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws CsvDataTypeMismatchException
	 * @throws InterruptedException
	 */

	private void simulate(LocalDateTime currentTime, LocalDateTime endTime) throws SecurityException, IOException,
			CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException {

		int complianceCounter = 0;
		long totalTravelTime = 0;
		long jobIdleTime = 0;
		
		availableGSTPool = GSTFactory.getGSTpool();
		ArrayList<Job> jobPool = JobFactory.getJobPool();
		do {
			int availableGSTs = availableGSTPool.size();
			for (Job j : jobPool) {

				if (currentTime.equals(j.getOrderCreateDateAndTime()) && availableGSTs == 0) {
					idleJobQueue.addLast(j);
				}
				if (!idleJobQueue.isEmpty() && availableGSTs > 0) {
					Iterator<Job> iter = idleJobQueue.iterator();
					iter.hasNext();
					{
						Job current = iter.next();
						jobIdleTime = SimUtils.calculateTimeBetween(current.getOrderCreateDateAndTime(), currentTime);
						current.setIdleTime(jobIdleTime);
						current.setEndDateAndTime(current.getOrderCreateDateAndTime().plusSeconds(jobIdleTime));
						jobQueue.add(current);
						iter.remove();
						break;
					}

				} else if (currentTime.equals(j.getOrderCreateDateAndTime()) && availableGSTs > 0
						&& idleJobQueue.isEmpty()) {
					jobQueue.add(j);
				}

			}

			if (jobQueue.size() > 0) {
				for (Job j : jobQueue) {
					if (j.getAssignedGST() == null) {
						Coordinate jobCoord = SimUtils.getJobLocation(j);
						LocalDateTime jobTime = j.getOrderCreateDateAndTime();
						int jobDuration = j.getJobDuration();
						GST gst = SimUtils.findClosestGst(jobCoord, ISOCHRONE_BOUNDARY, jobTime);
						int travelTime = 0;
						if (gst != null) {
							System.out.println("Found the closest GST: " + gst.getgSTid() + " in 30min isochrone.");
							Coordinate gstCoord = new Coordinate(gst.getLat(), gst.getLon());
							travelTime = AzureMapsApi.getRouteTime(gstCoord, jobCoord, currentTime);
							j.setTravelTimeInSeconds(travelTime);
							System.out.println("Travel Time is: " + SimUtils.formatSeconds(travelTime) + "\n");
							j.setEndDateAndTime(
									jobTime.plusMinutes(jobDuration).plusSeconds(travelTime).plusSeconds(jobIdleTime));
							totalTravelTime = totalTravelTime + travelTime;
							j.setAssignedGST(gst);
							gst.setFinishTime(
									j.getEndDateAndTime().plusSeconds(travelTime * 2).plusSeconds(j.getIdleTime()));
							availableGSTPool.remove(gst);
							busyGSTs.add(gst);
							complianceCounter++;
						} else if (gst == null && !availableGSTPool.isEmpty()) {
							gst = SimUtils.findGstByStraightLineDistance(jobCoord, availableGSTPool);
							System.out.println("Found the closest GST: " + gst.getgSTid() + " outside isochrone");
							Coordinate gstCoord = new Coordinate(gst.getLat(), gst.getLon());
							travelTime = AzureMapsApi.getRouteTime(gstCoord, jobCoord, currentTime);
							if (travelTime < ISOCHRONE_BOUNDARY) {
								complianceCounter++;
							}
							System.out.println("Travel Time is: " + SimUtils.formatSeconds(travelTime) + "\n");
							j.setTravelTimeInSeconds(travelTime);
							j.setEndDateAndTime(
									jobTime.plusMinutes(jobDuration).plusSeconds(travelTime).plusSeconds(jobIdleTime));
							totalTravelTime = totalTravelTime + travelTime;
							j.setAssignedGST(gst);
							
							//Set the time that the GST will become available again
							//Double the travel time to simulate a GST returning to their previous position
							gst.setFinishTime(
									j.getEndDateAndTime().plusSeconds(travelTime * 2).plusSeconds(j.getIdleTime()));
							busyGSTs.add(gst);
							availableGSTPool.remove(gst);
						} else {
							System.out.println("No GST available");
							break;

						}

					}

				}
			}
			SimUtils.removeCompletedJobFromQueue(currentTime, jobQueue, completedJobs);
			SimUtils.checkGstFinished(currentTime, availableGSTPool, busyGSTs);
			currentTime = currentTime.plusSeconds(1);
			runTimeInSeconds++;
		} while (currentTime.isBefore(endTime));
		int jobsCompleted = completedJobs.size();
		int incompleteJobs = idleJobQueue.size() + jobQueue.size();
		if (jobsCompleted == 0) {
			System.err.println("No Completed Jobs");
		} else {
			long avgTravelTime = totalTravelTime / jobsCompleted;
			float complianceRate = (float) complianceCounter / (jobsCompleted + incompleteJobs) * 100;
			SimUtils.generateOutput(avgTravelTime, complianceRate, incompleteJobs, completedJobs, GSTFactory.getGSTpool(), LOG_FILE_NAME);
		}

	}

	private void runSimulation() throws SecurityException, IOException, CsvDataTypeMismatchException,
			CsvRequiredFieldEmptyException, InterruptedException {
		JobFactory.readJobsFromCSV(JOB_FILE_PATH);
		GSTFactory.readGSTsFromCSV(GST_FILE_PATH);
		LocalDateTime firstJob = JobFactory.getJobPool().get(0).getOrderCreateDateAndTime();
		LocalTime startTime = LocalTime.of(7, 0);
		LocalDateTime startDate = LocalDateTime.of(firstJob.toLocalDate(), startTime);
		LocalDateTime endDate = JobFactory.getJobPool().get(JobFactory.getJobPool().size() - 1)
				.getOrderCreateDateAndTime().plusDays(1);
		simulate(startDate, endDate);

	}

	private void initFileNames(String jobFile, String gstFile, String outputFile) {
		JOB_FILE_PATH = jobFile;
		GST_FILE_PATH = gstFile;
		LOG_FILE_NAME = outputFile;
	}
	
	public static long getRunTime() {
		return runTimeInSeconds - SECONDS_IN_A_DAY;
	}

	public static void main(String[] args) throws SecurityException, IOException, CsvDataTypeMismatchException,
			CsvRequiredFieldEmptyException, InterruptedException {

		Simulation s = new Simulation();
		if (args.length == 3) {
			s.initFileNames(args[0], args[1], args[2]);
		}
		s.runSimulation();
		

	}// end main

}// end class