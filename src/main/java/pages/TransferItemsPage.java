package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TransferItemsPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Transfer Items']")
	WebElement transferItemsHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return transferItemsHeader.isDisplayed();
	}

}
