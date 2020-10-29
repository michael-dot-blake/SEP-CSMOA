package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class Log {
	
	public static <T> void writeListToCsv(List<T> list, String filename)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException {
		Writer writer = new FileWriter(filename);

		StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
				.withSeparator(',').withLineEnd(CSVWriter.DEFAULT_LINE_END).withOrderedResults(true).build();
		beanToCsv.write(list);
		beanToCsv.getCapturedExceptions();
		writer.close();

	}
	
	public static void appendSingleLineToCSV(String[] textToAppend, String filename) throws IOException {
		 CSVWriter writer = new CSVWriter(new FileWriter(filename, true));
		 writer.writeNext(textToAppend);
		 writer.close();
		
	}

}
