package pages;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
	
	@FindBy(xpath= "//p[text()='Welcome to Boss BAMR v4.0']")
	WebElement loginHeader;
	
	@FindBy(xpath= "//iframe[contains(@src, 'accounts.google.com/gsi/button')]")
	WebElement frame;
	
	@FindBy(xpath= "//div[@role='button']")
	WebElement signInButton;
	
	@FindBy(xpath= "//input[@type='email']")
	WebElement emailField;
	
	@FindBy(xpath= "//span[text()='Next']")
	WebElement nextButton;
	
	@FindBy(xpath= "//input[@type='password']")
	WebElement passwordField;
	
	@FindBy(xpath= "//a[@href='/changeLog']")
	WebElement updated;
	
	
	
	@Override
	public boolean isHeaderDisplayed() {
		return loginHeader.isDisplayed() ;
	}
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	public void switchToFrame() {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
	}
	
	public void clickSigninButton() {
		wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
	}
	
	public HomePage enterLoginCredentials(String email, String password) {
		// Get the main window handle before switching
        String mainWindowHandle = driver.getWindowHandle();

        // Wait for the pop-up window to appear and switch to it
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        
        while (iterator.hasNext()) {
            String childWindowHandle = iterator.next();
            if (!mainWindowHandle.equals(childWindowHandle)) {
                // Switch to the pop-up window
                driver.switchTo().window(childWindowHandle);
                emailField.sendKeys(email);
                wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
                for (int i = 0; i < 3; i++) {
                try {
					wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
//                	passwordField.sendKeys(password);
                	break;
				} catch (StaleElementReferenceException e) {
					// TODO Auto-generated catch block
					System.out.println("Stale Element. Retrying...");
				}
                }
                nextButton.click();
                
                // Switch back to the main window
                driver.switchTo().window(mainWindowHandle);
            }
        }
        return new HomePage();
	}


}