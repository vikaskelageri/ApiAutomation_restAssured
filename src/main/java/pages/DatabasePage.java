package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DatabasePage extends BasePage{
	
	@FindBy(xpath = "//h1[text()='PRODUCTS']")
	WebElement databaseHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return databaseHeader.isDisplayed();
	}

}
