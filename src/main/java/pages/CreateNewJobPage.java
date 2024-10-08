package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewJobPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Create Job']")
	WebElement createNewJobHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return createNewJobHeader.isDisplayed();
	}

}
