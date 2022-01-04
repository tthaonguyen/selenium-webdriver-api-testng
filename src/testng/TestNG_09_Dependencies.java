package testng;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_09_Dependencies {
	
	@Test
	public void TC_01_create_new_user() {
		System.out.println("create new user");
	}

	@Test
	public void TC_02_update_user() {
		System.out.println("update user");
		Assert.assertTrue(false);
	}

	@Test(dependsOnMethods = "TC_01_create_new_user")
	public void TC_03_delete_user() {
		System.out.println("delete user");
	}
	
	@Test (dependsOnMethods = {"TC_01_create_new_user", "TC_02_update_user"})
	public void TC_04_view_user() {
		System.out.println("delete user");
	}




}
