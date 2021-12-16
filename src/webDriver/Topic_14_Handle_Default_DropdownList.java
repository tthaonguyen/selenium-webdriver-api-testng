package webDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Handle_Default_DropdownList {

	WebDriver driver;
	Select select;
	String projectLocation = System.getProperty("user.dir");
	String firstName, lastName, dobDay, dobMonth, dobYear, email, companyName, password;

	By maleRadioBnt = By.id("gender-male");
	By firstNameTextbox = By.id("FirstName");
	By lastNameTextbox = By.id("LastName");
	By dobDayDropdownList = By.name("DateOfBirthDay");
	By dobMonthDropdownList = By.name("DateOfBirthMonth");
	By dobYearDropdownList = By.name("DateOfBirthYear");
	By emailTextbox = By.id("Email");
	By companyTextbox = By.id("Company");

	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectLocation + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);

		firstName = "Julian";
		lastName = "Victory";
		dobDay = "4";
		dobMonth = "December";
		dobYear = "1992";
		email = generateEmailAddress();
		companyName = "infomation technology";
		password = "1234567";

	}

	public String generateEmailAddress() {
		Random rng = new Random();
		return "nguyentong" + rng.nextInt(99999) + "@domain.com";
	}

	/*
	 * @Test public void TC01_Handle_HTML_DropdownList() {
	 * driver.get("https://www.rode.com/wheretobuy"); select = new
	 * Select(driver.findElement(By.id("'where_country")));
	 * 
	 * //verify that the dropdown list is not multiple
	 * Assert.assertFalse(select.isMultiple());
	 * 
	 * //select Vietnam select.selectByVisibleText("Vietnam");
	 * 
	 * //verify selected item is expected item
	 * Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
	 * 
	 * //click on search button
	 * driver.findElement(By.id("'search_loc_submit")).click();
	 * 
	 * //verify result is 31 Assert.assertEquals(driver.findElement(By.xpath(
	 * "//div[@class='result_count']/span")).getText(), "31");
	 * 
	 * //show on console all distribute names List<WebElement> distributeNameList =
	 * new ArrayList<WebElement>(); distributeNameList =
	 * driver.findElements(By.xpath(
	 * "//div[@class='result_item']//div[@class='store_name']"));
	 * 
	 * System.out.println("All distribute names"); for(WebElement distributeName :
	 * distributeNameList) { System.out.println(distributeName.getText()); } }
	 */
	@Test
	public void TC02_Signin() throws InterruptedException {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.className("ico-register")).click();

		// put valid input into form
		driver.findElement(maleRadioBnt).click();
		driver.findElement(firstNameTextbox).sendKeys(firstName);
		driver.findElement(lastNameTextbox).sendKeys(lastName);

		// select value and verify number of item each dropdown list
		// day
		select = new Select(driver.findElement(dobDayDropdownList));
		select.selectByVisibleText(dobDay);
		Assert.assertEquals(select.getOptions().size(), 32);
		// month
		select = new Select(driver.findElement(dobMonthDropdownList));
		select.selectByVisibleText(dobMonth);
		Assert.assertEquals(select.getOptions().size(), 13);
		// year
		select = new Select(driver.findElement(dobYearDropdownList));
		select.selectByVisibleText(dobYear);
		Assert.assertEquals(select.getOptions().size(), 112);

		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(companyTextbox).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

		// click on register bnt
		driver.findElement(By.xpath("//button[@name='register-button']")).click();
		driver.findElement(By.className("ico-account")).click(); 	

		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
		
		// click on my account
		driver.findElement(By.className("ico-account")).click();
		
		// verify registered information
		Assert.assertTrue(driver.findElement(maleRadioBnt).isSelected());
		Assert.assertEquals(driver.findElement(firstNameTextbox).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameTextbox).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(companyTextbox).getAttribute("value"), companyName);
		// date of birth dropdown list
		// day
		select = new Select(driver.findElement(dobDayDropdownList));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dobDay);
		// month
		select = new Select(driver.findElement(dobMonthDropdownList));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dobMonth);
		// year
		select = new Select(driver.findElement(dobYearDropdownList));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dobYear);

	}

	@AfterClass
	public void afterClass() {
	}

}
