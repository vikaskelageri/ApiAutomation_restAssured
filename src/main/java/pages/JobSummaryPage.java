package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobSummaryPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Jobs Summary']")
	WebElement jobSummaryHeader;
	
	@FindBy(xpath = "//th[normalize-space()='Active?']")
	WebElement activeColumn;
	
	@Override
	public boolean isHeaderDisplayed() {
		return jobSummaryHeader.isDisplayed();
	}
	
	public boolean isActiveColumnDisplayed() {
		return activeColumn.isDisplayed();
	}
	

}
