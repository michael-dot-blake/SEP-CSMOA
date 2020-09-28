package controller;

import java.io.FileReader;
import java.util.Arrays;

import com.opencsv.CSVReader;

public class CSVTest {

	public static void main(String[] args) {

		try {

			String file_name = "";

			CSVReader reader = new CSVReader(new FileReader(file_name));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {

				if (nextLine != null) {
					System.out.println(Arrays.toString(nextLine));
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("CSV Read Complete");

	}

}
