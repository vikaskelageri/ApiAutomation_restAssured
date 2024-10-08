package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class UpdateTimesheetsPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Enter Timesheets:']")
	WebElement updateTimesheetsHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return updateTimesheetsHeader.isDisplayed();
	}

}
