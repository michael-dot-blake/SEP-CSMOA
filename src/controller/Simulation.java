package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.PriorityQueue;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

import com.google.gson.JsonObject;
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

	private String JOB_FILE_PATH = "JobFiles/jobTestData.csv";

	private String GST_FILE_PATH = "GSTFiles/gstData10.csv";

	private static final String LOG_FILE_NAME = "output.csv";

	public static String formatSeconds(long timeInput) {
		// long seconds = timeInput % 60;
		long mins = (timeInput / 60) % 60;
		// long hours = (timeInput / 60) / 60;
		String timeString = String.format("%02d Minutes", mins);
		return timeString;

	}

	/**
	 * Main simulation method
	 * 
	 * @throws IOException
	 * @throws SecurityException
	 * @throws CsvRequiredFieldEmptyException
	 * @throws CsvDataTypeMismatchException
	 * @throws InterruptedException
	 */

	public void simulate(LocalDateTime currentTime, LocalDateTime endTime) throws SecurityException, IOException,
			CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException {

		int complianceCounter = 0;
		long totalTravelTime = 0;
		int jobIdleTime = 0;
		availableGSTPool = GSTFactory.getGSTpool();

		do {
			int availableGSTs = availableGSTPool.size();
			for (Job j : JobFactory.getJobPool()) {

				if (currentTime.equals(j.getOrderCreateDateAndTime()) && availableGSTs == 0) {
					idleJobQueue.addLast(j);
					System.out.println("Idle Job Queue size is " + idleJobQueue.size());
				}
				if (!idleJobQueue.isEmpty() && availableGSTs > 0) {
					Iterator<Job> iter = idleJobQueue.iterator();
					iter.hasNext();
					{
						Job current = iter.next();
						System.out.println("Job Added from idle to active queue");
						jobIdleTime = Job.calculateIdleTime(current.getOrderCreateDateAndTime(), currentTime);
						current.setIdleTime(jobIdleTime);
						current.setEndDateAndTime(current.getOrderCreateDateAndTime().plusMinutes(jobIdleTime));
						jobQueue.add(current);
						iter.remove();
						break;
					}

				} else if (currentTime.equals(j.getOrderCreateDateAndTime()) && availableGSTs > 0
						&& idleJobQueue.isEmpty()) {
					System.out.println("BusyPool size is " + busyGSTs.size());
					System.out.println("Total GSTs available is " + availableGSTPool.size());
					jobQueue.add(j);
					System.out.println("Job Queue size is " + jobQueue.size());
				}

			}

			if (jobQueue.size() > 0) {
				for (Job j : jobQueue) {
					if (j.getAssignedGST() == null) {
						Coordinate jobCoord = getJobLocation(j);
						LocalDateTime jobTime = j.getOrderCreateDateAndTime();
						int jobDuration = j.getJobDuration();
						GST gst = findClosestGst(jobCoord, 1800, jobTime);
						int travelTime = 0;
						if (gst != null) {
							System.out.println("Found GST: " + gst.getgSTid() + " in 30min isochrone.");
							Coordinate gstCoord = new Coordinate(gst.getLat(), gst.getLon());
							travelTime = AzureMapsApi.getRouteTime(gstCoord, jobCoord);
							j.setTravelTimeInSeconds(travelTime);
							System.out.println("Travel Time is: " + formatSeconds(travelTime) + "\n");
							j.setEndDateAndTime(
									jobTime.plusMinutes(jobDuration).plusSeconds(travelTime).plusSeconds(jobIdleTime));
							totalTravelTime = totalTravelTime + travelTime;
							j.setAssignedGST(gst);
							System.out.println("Job End Time is: " + j.getEndDateAndTime() + "\n");
							System.out.println("Job Idle Time is: " + formatSeconds(j.getIdleTime()) + "\n");
							gst.setFinishTime(
									j.getEndDateAndTime().plusSeconds(travelTime).plusSeconds(j.getIdleTime()));
							availableGSTPool.remove(gst);
							busyGSTs.add(gst);
							System.out.println("GST finish time is: " + gst.getFinishTime());
							System.out.println("busyPool size: " + busyGSTs.size() + "\n");
							complianceCounter++;
						} else if (gst == null && !availableGSTPool.isEmpty()) {
							System.out.println("No GST found within 30min!!!");
							gst = findGstByStraightLineDistance(jobCoord, availableGSTPool);
							System.out.println("Found the closest GST: " + gst.getgSTid() + " outside isochrone");
							Coordinate gstCoord = new Coordinate(gst.getLat(), gst.getLon());
							travelTime = AzureMapsApi.getRouteTime(gstCoord, jobCoord);
							j.setTravelTimeInSeconds(travelTime);
							System.out.println("Travel Time is: " + formatSeconds(travelTime) + "\n");
							j.setEndDateAndTime(
									jobTime.plusMinutes(jobDuration).plusSeconds(travelTime).plusSeconds(jobIdleTime));
							totalTravelTime = totalTravelTime + travelTime;
							j.setAssignedGST(gst);
							System.out.println("Job End Time is: " + j.getEndDateAndTime() + "\n");
							System.out.println("Job Idle Time is: " + formatSeconds(j.getIdleTime()) + "\n");
							gst.setFinishTime(
									j.getEndDateAndTime().plusSeconds(travelTime).plusSeconds(j.getIdleTime()));
							busyGSTs.add(gst);
							availableGSTPool.remove(gst);
							System.out.println("GST finish time is: " + gst.getFinishTime());
							System.out.println("busyPool size: " + busyGSTs.size() + "\n");
						} else {
							System.out.println("No GST available");
							break;

						}

					}

				}
			}
			removeCompletedJobFromQueue(currentTime);
			checkGstFinished(currentTime);
			currentTime = currentTime.plusSeconds(1);
		} while (currentTime.isBefore(endTime));

		int jobsCompleted = completedJobs.size();
		int incompleteJobs = idleJobQueue.size();
		if (jobsCompleted == 0) {
			System.err.println("No Completed Jobs");
		} else {
			long avgTravelTime = totalTravelTime / jobsCompleted;
			float complianceRate = (float) complianceCounter / (jobsCompleted + incompleteJobs) * 100;
			String compString = "Compliance Rate: " + (String.format("%.0f%%",complianceRate));
			String travTimeString = "Travel Time Mins: " + (avgTravelTime/60);
			String incompleteJobString = "Incomplete Jobs: " + Integer.toString(incompleteJobs);
			String[] comp = new String[] { compString };
			String[] trav = new String[] { travTimeString };
			String[] incomplete = new String[] { incompleteJobString };
			Log.writeToCsv(completedJobs, LOG_FILE_NAME);
			Log.appendSingleLineToCSV(comp, LOG_FILE_NAME);
			Log.appendSingleLineToCSV(trav, LOG_FILE_NAME);
			Log.appendSingleLineToCSV(incomplete, LOG_FILE_NAME);
		}

	}

	public void removeCompletedJobFromQueue(LocalDateTime currentTime) {
		for (Iterator<Job> jobQueueIter = jobQueue.iterator(); jobQueueIter.hasNext();) {
			Job jo = jobQueueIter.next();
			if (currentTime.equals(jo.getEndDateAndTime().plusMinutes(jo.getIdleTime()))) {
				System.out.println("Job Completed");
				completedJobs.add(new CompletedJobRecord(jo.getAssignedGST(), jo));
				jobQueueIter.remove();
			}
		}

	}

	public Coordinate getJobLocation(Job j) throws IOException {
		String number = j.getHouseNum1();
		String street = j.getStreet();
		String suburb = j.getSuburb();
		String postcode = j.getPostcode();
		System.out.println("Job Location: " + number + " " + street + ", " + suburb + ", " + "NSW " + postcode);
		return AzureMapsApi.getCoordinatesFromAddress(number, street, suburb, postcode);
	}

	public GST findClosestGst(Coordinate jobCoord, int timeBudgetInSeconds, LocalDateTime departureTime)
			throws IOException {

		JsonObject jsonObj = AzureMapsApi.getIsochroneCoords(jobCoord, timeBudgetInSeconds, departureTime);
		Polygon poly = AzureMapsApi.BuildPolygon(jsonObj);
		ArrayList<GST> nearbyGSTs = new ArrayList<GST>();

		for (GST g : GSTFactory.getGSTpool()) {
			Coordinate gstCoord = new Coordinate(g.getLat(), g.getLon());
			if (AzureMapsApi.checkIfLocationInIsochrone(poly, gstCoord)) {
				nearbyGSTs.add(g);
				// System.out.println(nearbyGSTs.size());
			}
		}

		if (nearbyGSTs.size() > 0) {
			for (GST closeGst : nearbyGSTs) {
				Coordinate gstCoord = new Coordinate(closeGst.getLat(), closeGst.getLon());
				int travelTime = AzureMapsApi.getRouteTime(gstCoord, jobCoord);
				// System.out.println(travelTime);
				closeGst.setTravelTime(travelTime);

			}
			System.out.println(nearbyGSTs);
			Collections.sort(nearbyGSTs);
			GST closestGST = nearbyGSTs.get(0);
			nearbyGSTs.remove(0);
			resetGstTravelTime(nearbyGSTs);
			System.out.println("The closest GST was " + closestGST.getgSTid());
			return closestGST;

		}

		else {
			System.out.println("NO GST found in isochrone");
			return null;
		}

	}

	public void resetGstTravelTime(ArrayList<GST> gstList) {

		for (GST g : gstList) {
			g.setTravelTime(0);
		}
	}

	public GST findGstByStraightLineDistance(Coordinate jobCoord, ArrayList<GST> gstPool) {
		GST closeGst = null;
		double jx = jobCoord.getX();
		double jy = jobCoord.getY();
		double gx = 0.0;
		double gy = 0.0;
		double minDistance = 200.00;
		for (GST g : gstPool) {
			gx = g.getLat();
			gy = g.getLon();
			if (calcDistance(jx, jy, gx, gy) < minDistance) {
				minDistance = calcDistance(jx, jy, gx, gy);
				closeGst = g;
			}
		}
		return closeGst;
	}

	public double calcDistance(double jx, double jy, double gx, double gy) {
		return Math.abs(Math.sqrt((jx - gx) * (jx - gx) + (jy - gy) * (jy - gy)));
	}

	public void checkGstFinished(LocalDateTime currTime) {
		for (Iterator<GST> busyGSTIter = busyGSTs.iterator(); busyGSTIter.hasNext();) {
			GST go = busyGSTIter.next();
			if (currTime.equals(go.getFinishTime())) {
				go.setFinishTime(null);
				System.out.println("GST re-added to availablePool");
				busyGSTIter.remove();
				availableGSTPool.add(go);
				System.out.println("GST Removed from BusyPool");

			}
		}
	}

	public void runSimulation() throws SecurityException, IOException, CsvDataTypeMismatchException,
			CsvRequiredFieldEmptyException, InterruptedException {
		JobFactory.readJobsFromCSV(JOB_FILE_PATH);
		GSTFactory.readGSTsFromCSV(GST_FILE_PATH);
		LocalDateTime startDate = JobFactory.getJobPool().get(0).getOrderCreateDateAndTime();
		LocalDateTime endDate = JobFactory.getJobPool().get(JobFactory.getJobPool().size() - 1)
				.getOrderCreateDateAndTime().plusDays(1);
		simulate(startDate, endDate);

	}

	public void initFileNames(String jobFile, String gstFile, String outputFile) {
		JOB_FILE_PATH = jobFile;
		GST_FILE_PATH = gstFile;
//		LOG_FILE_NAME = outputFile;
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