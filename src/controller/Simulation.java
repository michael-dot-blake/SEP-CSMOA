package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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

	private void log(String avgTravelTime, String percentJobCompliance) throws SecurityException, IOException {

		try {
//			FileHandler fh = new FileHandler("C:\\Users\\61469\\Desktop\\MyLog.log"); 
			Log myLog = new Log("logeer.log");
//			FileHandler fh = new FileHandler("C:/temp/test/MyLogFile.log"); 
			System.out.println(">>>>>>>>>>>END>>>>>>>>>>>>>>");
			for (CompletedJobRecord cj : completedJobs) {
				myLog.logger.log(Level.INFO, " " + cj.toString());
			}
			myLog.logger.log(Level.INFO, "Average Travel Time: " + avgTravelTime);
			myLog.logger.log(Level.INFO, "Percent Compliance: " + percentJobCompliance + "%");
//			myLog.addHandler(fh);

		} catch (Exception e) {

		}
	}

	private String formatSeconds(int travelTimeInSeconds) {
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

	private void runSimulation(LocalDateTime currentTime, LocalDateTime endTime) throws SecurityException, IOException {

		String path = "JobFiles/Jobs.csv";
		JobFactory.readJobsFromCSV(path);

		String path2 = "GSTFiles/gstTestData.csv";
		GSTFactory.readGSTsFromCSV(path2);

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
					Coordinate jobCoord = getJobLocation(j);
					LocalDateTime jobTime = j.getOrderCreateDateAndTime();
					int jobDuration = j.getJobDuration();
					GST gst = findGst(jobCoord, 1800, jobTime);
					System.out.println("For Job " + j.getOrderNum());

					if (gst != null) {
						System.out.println("Found GST: " + gst.getgSTid() + " in 30min isochrone.");
						Coordinate gstCoord = new Coordinate(gst.getLat(), gst.getLon());
						int travelTime = AzureMapsApi.getRouteTime(gstCoord, jobCoord);
						j.setTravelTimeInSeconds(travelTime);
						System.out.println("Travel Time is: " + formatSeconds(travelTime) + "\n");
						j.setEndDateAndTime(jobTime.plusMinutes(jobDuration).plusSeconds(travelTime));
						// gst.setAvailable(false);
						totalTravelTime = totalTravelTime + travelTime;
						complianceCounter++;

					} else {
						System.out.println("No GST found within 30min!!!");
						gst = simpleGetGst(jobCoord, GSTFactory.getGSTpool());
						if (gst == null) {
							System.out.println("NO AVAILABLE GST!\n");
						} else {
							System.out.println("Found the closest GST: " + gst.getgSTid() + " outside isochrone");
							// gst.setAvailable(false);
							Coordinate gstCoord = new Coordinate(gst.getLat(), gst.getLon());
							int travelTime = AzureMapsApi.getRouteTime(gstCoord, jobCoord);
							j.setTravelTimeInSeconds(travelTime);
							System.out.println("Travel Time is: " + formatSeconds(travelTime) + "\n");
							j.setEndDateAndTime(jobTime.plusMinutes(jobDuration).plusSeconds(travelTime));
							totalTravelTime = totalTravelTime + travelTime;
						}
					}
					if (gst != null) {
						gst.setAvailable(false);
						System.out.println("Job End Time is: " + j.getEndDateAndTime());
						gst.setFinishTime(j.getEndDateAndTime());
						busyGSTs.add(gst);
						System.out.println("busyPool size: " + busyGSTs.size() + "\n");
					}
					for (Iterator<Job> jobQueueIter = jobQueue.iterator(); jobQueueIter.hasNext();) {
						Job jo = jobQueueIter.next();
						if (currentTime.equals(jo.getEndDateAndTime()))
							System.out.println(" ");
						completedJobs.add(new CompletedJobRecord(gst, jo));
						jobQueueIter.remove();
					}
				}

			}
			checkGstFinished(currentTime);
			// System.out.println(currentTime);
			currentTime = currentTime.plusSeconds(1);
		} while (currentTime.isBefore(endTime));

		
		int jobsCompleted = completedJobs.size();
		float complianceRate = (float) complianceCounter / jobsCompleted * 100;
		String str = String.format("%2.02f", complianceRate);
		if(jobsCompleted == 0)
			System.err.println("No Completed Jobs");
		else {
			int avgTravelTime = totalTravelTime / jobsCompleted;
			log(formatSeconds(avgTravelTime), str);
		}

	}

	public Coordinate getJobLocation(Job j) throws IOException {
		String number = j.getHouseNum1();
		String street = j.getStreet();
		String suburb = j.getSuburb();
		String postcode = j.getPostcode();
		return AzureMapsApi.getCoordinatesFromAddress(number, street, suburb, postcode);
	}

	public GST findGst(Coordinate coord, int timeLimit, LocalDateTime depart) throws IOException {
		depart = LocalDateTime.now();
		JsonObject jsonObj = AzureMapsApi.getIsochroneCoords(coord, timeLimit, depart);
		Polygon p = AzureMapsApi.BuildPolygon(jsonObj);
		ArrayList<GST> closeGSTs = new ArrayList<GST>();
		for (GST g : GSTFactory.getGSTpool()) {
			Coordinate gstCoord = new Coordinate(g.getLat(), g.getLon());
			// System.out.println("GST Co-ord is: "+gstCoord);
			// System.out.println(AzureMapsApi.checkIfLocationInIsoChrone(p, gstCoord));
			if (AzureMapsApi.checkIfLocationInIsochrone(p, gstCoord) && g.getIsAvailable()) {
				closeGSTs.add(g);
			}
		}
		if (closeGSTs.size() != 0) {
			return simpleGetGst(coord, closeGSTs);
		}
		return null;
	}

	public GST simpleGetGst(Coordinate jobCoord, ArrayList<GST> gstPool) {
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

	public double calcDistance(double jx, double jy, double gx, double gy) {
		return Math.abs(Math.sqrt((jx - gx) * (jx - gx) + (jy - gy) * (jy - gy)));
	}

	public void checkGstFinished(LocalDateTime currTime) {
		for (Iterator<GST> busyGSTIter = busyGSTs.iterator(); busyGSTIter.hasNext();) {
			GST go = busyGSTIter.next();
			if (currTime.equals(go.getFinishTime())) {
				go.setAvailable(true);
				go.setFinishTime(null);
				busyGSTIter.remove();
			}
		}
	}

	public static void main(String[] args) throws SecurityException, IOException {

		Simulation s = new Simulation();
		LocalDate startDate = LocalDate.of(2021, 1, 8);
		LocalDate endDate = LocalDate.of(2021, 12, 26);
		LocalDateTime start = LocalDateTime.of(startDate, LocalTime.MIN);
		LocalDateTime end = LocalDateTime.of(endDate, LocalTime.MAX);
		s.runSimulation(start, end);


	}// end main

}// end class
