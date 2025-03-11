package pages;

import base.TestBase;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage extends TestBase {
    
	@FindBy(xpath = "//a[contains(@href, '/') and contains(text(), 'Home')]")
	WebElement homeButton;
	
	@FindBy(xpath = "//div[text()='Please select a job to continue']")
	WebElement jobSelectErrorNotification;


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

	 // Method to wait for an element to be visible
	    public static void waitForElementVisibility(WebElement element, int timeoutInSeconds) {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	            wait.until(ExpectedConditions.visibilityOf(element));
	            System.out.println("Element is visible.");
	        } catch (Exception e) {
	            System.out.println("Element not visible within timeout: " + e.getMessage());
	        }
	    }
	    
	 // Method to wait for an element to be clickable
	    public static void waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeoutInSeconds));
	            wait.until(ExpectedConditions.elementToBeClickable(element));
	            System.out.println("Element is clickable.");
	        } catch (Exception e) {
	            System.out.println("Element not clickable within timeout: " + e.getMessage());
	        }
	    }
	    
	    // Method to select an option from a dropdown
	    public static void selectDropdownByText(WebElement dropdownElement, String visibleText) {
	        try {
	            waitForElementVisibility(dropdownElement, 10);  // Ensure dropdown is visible before interacting
	            Select select = new Select(dropdownElement);
	            select.selectByVisibleText(visibleText);
	            System.out.println("Selected option: " + visibleText);
	        } catch (Exception e) {
	            System.out.println("Unable to select option '" + visibleText + "': " + e.getMessage());
	        }
	    }
	    
	    // Method to wait for an element to be invisible
	    protected void waitForElementToBeInvisible(WebElement element, int timeoutInSeconds) {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	            wait.until(ExpectedConditions.invisibilityOf(element));
	        } catch (Exception e) {
	            System.out.println("Element not invisible within timeout: " + e.getMessage());
	        }
	    }

    // You can add more common functionality here, like wait for elements, navigate, etc.
}
