package webDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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

public class Topic_22_Handle_Frame_IFrame_Window_Tab {
	WebDriver driver;
	String rootFolder = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", rootFolder + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");

		driver.switchTo().frame(driver.findElement(By.xpath("//frame")));
		driver.findElement(By.xpath("//input[@class='form-control text-muted']")).sendKeys("automationtest");
		driver.findElement(By.xpath("//a[@class='btn btn-primary login-btn']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='fldPasswordDispId']")).isDisplayed());
		driver.findElement(By.xpath("//div[@class='footer-btm']//a[text()='Terms and Conditions']")).click();
	}

	// @Test
	public void TC_02_IFrame() {
		By fbIframe = By.xpath("//div[@class='face-content']/iframe");
		By livechatIframe = By.cssSelector("iframe#cs_chat_iframe");
		String name = "John Wick";
		String phone = "0123456789";
		String message = "Java Bootcamp";
		String keyword = "Excel";
		Select serviceDropdownList;

		driver.get("https://kyna.vn/");

		Assert.assertTrue(driver.findElement(fbIframe).isDisplayed());

		// facebook iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='lfloat']/div[@class='_1drq']")).getText(),
				"167K likes");

		// default page
		driver.switchTo().defaultContent();
		driver.findElement(livechatIframe).click();

		// live chat iframe
		driver.switchTo().frame("cs_chat_iframe");

		driver.findElement(By.cssSelector("input.input_name")).sendKeys(name);
		driver.findElement(By.cssSelector("input.input_phone ")).sendKeys(phone);

		serviceDropdownList = new Select(driver.findElement(By.id("serviceSelect")));
		serviceDropdownList.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.cssSelector("textarea.input_textarea")).sendKeys(message);
		driver.findElement(By.cssSelector("input.submit")).click();
		sleepInSeconds(2);

		sleepInSeconds(1);
		Assert.assertEquals(driver
				.findElement(
						By.xpath("//div[@class='floater_inner_seriously']/label[@class='logged_in_name ng-binding']"))
				.getText(), name);
		Assert.assertEquals(driver
				.findElement(
						By.xpath("//div[@class='floater_inner_seriously']/label[@class='logged_in_phone ng-binding']"))
				.getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='message']")).getText(), message);

		driver.switchTo().defaultContent();
		sleepInSeconds(1);
		driver.findElement(By.id("live-search-bar")).sendKeys(keyword);
		driver.findElement(By.className("search-button")).click();
		sleepInSeconds(3);

		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='menu-heading']/h1")).getText(),
				"'" + keyword + "'");
		List<WebElement> courses = new ArrayList<WebElement>();
		courses = driver.findElements(By.xpath("//ul[@class='k-box-card-list']//div[@class='content']/h4"));

		for (WebElement course : courses) {
			Assert.assertTrue(course.getText().contains(keyword));
		}

	}

	// @Test
	public void TC_03_WindowTab() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		String parentID = driver.getWindowHandle();

		switchToWindownByID(parentID);
		Assert.assertEquals(driver.getTitle(), "Google");

		driver.switchTo().window(parentID);
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		switchToWindownByTitle("Facebook – log in or sign up");
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

		driver.switchTo().window(parentID);
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		switchToWindownByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		closeAllWindowsWithoutParent(parentID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_04_WindowTab() {
		driver.get("https://kyna.vn/");
		String parentID = driver.getWindowHandle();
		By fbIFrame = By.xpath("//div[@class='face-content']/iframe");

		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")).click();
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='youtube']")).click();

		// khong the clik
		// driver.findElement(By.xpath("(//a[@aria-label='k-footer-copyright']/img)[1]")).click();
		// driver.findElement(By.xpath("(//a[@aria-label='k-footer-copyright']/img)[2]")).click();
		clickToElementByJs(By.xpath("(//a[@aria-label='k-footer-copyright']/img)[1]"));
		clickToElementByJs(By.xpath("(//a[@aria-label='k-footer-copyright']/img)[2]"));

		driver.switchTo().frame(driver.findElement(fbIFrame));
	
		driver.findElement(By.xpath("//a[@title='Kyna.vn']")).click();
		driver.switchTo().defaultContent();
		sleepInSeconds(1);

		switchToWindownByTitle("Kyna.vn | Facebook");
		sleepInSeconds(1);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");

		switchToWindownByTitle("Kyna.vn - YouTube");
		sleepInSeconds(1);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");

		switchToWindownByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		sleepInSeconds(1);
		Assert.assertTrue(driver.getCurrentUrl().contains("http://online.gov.vn/Home/WebDetails/"));
		
		switchToWindownByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");
		sleepInSeconds(1);
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(driver.getCurrentUrl().contains("http://online.gov.vn/Home/WebDetails/"));

		

		switchToWindownByTitle("Kyna.vn | Facebook");
		sleepInSeconds(1);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn/");

		switchToWindownByID(parentID);
		sleepInSeconds(1);
		closeAllWindowsWithoutParent(parentID);
		sleepInSeconds(1);
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Học online cùng chuyên gia");

		sleepInSeconds(3);
	}
	
	//@Test
	public void TC_05_WindowTab() {
		driver.get("http://live.techpanda.org/");
		String product1 = "Sony Xperia";
		String product2 = "Samsung Galaxy";
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		driver.findElement(By.xpath("//a[text()='"+ product1 +"']//parent::h2//following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		sleepInSeconds(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product " + product1 +" has been added to comparison list.");
		
		
		driver.findElement(By.xpath("//a[text()='" + product2 +"']//parent::h2//following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		sleepInSeconds(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product " + product2 +" has been added to comparison list.");
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInSeconds(2);
		switchToWindownByID(parentID);
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		closeAllWindowsWithoutParent(parentID);
		driver.findElement(By.xpath("//div[@class='actions']/a")).click();
		sleepInSeconds(2);
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The comparison list was cleared.");
		
	}
	
	//@Test
	public void TC_06_WindowTab() {
		driver.get("https://dictionary.cambridge.org/vi/");
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[contains(@class,'cdo-login-button')]")).click();
		sleepInSeconds(2);
		switchToWindownByID(parentID);
		sleepInSeconds(2);
		driver.findElement(By.xpath("//a[text()='Forgot your password?']//ancestor::div[@class='gigya-layout-row']//following-sibling::div[@class='gigya-composite-control gigya-composite-control-submit']/input")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@class='gigya-input-text gigya-error']/following-sibling::span")).getText(),"This field is required");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@class='gigya-input-password gigya-error']/following-sibling::span")).getText(),"This field is required");
		
	}
	

	public void clickToElementByJs(By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click()", element);
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

	public void switchToWindownByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentWindowTitle = driver.getTitle();
			if (currentWindowTitle.equals(title)) {
				break;
			}
		}
	}
	
	public void switchToWindownByUrl(String url) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentWindowTitle = driver.getTitle();
			if (currentWindowTitle.equals(url)) {
				break;
			}
		}
	}

	public boolean closeAllWindowsWithoutParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if (driver.getWindowHandles().size() == 1) {
			return true;
		} else {
			return false;
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
