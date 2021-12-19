package webDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_15_16_Custom_DropdownList_Part_I_II {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectLocation = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor ;
	
	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectLocation + "\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor ) driver;	
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	//@Test
		public void TC_00_tiemchungcovid19() {
			By parentTextboxBy = By.xpath("//ng-select[@bindvalue='provinceCode']//input");
			By itemsBy = By.xpath("//div[contains(@class,'ng-option ng-star-inserted')]/span");
			
			driver.get("https://tiemchungcovid19.gov.vn/portal/portal-report");
			
			selectItemInEditableDropdown(parentTextboxBy, itemsBy, "Tỉnh Bình Dương");
			waitForSeconds(2);
			Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Tỉnh/Thành phố']//following-sibling::div/span[@class='ng-value-label ng-star-inserted']")).getText(), "Tỉnh Bình Dương");
		}

	//@Test
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
	
	//@Test
	public void TC_02_ReactJS() {
		By parentBy = By.xpath("//div[@class='ui fluid selection dropdown']");
		By itemsBy = By.xpath("//div[@class='visible menu transition']/div");
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInCustomDropdown(parentBy,itemsBy,"Jenny Hess" );
		waitForSeconds(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Jenny Hess");
		
		selectItemInCustomDropdown(parentBy,itemsBy,"Stevie Feliciano" );
		waitForSeconds(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Stevie Feliciano");
		
		selectItemInCustomDropdown(parentBy,itemsBy,"Matt" );
		waitForSeconds(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Matt");
	
	}
	
	//@Test
	public void TC_03_VueJS() {
		By parentBy  = By.xpath("//li[@class='dropdown-toggle']");
		By itemsBy = By.xpath("//ul[@class='dropdown-menu']/li");		
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInCustomDropdown(parentBy,itemsBy,"First Option" );
		waitForSeconds(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText().trim(), "First Option");
		
		selectItemInCustomDropdown(parentBy,itemsBy,"Second Option" );
		waitForSeconds(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
		
		selectItemInCustomDropdown(parentBy,itemsBy,"Third Option" );
		waitForSeconds(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");
		
	}
	
	//@Test
	public void TC_04_Angular() {
		By parentBy = By.id("games");
		By itemsBy = By.xpath("//ul[@id='games_options']/li");
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		
		selectItemInCustomDropdown(parentBy,itemsBy,"Basketball" );
		waitForSeconds(2);
	//	Assert.assertEquals(driver.findElement(By.xpath("//select[@id='games_hidden']/option")).getText(), "Basketball");
		//Assert.assertTrue(driver.findElement(By.xpath("//select[@id='games_hidden']/option")).isDisplayed());
		Assert.assertEquals(getAngularSelectedValue("select[id='games_hidden'] option", "text"), "Basketball");
		
		selectItemInCustomDropdown(parentBy,itemsBy,"Football" );
		waitForSeconds(2);
		Assert.assertEquals(getAngularSelectedValue("select[id='games_hidden'] option", "text"), "Football");
		
		selectItemInCustomDropdown(parentBy,itemsBy,"Tennis" );
		waitForSeconds(2);
		Assert.assertEquals(getAngularSelectedValue("select[id='games_hidden'] option", "text"), "Tennis");
	}
	
	
	//@Test
	public void TC_05_Editable_DropdownList() {
		By parentTextboxBy = By.cssSelector("#default-place input");
		By itemsBy = By.xpath("//div[@id='default-place']/ul/li");
		//driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		
		selectItemInEditableDropdown(parentTextboxBy, itemsBy, "Citroen");
		waitForSeconds(2);
		//div[id='default-place'] li[class='es-visible']
		Assert.assertEquals(getAngularSelectedValue("div[id='default-place'] li[class='es-visible']","textContent"), "Citroen");
		
		selectItemInEditableDropdown(parentTextboxBy, itemsBy, "Jeep");
		waitForSeconds(2);
		Assert.assertEquals(getAngularSelectedValue("div[id='default-place'] li[class='es-visible']","textContent"), "Jeep");
		
		selectItemInEditableDropdown(parentTextboxBy, itemsBy, "Volkswagen");
		waitForSeconds(2);
		Assert.assertEquals(getAngularSelectedValue("div[id='default-place'] li[class='es-visible']","textContent"), "Volkswagen");
		
		//---------------Other page----------------//
		parentTextboxBy= By.xpath("//input[@class='search']");
		itemsBy= By.xpath("//span[@class='text']");
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		selectItemInCustomDropdown(parentTextboxBy,itemsBy,"Australia" );
		waitForSeconds(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Australia");
		
		selectItemInCustomDropdown(parentTextboxBy,itemsBy,"Benin" );
		waitForSeconds(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Benin");
	}
	
	public void selectItemInEditableDropdown(By parent, By allItems, String expectedValue) {
		//1. enter keyword
		driver.findElement(parent).clear();
		driver.findElement(parent).sendKeys(expectedValue);;
		
		//2.wait for all items present
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItems));
		waitForSeconds(1);
		
		//3. Store items into list
		List<WebElement> itemList = new ArrayList<WebElement>();
		itemList = driver.findElements(allItems);
		
		//4. compare text and select expected item
		for (WebElement webElement : itemList) {
			if(webElement.getText().trim().equals(expectedValue)) {
				webElement.click();
				break;
			}
		}
	}
	

	
	public String getAngularSelectedValue(String cssSelector, String queryCommand) {
		System.out.println("return document.querySelector(\"" + cssSelector + "\").textContent");
		return (String) jsExecutor.executeScript("return document.querySelector(\"" + cssSelector + "\")." + queryCommand);
	}
	
	public void selectItemInCustomDropdown(By parent, By allItems, String expectedValue) {
		//1. click on dropdown list
		driver.findElement(parent).click();
		
		//2.wait for all items present
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItems));
		//waitForSeconds(1);
		
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
	
	@Test
	public void TC_06_Multiple_DropdownList() {
		By parentBy = By.xpath("(//button[@class='ms-choice'])[1]");
		By itemsBy = By.xpath("(//div[@class='ms-drop bottom'])[1]/ul/li//span");
		String[] expectedItems = {"February" , "April", "December", "October"}  ;//, "October", "December"};
		//(//button[@class='ms-choice'])[1]
		
		
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		selectItemInMultiplemDropdown(parentBy, itemsBy, expectedItems);
		areItemSelected(expectedItems);
	}
	
	public void selectItemInMultiplemDropdown(By parent, By allItems, String[] expectedValue) {
		driver.findElement(parent).click();
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItems));
		
		List<WebElement> itemList = new ArrayList<WebElement>();
		itemList = driver.findElements(allItems);
		int i = 0;
		boolean stop = false;
		for (String value : expectedValue) {

			for (WebElement webElement : itemList) {

				if(webElement.getText().trim().equals(value)) {
					webElement.click();
					waitForSeconds(1);
					break;		
					
					//Co the them code xet da du so items can chon hay chua
					
				}
		
			}	
		}
		
	}
	
	
	public boolean areItemSelected(String[] months) {
		List<WebElement> selectedItemList = new ArrayList<WebElement>();
		selectedItemList = driver.findElements(By.xpath("(//div[@class='ms-drop bottom'])[1]/ul/li[@class='selected']//span"));
		
		int numberItemSelected = selectedItemList.size();
		//result text
		String resultText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		boolean status = true;
		
		if(numberItemSelected <= 3 && numberItemSelected >0) {
			for (String month : months) {
				if(!resultText.contains(month)) {
					status = false;
					return status;
				}
			}
			return status;
		} else if (numberItemSelected >= 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
			
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
		} else {
			return false;
		}
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
