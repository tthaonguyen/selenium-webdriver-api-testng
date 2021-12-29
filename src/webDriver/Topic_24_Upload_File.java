package webDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Topic_24_Upload_File {
	WebDriver driver;
	String rootFolder = System.getProperty("user.dir");
	String picture1 = "lucky.jpg";
	String picture2 = "tree.jpg";
	String picture3 = "yotsuba.jpg";

	String imgPath1 = rootFolder + "\\imageFiles\\" + picture1;
	String imgPath2 = rootFolder + "\\imageFiles\\" + picture2;
	String imgPath3 = rootFolder + "\\imageFiles\\" + picture3;

	String firefoxUploadOneFile = rootFolder + "\\AutoITScript\\firefoxUploadOneTime.exe";
	String chromeUploadOneFile = rootFolder + "\\AutoITScript\\chromeUploadOneTime.exe";

	String firefoxUploadMultipleFile = rootFolder + "\\AutoITScript\\firefoxUploadMultiple.exe";
	String chromeUploadMultipleFile = rootFolder + "\\AutoITScript\\chromeUploadMultiple.exe";
	
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.chrome.driver", rootFolder +
		// "\\browserDriver\\chromedriver.exe");
		// driver = new ChromeDriver();
		System.setProperty("webdriver.gecko.driver", rootFolder + "\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		jsExecutor = (JavascriptExecutor) driver;
	}

	public void TC_01_Upload_File_By_SendKey_1filePerTime() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));

		uploadFile.sendKeys(imgPath1);
		sleepInSeconds(1);

		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(imgPath2);
		sleepInSeconds(1);

		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(imgPath3);
		sleepInSeconds(1);

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + picture1 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + picture2 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + picture3 + "']")).isDisplayed());

		List<WebElement> startButton = new ArrayList<WebElement>();
		startButton = driver.findElements(By.cssSelector("td .start"));

		for (WebElement button : startButton) {
			button.click();
			sleepInSeconds(1);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + picture1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + picture2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + picture3 + "']")).isDisplayed());

	}

	public void TC_01_Upload_File_By_SendKey_MultipleFilePerTime() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));

		uploadFile.sendKeys(imgPath1 + "\n" + imgPath2 + "\n" + imgPath3);
		sleepInSeconds(2);

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + picture1 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + picture2 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + picture3 + "']")).isDisplayed());

		List<WebElement> startButton = new ArrayList<WebElement>();
		startButton = driver.findElements(By.cssSelector("td .start"));

		for (WebElement button : startButton) {
			button.click();
			sleepInSeconds(1);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + picture1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + picture2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + picture3 + "']")).isDisplayed());
	}

	public void TC_02_Upload_File_By_AutoIT() throws Exception {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".btn-success")).click();

		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxUploadOneFile, imgPath1 });
		} else if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { chromeUploadOneFile, imgPath1 });
		}
		sleepInSeconds(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + picture1 + "']")).isDisplayed());

	}

	public void TC_02_Upload_Multiple_File_By_AutoIT() throws Exception {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".btn-success")).click();

		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxUploadMultipleFile, imgPath1, imgPath2, imgPath3 });
		} else if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { chromeUploadMultipleFile, imgPath1, imgPath2, imgPath3 });
		}
		sleepInSeconds(3);
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + picture1 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + picture2 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + picture3 + "']")).isDisplayed());

	}

	public void TC_03_Upload_File_By_Robot() throws AWTException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".btn-success")).click();

		// specify the file location with extension
		StringSelection select = new StringSelection(imgPath1);

		// copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		Robot robot = new Robot();
		sleepInSeconds(1);

		// press enter key
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// press Ctrl+V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		// release Ctrl+V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		// press enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		sleepInSeconds(3);
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + picture1 + "']")).isDisplayed());
	}

	@Test
	public void TC_04_Upload_File() {
		
		WebDriverWait explicit = new WebDriverWait(driver,15);
		driver.get("https://gofile.io/uploadFiles");
		sleepInSeconds(2);
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@id='uploadFile-Input']"));
		uploadFile.sendKeys(imgPath1 + "\n" + imgPath2);
		sleepInSeconds(1);
		explicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));
		
		clickToElementByJS(By.xpath("//a[@id='rowUploadSuccess-downloadPage']"));
		
		String parentID = driver.getWindowHandle();
		switchToWindownByID(parentID);
		
		List<WebElement> nameFiles = new ArrayList<WebElement>();
		nameFiles = driver.findElements(By.xpath("//div[@id='rowFolder-tableContent']/div//div[@class='col-md-5 text-center text-md-left']//span"));
		Assert.assertEquals(nameFiles.size(), 2);
		
		Assert.assertTrue(IsDownloadDisplay(picture1));
		Assert.assertTrue(IsDownloadDisplay(picture2));
		
	}
	public void clickToElementByJS(By locator) {

		jsExecutor.executeScript("arguments[0].click();", driver.findElement(locator));
	}
	
	public boolean IsDownloadDisplay(String nameFile) {
		return driver.findElement(By.xpath("//span[text()='" + nameFile +"']//ancestor::div[@class='col-md-5 text-center text-md-left']//following-sibling::div//span[text()='Download']")).isDisplayed();
	}
	
	public void switchToWindownByID(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentID)) {
				driver.switchTo().window(window);
				break;
			}
		}
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
