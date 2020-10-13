package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.logging.Level;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

import com.google.gson.JsonObject;

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

	private String JOB_FILE_PATH = "JobFiles/OneDayJob.csv";

	private String GST_FILE_PATH = "GSTFiles/gstTestData.csv";

	private String LOG_FILE_NAME = "log.log";

	private void log(String avgTravelTime, String percentJobCompliance) throws SecurityException, IOException {

		try {
			Log myLog = new Log(LOG_FILE_NAME);
			System.out.println(">>>>>>>>>>>END>>>>>>>>>>>>>>");
			for (CompletedJobRecord cj : completedJobs) {
				myLog.logger.log(Level.INFO, " " + cj.toString());
			}

			myLog.logger.log(Level.INFO, "The Following jobs were incomplete with current staffing");
			for (Job ijq : idleJobQueue) {
				myLog.logger.log(Level.INFO, " " + ijq.toString());
			}
			myLog.logger.log(Level.INFO, "Average Travel Time: " + avgTravelTime);
			myLog.logger.log(Level.INFO, "Percent Compliance: " + percentJobCompliance + "%");

		} catch (Exception e) {

		}
	}

	public static String formatSeconds(long timeInput) {
		long seconds = timeInput % 60;
		long mins = (timeInput / 60) % 60;
		long hours = (timeInput / 60) / 60;
		String timeString = String.format("%02d Hours %02d Minutes %02d Seconds ", hours, mins, seconds);
		return timeString;

	}

	/**
	 * Main simulation method
	 * 
	 * @throws IOException
	 * @throws SecurityException
	 */

	private void simulate(LocalDateTime currentTime, LocalDateTime endTime) throws SecurityException, IOException {

		int complianceCounter = 0;
		long totalTravelTime = 0;
		long jobIdleTime = 0;
		availableGSTPool = GSTFactory.getGSTpool();

		// currently method not properly removing jobs from queue. However not crashing
		// when GST not found
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
						jobQueue.add(current);
						System.out.println("Job Added from idle to active queue");
						jobIdleTime = current.calculateIdleTime(current.getOrderCreateDateAndTime(), currentTime);
						current.setIdleTime(jobIdleTime);
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
							gst.setAvailable(false);
							System.out.println("Job End Time is: " + j.getEndDateAndTime() + "\n");
							System.out.println("Job Idle Time is: " + formatSeconds(j.getIdleTime()) + "\n");
							gst.setFinishTime(
									j.getEndDateAndTime().plusSeconds(travelTime).plusSeconds(j.getIdleTime()));
							busyGSTs.add(gst);
							availableGSTPool.remove(gst);
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
							gst.setAvailable(false);
							System.out.println("Job End Time is: " + j.getEndDateAndTime() + "\n");
							System.out.println("Job Idle Time is: " + j.getIdleTime() + "\n");
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

			checkGstFinished(currentTime);
			removeCompletedJobFromQueue(currentTime);
			currentTime = currentTime.plusSeconds(1);
		} while (currentTime.isBefore(endTime));

		int jobsCompleted = completedJobs.size();
		int incompleteJobs = idleJobQueue.size();
		float complianceRate = (float) complianceCounter / (jobsCompleted + incompleteJobs) * 100;
		String str = String.format("%2.02f", complianceRate);
		if (jobsCompleted == 0) {
			System.err.println("No Completed Jobs");
		} else {
			long avgTravelTime = totalTravelTime / jobsCompleted;
			log(formatSeconds(avgTravelTime), str);
		}

	}

	private void removeCompletedJobFromQueue(LocalDateTime currentTime) {
		for (Iterator<Job> jobQueueIter = jobQueue.iterator(); jobQueueIter.hasNext();) {
			Job jo = jobQueueIter.next();
			if (currentTime.equals(jo.getEndDateAndTime())
					|| currentTime.equals(jo.getEndDateAndTime().plusSeconds(jo.getIdleTime()))) {
				System.out.println("Job Completed");
				completedJobs.add(new CompletedJobRecord(jo.getAssignedGST(), jo));
				jobQueueIter.remove();
			}
		}

	}

	private Coordinate getJobLocation(Job j) throws IOException {
		String number = j.getHouseNum1();
		String street = j.getStreet();
		String suburb = j.getSuburb();
		String postcode = j.getPostcode();
		System.out.println("Job Location: " + number + " " + street + ", " + suburb + ", " + "NSW " + postcode);
		return AzureMapsApi.getCoordinatesFromAddress(number, street, suburb, postcode);
	}

	private GST findClosestGst(Coordinate jobCoord, int timeBudgetInSeconds, LocalDateTime departureTime)
			throws IOException {

		JsonObject jsonObj = AzureMapsApi.getIsochroneCoords(jobCoord, timeBudgetInSeconds, departureTime);
		Polygon poly = AzureMapsApi.BuildPolygon(jsonObj);
		ArrayList<GST> nearbyGSTs = new ArrayList<GST>();

		for (GST g : GSTFactory.getGSTpool()) {
			Coordinate gstCoord = new Coordinate(g.getLat(), g.getLon());
			if (AzureMapsApi.checkIfLocationInIsochrone(poly, gstCoord) && g.getIsAvailable()) {
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

	private void resetGstTravelTime(ArrayList<GST> gstList) {

		for (GST g : gstList) {
			g.setTravelTime(0);
		}
	}

	private GST findGstByStraightLineDistance(Coordinate jobCoord, ArrayList<GST> gstPool) {
		GST closeGst = null;
		double jx = jobCoord.getX();
		double jy = jobCoord.getY();
		double gx = 0.0;
		double gy = 0.0;
		double minDistance = 200.00;
		for (GST g : gstPool) {
			gx = g.getLat();
			gy = g.getLon();
			if (calcDistance(jx, jy, gx, gy) < minDistance && g.getIsAvailable()) {
				minDistance = calcDistance(jx, jy, gx, gy);
				closeGst = g;
			}
		}
		return closeGst;
	}

	private double calcDistance(double jx, double jy, double gx, double gy) {
		return Math.abs(Math.sqrt((jx - gx) * (jx - gx) + (jy - gy) * (jy - gy)));
	}

	private void checkGstFinished(LocalDateTime currTime) {
		for (Iterator<GST> busyGSTIter = busyGSTs.iterator(); busyGSTIter.hasNext();) {
			GST go = busyGSTIter.next();
			if (currTime.equals(go.getFinishTime())) {
				go.setAvailable(true);
				go.setFinishTime(null);
				System.out.println("GST re-added to availablePool");
				availableGSTPool.add(go);
				busyGSTIter.remove();
				System.out.println("GST Removed from BusyPool");

			}
		}
	}

	private void runSimulation() throws SecurityException, IOException {
		JobFactory.readJobsFromCSV(JOB_FILE_PATH);
		GSTFactory.readGSTsFromCSV(GST_FILE_PATH);
		LocalDate startDate = JobFactory.getJobPool().get(0).getOrderCreateDate();
		LocalDate endDate = JobFactory.getJobPool().get(JobFactory.getJobPool().size() - 1).getOrderCreateDate();
		LocalDateTime start = LocalDateTime.of(startDate, LocalTime.MIN);
		LocalDateTime end = LocalDateTime.of(endDate, LocalTime.MAX);
		simulate(start, end);

	}

	private void initFileNames(String jobFile, String gstFile, String outputFile) {
		JOB_FILE_PATH = jobFile;
		GST_FILE_PATH = gstFile;
		LOG_FILE_NAME = outputFile;
	}

	public static void main(String[] args) throws SecurityException, IOException {

		Simulation s = new Simulation();
		if (args.length == 3) {
			s.initFileNames(args[0], args[1], args[2]);
		}
		s.runSimulation();

	}// end main

}// end class