package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

public class SimUtils {

	public static long calculateTimeBetween(LocalDateTime startTime, LocalDateTime endTime) {
		long seconds = startTime.until(endTime, ChronoUnit.SECONDS);
		return seconds;
	}

	public static double calcDistance(double jx, double jy, double gx, double gy) {
		return Math.abs(Math.sqrt((jx - gx) * (jx - gx) + (jy - gy) * (jy - gy)));
	}

	public static Coordinate getJobLocation(Job j) throws IOException {
		String number = j.getHouseNum1();
		String street = j.getStreet();
		String suburb = j.getSuburb();
		String postcode = j.getPostcode();
		System.out.println("Job Location: " + number + " " + street + ", " + suburb + ", " + "NSW " + postcode);
		return AzureMapsApi.getCoordinatesFromAddress(number, street, suburb, postcode);
	}

	public static void removeCompletedJobFromQueue(LocalDateTime currentTime, PriorityQueue<Job> jobQueue,
			ArrayList<CompletedJobRecord> completedJobs) {
		for (Iterator<Job> jobQueueIter = jobQueue.iterator(); jobQueueIter.hasNext();) {
			Job jo = jobQueueIter.next();
			if (currentTime.equals(jo.getEndDateAndTime().plusSeconds(jo.getIdleTime()))) {
				GST gst = jo.getAssignedGST();
				completedJobs.add(new CompletedJobRecord(gst, jo));
				gst.getMyJobsToday().add(jo);
				jobQueueIter.remove();
			}
		}
	}

	public static ArrayList<GST> getOverallGstStats(ArrayList<GST> gstPool) {

		ArrayList<GST> myGSTstats = new ArrayList<GST>();

		int totalTravelTime = 0;
		int numJobsCompleted = 0;
		int totalTimeWorked = 0;
		String gstId = null;
		double lat = 0.00;
		double lon = 0.00;
		LocalDate shift = null;

		for (GST g : gstPool) {
			if (g.getMyJobsToday().size() > 0) {
				gstId = g.getgSTid();
				lat = g.getLat();
				lon = g.getLon();
				shift = g.getShiftDate();
				for (Job myJobs : g.getMyJobsToday()) {
					totalTravelTime = totalTravelTime + (myJobs.getTravelTimeInSeconds() * 2);
					numJobsCompleted = g.getMyJobsToday().size();
					totalTimeWorked = totalTimeWorked + (myJobs.getJobDuration() * 60);
				}
				myGSTstats.add(new GST(gstId, lat, lon, shift, totalTravelTime, totalTimeWorked, numJobsCompleted));
				totalTravelTime = 0;
				numJobsCompleted = 0;
				totalTimeWorked = 0;

			} else {
				gstId = g.getgSTid();
				lat = g.getLat();
				lon = g.getLon();
				shift = g.getShiftDate();
				totalTravelTime = 0;
				numJobsCompleted = 0;
				totalTimeWorked = 0;
				myGSTstats.add(new GST(gstId, lat, lon, shift, totalTravelTime, totalTimeWorked, numJobsCompleted));

			}

		}
		return myGSTstats;
	}

	public static void generateOutput(long avgTravelTime, float complianceRate, int incompleteJobs,
			ArrayList<CompletedJobRecord> completedJobs, ArrayList<GST> gstRecord, String jobFilename,
			String gstFilename)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, InterruptedException {
		String compString = "Compliance Rate: " + (String.format("%.0f%%", complianceRate));
		String travTimeString = "Average Travel Time Mins: " + (avgTravelTime / 60);
		String incompleteJobString = "Incomplete Jobs: " + Integer.toString(incompleteJobs);
		String[] comp = new String[] { compString };
		String[] trav = new String[] { travTimeString };
		String[] incomplete = new String[] { incompleteJobString };
		Log.writeListToCsv(completedJobs, jobFilename);
		Log.appendSingleLineToCSV(comp, jobFilename);
		Log.appendSingleLineToCSV(trav, jobFilename);
		Log.appendSingleLineToCSV(incomplete, jobFilename);

		Log.writeListToCsv(gstRecord, gstFilename);

		System.out.println("Run Successful");
		System.out.println("Job Data written to: " + jobFilename);
		System.out.println("GST Data written to: " + gstFilename);

	}

	public static GST findClosestGst(Coordinate jobCoord, int timeBudgetInSeconds, LocalDateTime departureTime,
			ArrayList<GST> gstPool) throws IOException {

		JsonObject jsonObj = AzureMapsApi.getIsochroneCoords(jobCoord, timeBudgetInSeconds, departureTime);
		Polygon poly = AzureMapsApi.BuildPolygon(jsonObj);
		ArrayList<GST> nearbyGSTs = new ArrayList<GST>();

		for (GST g : gstPool) {
			Coordinate gstCoord = new Coordinate(g.getLat(), g.getLon());
			if (AzureMapsApi.checkIfLocationInIsochrone(poly, gstCoord)) {
				nearbyGSTs.add(g);
			}
		}

		System.out.println("GSTs in Isochrone: " + nearbyGSTs);
		if (nearbyGSTs.size() > 0) {
			GST closeGst = findGstByStraightLineDistance(jobCoord, nearbyGSTs);
			return closeGst;
		}

		else {
			System.out.println("NO GST found in isochrone");
			return null;
		}

	}

	public static GST findGstByStraightLineDistance(Coordinate jobCoord, ArrayList<GST> gstPool) {
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

	public static void checkGstFinished(LocalDateTime currTime, ArrayList<GST> availableGSTPool,
			ArrayList<GST> busyGSTs) {
		for (Iterator<GST> busyGSTIter = busyGSTs.iterator(); busyGSTIter.hasNext();) {
			GST go = busyGSTIter.next();
			if (currTime.equals(go.getFinishTime())) {
				go.setFinishTime(null);
				busyGSTIter.remove();
				availableGSTPool.add(go);

			}
		}
	}

	public static String formatSeconds(long timeInput) {
		// long seconds = timeInput % 60;
		long mins = (timeInput / 60);
		// long hours = (timeInput / 60) / 60;
		String timeString = String.format("%02d Minutes", mins);
		return timeString;

	}

}
