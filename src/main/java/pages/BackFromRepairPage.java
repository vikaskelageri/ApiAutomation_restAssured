package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BackFromRepairPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Return Items']")
	WebElement backFromRepairHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return backFromRepairHeader.isDisplayed();
	}

	

}
