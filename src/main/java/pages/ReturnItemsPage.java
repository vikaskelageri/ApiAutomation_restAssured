package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReturnItemsPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Return Items']")
	WebElement returnItemsHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return returnItemsHeader.isDisplayed();
	}

}
