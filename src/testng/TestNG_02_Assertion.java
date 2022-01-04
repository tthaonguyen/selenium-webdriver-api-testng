package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_02_Assertion {
	
	@Test
	public void TC_01() {
		String temp = "Automation testing";
		Assert.assertEquals(temp, "Automation testing" );
	}
	
	@Test
	public void TC_02() {
		String temp = "Automation testing";
		Assert.assertTrue(temp.contains("Automation testing"));
	}
	
	@Test
	public void TC_03() {
		String temp = "Automation testing";
		Assert.assertNotEquals(temp, "Automation" );
	}
	
	@Test
	public void TC_04() {
		String temp = "Automation testing";
		Assert.assertFalse(temp.equals("Automation"));
	}
	
	@Test
	public void TC_05() {
		String temp = null;
		Assert.assertNull(temp);
	}
	
  @Test
  public void TC_06() {
	  String temp = "automation";
	  Assert.assertNotNull(temp);
  }
  

}
