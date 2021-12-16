package webDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_15_Handle_Custom_DropdownList_Part_I {
	WebDriver driver;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		By parentBy = By.id("number-button");
		By itemBy = By.xpath("//li[@class='ui-menu-item']");
		By selectedItemBy = By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']");
		
		selectItemInCustomDropdown(parentBy, itemBy, "19");
		Assert.assertEquals(driver.findElement(selectedItemBy).getText(), "19");
		
		selectItemInCustomDropdown(parentBy, itemBy, "3");
		Assert.assertEquals(driver.findElement(selectedItemBy).getText(), "3");
		
		selectItemInCustomDropdown(parentBy, itemBy, "10");
		Assert.assertEquals(driver.findElement(selectedItemBy).getText(), "10");
		
	}
	
	public void selectItemInCustomDropdown(By parent, By allItems, String expectedValue) {
		//1. click on dropdown list
		driver.findElement(parent).click();
		
		//2.wait for all items present
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItems));
		
		//3. Store items into list
		List<WebElement> itemList = new ArrayList<WebElement>();
		itemList = driver.findElements(allItems);
		
		//4. compare text and select expected item
		for (WebElement webElement : itemList) {
			if(webElement.getText().equals(expectedValue)) {
				webElement.click();
				break;
			}
		}
	}
	
	 
	public void waitForSeconds(long seconds) {
		try {
			Thread.sleep(seconds*1000);
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
