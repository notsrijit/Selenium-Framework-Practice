package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import resources.Base;

public class DummyTest extends Base {
		
		//independent driver object so that test classes can run parallely.
		//Webdriver declared public so that listener from other package can access this
		public WebDriver driver;
		
		@BeforeMethod
		public void launchBrowser() throws IOException {
			
			driver = browserCall();
			driver.get(prop.getProperty("url"));
			
		}
		
		@Test
		public void pretendTest() throws IOException {
			
			System.out.println(driver.getTitle());
			Assert.assertTrue(false);
			
			
		}
		
		@AfterMethod
		public void browserClose() throws InterruptedException {
			
			Thread.sleep(5000);
			driver.quit();
			
		}
			

}
