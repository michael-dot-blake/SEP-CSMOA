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

class TestEight {

	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		System.out.println("This Test Has Started");
	}
	
	@Test
//	@SpiraTestCase(testCaseId=25574)
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
