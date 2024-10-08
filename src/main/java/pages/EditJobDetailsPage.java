package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditJobDetailsPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Edit Job']")
	WebElement editJobDetailsHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return editJobDetailsHeader.isDisplayed();
	}

}
