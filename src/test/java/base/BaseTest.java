package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.APIEndpoints;
import utils.ConfigManager;
import utils.ExtentReportManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class BaseTest {
    protected Properties config;
    protected static String accessToken;

    @BeforeSuite
    public void setup() {
        config=ConfigManager.getConfig();
        if(config!=null) {
            RestAssured.baseURI = config.getProperty("baseURI");
            ExtentReportManager.initReport();
            try {
                authenticate(); // Call access token method
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Authentication failed. Cannot proceed with tests.");
            }
        }
        else{
            System.out.println("config is NULL! ");
        }

    }

    @AfterSuite
    public void tearDown() {
        // Flush the report after all tests
        ExtentReportManager.flushReport();
    }

    private void authenticate() throws Exception {
        String username = config.getProperty("username");
        String password = config.getProperty("password");
        String payload = getPayloadFromConfig("loginJson");

        Response rs = given()
                .auth().preemptive().basic(username, password)
                .body(payload)
                .contentType("application/json")
                .post(APIEndpoints.login())
                .then()
                .extract().response();

        System.out.println("Auth Response: " + rs.asString());

        // Assuming token is in JSON field "access_token"
        accessToken = rs.jsonPath().getString("detail.access");
        System.out.println("detail.access Auth Response: " + accessToken);
    }
    protected String getPayloadFromConfig(String configKey) throws IOException {
        String path = config.getProperty(configKey);
        if (path == null || path.isEmpty()) {
            throw new IOException("Payload path not found for key: " + configKey);
        }
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
