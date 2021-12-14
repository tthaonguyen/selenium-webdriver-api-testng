package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_WebElement_Element_Excercise {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");

	By emailTextBox = By.id("mail");
	By ageUnder18Radio = By.id("under_18");
	By educationTextBox = By.id("edu");
	By user5 = By.xpath("//h5[contains(text(),'User5')]");
	By jobRole1TextBox = By.id("job1");
	By developementCheckbox = By.id("development"); // javaLanguageCheckbox
	By slider1 = By.id("slider-1");
	By javaLanguageCheckbox = By.id("java");

	By passwordTextBox = By.xpath("//input[@name='user_pass']");
	By radioButtonIsDisable = By.id("radio-disabled");
	By biographyTextbox = By.id("bio");
	By job3Textbox = By.id("job3");
	By checkboxIsDisable = By.id("check-disbaled");
	By slider2 = By.id("slider-2");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectLocation + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC01_Element_isDisplayed() throws InterruptedException {
		System.out.println("TEST CASE 01");
		// Step 1
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Step 2
		if (isElementDisplayed(emailTextBox)) {
			driver.findElement(By.id("mail")).sendKeys("email@gmail.com");
		}
		Thread.sleep(1000);

		if (isElementDisplayed(ageUnder18Radio)) {
			driver.findElement(By.xpath("//label[@for='under_18']")).click();
		}
		Thread.sleep(1000);

		if (isElementDisplayed(educationTextBox)) {
			driver.findElement(By.id("edu")).sendKeys("Automation testing");
		}
		Thread.sleep(1000);

		// Step 3
		if (!isElementDisplayed(user5)) {
			driver.findElement(By.cssSelector("img[alt='User Avatar 05']")).click();
			Assert.assertTrue(isElementDisplayed(user5));
		}

	}

	@Test
	public void TC02_Element_isEnabled() throws InterruptedException {
		System.out.println("\n TEST CASE 02: ");
		// Step 1
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.navigate().refresh();

		Thread.sleep(1000);

		// Step 2
		Assert.assertTrue(isElementEnable(emailTextBox));
		Assert.assertTrue(isElementEnable(ageUnder18Radio));
		Assert.assertTrue(isElementEnable(educationTextBox));
		Assert.assertTrue(isElementEnable(jobRole1TextBox));
		Assert.assertTrue(isElementEnable(developementCheckbox));
		Assert.assertTrue(isElementEnable(slider1));

		// Step 03
		Assert.assertFalse(isElementEnable(passwordTextBox));
		Assert.assertFalse(isElementEnable(radioButtonIsDisable));
		Assert.assertFalse(isElementEnable(biographyTextbox));
		Assert.assertFalse(isElementEnable(job3Textbox));
		Assert.assertFalse(isElementEnable(checkboxIsDisable));
		Assert.assertFalse(isElementEnable(slider2));

	}

	@Test
	public void TC03_Element_isSelected() throws InterruptedException {
		System.out.println("\n TEST CASE 03: ");
		// Step 1
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Step 2
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(javaLanguageCheckbox).click();
		Thread.sleep(1000);

		// Step 03:
		Assert.assertTrue(isElementSelected(ageUnder18Radio));
		Assert.assertTrue(isElementSelected(javaLanguageCheckbox));

		// Clicked again
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(javaLanguageCheckbox).click();
		Thread.sleep(1000);

		// assert again
		Assert.assertTrue(isElementSelected(ageUnder18Radio));
		Assert.assertFalse(isElementSelected(javaLanguageCheckbox));
	}

	@Test
	public void TC04_Element_IntegrateAllAbove() throws InterruptedException {
		System.out.println("\n TEST CASE 04: ");
		// Step 1
		driver.get("https://login.mailchimp.com/signup/");

		// Step 2
		driver.findElement(By.id("email")).sendKeys("email@domain.com");
		driver.findElement(By.id("new_username")).sendKeys("automation testing");

		Assert.assertFalse(isElementEnable(By.id("create-account")));

		// Step 3
		// lowercase
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("auto");
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class = 'lowercase-char completed' and text() = 'One lowercase character']")));

		// number
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("auto1");
		Thread.sleep(1000);
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class = 'lowercase-char completed' and text() = 'One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class = 'number-char completed' and text() = 'One number']")));

		// upper case
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("auto1A");
		Thread.sleep(1000);
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class = 'lowercase-char completed' and text() = 'One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class = 'number-char completed' and text() = 'One number']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class = 'uppercase-char completed' and text() = 'One uppercase character']")));

		// special character
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("auto1A*");
		Thread.sleep(1000);
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class = 'lowercase-char completed' and text() = 'One lowercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class = 'number-char completed' and text() = 'One number']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class = 'uppercase-char completed' and text() = 'One uppercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class = 'special-char completed' and text() = 'One special character']")));

		// least 8 characters
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("auto1Auto");
		Thread.sleep(1000);
		Assert.assertFalse(isElementEnable(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class = 'number-char completed' and text() = 'One number']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class = 'uppercase-char completed' and text() = 'One uppercase character']")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//li[@class = '8-char completed' and text() = '8 characters minimum']")));

		// valid password
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("auto1Auto*");
		Thread.sleep(1000);
		Assert.assertTrue(isElementEnable(By.id("create-account")));
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='c-mediaBody--centered']/h4")));

	}

	public boolean isElementDisplayed(By element) {
		if (driver.findElement(element).isDisplayed()) {
			System.out.println("Element is displayed");
			return true;
		} else {
			System.out.println("Element is not displayed");
			return false;
		}
	}

	public boolean isElementEnable(By element) {
		if (driver.findElement(element).isEnabled()) {
			System.out.println("Element is enabled");
			return true;
		} else {
			System.out.println("Element is not enabled");
			return false;
		}
	}

	public boolean isElementSelected(By element) {
		if (driver.findElement(element).isSelected()) {
			System.out.println("Element is selected");
			return true;
		} else {
			System.out.println("Element is not selected");
			return false;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
