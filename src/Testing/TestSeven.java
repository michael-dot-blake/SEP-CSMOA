package Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.GSTFactory;
import controller.Simulation;

import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;

class TestSeven {

	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		System.out.println("This Test Has Started");
	}
	
		@Test
	//@SpiraTestCase(testCaseId=25574)
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
