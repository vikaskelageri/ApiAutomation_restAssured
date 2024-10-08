package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ImportEmployeeReportCsvPage extends BasePage{
	
	@FindBy(xpath = "//h1[text()='Update Employee Records']")
	WebElement importEmployeeReportCsvHeader;
	
	@Override
	public boolean isHeaderDisplayed() {
		return importEmployeeReportCsvHeader.isDisplayed();
	}

}
