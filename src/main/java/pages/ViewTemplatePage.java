package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewTemplatePage extends BasePage{
	
	@FindBy(xpath = "//p[text()='Pre-selected Products']")
	WebElement viewTemplateHeader;

	@Override
	public boolean isHeaderDisplayed() {
		return viewTemplateHeader.isDisplayed();
	}

}
