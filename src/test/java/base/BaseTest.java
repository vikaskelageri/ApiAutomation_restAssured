package base;

import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ConfigManager;
import utils.ExtentReportManager;
import utils.TokenManagement; // Import TokenManagement

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class BaseTest {
    // No longer need static accessToken here, TokenManagement will hold it
    public static Properties config;

    @BeforeSuite(alwaysRun = true) // Ensure it runs even if groups are specified in suite XML
    public void setupSuite() { // Renamed for clarity
        System.out.println("Running BeforeSuite setupSuite");
        try {
            config = ConfigManager.getConfig();
            if (config != null) {
                RestAssured.baseURI = config.getProperty("baseURI");
                ExtentReportManager.initReport();

                // *** Trigger authentication ONCE here ***
                System.out.println("Attempting to initialize token...");
                TokenManagement.initializeToken();
                System.out.println("Token initialization triggered.");
                // Verify token was obtained (optional but good practice)
                if (TokenManagement.getAccessToken() == null || TokenManagement.getAccessToken().isEmpty()) {
                    throw new RuntimeException("Authentication failed in @BeforeSuite. Token is null or empty.");
                }
                System.out.println("Token successfully initialized in @BeforeSuite.");

            } else {
                System.err.println("Config is NULL! Cannot proceed.");
                throw new RuntimeException("Configuration could not be loaded.");
            }
        } catch (Exception e) {
            System.err.println("Error during @BeforeSuite setup: " + e.getMessage());
            e.printStackTrace();
            // Re-throw to stop the suite if setup fails critically
            throw new RuntimeException("Failed to setup test suite environment.", e);
        }
    }

    // getPayloadFromConfig is not used in this flow anymore,
    // but keep it if other tests need it.
    // protected String getPayloadFromConfig(String configKey) throws IOException { ... }
    protected String getPayloadFromConfig(String configKey) throws IOException {

        String path = config.getProperty(configKey);

        if (path == null || path.isEmpty()) {

            throw new IOException("Payload path not found for key: " + configKey);

        }

        return new String(Files.readAllBytes(Paths.get(path)));

    }

    @AfterSuite(alwaysRun = true) // Ensure it runs even if setup fails or tests are skipped
    public void tearDownSuite() { // Renamed for clarity
        System.out.println("Running AfterSuite tearDownSuite");
        // Flush the report after all tests
        ExtentReportManager.flushReport();
    }
}