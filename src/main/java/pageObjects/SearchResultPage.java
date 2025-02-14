package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {
	
	WebDriver driver;
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		
		//Initiating Page Factory to initialize all the objects everytime.
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='product-thumb']//h4/a")
	WebElement foundItem;
	
	@FindBy(xpath = "//button[contains(@data-original-title, 'Wish List') and contains(@onclick, '49')]")
	WebElement wishList;

	public WebElement getFoundItem() {
		return foundItem;
	}

	public WebElement getWishList() {
		return wishList;
	}
	
	
	

}
