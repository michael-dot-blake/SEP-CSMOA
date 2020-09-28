package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

import com.google.gson.JsonObject;

import model.CompletedJobRecord;
import model.GST;
import model.Job;

/**
 * @author Michael Blake Simulation class to handle assigning jobs to GSTs in
 *         early stages of development
 */
public class Simulation {

	Random rand = new Random();

	// Queue storage for jobs
	private PriorityQueue<Job> jobQueue = new PriorityQueue<Job>();

	// Store completed jobs
	private ArrayList<CompletedJobRecord> completedJobs = new ArrayList<CompletedJobRecord>();

	//Store the Isochrone results from simulation
	private ArrayList<String> simResults = new ArrayList<String>();

	private void log() throws SecurityException, IOException {

		try {
			Log myLog = new Log("log.txt");
			System.out.println(">>>>>>>>>>>END>>>>>>>>>>>>>>");
			for (CompletedJobRecord cj : completedJobs) {
				myLog.logger.log(Level.INFO, " " + cj.toString());
			}
			for (int i = 0; i < simResults.size() ; i++) {
				myLog.logger.log(Level.INFO, " " + simResults.get(i));
			}

		} catch (Exception e) {

		}
	}

	private LocalDateTime inputDateAndTime() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Year");
		int year = sc.nextInt();
		System.out.println("Enter Month 1 - 12");
		int month = sc.nextInt();
		System.out.println("Enter Day of Month");
		int dayOfMonth = sc.nextInt();
		LocalDate date = LocalDate.of(year, month, dayOfMonth);
		System.out.println("Enter hour 0 - 23");
		int hour = sc.nextInt();
		System.out.println("Enter Minute 0 - 59");
		int minute = sc.nextInt();
		LocalTime time = LocalTime.of(hour, minute);
		LocalDateTime dateTime = LocalDateTime.of(date, time);
		return dateTime;
	}

	/**
	 * Main simulation method
	 * 
	 * @throws IOException
	 * @throws SecurityException
	 */
	private void runSim(LocalDateTime currentTime, LocalDateTime endTime) throws SecurityException, IOException {

		String path = "JobFiles/testHisData.csv";
		JobFactory.readJobsFromCSV(path);

		String path2 = "GSTFiles/gstTestData.csv";
		GSTFactory.readGSTsFromCSV(path2);

		Iterator<Job> jobQueueIter = jobQueue.iterator();
		Iterator<Job> jobPoolIter = JobFactory.getJobPool().iterator();
		Iterator<GST> gstPoolIter = GSTFactory.getGSTpool().iterator();

		do {
			for (Job j : JobFactory.getJobPool()) {
				// if the time and date of the job creation equals the currentTime object
				// then the job will be added to the jobQueue
				if (currentTime.isEqual(j.getOrderCreateDateAndTime())) {
					//System.out.println("Times match");
					jobQueue.add(j);
					System.out.println("For job "+j.getOrderNum());
					simResults.add("For job "+j.getOrderNum());
					if (checkGst(j, 900) != null) {
						System.out.println("Found GST in 15min isochrone\n");
						simResults.add("Found GST in 15min isochrone\n");
					}
					else if (checkGst(j, 1800) != null) {
						System.out.println("Found GST in 30min isochrone\n");
						simResults.add("Found GST in 30min isochrone\n");
					}
					else {
						System.out.println("No GST found in 30min isochone\n");
						simResults.add("No GST found in 30min isochone\n");
					}
					//System.out.println(jobQueue);
					// Now loop through both the jobQueue and the gstPool to assign a GST
					for (Job jq : jobQueue) {
						for (GST g : GSTFactory.getGSTpool()) {
							if ((g.getIsAvailable() == true)) {
								g.setAvailable(false);
								// when the timer progresses to the time that the job is completed
								// a completedjob object is created and GST is set to available
								jq.setEndDateAndTime(
										jq.getOrderCreateDateAndTime().plusHours(1));
								if (currentTime.isEqual(jq.getEndDateAndTime())) {
									System.out.println("myDateTime is equal");
									completedJobs.add(new CompletedJobRecord(g, j));
									jobQueue.remove(j);

									// The gst flag is reset to available and they can now be assigned new jobs
									g.setAvailable(true);

								}

							}

						}

					}

				}

			}
			currentTime = currentTime.plusSeconds(1);
		} while (currentTime.isBefore(endTime));
		
	log();

	}
	
	private void runSim2(LocalDateTime currentTime, LocalDateTime endTime) throws SecurityException, IOException {
		
		String path = "JobFiles/testHisData.csv";
		JobFactory.readJobsFromCSV(path);

		String path2 = "GSTFiles/gstTestData.csv";
		GSTFactory.readGSTsFromCSV(path2);
		
		do {
			for (Job j : JobFactory.getJobPool()) {
				if (currentTime.isEqual(j.getOrderCreateDateAndTime())) {
					jobQueue.add(j);
				}
			}
			if (jobQueue.size()>0) {
				for (Job j : jobQueue) {
					GST gst = null;
					if (checkGst(j, 900) != null) {
						gst = checkGst(j, 900);
					}
				}
			}
		}
		while (currentTime.isBefore(endTime));
	}
	
	public GST checkGst(Job j, int timeLimit) throws IOException {
		String number = j.getHouseNum1();
		String street = j.getStreet();
		String suburb = j.getSuburb();
		String postcode = j.getPostcode();
		
		Coordinate coord = AzureMapsApi.getCoordinatesFromAddress(number, street, suburb, postcode);
		JsonObject jsonObj = AzureMapsApi.getIsochroneCoords(coord, timeLimit);
		Polygon p = AzureMapsApi.BuildPolygon(jsonObj);
		for (GST g : GSTFactory.getGSTpool()) {
			Coordinate gstCoord = new Coordinate(g.getLat(), g.getLon());
			//System.out.println("GST Co-ord is: "+gstCoord);
			//System.out.println(AzureMapsApi.checkIfLocationInIsoChrone(p, gstCoord));
			if (AzureMapsApi.checkIfLocationInIsoChrone(p, gstCoord)) {
				return g;
			}
		}
		return null;
	}

	public static void main(String[] args) throws SecurityException, IOException {

		Simulation s = new Simulation();
		Scanner scan = new Scanner(System.in);
//		System.out.println("Enter the Start Date(YYYY-MM-DD): ");
//		String startDateString = scan.nextLine();
		LocalDate startDate = LocalDate.of(2019, 8, 8);
//		System.out.println("Enter the Time(HH:MM:SS): ");
//		String timeString = scan.nextLine();
//		LocalTime time = LocalTime.parse(timeString);
//		System.out.println("Enter the EndDate(YYYY-MM-DD): ");
//		String endDateString = scan.nextLine();
		LocalDate endDate = LocalDate.of(2019, 8, 24);

		LocalDateTime start = LocalDateTime.of(startDate, LocalTime.MIN);
		LocalDateTime end = LocalDateTime.of(endDate, LocalTime.MIN);
		s.runSim(start, end);

	}// end main

}// end class
