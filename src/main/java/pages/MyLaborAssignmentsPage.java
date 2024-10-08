package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyLaborAssignmentsPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Manage Labor']")
	WebElement myLaborAssignmentsHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return myLaborAssignmentsHeader.isDisplayed();
	}

}
