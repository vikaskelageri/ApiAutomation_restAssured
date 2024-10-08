package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CancellationHolidayPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Call Holiday']")
	WebElement cancellationHolidayHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return cancellationHolidayHeader.isDisplayed();
	}

}
