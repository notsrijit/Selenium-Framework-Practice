package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;
import resources.ExcelReader;

public class LoginTest extends Base{
	
	//independent driver object so that test classes can run parallely.
	//Webdriver declared public so that listener from other package can access this
	public WebDriver driver;
	
	@BeforeMethod
	public void launchBrowser() throws IOException {
		
		driver = browserCall();
		driver.get(prop.getProperty("url"));
		
	}
	
	@Test(dataProvider = "getLoginData")
	public void login(String email, String password, String expected) throws IOException {
		
		LandingPage landing = new LandingPage(driver);
		landing.getMyAccountDropdown().click();
		landing.getLoginButton().click();
		
		LoginPage login = new LoginPage(driver);
		login.getEmailTextbox().sendKeys(email);
		login.getPasswordTextbox().sendKeys(password);
		login.getLoginButton().click();
		
		AccountPage account = new AccountPage(driver);
//		Assert.assertTrue(account.getAccountBreadcrumb().isDisplayed());
//		Or,
//		Handle like a champ because code will break if breadcrumb is not displayed.
		
		String actual=null;
		try {			
			if(account.getEdityouraccInfo().isDisplayed()) {
				actual="pass";
			}			
		} catch (Exception e) {
			actual="fail";
		}
		
		Assert.assertEquals(actual, expected);		
		
	}
	
	@AfterMethod
	public void browserClose() {
		
		driver.quit();
		
	}
	
	@DataProvider
	public Object[][] getLoginData() throws IOException {
		
		ExcelReader excel = new ExcelReader();
		//String 2D array data being stored in object 2D array
		Object [][] excelData = excel.excelRead();
		return excelData;
		
	}

}
