package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;

import controller.Simulation;




@SpiraTestConfiguration(
		// following are REQUIRED
		url = "https://rmit-university.spiraservice.net",
		login="MRHasan",
		
//		rssToken = "{93C84FF0-81B2-4FBD-B602-837F4AD1A7E3}", 
		projectId=715
)







class TestTwo {
	// Initialization of Test

	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		//Begining Mesage of Test 
		System.out.println("This Test Has Started");
	}
	
	@Test
	@SpiraTestCase(testCaseId=25575)
	public void test2() throws NoSuchMethodException, SecurityException, IOException
	{
		
		
		double expected=10.04987562112089;
		double actual=s.calcDistance(12, 10, 13, 20);
		//Assertions 

		assertEquals(expected,actual);
		

	}
	
	
	
	@AfterEach
	public void print()
	{
		//Finsing of Test 
		System.out.println("This Test is Finished!!");
	}

}
