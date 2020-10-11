package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
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
 * @author Michael Blake Simulation class to handle assigning jobs to GSTs in
 *         early stages of development Clark Skinner worked on runsim/2(),
 *         getJobLocation(), findGst(), simpleGetGst(), calcDistance().
 */
public class Simulation {

	// Queue storage for jobs
	private PriorityQueue<Job> jobQueue = new PriorityQueue<Job>();

	// Store completed jobs
	private ArrayList<CompletedJobRecord> completedJobs = new ArrayList<CompletedJobRecord>();

	private ArrayList<GST> busyGSTs = new ArrayList<GST>();
	
	private final String JOB_FILE_PATH = "JobFiles/FutureJobs.csv";

	
	private final String GST_FILE_PATH = "GSTFiles/gstData10.csv";
	

	private void log(String avgTravelTime, String percentJobCompliance) throws SecurityException, IOException {

		try {
			Log myLog = new Log("log.log");
			System.out.println(">>>>>>>>>>>END>>>>>>>>>>>>>>");
			for (CompletedJobRecord cj : completedJobs) {
				myLog.logger.log(Level.INFO, " " + cj.toString());
			}
			myLog.logger.log(Level.INFO, "Average Travel Time: " + avgTravelTime);
			myLog.logger.log(Level.INFO, "Percent Compliance: " + percentJobCompliance + "%");

		} catch (Exception e) {

		}
	}

	public static String formatSeconds(int travelTimeInSeconds) {
		int seconds = travelTimeInSeconds % 60;
		int mins = (travelTimeInSeconds / 60) % 60;
		int hours = (travelTimeInSeconds / 60) / 60;
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

		

		int totalTravelTime = 0;
		int complianceCounter = 0;
		

		do {
			for (Job j : JobFactory.getJobPool()) {
				if (currentTime.isEqual(j.getOrderCreateDateAndTime())) {
//					System.out.println(j.getOrderCreateDateAndTime());
					jobQueue.add(j);
				}
			}
			if (jobQueue.size() > 0) {
				for (Job j : jobQueue) {
					if (j.getAssignedGST() == null) {
						Coordinate jobCoord = getJobLocation(j);
						LocalDateTime jobTime = j.getOrderCreateDateAndTime();
						int jobDuration = j.getJobDuration();
						GST gst = findClosestGst(jobCoord, 1800, jobTime);
						System.out.println("For Job " + j.getOrderNum());

						if (gst != null) {
							System.out.println("Found GST: " + gst.getgSTid() + " in 30min isochrone.");
							Coordinate gstCoord = new Coordinate(gst.getLat(), gst.getLon());
							int travelTime = AzureMapsApi.getRouteTime(gstCoord, jobCoord);
							j.setTravelTimeInSeconds(travelTime);
							System.out.println("Travel Time is: " + formatSeconds(travelTime) + "\n");
							j.setEndDateAndTime(jobTime.plusMinutes(jobDuration).plusSeconds(travelTime));
							totalTravelTime = totalTravelTime + travelTime;
							complianceCounter++;

						} else {
							System.out.println("No GST found within 30min!!!");
							gst = findGstByStraightLineDistance(jobCoord, GSTFactory.getGSTpool());
							if (gst == null) {
								System.out.println("NO AVAILABLE GST!\n");
							} else {
								System.out.println("Found the closest GST: " + gst.getgSTid() + " outside isochrone");
								Coordinate gstCoord = new Coordinate(gst.getLat(), gst.getLon());
								int travelTime = AzureMapsApi.getRouteTime(gstCoord, jobCoord);
								j.setTravelTimeInSeconds(travelTime);
								System.out.println("Travel Time is: " + formatSeconds(travelTime) + "\n");
								j.setEndDateAndTime(jobTime.plusMinutes(jobDuration).plusSeconds(travelTime));
								totalTravelTime = totalTravelTime + travelTime;
							}
						}
						if (gst != null) {
							j.setAssignedGST(gst);
							gst.setAvailable(false);
							System.out.println("Job End Time is: " + j.getEndDateAndTime());
							gst.setFinishTime(j.getEndDateAndTime());
							busyGSTs.add(gst);
							System.out.println("busyPool size: " + busyGSTs.size() + "\n");
						}
					}
				}

			}
			for (Iterator<Job> jobQueueIter = jobQueue.iterator(); jobQueueIter.hasNext();) {
				Job jo = jobQueueIter.next();
				if (currentTime.equals(jo.getEndDateAndTime())) {
					System.out.println(" ");
					completedJobs.add(new CompletedJobRecord(jo.getAssignedGST(), jo));
					jobQueueIter.remove();
				}
			}
			checkGstFinished(currentTime);
			
			
			// System.out.println(currentTime);
			currentTime = currentTime.plusSeconds(1);
		} while (currentTime.isBefore(endTime));

		
		int jobsCompleted = completedJobs.size();
		float complianceRate = (float) complianceCounter / jobsCompleted * 100;
		String str = String.format("%2.02f", complianceRate);
		if(jobsCompleted == 0) {
			System.err.println("No Completed Jobs");
		}
		else {
			int avgTravelTime = totalTravelTime / jobsCompleted;
			log(formatSeconds(avgTravelTime), str);
		}

	}

	private Coordinate getJobLocation(Job j) throws IOException {
		String number = j.getHouseNum1();
		String street = j.getStreet();
		String suburb = j.getSuburb();
		String postcode = j.getPostcode();
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
				//System.out.println(nearbyGSTs.size());
			}
		}
	
		if (nearbyGSTs.size() > 0) {
			for (GST closeGst : nearbyGSTs) {
				Coordinate gstCoord = new Coordinate(closeGst.getLat(), closeGst.getLon());
				int travelTime = AzureMapsApi.getRouteTime(gstCoord, jobCoord);
				//System.out.println(travelTime);
				closeGst.setTravelTime(travelTime);

			}
			System.out.println(nearbyGSTs);
			Collections.sort(nearbyGSTs);
			GST closestGST = nearbyGSTs.get(0);
			nearbyGSTs.remove(0);
			resetGstTravelTime(nearbyGSTs);
			System.out.println("The closest GST was "+closestGST.getgSTid());
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
				busyGSTIter.remove();
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
	public static void main(String[] args) throws SecurityException, IOException {
		
		Simulation s = new Simulation();
		s.runSimulation();
		

	}// end main

}// end class
