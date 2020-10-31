package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import model.GST;

public class GSTFactory {
	
	private static ArrayList<GST> gstPool = new ArrayList<GST>();
	
	public static void readGSTsFromCSV(String filename) {
		try {
			File file = new File(filename);	
			
			if(file.exists() && !file.isDirectory()) { 
				
				Scanner sc = new Scanner(file);
				@SuppressWarnings("unused")
				String header = sc.nextLine();
				//System.out.println("GST header: "+header);
				while (sc.hasNext()) {
					boolean gstAdded = false;
					String row = sc.nextLine();
					String[] reform = row.split(",");
					String GSTid = reform[0];
					String latString = reform[1];
					String lonString = reform[2];
					String dateString = reform[3];
					double lat = 0;
					double lon = 0;
					LocalDate date = null;
					try {
						lat = Double.parseDouble(latString);
						lon = Double.parseDouble(lonString);
						date = LocalDate.parse(dateString);
						gstPool.add(new GST(GSTid, lat, lon, date));
						gstAdded = true;
					}
					catch (Exception e) {
						System.out.println("co-ord parse error.");
						e.printStackTrace();
					}
					if (!gstAdded) {
						System.err.println("\nFAILED TO INIT GST!");
						System.out.println("GST id:"+GSTid+" lat:"+lat+" lon:"+lon);
					}
				}
				System.out.println("Number of GSTs in the Simulation: "+gstPool.size());
				sc.close();
			    
			}
			else {
				System.out.println("GST File Not Found!");
				System.out.println("WARNING!! Please Provide Proper File Name & File Directory");
			
				System.exit(0);
			}

		}
		catch (FileNotFoundException e) {
			System.out.println("Something went wrong with GST file.\n");
			e.printStackTrace();
		}
//		for (GST gst : gstPool) {
//			System.out.println(gst);
//		}
	}
	
	public static ArrayList<GST> getGSTpool() {
		return gstPool;
	}
	
	public static ArrayList<GST> getNextGSTs(LocalDate currDate) {
		ArrayList<GST> availableGSTs = new ArrayList<GST>();
		for (GST g : gstPool) {
			if (g.getShiftDate().compareTo(currDate) == 0) {
				availableGSTs.add(g);
			}
		}
		
		return availableGSTs;
	}

}
