package webDriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_WebBrowser_Commands_Exercise {
	WebDriver driver;
	String projectDir = System.getProperty("user.dir");
	
 @BeforeClass
 public void beforeClass() {
	 System.setProperty("webdriver.chrome.driver", projectDir + "\\browserDriver\\chromedriver.exe");
	 driver = new ChromeDriver();
	 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 }
	
	
  @Test
  public void TC01_Verify_url() {
	  driver.get("http://live.techpanda.org/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title = 'My Account']")).click();
	  Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
	  driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	  
  }
  
  @Test
  public void TC02_Verify_Title() {
	  driver.get("http://live.techpanda.org/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title = 'My Account']")).click();
	  Assert.assertEquals(driver.getTitle(), "Customer Login");
	  driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
	  Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
  }
  
  @Test
  public void TC03_Navigate_Function() {
	  driver.get("http://live.techpanda.org/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title = 'My Account']")).click();
	  driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	  driver.navigate().back();
	  Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
	  driver.navigate().forward();
	  Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
  }
  
  @Test
  public void TC04_GetPageSourceCode() {
	  driver.get("http://live.techpanda.org/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title = 'My Account']")).click();
	  Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
	  driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
	  Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
}
