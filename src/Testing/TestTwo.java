package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;

import controller.Simulation;

class TestTwo {

	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		System.out.println("This Test Has Started");
	}
	
	@Test
	@SpiraTestCase(testCaseId=25574)
	public void test2() throws NoSuchMethodException, SecurityException, IOException
	{
		
		
		double expected=10.04987562112089;
		double actual=s.calcDistance(12, 10, 13, 20);

		assertEquals(expected,actual);
		

	}
	
	
	
	@AfterEach
	public void print()
	{
		System.out.println("This Test is Finished!!");
	}

}
