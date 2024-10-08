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

    // Method to capture a screenshot and return the file path
    public static String captureScreenshot(String fileName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filePath = System.getProperty("user.dir") + "/screenshots/" + fileName + ".png";
        try {
            FileUtils.copyFile(srcFile, new File(filePath));
            System.out.println("Screenshot captured and saved at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
        return filePath;  // Return the path of the saved screenshot
    }
}
