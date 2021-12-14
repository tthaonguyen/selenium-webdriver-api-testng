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

public class Topic_11_WebElement_Command_Exercise {
	WebDriver driver;
	String projectDir = System.getProperty("user.dir");
	By emailField = By.id("email");
	By passwordField = By.id("pass");
	Random rng;
	String emailAddress;
	String password;
	String firstName;
	String lastName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectDir + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		rng = new Random();

		emailAddress = "thaonguyentong" + rng.nextInt(999999) + "@automation.vn";
		password = "123456";
		firstName = "ThaoNguyen";
		lastName = "Tong";

		driver.get("http://live.techpanda.org/");

	}

	private void EnterInfoAccount(String email, String password) {
		driver.findElement(emailField).sendKeys(email);
		driver.findElement(passwordField).sendKeys(password);
	}

	@Test
	public void TC_01_Login_Empty_Email_And_Password() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		EnterInfoAccount("", "");
		driver.findElement(By.id("send2")).click();
		// Verify email
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
				"This is a required field.");

		// Verify password
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
				"This is a required field.");
	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		EnterInfoAccount("123456@123.123", "123456");
		driver.findElement(By.id("send2")).click();

		// Verify email
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_Login_Invalid_Password() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		EnterInfoAccount("nguyen@gmail.com", "123");
		driver.findElement(By.id("send2")).click();

		// Verify password
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_04_Login_Incorrect_Email_Or_Password() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		EnterInfoAccount("nguyen@gmail.com", "123456");
		driver.findElement(By.id("send2")).click();

		Assert.assertEquals(driver.findElement(By.xpath(".//li[@class='error-msg']//span")).getText(),
				"Invalid login or password.");
	}

	// nguyen@gmail.com
	// 123123

	@Test
	public void TC_05_Create_A_New_Account() {
		// Go to My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		// Click Create a new account
		driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();

		// Enter information
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);

		// Click Register
		driver.findElement(By.cssSelector("button[title = 'Register']")).click();

		// Verify message
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"Thank you for registering with Main Website Store.");

		// Verify User
		String strVerify = driver.findElement(By.xpath(
				"//h3[text()='Contact Information']//parent::div/following-sibling::div[@class='box-content']/p"))
				.getText();
		Assert.assertTrue(strVerify.contains(firstName));
		Assert.assertTrue(strVerify.contains(lastName));
		Assert.assertTrue(strVerify.contains(emailAddress));

		// Logout
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();

		// Verify: navigate to Home page, after logout success
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src $='logo.png']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
