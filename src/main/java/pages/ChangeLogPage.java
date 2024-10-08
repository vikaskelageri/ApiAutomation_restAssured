package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class ChangeLogPage extends BasePage {
	
	@FindBy(xpath = "//span[text()='BOSS Updates']")
	WebElement changeLogHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return changeLogHeader.isDisplayed();
	}

}
