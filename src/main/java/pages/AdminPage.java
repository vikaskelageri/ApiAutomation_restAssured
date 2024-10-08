package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AdminPage extends BasePage  {
	
	@FindBy(xpath = "//h1[normalize-space()='LABORADMIN']")
	WebElement laborAdminHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return laborAdminHeader.isDisplayed();
	}

}
