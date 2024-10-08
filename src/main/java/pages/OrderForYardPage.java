package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderForYardPage extends BasePage{
	
	@FindBy(xpath = "//h1[text()='Order For Yard']")
	WebElement orderForYardHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return orderForYardHeader.isDisplayed();
	}

}
