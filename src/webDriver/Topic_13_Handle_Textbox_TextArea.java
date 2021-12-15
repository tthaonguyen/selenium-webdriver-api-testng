package webDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_13_Handle_Textbox_TextArea {
	// Firefox 47.0.2
	WebDriver driver;
	String url;
	
	String email, userID, password;
	String name, dobInput, dobOutput, address, city, state, pin, phone, customerID;
	
	String addressEdit, cityEdit, stateEdit, pinEdit, phoneEdit, emailEdit;
	
	By nameBy = By.name("name");
	By dateOfBirthBy = By.name("dob");
	By addressBy = By.name("addr");
	By cityBy = By.name("city");
	By stateBy = By.name("state");
	By pinBy = By.name("pinno");
	By phoneBy = By.name("telephoneno");
	By emailBy = By.name("emailid");
	By passwordBy = By.name("password");
	
	
	
	
	
	public String generateEmailAddress() {
		Random rng = new Random();
		return "nguyentong" + rng.nextInt(99999) + "@domain.com";
	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
		url = "http://demo.guru99.com/v3/";		
		driver.manage().window().maximize();
		driver.get(url);
		
		//variables for input
		name = "Sloan Tampling";
		dobInput= "12/03/1999";
		dobOutput = "1999-12-03";
		address= "89 Acker Way";
		city= "Walnut Grove";
		state= "British Columbia";
		pin= "958330";
		phone= "8875085712";
		email = generateEmailAddress();
		
		//variables to edit
		addressEdit= "Lillian";
		cityEdit = "Huntington";
		stateEdit= "West Virginia";
		pinEdit= "908765";	
		phoneEdit= "0918723546"; 
		emailEdit = generateEmailAddress();
		
	}
	//mngr374197
	//rUrEmUr

	@Test
	public void TC01_Signup() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();	
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
	}

	@Test
	public void TC02_Login() throws InterruptedException {
		driver.get(url);
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),"Welcome To Manager's Page of Guru99 Bank");
		
	}

	@Test
	public void TC03_Creat_New_Customer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(nameBy).sendKeys(name);
		driver.findElement(dateOfBirthBy).sendKeys(dobInput);
		driver.findElement(addressBy).sendKeys(address);
		driver.findElement(cityBy).sendKeys(city);
		driver.findElement(stateBy).sendKeys(state);
		driver.findElement(pinBy).sendKeys(pin);
		driver.findElement(phoneBy).sendKeys(phone);
		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(passwordBy).sendKeys(password);
		
		driver.findElement(By.name("sub")).click();
	
		//verify value after create successfully
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(), "Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dobOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
	
		
	}

	@Test
	public void TC04_Edit_Customer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		//verify value on Edit Customer page matching with value at New Customer
		Assert.assertEquals(driver.findElement(nameBy).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(dateOfBirthBy).getAttribute("value"), dobOutput);
		Assert.assertEquals(driver.findElement(addressBy).getAttribute("value"), address);
		Assert.assertEquals(driver.findElement(cityBy).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateBy).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinBy).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneBy).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), email);
		
		//Edit at Edit Customer
		driver.findElement(addressBy).clear();
		driver.findElement(addressBy).sendKeys(addressEdit);
		driver.findElement(cityBy).clear();
		driver.findElement(cityBy).sendKeys(cityEdit);
		driver.findElement(stateBy).clear();
		driver.findElement(stateBy).sendKeys(stateEdit);
		driver.findElement(pinBy).clear();
		driver.findElement(pinBy).sendKeys(pinEdit);
		driver.findElement(phoneBy).clear();
		driver.findElement(phoneBy).sendKeys(phoneEdit);
		driver.findElement(emailBy).clear();
		driver.findElement(emailBy).sendKeys(emailEdit);
		driver.findElement(By.name("sub")).click();
		
		Thread.sleep(2000);
		
		//verify values after edit
		 Alert alert = driver.switchTo().alert();
         alert.accept();
		
         driver.findElement(By.name("cusid")).sendKeys(customerID);
 		 driver.findElement(By.name("AccSubmit")).click();
		
		Assert.assertEquals(driver.findElement(addressBy).getAttribute("value"), addressEdit);
		Assert.assertEquals(driver.findElement(cityBy).getAttribute("value"), cityEdit);
		Assert.assertEquals(driver.findElement(stateBy).getAttribute("value"), stateEdit);
		Assert.assertEquals(driver.findElement(pinBy).getAttribute("value"), pinEdit);
		Assert.assertEquals(driver.findElement(phoneBy).getAttribute("value"), phoneEdit);
		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), emailEdit);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
