package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WeatherCancellationPage extends BasePage{
	
	@FindBy(xpath = "//h1[text()='Call Weather Cancellation']")
	WebElement weatherCancellationHeader;

	@Override
	public boolean isHeaderDisplayed() {
		return weatherCancellationHeader.isDisplayed();
	}

}
