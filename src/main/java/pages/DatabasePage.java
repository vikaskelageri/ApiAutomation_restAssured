package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DatabasePage extends BasePage{
	
	@FindBy(xpath = "//h1[text()='PRODUCTS']")
	WebElement databaseHeader;
	
	@FindBy(xpath = "//button[normalize-space()='Add New']")
	WebElement addNewButton;
	
	@Override
	public boolean isHeaderDisplayed() {
		return databaseHeader.isDisplayed();
	}
	
	public boolean isAddNewButtonDisplayed() {
		return addNewButton.isDisplayed();
	}
	
	

}
