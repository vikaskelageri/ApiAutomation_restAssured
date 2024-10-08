package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import util.Utilities;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
    
    public TestBase() {
    	prop = new Properties();
        try {
            String configPath = System.getProperty("config.path", System.getProperty("user.dir") + "/src/main/java/config/config.properties");
            FileInputStream ip = new FileInputStream(configPath);
            prop.load(ip);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Configuration file not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file", e);
        }
    }

    public static void initialization() {
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//          options.addArguments("--headless");
//			options.addArguments("--window-size=1920,1080");
//			options.addArguments("--disable-gpu"); 
//			options.addArguments("--no-sandbox");
//			options.addArguments("--disable-dev-shm-usage");


            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIMEOUT));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT));
            driver.get(prop.getProperty("url"));
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
