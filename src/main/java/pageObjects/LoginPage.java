package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		
		//Initiating Page Factory to initialize all the objects everytime.
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name='email']")
	WebElement emailTextbox;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement passwordTextbox;
	
	@FindBy(xpath = "//input[contains(@class, 'btn-primary')]")
	WebElement loginButton;

	public WebElement getEmailTextbox() {
		return emailTextbox;
	}

	public WebElement getPasswordTextbox() {
		return passwordTextbox;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	

}
