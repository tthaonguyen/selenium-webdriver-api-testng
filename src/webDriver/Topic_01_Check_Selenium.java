package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Check_Selenium {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_01_ValidateCurrentURl() {
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "http://demo.guru99.com/v4/");
	}
	 
	@Test
	public void TC_02_ValidatePageTitle() {
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, "Guru99 Bank Home Page");
	}
	
	@Test
	public void TC_03_LoginFormDisplay() {
		Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
