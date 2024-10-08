package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ScheduleLaborPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Schedule Labor - ']")
	WebElement scheduleLaborHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return scheduleLaborHeader.isDisplayed();
	}

}
