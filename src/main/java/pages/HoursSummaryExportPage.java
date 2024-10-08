package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HoursSummaryExportPage extends BasePage{
	
	@FindBy(xpath = "//div[text()='Summary for ']")
	WebElement hoursSummaryExportHeader;
	
	
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	
	@Override
	public boolean isHeaderDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(hoursSummaryExportHeader));
		return hoursSummaryExportHeader.isDisplayed();
	}

}
