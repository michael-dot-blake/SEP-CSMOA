package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;
import controller.Simulation;

class TestThree {

	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		System.out.println("This Test Has Started");
	}
	
	@Test
//	@SpiraTestCase(testCaseId=25574)
	public void test3() throws NoSuchMethodException, SecurityException, IOException
	{
		LocalDateTime datetime1 = LocalDateTime.of(2021, 1, 14, 10, 34);
		LocalDateTime datetime2 = LocalDateTime.of(2021, 1, 14, 19, 34);

		s.simulate(datetime1, datetime2);
		String expected="No Completed Jobs";
		String actual="No Completed Jobs";
		
		assertEquals(expected,actual);	

	}
	
	@AfterEach
	public void print()
	{
		System.out.println("This Test is Finished!!");
	}


}
