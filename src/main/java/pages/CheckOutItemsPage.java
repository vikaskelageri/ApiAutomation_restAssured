package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutItemsPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Check Out']")
	WebElement checkOutItemsHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return checkOutItemsHeader.isDisplayed();
	}

}
