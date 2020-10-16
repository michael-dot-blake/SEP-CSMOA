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

	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		System.out.println("This Test Has Started");
	}
	
	@Test
	@SpiraTestCase(testCaseId=25581)
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
