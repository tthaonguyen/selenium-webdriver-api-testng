package webDriver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_WebDriverWait_Part_III {
	WebDriver driver;
	String rootFolder = System.getProperty("user.dir");
	WebDriverWait explicitWait;

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
	public void TC_04_Explicit_Wait_Use_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("#start>button")).click();

		// explicitWait = new WebDriverWait(driver, 3);
		explicitWait = new WebDriverWait(driver, 5);

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

//@Test
	public void TC_05_Explicit_Wait_Use_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("#start>button")).click();

		explicitWait = new WebDriverWait(driver, 10);

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));

		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}

	//@Test
	public void TC_06_Explicit_Wait() {
		By selectedDatesField = By.cssSelector("#ctl00_ContentPlaceholder1_Label1");
		By date1 = By.xpath("//a[text()='3']/parent::td");
		By date2 = By.xpath("//a[text()='12']/parent::td");
		By loadingIcon = By.xpath("//div[starts-with(@style,'position: absolute;')]/div[@class='raDiv']");
		
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		explicitWait = new WebDriverWait(driver, 20);

		explicitWait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.cssSelector("#ctl00_ContentPlaceholder1_RadCalendar1_Top")));

		Assert.assertEquals(driver.findElement(selectedDatesField).getText(), "No Selected Dates to display.");
		
		String dateString1 = driver.findElement(date1).getAttribute("title");
		String dateString2 = driver.findElement(date2).getAttribute("title");
		
		driver.findElement(date1).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		
		Assert.assertTrue(driver.findElement(selectedDatesField).getText().contains("3"));
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='3']/parent::td[@class='rcSelected rcHover']")).isDisplayed());
		
		driver.findElement(date2).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		
		Assert.assertTrue(driver.findElement(selectedDatesField).getText().contains("12"));
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='12']/parent::td[@class='rcSelected rcHover']")).isDisplayed());
		
	}
	
	String picture1 = "lucky.jpg";
	String picture2 = "tree.jpg";

	String imgPath1 = rootFolder + "\\imageFiles\\" + picture1;
	String imgPath2 = rootFolder + "\\imageFiles\\" + picture2;
	
	@Test
	public void TC_07_Explicit_Wait() {
		driver.get("https://filebin.net/");
		explicitWait = new WebDriverWait(driver,60);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[starts-with(@class,'fileUpload')]")));
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(imgPath1 + "\n" + imgPath2);
		
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath("//div[@class='progress']"))));
		
		List<WebElement> nameFiles = new ArrayList<WebElement>();
		nameFiles = driver.findElements(By.xpath("//td//a[@class='link-primary link-custom']"));
		
		Assert.assertEquals(nameFiles.get(0).getText(), picture1);
		Assert.assertEquals(nameFiles.get(1).getText(), picture2);
		
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
