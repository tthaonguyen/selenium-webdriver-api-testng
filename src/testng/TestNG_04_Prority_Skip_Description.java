package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_04_Prority_Skip_Description {

	// User group
	@Test(groups = "user", priority = 1, enabled = true, description = "create a new user with valid name")
	public void TC_01_create_new_user() {
		System.out.println("create new user");
	}

	@Test(groups = "user", priority = 3, enabled = false, description = "update name of user")
	public void TC_02_update_user() {
		System.out.println("update user");
	}

	@Test(groups = "user", priority = 2, enabled = true, description = "delete a user by id")
	public void TC_03_delete_user() {
		System.out.println("delete user");
	}

	

}
