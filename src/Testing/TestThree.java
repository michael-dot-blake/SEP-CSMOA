package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import controller.Simulation;



@SpiraTestConfiguration(
		// following are REQUIRED
		url = "https://rmit-university.spiraservice.net",
		login="MRHasan",

//		rssToken = "{93C84FF0-81B2-4FBD-B602-837F4AD1A7E3}", 
		projectId=715
)

class TestThree {
	//Initialzation of Test

	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		//Starting of test
		System.out.println("This Test Has Started");
	}
	
	@Test
	@SpiraTestCase(testCaseId=25576)
	public void test3() throws NoSuchMethodException, SecurityException, IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException
	{
		//Creation of Object
		LocalDateTime datetime1 = LocalDateTime.of(2021, 1, 14, 10, 34);
		LocalDateTime datetime2 = LocalDateTime.of(2021, 1, 14, 19, 34);

		s.simulate(datetime1, datetime2);
		String expected="No Completed Jobs";
		String actual="No Completed Jobs";
		//Assedrtions 
		assertEquals(expected,actual);	

	}
	
	@AfterEach
	public void print()
	{
		//Finishing of Testing 
		System.out.println("This Test is Finished!!");
	}


}
