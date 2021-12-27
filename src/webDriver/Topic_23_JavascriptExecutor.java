package webDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_JavascriptExecutor {
	WebDriver driver;
	String rootFolder = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	
	String name, dobInput, dobOutput, address, city, state, pin, phone, customerID, email;

	By nameBy = By.name("name");
	By dateOfBirthBy = By.name("dob");
	By addressBy = By.name("addr");
	By cityBy = By.name("city");
	By stateBy = By.name("state");
	By pinBy = By.name("pinno");
	By phoneBy = By.name("telephoneno");
	By emailBy = By.name("emailid");
	By passwordBy = By.name("password");

	

	@BeforeClass
	public void beforeClass() {
		// driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", rootFolder + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		jsExecutor = (JavascriptExecutor) driver;
		
		// variables for input
		name = "Sloan Tampling";
		dobInput = "12/03/1999";
		dobOutput = "1999-12-03";
		address = "89 Acker Way";
		city = "Walnut Grove";
		state = "British Columbia";
		pin = "958330";
		phone = "8875085712";
		email = generateEmailAddress();

	}

	public void TC_01_JavaScript_Executor_JE() {
		navigateToUrlByJS("http://live.techpanda.org/");

		String domain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domain, "live.techpanda.org");

		String url = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(url, "http://live.techpanda.org/");

		clickToElementByJS(By.xpath("//a[text()='Mobile']"));

		clickToElementByJS(
				By.xpath("//a[@title='Samsung Galaxy']//following-sibling::div/div[@class='actions']/button"));

		Assert.assertTrue(AreExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		clickToElementByJS(By.xpath("//a[text()='Customer Service']"));
		sleepInSeconds(2);

		String title = (String) executeForBrowser("return document.title");
		Assert.assertEquals(title, "Customer Service");

		scrollToElementOnDown(By.xpath("//div[@class='block block-subscribe']//strong/span"));
		sleepInSeconds(1);

		sendkeyToElementByJS(By.xpath("//input[@id='newsletter']"), generateEmailAddress());

		clickToElementByJS(By.xpath("//button[@title='Subscribe']"));
		sleepInSeconds(2);
		Assert.assertTrue(AreExpectedTextInInnerText("Thank you for your subscription."));

		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSeconds(2);
		url = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(url, "http://demo.guru99.com/v4/");
	}

	public void TC_02_Verify_HTML5_Validation_Message() {
		By submitBtn = By.xpath("//input[@name='submit-btn']");
		By nameField = By.id("fname");
		By passField = By.id("pass");
		By emailField = By.id("em");
		By address = By.xpath("//select");
		// step 1
		driver.get("https://automationfc.github.io/html5/index.html");
		sleepInSeconds(1);
		// Select select = new Select(driver.findElement());

		// step 2
		driver.findElement(submitBtn).click();
		Assert.assertEquals(getElementValidationMessage(nameField), "Please fill out this field.");

		// step 3
		driver.findElement(nameField).sendKeys("Nguyen Tong");
		driver.findElement(submitBtn).click();
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(passField), "Please fill out this field.");
		// step 4
		driver.findElement(passField).sendKeys("Nguyen Tong");
		driver.findElement(submitBtn).click();
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(emailField), "Please fill out this field.");

		// step 5
		driver.findElement(emailField).clear();
		driver.findElement(emailField).sendKeys("123@com");
		driver.findElement(submitBtn).click();
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(emailField), "Please match the requested format.");

		// step 6
		driver.findElement(emailField).clear();
		driver.findElement(emailField).sendKeys("123@!#.com");
		driver.findElement(submitBtn).click();
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(emailField),
				"A part following '@' should not contain the symbol '!'.");

		// step 7
		driver.findElement(emailField).clear();
		driver.findElement(emailField).sendKeys("nguyentong@gmail.com");
		driver.findElement(submitBtn).click();
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(address), "Please select an item in the list.");

	}

	public void TC_03_Verify_HTML5_Validation_Message() {
		// page 1
		driver.get("https://login.ubuntu.com/");

		if (driver.findElement(By.xpath("//div[@class='p-modal__dialog']")).isDisplayed()) {
			driver.findElement(By.xpath("//button[@id='cookie-policy-button-accept']")).click();
		}

		driver.findElement(By.xpath("//form[@id='login-form']//input[@id='id_email']")).sendKeys("a");
		driver.findElement(By.xpath("//button[@data-qa-id='login_button']")).click();
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(By.xpath("//form[@id='login-form']//input[@id='id_email']")),
				"Please include an '@' in the email address. 'a' is missing an '@'.");

		// page 2
		driver.get("https://sieuthimaymocthietbi.com/account/register");
		driver.findElement(By.xpath("//button[@value='Đăng ký']")).click();
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(By.xpath("//input[@id='lastName']")),
				"Please fill out this field.");

		// Page 3
		driver.get("https://warranty.rode.com/");
		driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(By.xpath("//input[@id='firstname']")),
				"Please fill out this field.");

		// Page 4
		driver.get("https://www.pexels.com/vi-vn/join-contributor/");
		clickToElementByJS(By.xpath("//button[contains(text(),'Tạo tài khoản mới')]"));
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(By.id("user_first_name")), "Please fill out this field.");

	}

	
	public void TC_04_Remove_Attribute() {
		String userID = "mngr374197";
		String password = "rUrEmUr";

		

		driver.get("http://demo.guru99.com/v4/");

		// login
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		sleepInSeconds(2);

		// Creat_New_Customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameBy).sendKeys(name);
		driver.findElement(addressBy).sendKeys(address);
		driver.findElement(cityBy).sendKeys(city);
		driver.findElement(stateBy).sendKeys(state);
		driver.findElement(pinBy).sendKeys(pin);
		driver.findElement(phoneBy).sendKeys(phone);
		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(passwordBy).sendKeys(password);

		//Remove attribute
		removeAttributeInDOM (dateOfBirthBy, "type");
		driver.findElement(dateOfBirthBy).sendKeys(dobInput);
		sleepInSeconds(1);
		
		driver.findElement(By.name("sub")).click();
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");

	}
	@Test
	public void TC_05_Create_An_Account_JE() {
		navigateToUrlByJS("http://live.techpanda.org/");
		
		clickToElementByJS(By.xpath("//div[@id='header-account']//a[@title='My Account']"));
		
		clickToElementByJS(By.xpath("//a[@title='Create an Account']"));
		sleepInSeconds(1);
		
		sendkeyToElementByJS(By.id("firstname"), name);
		sendkeyToElementByJS(By.id("lastname"), name);
		sendkeyToElementByJS(By.id("email_address"), email);
		sendkeyToElementByJS(By.id("password"), "password");
		sendkeyToElementByJS(By.id("confirmation"), "password");
		clickToElementByJS(By.xpath("//button[@title='Register']"));
		sleepInSeconds(1);
		
		AreExpectedTextInInnerText("Thank you for registering with Main Website Store.");
		
		clickToElementByJS(By.xpath("//a[@title='Log Out']"));
		sleepInSeconds(2);
		
		Assert.assertTrue(isImageLoaded(By.xpath("//div[@class='page-title']//img")));
		
	}

	public String generateEmailAddress() {
		Random rng = new Random();
		return "nguyentong" + rng.nextInt(99999) + "@domain.com";
	}

	public void sleepInSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -------------------------------------common javascript
	// functions------------------------//

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean AreExpectedTextInInnerText(String expectedText) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0];");
		return textActual.equals(expectedText);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(By locator) {

		WebElement element = driver.findElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAtribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSeconds(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(By locator) {

		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(By locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));

	}

	public void scrollToElementOnDown(By locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(By locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(By locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(By locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(By locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
