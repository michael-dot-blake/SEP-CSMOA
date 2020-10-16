package Testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.GSTFactory;
import controller.Simulation;
import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;

class TestSix {

	Simulation s=new Simulation();
	
	
	@BeforeEach
	public void initialize()
	{
		System.out.println("This Test Has Started");
	}
	
	@Test
//	@SpiraTestCase(testCaseId=25574)
	public void test6() throws NoSuchMethodException, SecurityException, IOException
	{
		
	GSTFactory gs=new GSTFactory();	
	assertNotNull(gs.getGSTpool());


	}
	
	@AfterEach
	public void print()
	{
		System.out.println("This Test is Finished!!");
	}

}
