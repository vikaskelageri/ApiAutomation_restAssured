package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlaceOrderPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Order']")
	WebElement placeOrderHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return placeOrderHeader.isDisplayed();
	}

}
