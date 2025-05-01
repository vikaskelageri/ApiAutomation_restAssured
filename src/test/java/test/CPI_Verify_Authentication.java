package test;



import listeners.ExtentTestListener;
import org.testng.Reporter;
import org.testng.annotations.*;
import io.restassured.response.Response;
import base.BaseTest;
import utils.APIEndpoints;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

@Listeners(ExtentTestListener.class)
public class CPI_Verify_Authentication extends BaseTest {



    @Test(groups = {"Verify Authentication token"}) @BeforeTest
    public void verifyAuth() throws Exception{

        String username=config.getProperty("username");
        String password=config.getProperty("password");

        String payload = getPayloadFromConfig("loginJson");

        Response rs=given()
                .auth()
                .preemptive()
                .basic(username, password)
                .body(payload)
                .log().body()
                .contentType("application/json")
                .post(APIEndpoints.login())
                .then()
                .extract().response();
        System.out.println(rs.asString());

    }

}
