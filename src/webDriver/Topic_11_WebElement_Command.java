package webDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_WebElement_Command {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	WebElement element;
	List<WebElement> elements;
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectLocation + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void f() {
		// Textbox, textArea/ Dropdown list (Editable)
		// Xóa dữ liệu trong fiels (trước khi sendkey)
		element.clear();

		// Nhập dữ liệu vào 1 textbox/ textarea
		element.sendKeys("");

		// Tìm 1 element (nhiều) vs locator là gì
			// Cách 1: Nếu như mà element này chỉ dùng 1 lần
		driver.findElement(By.id("search")).sendKeys("Samsung");
			// Cách 2: Nếu như mà element này thao tác nhiều lần -> Khai báo biến
		element = driver.findElement(By.id("search"));
		
		//Tìm nhiều elements
		elements = driver.findElements(By.xpath(""));
		
		//Lấy giá trị của 1 attribute thuộc 1 element (ví dụ lấy content của placeholder)
		WebElement searchTextbox = driver.findElement(By.xpath("//input[@id='search']"));
		searchTextbox.getAttribute("placeholder");
		searchTextbox.getAttribute("value");
		
		//Lấy giá trị của css. GUI Testing
		WebElement subscribeButton = driver.findElement(By.xpath("button[@title='Subscribe']"));
		subscribeButton.getCssValue("background");
		subscribeButton.getCssValue("font-family");
		subscribeButton.getCssValue("font-size");
		
		// Build framework: Chụp hình nhúng vào Report
		//HTML report: ReportNG, ExtentReport/ AllureReport
		// searchTextbox.getScreenshotAs(target);
		
		//getTagname
		WebElement subscribeTextbox = driver.findElement(By.cssSelector("#newsletter"));
		String subcribeTagname = subscribeTextbox.getTagName();
		//lam input cho cau lenh khac
		driver.findElement(By.xpath("//" + subcribeTagname + "[id = 'search']"));
		
		// Trả về text của 1 element: link/ button/ label/...
		//text nằm trong thẻ nhưng không nằm trong 1 attribute nào
		String searchText = searchTextbox.getText();
		
		//Element có hiển thị hay không: user can see and interact it.
		element.isDisplayed(); //=> true, false
		
		//element có thể tương tác được hay không
		element.isEnabled(); //=> true/false
		
		//1 element được chọn hay chưa
		//Dropdown (item) -> thư viện riêng => class riêng = select (selenium)
		boolean result = element.isSelected();
		Assert.assertEquals(result, false);
		
		//checkbox, image/link,...
		element.click();
		
		//only apply for element in Form
		//likely Enter key board (using rarely)
		element.submit();
	}

	@AfterClass
	public void afterClass() {
	}

}
