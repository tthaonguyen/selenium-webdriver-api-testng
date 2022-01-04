package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNG_03_Group {

	//User group
	@Test(groups = "user")
	public void TC_01_create_new_user() {
		System.out.println("create new user");
	}

	@Test(groups = "user")
	public void TC_02_update_user() {
		System.out.println("update user");
	}

	@Test(groups = "user")
	public void TC_03_delete_user() {
		System.out.println("delete user");
	}

	//product group
	@Test(groups = "product")
	public void TC_04_create_new_product() {
		System.out.println("create new product");
	}

	@Test(groups = "product")
	public void TC_05_update_product() {
		System.out.println("update product");
	}

	@Test(groups = "product")
	public void TC_06_delete_product() {
		System.out.println("delete product");
	}

}
