package Testing;


import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import controller.AzureMapsApi;
import controller.Simulation;
import org.junit.Assert;
import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;


@SpiraTestConfiguration(
		// following are REQUIRED
		url = "https://rmit-university.spiraservice.net",
		login="MRHasan",

//		rssToken = "{93C84FF0-81B2-4FBD-B602-837F4AD1A7E3}", 
		projectId=715
)

class TestFour {
	
	//Initialization of Test

	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		//Message to indicate the starting of test
		System.out.println("This Test Has Started");
	}
	
	@Test
	@SpiraTestCase(testCaseId=25577)
	public void test4() throws NoSuchMethodException, SecurityException, IOException
	{

		//Creation of Methods
		String p="23";
		String q="Robert Street";
		String r="Esendon";
		String s="3309";
				//Assertions 
		Assert.assertNotNull(AzureMapsApi.getCoordinatesFromAddress(p, q, r, s));

	}
	@AfterEach
	public void print()
	{
		//Finishing of Test
		System.out.println("This Test is Finished!!");
	}


}
