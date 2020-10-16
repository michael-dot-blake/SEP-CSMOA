package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.AzureMapsApi;
import controller.Simulation;
import org.junit.Assert;
import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;

class TestFour {

	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		System.out.println("This Test Has Started");
	}
	
	@Test
	@SpiraTestCase(testCaseId=25574)
	public void test4() throws NoSuchMethodException, SecurityException, IOException
	{

		
//		AzureMapsApi ap =new AzureMapsApi();
		String p="23";
		String q="Robert Street";
		String r="Esendon";
		String s="3309";
				
		Assert.assertNotNull(AzureMapsApi.getCoordinatesFromAddress(p, q, r, s));

	}
	@AfterEach
	public void print()
	{
		System.out.println("This Test is Finished!!");
	}


}
