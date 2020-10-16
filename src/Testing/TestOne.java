package Testing;


import static org.junit.jupiter.api.Assertions.assertEquals;
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






class TestOne {
	
	//Initialization of Test
	
	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		// Starting Message for Begining of Test
		System.out.println("This Test Has Started");
	}

	@Test
	@SpiraTestCase(testCaseId=25574)
	public void test1()
	{

		String expected="01 Minutes";
		String actual=Simulation.formatSeconds(60);
		
		//Assertions 
		assertEquals(expected,actual);
				
	}
	
	@AfterEach
	public void print()
	{
		//Finishing of Test
		System.out.println("This Test is Finished!!");
	}

}
