package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendTextMessagePage extends BasePage{
	
	@FindBy(xpath = "//h4[text()='Create New Message']")
	WebElement sendTextMessageHeader;
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	
	@Override
	public boolean isHeaderDisplayed() {
		wait.until(ExpectedConditions.visibilityOf(sendTextMessageHeader));
		return sendTextMessageHeader.isDisplayed();
	}

}
