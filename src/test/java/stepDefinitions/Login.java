package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import pageObjects.AccountPage;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class Login extends Base {

	WebDriver driver;
	LoginPage login;

	@Given("^Open any Browser$")
	public void open_any_browser() throws Throwable {
		
		driver = browserCall();
	}

	@And("^Navigate to the login page$")
	public void navigate_to_the_login_page() throws Throwable {
		
		driver.get(prop.getProperty("url"));
		
		LandingPage landing = new LandingPage(driver);
		landing.getMyAccountDropdown().click();
		landing.getLoginButton().click();

	}

	@When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" into the fields$")
	public void user_enters_username_and_password_into_the_fields(String username, String password) throws Throwable {
		
		login = new LoginPage(driver);
		login.getEmailTextbox().sendKeys(username);
		login.getPasswordTextbox().sendKeys(password);

	}

	@And("^User clicks on the Login Button$")
	public void user_clicks_on_the_login_button() throws Throwable {
		
		login.getLoginButton().click();

	}

	@Then("^verify user is able to successfully login$")
	public void verify_user_is_able_to_successfully_login() throws Throwable {
		
		AccountPage account = new AccountPage(driver);
		Assert.assertTrue(account.getEdityouraccInfo().isDisplayed());

	}
	
	//Closing browser after step definitions are executed
	@After
	public void closeBrowser() throws InterruptedException {
		
		Thread.sleep(3000);
		driver.quit();
		
	}
}
