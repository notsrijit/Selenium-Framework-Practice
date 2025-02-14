package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage {
	
	WebDriver driver;
	
	public ItemPage(WebDriver driver) {
		this.driver = driver;
		
		//Initiating Page Factory to initialize all the objects everytime.
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='input-quantity']")
	WebElement itemQuantity;
	
	@FindBy(xpath = "//button[@id='button-cart']")
	WebElement cartButton;
	
	@FindBy(xpath = "//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")
	WebElement viewCartButton;
	
	@FindBy(xpath = "//strong[normalize-space()='View Cart']")
	WebElement goToCart;

	public WebElement getGoToCart() {
		return goToCart;
	}

	public WebElement getViewCartButton() {
		return viewCartButton;
	}

	public WebElement getItemQuantity() {
		return itemQuantity;
	}

	public WebElement getCartButton() {
		return cartButton;
	}
	
	

}
