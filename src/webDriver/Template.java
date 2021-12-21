package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Template {
	WebDriver driver;
	String rootFolder = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", rootFolder + "\\browserDriver\\chromedriver.exe");
		//driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01() {
		
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
	public void afterClass() {
		driver.quit();
	}

}
