package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		
		//Initiating Page Factory to initialize all the objects everytime.
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement dangerAlert;
	
	@FindBy(xpath = "//a[text()='Checkout']")
	WebElement checkoutButton;
	
	@FindBy(xpath = "//div[@class='table-responsive']//td[@class='text-left']/a")
	WebElement cartItem;

	public WebElement getCartItem() {
		return cartItem;
	}

	public WebElement getCheckoutButton() {
		return checkoutButton;
	}

	public WebElement getDangerAlert() {
		return dangerAlert;
	}
	
	

}
