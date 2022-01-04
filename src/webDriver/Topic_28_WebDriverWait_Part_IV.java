package webDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_28_WebDriverWait_Part_IV {
	WebDriver driver;
	String rootFolder = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluent;

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.chrome.driver", rootFolder +
		// "\\browserDriver\\chromedriver.exe");
		// driver = new ChromeDriver();
		System.setProperty("webdriver.gecko.driver", rootFolder + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_08_Fluent_Wait() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		By countdown = By.xpath("//div[@id='javascript_countdown_time']");
		waitAndVerifyTextEnd(countdown, "00");
	}

	//@Test
	public void TC_09_Explicit_Wait_Use_Visible() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		By startButton = By.cssSelector("div#start>button");
		By resultText = By.cssSelector("div#finish>h4");
		
		waitAndClickButton(startButton);
		
		waitAndVerifyTextEnd(resultText,"Hello World!");
	}
	@Test
	public void TC_10_Explicit_Wait_Use_Visible() {
		
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
		driver.findElement(By.cssSelector("input#btnLogin")).click();
		
		explicitWait = new WebDriverWait(driver,15);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='table']//span")));
		Assert.assertEquals(driver.findElement(By.xpath("//table[@class='table']//span")).getText(), "3 month(s)");
		
		driver.findElement(By.cssSelector("a#menu_pim_viewPimModule")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#employee-information")));
		driver.findElement(By.cssSelector("input#empsearch_employee_name_empName")).sendKeys("Peter Mac");
		driver.findElement(By.cssSelector("input#searchBtn")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@class='ac_loading']")));
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Peter Mac']")).isDisplayed());
		
		
	}
	
	public void waitAndClickButton(By locator) {
		fluent = new FluentWait<WebDriver>(driver);
		
		fluent.withTimeout(15, TimeUnit.SECONDS)
		.pollingEvery(100, TimeUnit.MILLISECONDS)
		.ignoring(NoSuchElementException.class);
		
		WebElement button = fluent.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		
		button.click();
	}
	

	@SuppressWarnings("deprecation")
	public void waitAndVerifyTextEnd(By locator, String expectedText) {
		fluent = new FluentWait<WebDriver>(driver);

		// Tong thoi gian cho
		fluent.withTimeout(15, TimeUnit.SECONDS)
				// Tần số cứ 0.5 giây check 1 lần.
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				// Nếu gặp exception là No Such Element Exception là sẽ bỏ qua
				.ignoring(NoSuchElementException.class);

		fluent.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				String actualText = driver.findElement(locator).getText();
				return actualText.endsWith(expectedText);
			}
		});
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
