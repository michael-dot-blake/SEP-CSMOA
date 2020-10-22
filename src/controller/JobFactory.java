package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import model.GST;
import model.Job;

public class JobFactory {

	private static ArrayList<Job> jobPool = new ArrayList<Job>();

	public static void readJobsFromCSV(String filename) {
		try {
			File file = new File(filename);

			if (file.exists() && !file.isDirectory()) {

				Scanner sc = new Scanner(file);
				@SuppressWarnings("unused")
				String header = sc.nextLine();
				// System.out.println("Job header: "+header);
				while (sc.hasNext()) {
					boolean jobAdded = false;
					String row = sc.nextLine();
					String[] reform = row.split(",");
					String jobId = reform[0];
					String jobPriority = reform[1];
					String houseNum1 = reform[2];
					String jobStreet = reform[3];
					String jobSuburb = reform[4];
					String postCode = reform[5];
					String jobTimeString = reform[6];
					String jobDurationString = reform[7];
					String yearStr = reform[8].substring(0, 4);
					String monthStr = reform[8].substring(4, 6);
					String dayStr = reform[8].substring(6, reform[8].length());
					try {
						LocalDate jobDate = LocalDate.parse(yearStr + "-" + monthStr + "-" + dayStr);
						LocalTime jobTime = LocalTime.parse(jobTimeString);
						LocalDateTime jobDateAndTime = LocalDateTime.of(jobDate, jobTime);
						int jobDurationInMinutes = 0;
						if (jobDurationString.length() == 0) {
							jobDurationInMinutes = 30;
						} else {
							System.out.println("Job Duration is not null");
							jobDurationInMinutes = Integer.parseInt(jobDurationString);
							System.out.println(jobDurationInMinutes);

						}

						// The following variables will be empty when a job is created and set when the
						// job is completed
						LocalDateTime endDateAndTime = null;
						int travelTimeInSeconds = 0;
						long idleTime = 0;
						GST assignedGST = null;

						jobPool.removeIf(jo -> jobId.equals(jo.getOrderNum()));
						jobPool.add(new Job(jobId, jobPriority, houseNum1, jobStreet, jobSuburb, postCode,
								jobDateAndTime, jobDurationInMinutes, assignedGST, endDateAndTime, idleTime,
								travelTimeInSeconds));
						jobAdded = true;
						ListSort(jobPool);
					} catch (Exception e) {
						System.out.println("job data parse error.");
						e.printStackTrace();
					}
					if (!jobAdded) {
						System.err.println("\nFAILED TO INIT JOB!");
					}
				}
				sc.close();

			}

			else {
				System.out.println("Job File Not Found!");
				System.out.println("WARNING!! Please Provide Proper File Name & File Directory");

				System.exit(0);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong with job file.\n");
			e.printStackTrace();
		}

		System.out.println("Number of Jobs in the Simulation: " + jobPool.size());
	}

	public static ArrayList<Job> getJobPool() {
		return jobPool;
	}

	public static void ListSort(ArrayList<Job> jobPool) {
		Collections.sort(jobPool, new Comparator<Job>() {
			@Override
			public int compare(Job j1, Job j2) {
				return j1.getOrderCreateDateAndTime().compareTo(j2.getOrderCreateDateAndTime());

			}
		});
	}

}
