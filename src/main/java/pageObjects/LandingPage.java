package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		
		//Initiating Page Factory to initialize all the objects everytime.
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@title='My Account']")
	WebElement myAccountDropdown;
	
	@FindBy(xpath = "//a[text()='Login']")
	WebElement loginButton;
	
	@FindBy(xpath = "//input[@name='search']")
	WebElement searchBox;
	
	@FindBy(xpath = "//button[contains(@class, 'btn-default')]")
	WebElement searchButton;

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getSearchBox() {
		return searchBox;
	}

	public WebElement getMyAccountDropdown() {
		return myAccountDropdown;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

}
