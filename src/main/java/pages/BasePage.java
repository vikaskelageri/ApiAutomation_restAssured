package pages;

import base.TestBase;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage extends TestBase {
    
	@FindBy(xpath = "//a[contains(@href, '/') and contains(text(), 'Home')]")
	WebElement homeButton;


    public BasePage() {
        PageFactory.initElements(driver, this);
    }
    
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Common method to check if a header is displayed
    public abstract boolean isHeaderDisplayed();
    
    public HomePage clickOnHome() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(homeButton)).click();
        } catch (ElementClickInterceptedException e) {
        	JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", homeButton);
        }
        return new HomePage();
    }


    // You can add more common functionality here, like wait for elements, navigate, etc.
}
