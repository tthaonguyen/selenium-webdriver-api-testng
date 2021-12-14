package webDriver;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login {

	WebDriver driver;
	String project_location = System.getProperty("user.dir");
	Random rng;
	String emailAddress;
	String password;
	String firstName;
	String lastName;
	
	
	 @BeforeClass
	  public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", project_location + "\\browserDriver\\chromedriver.exe");	
		rng = new Random();
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/");
		
		emailAddress = "thaonguyen" + rng.nextInt(99999) + "@domain.com";
		password = "123456";
		firstName = "Nguyen";
		lastName = "Tong";
	}
	

	@Test
	public void TC01_CreateANewAccount() {
		driver.findElement(By.xpath("//div[@class=\"footer-container\"]//a[@title=\"My Account\"]")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		//click on sign up
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		//verify Dashboard
		String titleActual = driver.findElement(By.xpath("//div[@class=\"page-title\"]/h1")).getText();
		String titleExpected = "MY DASHBOARD";
		Assert.assertEquals(titleActual,titleExpected);
		//Verify welcome message
		String welcomeMsgActual = driver.findElement(By.xpath("//li[@class=\"success-msg\"]//span")).getText();
		String welcomeMsgExpected = "Thank you for registering with Main Website Store.";
		Assert.assertEquals(welcomeMsgActual, welcomeMsgExpected);
		//Verify greeting
		String greetingExpected = "Hello, " + firstName + " " + lastName + "!";
		String greetingActual = driver.findElement(By.xpath("//p[@class=\"hello\"]/strong")).getText();
		Assert.assertEquals(greetingActual, greetingExpected);
		//Verify user information
		String infoString = driver.findElement(By.xpath("//div[@class=\"box-content\"]/p")).getText();
		Assert.assertTrue(infoString.contains(firstName));
		Assert.assertTrue(infoString.contains(lastName));
		Assert.assertTrue(infoString.contains(emailAddress));
		
		//log out
		driver.findElement(By.xpath("//div[@class=\"account-cart-wrapper\"]//span[text()=\"Account\"]")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
		//verify home page
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src $= 'logo.png']")).isDisplayed());
		
	}
	

	 public void EnterEmailAndPassword(String email, String password) {
		 driver.findElement(By.id("email")).sendKeys(email);
		 driver.findElement(By.cssSelector("#pass")).sendKeys(password);
	 }
	 
	@Test
	public void TC02_LoginWithEmptyEmailAndPassword() {
		driver.findElement(By.xpath("//div[@class=\"footer-container\"]//a[@title=\"My Account\"]")).click();
		EnterEmailAndPassword("", "");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");		
	}
	
	@Test
	public void TC03_LoginWithInvalidEmail() {
		driver.findElement(By.xpath("//div[@class=\"footer-container\"]//a[@title=\"My Account\"]")).click();
		EnterEmailAndPassword("thaonguyen061221@domain.com", "123456");
		driver.findElement(By.id("send2")).click();
		String expectedString = "Invalid login or password.";
		String actualString = driver.findElement(By.xpath("//li[@class=\"error-msg\"]//span")).getText();
		Assert.assertEquals(expectedString, actualString);			
	}
	
	@Test
	public void TC04_LoginWithInvalidPassword() {
		driver.findElement(By.xpath("//div[@class=\"footer-container\"]//a[@title=\"My Account\"]")).click();
		EnterEmailAndPassword("nguyen@gmail.com","123");
		driver.findElement(By.id("send2")).click();
		String expectedString = "Please enter 6 or more characters without leading or trailing spaces.";
		String actualString = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(expectedString, actualString);			
	}

	@Test
	public void TC05_LoginWithIncorrectEmailOrPassword() {
		driver.findElement(By.xpath("//div[@class=\"footer-container\"]//a[@title=\"My Account\"]")).click();
		EnterEmailAndPassword("nguyen@gmail.com","123123123");
		driver.findElement(By.id("send2")).click();
		String expectedString = "Invalid login or password.";
		String actualString = driver.findElement(By.xpath("//li[@class=\"error-msg\"]//span")).getText();
		Assert.assertEquals(expectedString, actualString);			
	}
	
	@Test
	public void TC05_LoginWithValidEmailOrPassword() {
		driver.findElement(By.xpath("//div[@class=\"footer-container\"]//a[@title=\"My Account\"]")).click();
		EnterEmailAndPassword(emailAddress,password);
		driver.findElement(By.id("send2")).click();
		//verify Dashboard
		String titleActual = driver.findElement(By.xpath("//div[@class=\"page-title\"]/h1")).getText();
		String titleExpected = "MY DASHBOARD";
		Assert.assertEquals(titleActual,titleExpected);
		//Verify greeting
		String greetingExpected = "Hello, " + firstName + " " + lastName +"!";
		String greetingActual = driver.findElement(By.xpath("//p[@class=\"hello\"]/strong")).getText();
		Assert.assertEquals(greetingActual, greetingExpected);
		//Verify user information
		String infoString = driver.findElement(By.xpath("//div[@class=\"box-content\"]/p")).getText();
		Assert.assertTrue(infoString.contains(firstName));
		Assert.assertTrue(infoString.contains(lastName));
		Assert.assertTrue(infoString.contains(emailAddress));
	}

	  @AfterClass
	  public void afterClass() {
		  driver.quit();
	  }


}
