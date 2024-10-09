package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DriverEnterPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Trucking Time Sheets: ']")
	WebElement driverEnterHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return driverEnterHeader.isDisplayed();
	}

}
