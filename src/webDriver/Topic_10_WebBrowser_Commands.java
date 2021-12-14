package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic_10_WebBrowser_Commands {
	WebDriver driver;
  @Test
  public void f() {
	  
	  //Apply cho tab/window
	  driver.close(); //**
	  
	  //Apply cho browser
	  driver.quit(); //**
	  
	  //Mo ra mot web app (Url)
	  driver.get("facebook.com"); //**
	  
	  //Các hàm tương tác trên trình duyệt/element -> kiểu trả về của hàm là void
	  //Các hàm là lấy dữ liệu thì sẽ có kiểu trả về chứa dữ liệu đó (string/int/boolean)
	  
	  //Lấy ra cái url của page hiện tại
	  String loginPageUrl = driver.getCurrentUrl(); //**
	  
	  //Lấy ra html code của page hiện tại
	  driver.getPageSource();
	  
	  //Lấy title của page hiện tại
	  driver.getTitle(); //** Có thể dùng document.Title để lấy trên trình duyệt
	  
	  //Lấy GUID của tab/window đang có
	  driver.getWindowHandle(); //**
	  
	  //Lấy GUID của tất cả tab/window đang có
	  driver.getWindowHandles(); //**
	  
	  //Chờ cho element được load thành công trong vòng 15second
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); //**
	  
	  //Chờ page load thành công
	  driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
	  
	  //JS Excutor
	  driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
	  
	  //Full screen
	  driver.manage().window().maximize();
	  driver.manage().window().fullscreen();
	  
	  //Point arg0;
	  //Responsive
	  //driver.manage().window().setPosition(arg0);
	  
	  //back lại page trước
	  driver.navigate().back();
	  
	  //forward tới page trước
	  driver.navigate().forward();
	  
	  //refresh
	  driver.navigate().refresh();
	  
	  driver.navigate().to("facebook.com");
	  
	  //Alert/aFrame (Frame)/ Window (Tab)
	  driver.switchTo().alert(); //**
	  
	  driver.switchTo().frame(""); //**
	  
	  driver.switchTo().window(""); //**
	  
  }
}
