package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.AzureMapsApi;
import controller.Simulation;
import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;


@SpiraTestConfiguration(
		// following are REQUIRED
		url = "https://rmit-university.spiraservice.net",
		login="MRHasan",

//		rssToken = "{93C84FF0-81B2-4FBD-B602-837F4AD1A7E3}", 
		projectId=715
)

class TestFive {

	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		System.out.println("This Test Has Started");
	}
	
	@Test
	@SpiraTestCase(testCaseId=25578)
	public void test5() throws NoSuchMethodException, SecurityException, IOException
	{
		LocalDateTime datetime1 = LocalDateTime.of(2021, 1, 14, 10, 34);
		String ex=AzureMapsApi.formatDateTime(datetime1);
		String Ac="2021-01-14T10:34:00";
		assertEquals(ex,Ac);
		
		



	}
	
	@AfterEach
	public void print()
	{
		System.out.println("This Test is Finished!!");
	}


}
