package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.GSTFactory;
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

class TestSeven {

	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		System.out.println("This Test Has Started");
	}
	
	
	@Test
	@SpiraTestCase(testCaseId=25580)
	public void test7() 
	{
		
	GSTFactory gs=new GSTFactory();	
//	gs.readGSTsFromCSV("dfg");
	
	String s="Error Occured";
	String a="Error Occured";
	assertEquals(s,a);
	
	
	}
		
	@AfterEach
	public void print()
	{
		System.out.println("This Test is Finished!!");
	}

}
