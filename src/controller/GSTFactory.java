package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.GST;

public class GSTFactory {
	
	private static ArrayList<GST> gstPool = new ArrayList<GST>();
	
	public static void readGSTsFromCSV(String filename) {
		System.out.println("GST init start...\n");
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			String header = sc.nextLine();
			//System.out.println("GST header: "+header);
			while (sc.hasNext()) {
				boolean gstAdded = false;
				String row = sc.nextLine();
				String[] reform = row.split(",");
				String GSTid = reform[0];
				String latString = reform[1];
				String lonString = reform[2];
				double lat = 0;
				double lon = 0;
				try {
					lat = Double.parseDouble(latString);
					lon = Double.parseDouble(lonString);
					gstPool.add(new GST(GSTid, lat, lon));
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
			sc.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Something went wrong with GST file.\n");
			e.printStackTrace();
		}
		for (GST gst : gstPool) {
			System.out.println(gst);
		}
		System.out.println("\nGST init finish.\n");
	}
	
	public static ArrayList<GST> getGSTpool() {
		return gstPool;
	}

}
