package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_WebDriverWait_Part_I {
	WebDriver driver;
	String rootFolder = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.chrome.driver", rootFolder + "\\browserDriver\\chromedriver.exe");
		// driver = new ChromeDriver();
		System.setProperty("webdriver.gecko.driver", rootFolder + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();

		explicitWait = new WebDriverWait(driver,10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_Element_Visible_Or_Displayed() {
		//Visible/Displayed condition:
			//Element is display on UI (*)
			//Element is in DOM (*)
		
		//wait for my account in footer is displayed
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='footer']//a[@title='My Account']")));		
		//verify my account is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).isDisplayed());
		
		//click on login button
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		//wait for error message is displayed
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='advice-required-entry-email']")));
		//verify error message is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).isDisplayed());	
	}
	
	@Test
	public void TC_02_Invisible_Undisplayed_In_DOM() {
		//Visible/Displayed condition:
			//Element is not display on UI (*)
			//Element is in DOM
		
		driver.navigate().refresh();
		//wait for my account in header is not displayed
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[@title='My Account']")));
	}
	
	@Test
	public void TC_02_Invisible_Undisplayed_Not_In_DOM() {
		//Visible/Displayed condition:
			//Element is not display on UI (*)
			//Element is not in DOM
		
		driver.navigate().refresh();
		//wait for error message is not displayed
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='advice-required-entry-email']")));
	}
	
	@Test
	public void TC_04_Element_Presence() {
		//Visible/Displayed condition:
			//Element is not display on UI (*)
			//Element is in DOM
		
		driver.navigate().refresh();
		
		//wait for my account in footer is displayed (be in UI)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='footer']//a[@title='My Account']")));
		
		//wait for my account in header is not displayed (not be in UI)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='header-account']//a[@title='My Account']")));
			
	}
	
	@Test
	public void TC_05_Element_Staleness() {
		//Visible/Displayed condition:
			//Element is not display on UI (*)
			//Element is not in DOM (*)
		
		driver.navigate().refresh();
		
		//click on login button
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		WebElement element = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']"));
		
		driver.navigate().refresh();
		
		explicitWait.until(ExpectedConditions.stalenessOf(element));
		
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
