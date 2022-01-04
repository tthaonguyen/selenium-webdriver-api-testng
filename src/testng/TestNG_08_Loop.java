package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_08_Loop {
	WebDriver driver;
	String rootFolder = System.getProperty("user.dir");

	@Parameters({ "browser" })
	@BeforeClass
	public void before(@Optional("firefox") String browserName) {
			System.setProperty("webdriver.chrome.driver", rootFolder + "\\browserDriver\\chromedriver.exe");
			driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	
	 @Test(invocationCount=3)
	  public void TC_Create_A_New_Account() {
		 driver.get("http://live.techpanda.org/index.php/customer/account/create/");
		 	
		 String email = generateEmail();

		 //Enter information
		  driver.findElement(By.id("firstname")).sendKeys("Nguyen");
		  driver.findElement(By.id("lastname")).sendKeys("My");
		  driver.findElement(By.id("email_address")).sendKeys(email);
		  driver.findElement(By.id("password")).sendKeys("123456");
		  driver.findElement(By.id("confirmation")).sendKeys("123456");
		  
		  //Click Register
		  driver.findElement(By.cssSelector("button[title = 'Register']")).click();
		  
		  //Verify message
		  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		  
		  //Logout
		  driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		  driver.findElement(By.cssSelector("a[title='Log Out']")).click();
		  
		  sleepInSeconds(2);

	  }
	 
	 public String generateEmail() {
		 Random rgn = new Random();
		 return "gintamatoshi" + rgn.nextInt(99999) + "@domain.com";
	 }
	  

	public void sleepInSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void after() {
		driver.quit();
	}

}
