package util;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import base.TestBase;

public class Utilities extends TestBase {

    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 20;
    
    
 // Method to capture a screenshot and return the Base64 string
    public static String captureScreenshotAsBase64() {
        // Capture screenshot as Base64 encoded string
        String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        
        // Print message for confirmation
        System.out.println("Screenshot captured as Base64.");
        
        // Return the Base64 string
        return base64Screenshot;
    }
}