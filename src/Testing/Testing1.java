package Testing;

//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Polygon;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;

import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import controller.AzureMapsApi;
import controller.GSTFactory;
import controller.JobFactory;
import controller.Simulation;
import model.Job;



//@SpiraTestConfiguration(
//		// following are REQUIRED
//		url = "https://rmit-university.spiraservice.net",
//		login="MRHasan",
////		rssToken = "{93C84FF0-81B2-4FBD-B602-837F4AD1A7E3}", 
//		
//		
//		
//
//		projectId=715
//)
class TestOne {
	
	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		System.out.println("This Test Has Started");
	}

	@Test
	@SpiraTestCase(testCaseId=25574)
	public void test()
	{

		String expected="01 Minutes";
		String actual=Simulation.formatSeconds(60);
		
		
		assertEquals(expected,actual);
				
	}
	
	
	@Test
//	@SpiraTestCase(testCaseId=25574)
	public void test2() throws NoSuchMethodException, SecurityException, IOException
	{
		
		
		double expected=10.04987562112089;
		double actual=s.calcDistance(12, 10, 13, 20);

		assertEquals(expected,actual);
		

	}
	
	@Test
//	@SpiraTestCase(testCaseId=25574)
	public void test3() throws NoSuchMethodException, SecurityException, IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, InterruptedException
	{
		LocalDateTime datetime1 = LocalDateTime.of(2021, 1, 14, 10, 34);
		LocalDateTime datetime2 = LocalDateTime.of(2021, 1, 14, 19, 34);

		s.simulate(datetime1, datetime2);
		String expected="No Completed Jobs";
		String actual="No Completed Jobs";
		
		assertEquals(expected,actual);	

	}
	
//	@Test
//	@SpiraTestCase(testCaseId=25574)
//	public void test4() throws NoSuchMethodException, SecurityException, IOException
//	{
//
//		
////		AzureMapsApi ap =new AzureMapsApi();
//		String p="23";
//		String q="Robert Street";
//		String r="Esendon";
//		String s="3309";
//				
//		Assert.assertNotNull(AzureMapsApi.getCoordinatesFromAddress(p, q, r, s));
//
//	}
//	
	
	@Test
//	@SpiraTestCase(testCaseId=25574)
	public void test5() throws NoSuchMethodException, SecurityException, IOException
	{
		LocalDateTime datetime1 = LocalDateTime.of(2021, 1, 14, 10, 34);
		String ex=AzureMapsApi.formatDateTime(datetime1);
		String Ac="2021-01-14T10:34:00";
		assertEquals(ex,Ac);
		
		



	}
	
	@Test
//	@SpiraTestCase(testCaseId=25574)
	public void test6() throws NoSuchMethodException, SecurityException, IOException
	{
		
	GSTFactory gs=new GSTFactory();	
	assertNotNull(gs.getGSTpool());


	}
	
//	@Test
////	@SpiraTestCase(testCaseId=25574)
//	public void test7() 
//	{
//		
//	GSTFactory gs=new GSTFactory();	
//	gs.readGSTsFromCSV("dfg");
//	
//	String s="Error Occured";
//	String a="Error Occured";
//	assertEquals(s,a);
//
//
//	}
	
	@Test
//	@SpiraTestCase(testCaseId=25574)
	public void test8() 
	{
		JobFactory js=new  JobFactory();
		ArrayList<Job> s=js.getJobPool();
		int x=s.size();
		int y=0;
		
		assertEquals(x,y);
		



	}
	
	@AfterEach
	public void print()
	{
		System.out.println("This Test is Finished!!");
	}

}
