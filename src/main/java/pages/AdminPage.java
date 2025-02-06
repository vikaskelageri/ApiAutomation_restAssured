package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AdminPage extends BasePage  {
	
	@FindBy(xpath = "//h1[normalize-space()='LABOR ADMIN']")
	WebElement laborAdminHeader;
	
	@FindBy(xpath = "//button[normalize-space()='Add New Worker']")
	WebElement addNewWorkerButton;
	
	@Override
	public boolean isHeaderDisplayed() {
		return laborAdminHeader.isDisplayed();
	}
	
	public boolean isAddNewWorkerButtonDisplayed() {
		return addNewWorkerButton.isDisplayed();
	}

}
