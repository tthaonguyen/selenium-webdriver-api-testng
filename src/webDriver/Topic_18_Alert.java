package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_18_Alert {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Alert alert;
	String rootFolder = System.getProperty("user.dir");
	String authenFirefox = rootFolder + "\\AutoITScript\\authen_firefox.exe";
	String authenChrome = rootFolder + "\\AutoITScript\\authen_chrome.exe";
	String username ="admin";
	String password = "admin";

	@BeforeClass
	public void beforeClass() {
		 driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", rootFolder + "\\browserDriver\\chromedriver.exe");
		//driver = new ChromeDriver();
		 explicitWait = new WebDriverWait(driver,30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
		public void TC_05_Accept_Alert() {
			// step 1:
			driver.get("https://automationfc.github.io/basic-form/index.html");

			// step 2:
			driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
			
			explicitWait.until(ExpectedConditions.alertIsPresent());
			
			alert = driver.switchTo().alert();

			// step 3:
			Assert.assertEquals(alert.getText(), "I am a JS Alert");

			// step 4:
			alert.accept();
			Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
		}

		// @Test
		public void TC_06_Confirm_Alert() {
			// step 1:
			driver.get("https://automationfc.github.io/basic-form/index.html");

			// step 2:
			driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
			explicitWait.until(ExpectedConditions.alertIsPresent());
			alert = driver.switchTo().alert();

			// step 3:
			Assert.assertEquals(alert.getText(), "I am a JS Confirm");
			waitForSeconds(1);
			// step 4:
			alert.accept();
			Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Ok");

			// -----------------again-------------

			// step 2:
			driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
			explicitWait.until(ExpectedConditions.alertIsPresent());
			alert = driver.switchTo().alert();

			// step 3:
			Assert.assertEquals(alert.getText(), "I am a JS Confirm");
			waitForSeconds(1);
			// step 4:
			alert.dismiss();
			Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");

		}

		//@Test
		public void TC_07_Prompt_Alert() {
			String text = "automation testing";
			
			// step 1:
			driver.get("https://automationfc.github.io/basic-form/index.html");

			// step 2:
			driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
			explicitWait.until(ExpectedConditions.alertIsPresent());
			alert = driver.switchTo().alert();
			
			// step 3:
			waitForSeconds(1);
			alert.sendKeys(text);
			waitForSeconds(2);
			alert.accept();
			
			//You entered: auto
			//step 4

			Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: "+ text);
			
		}

		//@Test
		public void TC_08_Authentication_Alert_By_Pass() {
			
			driver.get("http://the-internet.herokuapp.com/");
			
			String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
			
			String authenUrl = getAuthenticationUrl(url, username, password);
			
			//go to page and assert
			driver.get(authenUrl);
			Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
			
		}
		
		@Test
		public void TC_09_Authentication_Alert_AutoIT() throws Exception {
			String authenUrl = "http://the-internet.herokuapp.com/basic_auth";
			
			if(driver.toString().contains("firefox")) {
				Runtime.getRuntime().exec(new String[] {authenFirefox, username,password});
			} else if(driver.toString().contains("chrome")) {
				Runtime.getRuntime().exec(new String[] {authenChrome, username, password});
			}
			
			//chay sau: do khi truy cap url thi alert se hien thi va cau lenh get se chua chay xong cho den khi user nhap thong tin login thanh cong
			driver.get(authenUrl);
			Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());		
			
		}
		
		public String getAuthenticationUrl(String url, String username, String password) {
			String[] urlValue = url.split("//");
			String authenUrl = urlValue[0] + username + ":" + password + "@" + urlValue[1];
			return authenUrl;
		}
		public void waitForSeconds(long seconds) {
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
