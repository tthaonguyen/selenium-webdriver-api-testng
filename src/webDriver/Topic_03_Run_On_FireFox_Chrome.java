package webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

//Lesson: set up environment

public class Topic_03_Run_On_FireFox_Chrome {
	WebDriver driver;
	String project_location = System.getProperty("user.dir");

	@Test
	public void TC_01_RunOnChrome() {
		//relative path
		//Sol 1:
		//System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		//Sol 2:
		System.setProperty("webdriver.chrome.driver", project_location + "\\browserDriver\\chromedriver.exe");

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
