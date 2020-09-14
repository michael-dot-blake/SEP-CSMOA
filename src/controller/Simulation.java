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

	Random rand = new Random();

	// Queue storage for jobs
	private PriorityQueue<Job> jobQueue = new PriorityQueue<Job>();

	// Store completed jobs
	private ArrayList<CompletedJob> completedJobs = new ArrayList<CompletedJob>();

	private ArrayList<GST> gstPool = new ArrayList<GST>();

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
	 * 
	 * @throws IOException
	 * @throws SecurityException
	 */
	private void runSim(LocalDate date, LocalTime time, int runtimeInHours) throws SecurityException, IOException {

		// initialise logger
		initLogger();

		// Read in jobs from provided csv file
		String path = "JobFiles/testHisData.csv";
		JobFactory.readJobsFromCSV(path);
		
		//Read in GSTs from provided csv file
		String path2 = "GSTFiles/gstTestData.csv";
		GSTFactory.readGSTsFromCSV(path2);

		// convert the user input of hours into seconds
		long runtimeInSeconds;
		runtimeInSeconds = runtimeInHours * 3600;

		// initialise counter which will increment each time a second is added to the
		// clock
		int count = 0;

		// create LocalDateTime object based on user inputs of date and time
		LocalDateTime myDateTime = LocalDateTime.of(date, time);

		do {
			for (Job j : JobFactory.getJobPool()) {

				// if the time and date of the job creation equals the myDateTime object
				// then the job will be added to the jobQueue
				if (myDateTime.isEqual(j.getOrderCreateDateAndTime())) {
					System.out.println("Times match");
					jobQueue.add(j);
					System.out.println(jobQueue);

					// Now loop through both the jobQueue and the gstPool to assign a GST
					for (Job jq : jobQueue) {
						for (GST g : GSTFactory.getGSTpool()) {
							if ((g.getIsAvailable() == true)) {
								g.setAvailable(false);

								// when the timer progresses to the time that the job is completed
								// a completedjob object is created and GST is set to available
								jq.setEndDateAndTime(
										jq.getOrderCreateDateAndTime().plusSeconds(jq.getJobDuration() * 60));
								if (myDateTime.isEqual(jq.getEndDateAndTime())) {
									//System.out.println("myDateTime is equal");
									completedJobs.add(new CompletedJob(jq.getOrderNum(), jq.getOrderType(),
											jq.getOrderDescription(), jq.getIssueCode(), jq.getIssuedescription(),
											jq.getMainActType(), jq.getMainActDescription(), jq.getOrderCreateDate(),
											jq.getOrderCreateTime(), jq.getOrderCreateDateAndTime(),
											jq.getJobPriority(), jq.getSuburb(), jq.getStreet(), jq.getHouseNum1(),
											jq.getHouseNum2(), jq.getPostcode(), jq.getJobDuration(),
											jq.getFitterDistrict(), jq.getEndDateAndTime(), g));
									jobQueue.remove(j);

									// The gst flag is reset to available and they can now be assigned new jobs
									g.setAvailable(true);

								}

							}

						}

					}

				}

			}
			myDateTime = myDateTime.plusSeconds(1);
			//System.out.println(myDateTime);
			count++;
		} while (count <= runtimeInSeconds);

	}

	public static void main(String[] args) throws SecurityException, IOException {

		Simulation s = new Simulation();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the date(YYYY-MM-DD): ");
		String dateString = scan.nextLine();
		LocalDate date = LocalDate.parse(dateString);
		System.out.println("Enter the Time(HH:MM:SS): ");
		String timeString = scan.nextLine();
		LocalTime time = LocalTime.parse(timeString);
		System.out.println("Enter the Simulation running Time(In hours): ");
		int runningTime = scan.nextInt();
		s.runSim(date, time, runningTime);

	}// end main

}// end class
