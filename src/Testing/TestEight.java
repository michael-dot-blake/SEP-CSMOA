package Testing;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controller.JobFactory;
import controller.Simulation;
import model.Job;
import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;



@SpiraTestConfiguration(
		// following are REQUIRED
		url = "https://rmit-university.spiraservice.net",
		login="MRHasan",

//		rssToken = "{93C84FF0-81B2-4FBD-B602-837F4AD1A7E3}", 
		projectId=715
)

class TestEight {
	
	//Initialization for Test

	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		//System output when testing starts
		System.out.println("This Test Has Started");
	}
	
	@Test
	@SpiraTestCase(testCaseId=25581)
	public void test8() 
	{
		// Creation of Object
		JobFactory js=new  JobFactory();
		ArrayList<Job> s=js.getJobPool();
		int x=s.size();
		int y=0;
		//Assertions
		assertEquals(x,y);
		



	}
	
	@AfterEach
	public void print()
	{
		//Finishing of Test
		System.out.println("This Test is Finished!!");
	}

}
