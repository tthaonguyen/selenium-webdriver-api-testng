
package webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

//Topic 06: XPAth_Css part 1

public class Topic_02_XPath_Css_Part_I_Locator {
  WebDriver driver;
	
	@Test
  public void TC_01()  {
		//Open browser 
		driver = new FirefoxDriver();
		
		//Go to url
		driver.get("https://m.facebook.com/");
		
		//Locate Element methods		
		//ID
		driver.findElement(By.id("m_login_email")).sendKeys("nguyenyuki99@gmail.com");
		
		//Name
		driver.findElement(By.name("pass")).sendKeys("123456789");
		
		//Class
		 boolean display = driver.findElement(By.className("mobile-login-form")).isDisplayed();
		 System.out.println(display);
		 
		//Tag name
		 int count = driver.findElements(By.tagName("a")).size();
		 System.out.println(count);
		
		//Link Text
		driver.findElement(By.linkText("English (UK)")).click();
		 
		//Partial link text
		driver.findElement(By.partialLinkText("Tiếng")).click();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Css
		driver.findElement(By.cssSelector("#m_login_email")).sendKeys("auto@gmail.com");
		driver.findElement(By.cssSelector("#m_login_email")).clear();;
		
		//Xpath
		driver.findElement(By.xpath(".//*[@id='m_login_password']")).sendKeys("12345");
		driver.findElement(By.xpath(".//*[@id='m_login_password']")).clear();
		
		
		//Xpath text
		driver.findElement(By.xpath(".//a[text()='Tạo tài khoản mới']")).click();
		
		driver.quit();
  }
}
