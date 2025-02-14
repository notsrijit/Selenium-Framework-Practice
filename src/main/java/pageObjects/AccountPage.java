package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	
	WebDriver driver;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		
		//Initiating Page Factory to initialize all the objects everytime.
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='row']//h2[text()='My Account']")
	WebElement accountBreadcrumb;
	
	@FindBy(xpath = "//a[contains(text(), 'Edit your')]")
	WebElement edityouraccInfo;

	public WebElement getAccountBreadcrumb() {
		return accountBreadcrumb;
	}

	public WebElement getEdityouraccInfo() {
		return edityouraccInfo;
	}
	

}
