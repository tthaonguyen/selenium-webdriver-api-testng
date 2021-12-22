package webDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_21_Handle_Popup {
	WebDriver driver;
	String rootFolder = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", rootFolder + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	
	//@Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		By loginPopup = By.cssSelector("div#modal-login-v1 div.modal-content");
		
		Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
		driver.findElement(By.cssSelector("button.login_.icon-before")).click();
		Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='input']//following-sibling::div[@class='row error-login-panel']")).getText(),"Tài khoản không tồn tại!");
		
	}
	//@Test
	public void TC_02_Random_Popup_In_DOM() {
		String keyword = "Selenium";
		
		driver.get("https://blog.testproject.io/");
		sleepInSeconds(10);
		
		if(driver.findElement(By.cssSelector("#mailch-bg .mailch-wrap")).isDisplayed()) {
			driver.findElement(By.cssSelector("#close-mailch")).click();
			sleepInSeconds(2);
		}
		
		driver.findElement(By.cssSelector("#search-2 .search-field")).sendKeys(keyword);
		driver.findElement(By.cssSelector("#search-2 .glass")).click();
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".page-title>span")).getText(),"\"" + keyword + "\":");
		List<WebElement> titles = new ArrayList<WebElement>();
		titles = driver.findElements(By.cssSelector("h3.post-title a"));
		
		for (WebElement  title : titles) {
			Assert.assertTrue(title.getText().contains(keyword));
		}
	}
	//@Test
	public void TC_03_Random_Popup_In_DOM() {
		driver.get("https://vnk.edu.vn/");
		sleepInSeconds(20);
		
		if(driver.findElement(By.xpath("//div[@class='tve-page-section-in tve_empty_dropzone']")).isDisplayed()) {
			driver.findElement(By.cssSelector("div.tve_ea_thrive_leads_form_close")).click();
			sleepInSeconds(2);
		}
		
		driver.findElement(By.xpath("//a[text()='Kết nối doanh nghiệp hỗ trợ việc làm']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://vnk.edu.vn/tuyen-dung-co-dien/");
		
	}
	//@Test
	public void TC_04_Random_Popup_Not_In_DOM() {
		driver.get("https://dehieu.vn/");
		sleepInSeconds(5);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> elements = new ArrayList<WebElement>();
		elements = driver.findElements(By.cssSelector("div.popup-content"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		if(elements.size() != 0) {
			driver.findElement(By.xpath("//button[@id='close-popup']")).click();
			sleepInSeconds(2);
		}
		
		driver.findElement(By.xpath("(//div[@class='course-info hachium'])[1]")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/khoa-hoc/khoa-hoc-thiet-ke-he-thong-me-toa-nha-");

	}
	@Test
	public void TC_05_Random_Popup_Not_In_DOM() {
		String keyword = "Macbook Pro";
		
		//Trang shopee chứa pop up trong shadow Dom sẽ không tìm trên dev tool cũng như cách finndElement bình thường.
		driver.get("https://shopee.vn/");
		sleepInSeconds(4);
		
		//fail nếu popup không xuất hiện
		WebElement popup = getElementInShadowDom("body shopee-banner-popup-stateful",".home-popup__content");	
		if(popup.isDisplayed()) {
			WebElement closeButton = getElementInShadowDom("body shopee-banner-popup-stateful",".shopee-popup__close-btn");
			closeButton.click();
			sleepInSeconds(4);
		}
		
		driver.findElement(By.cssSelector(".shopee-searchbar-input__input")).sendKeys(keyword);
		driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
		
		sleepInSeconds(8);
		Assert.assertEquals(driver.findElement(By.cssSelector("span.shopee-search-user-brief__header-text-highlight")).getText(), keyword.toLowerCase());
		
		//fail khi keyword không giống 100%
//		List<WebElement> elements = new ArrayList<WebElement>();
//		elements = driver.findElements(By.xpath("//div[@class='ZG__4J']/div"));
//		for (WebElement element : elements) {
//			Assert.assertTrue(element.getText().contains(keyword));
//		}
	}
	
	public WebElement getElementInShadowDom(String rootShadownCss, String elementCss) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		System.out.println("return document.querySelector(\"" + rootShadownCss +"\").shadowRoot.querySelector(\"" + elementCss + "\")");
		return (WebElement) jsExecutor.executeScript("return document.querySelector(\"" + rootShadownCss +"\").shadowRoot.querySelector(\"" + elementCss + "\")");
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
