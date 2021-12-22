package webDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_19_20_User_Interface_Part {
	WebDriver driver;
	Actions action;
	Alert alert;
	JavascriptExecutor jsExecutor;
	String rootFolder = System.getProperty("user.dir");
	
	String javascriptPath = rootFolder + "\\DrapAndDrop\\drag_and_drop_helper.js";
	String jqueryPath = rootFolder + "\\DrapAndDrop\\jquery_load_helper.js";
	
	@BeforeClass
	public void beforeClass() {

		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", rootFolder + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Hover_To_Element_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		By textbox = By.id("age");
	
		//hover
		action.moveToElement(driver.findElement(textbox)).perform();
		//assert: visible
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@class='ui-tooltip-content']")));
		//assert content of tooltip
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
		
	}
	
	//@Test
	public void TC_02_Hover_To_Element() {
		//KHONG DUNG DUOC TREN FIREFOX 47.0 
		//exception:  Permission denied to access property "handleEvent"
		
		driver.get("https://www.myntra.com/");
		By kidsElement = By.xpath("//a[@data-group='kids']");

		By subElement = By.xpath("//div[@data-group='kids']//a[text()='Home & Bath']") ;
		
		//hover KIDS
		action.moveToElement(driver.findElement(kidsElement)).perform();
		sleepInSeconds(1);
		//choose sub-element
		action.click(driver.findElement(subElement)).perform();
		sleepInSeconds(1);
		//assert
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title-title']")).getText(), "Kids Home Bath");
	}
	
	//@Test
	public void TC_03_Hover_To_Element() {
		driver.get("https://www.fahasa.com/flashsale?fhs_campaign=homepageicon");
		//hover
		action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'custom-menu-none-homepage')]//span[text()='Danh Mục Sản Phẩm']"))).perform();
		//hover
		action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'custom-menu-none-homepage')]//span[text()='Sách Trong Nước']"))).perform();
		
		//
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'custom-menu-none-homepage')]//a[text()='Kỹ Năng Sống']")).isDisplayed());
		
		driver.findElement(By.xpath("//div[contains(@class,'custom-menu-none-homepage')]//a[text()='Kỹ Năng Sống']")).click();
		sleepInSeconds(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//ol[@class='breadcrumb']/li[last()]")).getText(), "KỸ NĂNG SỐNG");
	
	}
	
	//@Test
	public void TC_04_Click_and_Hold_Element() {
		//go to page
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> numbers = new ArrayList<WebElement>();
		numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		//select a group of numbers
		action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(3)).release().perform();
		//get selected number
		List<WebElement> selectedNumbers = new ArrayList<WebElement>();
		selectedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		//assert
		Assert.assertEquals(selectedNumbers.size(), 4);
		
	}
	
	//@Test
	public void TC_05_Click_And_Select_Element() {
		int[] input = {1,3,8,5,11};
		
		//option:
		List<String> expectedSelectedNumbers = new ArrayList<String>();
		for (int i : input) {
			expectedSelectedNumbers.add(String.valueOf(i));
		}
	
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> numbers = new ArrayList<WebElement>();
		numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		//select numbers
		action.keyDown(Keys.CONTROL).perform();
		for(int i = 0; i < input.length; i++ ) {
			action.click(numbers.get(input[i]-1)).perform();
		}
		action.keyUp(Keys.CONTROL).perform();
		
		//assert
		List<WebElement> actualSelectedNumbers = new ArrayList<WebElement>();
		actualSelectedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		
		//quantities of selected numbers
		Assert.assertEquals(expectedSelectedNumbers.size(), actualSelectedNumbers.size());
		
		for (WebElement webElement : actualSelectedNumbers) {
			Assert.assertTrue(expectedSelectedNumbers.contains(webElement.getText()));
		}
		

	}
	
	//@Test
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "Hello Automation Guys!");
	}
	
	
	//@Test
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		//right click
		action.contextClick(driver.findElement(By.cssSelector("span[class='context-menu-one btn btn-neutral']"))).perform();
		
		//hover
		action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-quit"))).perform();
		sleepInSeconds(1);
		
		//assert quit menu visible
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='context-menu-list context-menu-root']")).isDisplayed());
		//assert quit element visible
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
		
		//click on quit
		action.click(driver.findElement(By.cssSelector(".context-menu-icon-quit"))).perform();
		
		//switch to alert
		driver.switchTo().alert().accept();
		
		//sleep for quit menu hide
		sleepInSeconds(1);
		//assert quit menu is invisible
		Assert.assertFalse(driver.findElement(By.xpath("//ul[@class='context-menu-list context-menu-root']")).isDisplayed());
	}
	 
	//@Test
	public void TC_08_Drag_and_Drop_HTML4() {
		
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement sourceCircle = driver.findElement(By.cssSelector("div[id='draggable']"));
		WebElement targetCircle = driver.findElement(By.cssSelector("div[id='droptarget']"));
		
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		sleepInSeconds(1);
		Assert.assertEquals(targetCircle.getText(), "You did great!");
	}
	
	@Test
	public void TC_09_Drag_and_Drop_HTML5_Js_JQuery() throws IOException {

		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		String sourceCss = "#column-a";
		String targetCss = "#column-b";
		
		//Đọc nội dung file đưa vào biến
		String javaContent  = getContentFile(javascriptPath);
		// Inject Jquery lib to site
		String jqueryContent = getContentFile(jqueryPath);
		sleepInSeconds(2);
		//Nhúng jQuery vào trình duyệt (Jquery đã có sẵn trong web app đang test nên đoạn ở dưới không cần)
		//jsExecutor.executeScript(jqueryContent);

		// A to B
		javaContent = javaContent + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		
		//nhúng javascript vào trình duyệt
		jsExecutor.executeScript(javaContent);
		sleepInSeconds(3);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));

//		// B to A
		jsExecutor.executeScript(javaContent);
		sleepInSeconds(3);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-b']/header[text()='B']"));
		
	}
	
	public boolean isElementDisplayed(String locator) {
		return driver.findElement(By.xpath(locator)).isDisplayed();
	}
	
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	
	//@Test
	
public void TC_09_Drag_and_Drop_HTML5_CoOrdinates() throws AWTException, InterruptedException {
		//Chay khong on dinh, luc pass het luc se bi fail
		
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
		
		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";
		
		dragAndDropHTML5ByXpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));
		
		dragAndDropHTML5ByXpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-b']/header[text()='B']"));
		
		dragAndDropHTML5ByXpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));
	}

	@SuppressWarnings("deprecation")
public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

	WebElement source = driver.findElement(By.xpath(sourceLocator));
	WebElement target = driver.findElement(By.xpath(targetLocator));

	// Setup robot
	Robot robot = new Robot();
	robot.setAutoDelay(500);

	// Get size of elements
	Dimension sourceSize = source.getSize();
	Dimension targetSize = target.getSize();

	// Get center distance
	int xCentreSource = sourceSize.width / 2;
	int yCentreSource = sourceSize.height / 2;
	int xCentreTarget = targetSize.width / 2;
	int yCentreTarget = targetSize.height / 2;

	Point sourceLocation = source.getLocation();
	Point targetLocation = target.getLocation();
	System.out.println(sourceLocation.toString());
	System.out.println(targetLocation.toString());

	// Make Mouse coordinate center of element
	sourceLocation.x += 20 + xCentreSource;
	sourceLocation.y += 110 + yCentreSource;
	targetLocation.x += 20 + xCentreTarget;
	targetLocation.y += 110 + yCentreTarget;

	System.out.println(sourceLocation.toString());
	System.out.println(targetLocation.toString());

	// Move mouse to drag from location
	robot.mouseMove(sourceLocation.x, sourceLocation.y);

	// Click and drag
	robot.mousePress(InputEvent.BUTTON1_MASK);
	robot.mousePress(InputEvent.BUTTON1_MASK);
	robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

	// Move to final position
	robot.mouseMove(targetLocation.x, targetLocation.y);

	// Drop
	robot.mouseRelease(InputEvent.BUTTON1_MASK);
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
