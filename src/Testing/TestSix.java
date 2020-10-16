package Testing;

import static org.junit.Assert.assertNotNull;
//import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

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

class TestSix {

	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		System.out.println("This Test Has Started");
	}
	
	@Test
	@SpiraTestCase(testCaseId=25579)
	public void test6() throws NoSuchMethodException, SecurityException, IOException
	{
		
//	GSTFactory gs=new GSTFactory();	
	assertNotNull(GSTFactory.getGSTpool());


	}
	
	@AfterEach
	public void print()
	{
		System.out.println("This Test is Finished!!");
	}

}
