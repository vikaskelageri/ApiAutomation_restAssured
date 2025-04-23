package base;

import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ExtentReportManager;

public class BaseTest {

    @BeforeSuite
    public void setup() {
        // Set Base URI for all API calls
        RestAssured.baseURI = "https://cpiai-dev.azurewebsites.net";

        // Initialize Extent Report
        ExtentReportManager.initReport();
    }

    @AfterSuite
    public void tearDown() {
        // Flush the report after all tests
        ExtentReportManager.flushReport();
    }
}
