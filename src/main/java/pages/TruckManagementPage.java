package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TruckManagementPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Edit Truck Status']")
	WebElement truckManagementHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return truckManagementHeader.isDisplayed();
	}

}
