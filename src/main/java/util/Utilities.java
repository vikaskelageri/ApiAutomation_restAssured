package util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import base.TestBase;

public class Utilities extends TestBase {

    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 10;

//    // Method to capture a screenshot and return the file path or URL
//    public static String captureScreenshot(String fileName) {
//        // Capture screenshot as a file
//        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//        // Determine if the environment is local or remote and use appropriate path
//        String filePath = getReportPath() + fileName + ".png";
//        
//        try {
//            // Save the screenshot locally at the specified file path
//            FileUtils.copyFile(srcFile, new File(filePath));
//            System.out.println("Screenshot captured and saved at: " + filePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("Failed to capture screenshot: " + e.getMessage());
//        }
//
//        // If remote, return the Jenkins-accessible URL
//        if (prop.getProperty("env").equalsIgnoreCase("remote")) {
//            return prop.getProperty("remoteReportPath") + fileName + ".png";  // URL for Jenkins
//        }
//        
//        // If local, return the local file path
//        return filePath;
//    }
//
//    // Method to get the report path based on the environment
//    public static String getReportPath() {
//        String env = prop.getProperty("env");  // Get the 'env' property from the config file
//        
//        // If running locally, save screenshots in the local system
//        if (env.equalsIgnoreCase("local")) {
//            return System.getProperty("user.dir") + prop.getProperty("localReportPath");  // Get local path
//        } 
//        // If running on Jenkins or remote, save screenshots in the Jenkins workspace
//        else {
//        	return prop.getProperty("remoteReportPath");  // URL for Jenkins
//        }
//    }
    
    
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
