package webDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Handle_Button_Checkbox_Radio {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	String rootFolder = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		 driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", rootFolder + "\\browserDriver\\chromedriver.exe");
		//driver = new ChromeDriver();		
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Button() {
		By signinTab = By.cssSelector(".popup-login-tab-login");
		By signinButton = By.cssSelector(".fhs-btn-login");
		By emailTextbox = By.id("login_username");
		By passwordTextbox = By.id("login_password");

		// step 1:
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");

		// strep 2: navigate
		driver.findElement(signinTab).click();

		// step 3:verify that sign in button is disable
		Assert.assertFalse(isElementEnable(signinButton));

		// step 4:
		driver.findElement(emailTextbox).sendKeys("nguyen@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("123456");
		waitForSeconds(1);

		// step 5:
		Assert.assertTrue(isElementEnable(signinButton));

		// step 6:
		// Assert.assertEquals(driver.findElement(signinButton).getCssValue("background-color"),
		// "rgb(201, 33, 39)"); //rgba(201, 33, 39, 1)
		String colorCode = driver.findElement(signinButton).getCssValue("background-color");
		String hexacolor = Color.fromString(colorCode).asHex();
		Assert.assertEquals(hexacolor.toUpperCase(), "#C92127");

		// step 7:
		driver.navigate().refresh();
		driver.findElement(signinTab).click();

		// step 8: remove disable attribute
		removeAttributeByJS("disabled", signinButton);

		// step 9
		driver.findElement(signinButton).click();

		// step 10:
		waitForSeconds(1);
		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//input[@id='login_username']/parent::div/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//input[@id='login_password']/parent::div/following-sibling::div[@class='fhs-input-alert']"))
				.getText(), "Thông tin này không thể để trống");

	}

	// @Test
	public void TC_02_Default_Checkbox_Or_Radio() {
		// ----------------Checkbox----------------------
		// step 1
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		// step 2: Dual-zone air conditioning
		driver.findElement(By.id("eq5")).click();

		// Step 3: verify
		Assert.assertTrue(isElementSelected(By.id("eq5")));

		// Step 4:
		uncheckToCheckboxOrRadio(By.id("eq5"));
		waitForSeconds(1);
		Assert.assertFalse(isElementSelected(By.id("eq5")));

		// -----------------Radio------------------------------
		// step 5:
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

		// step 6: 2.0 Petrol, 147kW
		By radioElement = By.id("engine3");
		checkToCheckboxOrRadio(radioElement);
		waitForSeconds(1);
		// step 7:
		Assert.assertTrue(isElementSelected(radioElement));
	}

	// @Test
	public void TC_03_Custom_Checkbox_Or_Radio() {

		// step 1:
		driver.get("https://material.angular.io/components/radio/examples");
		By springRadio = By.xpath("//input[@value='Autumn']");

		// step 2:
		clickToElementByJS(springRadio);

		// step 3:
		Assert.assertTrue(isElementSelected(springRadio));

		// step 4:
		driver.get("https://material.angular.io/components/checkbox/examples");
		By checkedCheckbox = By.xpath("//input[@id='mat-checkbox-1-input']");
		By indeterminateCheckbox = By.xpath("//input[@id='mat-checkbox-2-input']");

		// step 5:
		clickToElementByJS(checkedCheckbox);
		clickToElementByJS(indeterminateCheckbox);
		waitForSeconds(1);

		// step 6:
		Assert.assertTrue(isElementSelected(checkedCheckbox));
		Assert.assertTrue(isElementSelected(indeterminateCheckbox));

		// step 7:
		clickToElementByJS(checkedCheckbox);
		clickToElementByJS(indeterminateCheckbox);
		waitForSeconds(1);

		// step 8:
		Assert.assertFalse(isElementSelected(checkedCheckbox));
		Assert.assertFalse(isElementSelected(indeterminateCheckbox));

	}

	// @Test
	public void TC_04_Custom_Checkbox_Or_Radio() {
		// step 1:
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		// step 2:
		By targetElement = By.xpath("//div[@id='i14']");
		Assert.assertEquals(driver.findElement(targetElement).getAttribute("aria-checked"), "false");
		driver.findElement(targetElement).click();
		waitForSeconds(1);
		Assert.assertEquals(driver.findElement(targetElement).getAttribute("aria-checked"), "true");
		waitForSeconds(2);
		isElementSelected(targetElement);
	}

	
	public void clickToElementByJS(By locator) {
		WebElement element = driver.findElement(locator);
		jsExecutor.executeScript("arguments[0].click()", element);

	}

	public void checkToCheckboxOrRadio(By location) {
		if (!isElementSelected(location)) {
			driver.findElement(location).click();
		}
	}

	public void uncheckToCheckboxOrRadio(By location) {
		if (isElementSelected(location)) {
			driver.findElement(location).click();
		}
	}

	public void removeAttributeByJS(String attribute, By location) {
		WebElement element = driver.findElement(location);
		System.out.println("arguments[0].removeAttribute('" + attribute + "')");
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attribute + "')", element);
	}

	public void waitForSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		driver.quit();
	}

}
