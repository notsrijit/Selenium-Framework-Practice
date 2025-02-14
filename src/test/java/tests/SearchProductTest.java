package tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ItemPage;
import pageObjects.LandingPage;
import pageObjects.SearchResultPage;
import resources.Base;
import resources.ExcelReader;

public class SearchProductTest extends Base {
	
	//independent driver object so that test classes can run parallely.
	//Webdriver declared public so that listener from other package can access this
	public WebDriver driver;
	
	@BeforeMethod
	public void launchBrowser() throws IOException {
		
		driver = browserCall();
		driver.get(prop.getProperty("url"));
		
	}
	
	@Test(dataProvider = "getLoginData")
	public void searchProduct(String email, String password, String expected) throws IOException, InterruptedException {
		
		LandingPage landing = new LandingPage(driver);
		landing.getSearchBox().sendKeys(prop.getProperty("item"));
		landing.getSearchButton().click();
		
		SearchResultPage search = new SearchResultPage(driver);
		search.getFoundItem().click();
		
		ItemPage item = new ItemPage(driver);
		item.getItemQuantity().sendKeys(Keys.BACK_SPACE);
		item.getItemQuantity().sendKeys(prop.getProperty("count"));
		item.getCartButton().click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		item.getViewCartButton().click();
		item.getGoToCart().click();
		
		CartPage cart = new CartPage(driver);
		
//		try {
//			
//			if(cart.getDangerAlert().isDisplayed()) {
//				System.out.println("Items Unavailable");
//			}
//			
//		} catch (Exception e) {
//			
			String actualCartItem = cart.getCartItem().getText();
			String expectedCartItem = prop.getProperty("item");
			
			Assert.assertEquals(actualCartItem, expectedCartItem);
			
			cart.getCheckoutButton().click();
//		}
			
		CheckoutPage checkout = new CheckoutPage(driver);
		checkout.getEmailTextbox().sendKeys(email);
		checkout.getPasswordTextbox().sendKeys(password);
		Thread.sleep(5000);
		checkout.getLoginButton().click();
		
	}
	
	@AfterMethod
	public void browserClose() throws InterruptedException {
		
		Thread.sleep(5000);
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
