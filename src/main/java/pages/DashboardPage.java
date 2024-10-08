package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage{
	
	@FindBy(xpath = "//span[text()='Transfers']")
	WebElement dashboardHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return dashboardHeader.isDisplayed();
	}

}
