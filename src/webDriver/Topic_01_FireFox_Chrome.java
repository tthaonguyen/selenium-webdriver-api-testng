package webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

//Lesson: set up environment

public class Topic_01_FireFox_Chrome {
	WebDriver driver;

	@Test
	public void TC_01_RunOnChrome() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("http://demo.guru99.com/v4/");

		driver.quit();
	}

	@Test
	public void TC_01_RunOnFirefox() {
		driver = new FirefoxDriver();

		driver.get("http://demo.guru99.com/v4/");
		driver.close();
		
		driver.quit() ;
	}

}
